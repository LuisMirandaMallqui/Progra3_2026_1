// =====================================================================
// ExamenImpl.cs — DAO de Examen
// =====================================================================
// Este es el archivo MAS IMPORTANTE del proyecto porque demuestra:
// 1. TRANSACCIONES: insertar cabecera + detalles (maestro-detalle)
// 2. PATRON 2 FASES: evitar conexiones anidadas en lecturas
// 3. DbParameter con ParameterDirection.Output para obtener IDs
// =====================================================================

using SoftProgDBManager;
using TestSoftModel.Alumno;
using TestSoftModel.Examen;
using TestSoftModel.Pregunta;
using TestSoftPersistance.Alumnos.DAO;
using TestSoftPersistance.Alumnos.Impl;
using TestSoftPersistance.Examenes.DAO;
using System.Data;
using System.Data.Common;

namespace TestSoftPersistance.Examenes.Impl
{
    public class ExamenImpl : ExamenDAO
    {
        // =============================================================
        // INSERTAR CON TRANSACCION (Maestro-Detalle)
        // =============================================================
        // ¿QUE ES UNA TRANSACCION?
        //   Es un bloque de operaciones que se ejecutan como UNA SOLA:
        //   - Si TODAS funcionan → Commit (se guardan)
        //   - Si UNA falla → Rollback (se deshacen TODAS)
        //
        // ¿POR QUE SE USA AQUI?
        //   Porque insertar un examen requiere 2 pasos:
        //   1. INSERT en tabla 'examen' (cabecera)
        //   2. N INSERTs en tabla 'examen_pregunta' (detalles)
        //   Si el paso 2 falla, queremos que el paso 1 también se deshaga.
        //
        // FLUJO:
        //   AbrirConexion → BeginTransaction → ejecutar SP cabecera
        //   → loop ejecutar SP detalle → Commit
        //   Si algo falla → catch → Rollback
        //
        // SP cabecera: insertar_examen(IN p_id_alumno, IN p_titulo, OUT p_id)
        // SP detalle:  insertar_examen_pregunta(IN p_id_examen, IN p_id_pregunta, IN p_orden, OUT p_id)
        // =============================================================
        public int Insertar(Examen examen)
        {
            int idExamen = 0;

            // Abrir conexion y comenzar transaccion
            // IMPORTANTE: usamos 'using' para que se cierre automáticamente al salir
            using DbConnection con = DBManager.Instance.AbrirConexion();
            DbTransaction transaccion = con.BeginTransaction();

            try
            {
                // --- PASO 1: Insertar cabecera del examen ---
                // CreateParam(nombre, tipo, valor, dirección)
                // ParameterDirection.Output → el SP devuelve el valor via este parámetro
                DbParameter[] paramsCab = new DbParameter[3];
                paramsCab[0] = DBManager.Instance.CreateParam("p_id_alumno", DbType.Int32, examen.IdAlumno, ParameterDirection.Input);
                paramsCab[1] = DBManager.Instance.CreateParam("p_titulo", DbType.String, examen.Titulo, ParameterDirection.Input);
                paramsCab[2] = DBManager.Instance.CreateParam("p_id", DbType.Int32, null, ParameterDirection.Output);

                // EjecutarProcedimientoTransaccion: usa la MISMA conexión y transacción
                // (a diferencia de EjecutarProcedimiento que abre y cierra su propia conexión)
                DBManager.Instance.EjecutarProcedimientoTransaccion("insertar_examen", paramsCab, transaccion);

                // Recuperar el ID generado por la BD (auto_increment)
                idExamen = Convert.ToInt32(paramsCab[2].Value);
                examen.Id = idExamen;

                // --- PASO 2: Insertar cada pregunta (detalles) ---
                // Se itera la lista de preguntas y se inserta una fila en
                // la tabla puente examen_pregunta por cada una
                int orden = 1;
                foreach (Pregunta pregunta in examen.Preguntas)
                {
                    DbParameter[] paramsDet = new DbParameter[4];
                    paramsDet[0] = DBManager.Instance.CreateParam("p_id_examen", DbType.Int32, idExamen, ParameterDirection.Input);
                    paramsDet[1] = DBManager.Instance.CreateParam("p_id_pregunta", DbType.Int32, pregunta.Id, ParameterDirection.Input);
                    paramsDet[2] = DBManager.Instance.CreateParam("p_orden", DbType.Int32, orden, ParameterDirection.Input);
                    paramsDet[3] = DBManager.Instance.CreateParam("p_id", DbType.Int32, null, ParameterDirection.Output);

                    // Usa la MISMA transacción que la cabecera
                    DBManager.Instance.EjecutarProcedimientoTransaccion("insertar_examen_pregunta", paramsDet, transaccion);
                    orden++;
                }

                // --- PASO 3: Confirmar ---
                // Si llegamos aquí sin excepciones, TODO se guarda en la BD
                transaccion.Commit();
            }
            catch (Exception ex)
            {
                // --- PASO 4: Deshacer si algo falló ---
                // Rollback deshace la cabecera Y todos los detalles insertados
                transaccion.Rollback();
                Console.WriteLine("Error al insertar examen con transaccion: " + ex.Message);
                idExamen = 0;
            }
            return idExamen;
        }

        // SP: modificar_examen(IN p_id, IN p_titulo)
        public int Modificar(Examen examen)
        {
            DbParameter[] parametros = new DbParameter[2];
            parametros[0] = DBManager.Instance.CreateParam("p_id", DbType.Int32, examen.Id, ParameterDirection.Input);
            parametros[1] = DBManager.Instance.CreateParam("p_titulo", DbType.String, examen.Titulo, ParameterDirection.Input);
            return DBManager.Instance.EjecutarProcedimiento("modificar_examen", parametros);
        }

        // SP: eliminar_examen(IN p_id)
        // El SP internamente borra primero examen_pregunta y luego examen (por las FK)
        public int Eliminar(int id)
        {
            DbParameter[] parametros = new DbParameter[1];
            parametros[0] = DBManager.Instance.CreateParam("p_id", DbType.Int32, id, ParameterDirection.Input);
            return DBManager.Instance.EjecutarProcedimiento("eliminar_examen", parametros);
        }

        // =============================================================
        // BUSCAR POR ID — PATRON 2 FASES
        // =============================================================
        // ¿QUE ES EL PATRON 2 FASES?
        //   Cuando un examen tiene relaciones (Alumno, Preguntas), NO puedes
        //   abrir una nueva conexión/DataReader mientras otra está abierta.
        //
        //   FASE 1: lees TODOS los datos planos del DataReader (id, titulo,
        //           fecha, id_alumno) y CIERRAS el DataReader (using lo hace)
        //   FASE 2: con el DataReader ya cerrado, abres nuevas conexiones
        //           para cargar Alumno y Preguntas
        //
        // SI NO HACES 2 FASES:
        //   Intentar AlumnoImpl.BuscarPorId() mientras el DataReader del
        //   examen sigue abierto → error "There is already an open DataReader"
        // =============================================================
        public Examen BuscarPorId(int id)
        {
            Examen? examen = null;
            int idAlumno = 0;

            // --- FASE 1: leer datos planos ---
            DbParameter[] parametros = new DbParameter[1];
            parametros[0] = DBManager.Instance.CreateParam("p_id", DbType.Int32, id, ParameterDirection.Input);

            // 'using' cierra el DataReader (y la conexión) al salir del bloque
            using (DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("buscar_examen_por_id", parametros))
            {
                if (lector.Read())
                {
                    examen = new Examen();
                    examen.Id = lector.GetInt32(lector.GetOrdinal("id"));
                    examen.Titulo = lector.GetString(lector.GetOrdinal("titulo"));
                    examen.FechaCreacion = lector.GetDateTime(lector.GetOrdinal("fecha_creacion"));
                    idAlumno = lector.GetInt32(lector.GetOrdinal("id_alumno"));
                    // NO cargar Alumno aquí — el DataReader sigue abierto
                }
            }
            // Aquí el DataReader YA está cerrado (el using lo cerró)

            // --- FASE 2: cargar relaciones (nuevas conexiones) ---
            if (examen != null)
            {
                // Cada llamada abre y cierra su propia conexión — sin conflictos
                AlumnoDAO alumnoDAO = new AlumnoImpl();
                examen.Alumno = alumnoDAO.BuscarPorId(idAlumno);
                examen.IdAlumno = idAlumno;
                examen.Preguntas = ListarPreguntasPorExamen(examen.Id);
            }

            return examen;
        }

        // =============================================================
        // LISTAR TODOS — También usa PATRON 2 FASES
        // =============================================================
        public List<Examen> ListarTodos()
        {
            List<Examen> lista = new List<Examen>();
            List<int> idsAlumno = new List<int>();

            // FASE 1: leer solo datos planos del DataReader
            using (DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("listar_examenes", null))
            {
                while (lector.Read())
                {
                    Examen e = new Examen();
                    e.Id = lector.GetInt32(lector.GetOrdinal("id"));
                    e.Titulo = lector.GetString(lector.GetOrdinal("titulo"));
                    e.FechaCreacion = lector.GetDateTime(lector.GetOrdinal("fecha_creacion"));
                    idsAlumno.Add(lector.GetInt32(lector.GetOrdinal("id_alumno")));
                    lista.Add(e);
                }
            }
            // DataReader cerrado

            // FASE 2: cargar Alumno + Preguntas de cada examen
            AlumnoDAO alumnoDAO = new AlumnoImpl();
            for (int i = 0; i < lista.Count; i++)
            {
                Examen e = lista[i];
                e.IdAlumno = idsAlumno[i];
                e.Alumno = alumnoDAO.BuscarPorId(idsAlumno[i]);
                e.Preguntas = ListarPreguntasPorExamen(e.Id);
            }

            return lista;
        }

        // SP: listar_preguntas_por_examen(IN p_id_examen) → JOIN pregunta
        public List<Pregunta> ListarPreguntasPorExamen(int idExamen)
        {
            List<Pregunta> lista = new List<Pregunta>();
            DbParameter[] parametros = new DbParameter[1];
            parametros[0] = DBManager.Instance.CreateParam("p_id_examen", DbType.Int32, idExamen, ParameterDirection.Input);

            using DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("listar_preguntas_por_examen", parametros);
            while (lector.Read())
            {
                Pregunta p = new Pregunta();
                p.Id = lector.GetInt32(lector.GetOrdinal("id"));
                p.Enunciado = lector.GetString(lector.GetOrdinal("enunciado"));
                lista.Add(p);
            }
            return lista;
        }
    }
}
