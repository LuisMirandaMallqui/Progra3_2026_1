// =====================================================================
// AlumnoImpl.cs — DAO de Alumno
// =====================================================================
// Este archivo muestra TODOS los patrones básicos de acceso a datos:
// 1. INSERTAR con parámetro OUT (recuperar ID auto_increment)
// 2. MODIFICAR y ELIMINAR (operaciones simples)
// 3. BUSCAR POR ID con DataReader (EjecutarProcedimientoLectura)
// 4. LISTAR TODOS con DataReader
// 5. SELECT INTO — buscar sin DataReader, usando parámetro OUT
// =====================================================================

using GestionAlumnosDBManager;
using GestionAlumnosModel.Alumno;
using GestionAlumnosPersistance.Alumnos.DAO;
using System.Data;
using System.Data.Common;

namespace GestionAlumnosPersistance.Alumnos.Impl
{
    public class AlumnoImpl : AlumnoDAO
    {
        public int Insertar(Alumno alumno)
        {
            // Crear array de parámetros (3 IN + 1 OUT = 4 total)
            DbParameter[] parametros = new DbParameter[4];

            // CreateParam(nombre, tipo, valor, dirección)
            // Los nombres DEBEN coincidir con los del SP en MySQL
            parametros[0] = DBManager.Instance.CreateParam("p_codigo", DbType.String, alumno.Codigo, ParameterDirection.Input);
            parametros[1] = DBManager.Instance.CreateParam("p_nombre", DbType.String, alumno.Nombre, ParameterDirection.Input);
            parametros[2] = DBManager.Instance.CreateParam("p_correo", DbType.String, alumno.Correo, ParameterDirection.Input);

            // Parámetro OUTPUT: valor = null, dirección = Output
            // Después de ejecutar el SP, este parámetro contendrá el ID generado
            parametros[3] = DBManager.Instance.CreateParam("p_id", DbType.Int32, null, ParameterDirection.Output);

            // EjecutarProcedimiento → para INSERT/UPDATE/DELETE (no retorna DataReader)
            DBManager.Instance.EjecutarProcedimiento("insertar_alumno", parametros);

            // Leer el valor OUT: el ID que la BD generó con auto_increment
            alumno.Id = Convert.ToInt32(parametros[3].Value);
            return alumno.Id;
        }

        public int Modificar(Alumno alumno)
        {
            DbParameter[] parametros = new DbParameter[4];
            parametros[0] = DBManager.Instance.CreateParam("p_id", DbType.Int32, alumno.Id, ParameterDirection.Input);
            parametros[1] = DBManager.Instance.CreateParam("p_codigo", DbType.String, alumno.Codigo, ParameterDirection.Input);
            parametros[2] = DBManager.Instance.CreateParam("p_nombre", DbType.String, alumno.Nombre, ParameterDirection.Input);
            parametros[3] = DBManager.Instance.CreateParam("p_correo", DbType.String, alumno.Correo, ParameterDirection.Input);

            return DBManager.Instance.EjecutarProcedimiento("modificar_alumno", parametros);
        }

        // =============================================================
        // ELIMINAR — Solo necesita el ID
        // =============================================================
        // SP: eliminar_alumno(IN p_id)
        // =============================================================
        public int Eliminar(int id)
        {
            DbParameter[] parametros = new DbParameter[1];
            parametros[0] = DBManager.Instance.CreateParam("p_id", DbType.Int32, id, ParameterDirection.Input);
            return DBManager.Instance.EjecutarProcedimiento("eliminar_alumno", parametros);
        }


        public Alumno BuscarPorId(int id)
        {
            Alumno? alumno = null; // '?' = nullable, puede ser null si no se encuentra

            DbParameter[] parametros = new DbParameter[1];
            parametros[0] = DBManager.Instance.CreateParam("p_id", DbType.Int32, id, ParameterDirection.Input);

            // 'using' cierra el DataReader al salir del bloque
            using DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("buscar_alumno_por_id", parametros);

            // lector.Read() → avanza a la siguiente fila. Retorna false si no hay más.
            // Para BuscarPorId, esperamos 0 o 1 fila, por eso usamos 'if' en vez de 'while'.
            if (lector.Read())
            {
                alumno = new Alumno();
                alumno.Id = lector.GetInt32(lector.GetOrdinal("id"));
                alumno.Codigo = lector.GetString(lector.GetOrdinal("codigo"));
                alumno.Nombre = lector.GetString(lector.GetOrdinal("nombre"));
                alumno.Correo = lector.GetString(lector.GetOrdinal("correo"));
            }
            return alumno;
        }

        public List<Alumno> ListarTodos()
        {
            List<Alumno> lista = new List<Alumno>();

            // null como parámetros → el SP no recibe parámetros
            using DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("listar_alumnos", null);

            // while → recorre TODAS las filas
            while (lector.Read())
            {
                Alumno a = new Alumno();
                a.Id = lector.GetInt32(lector.GetOrdinal("id"));
                a.Codigo = lector.GetString(lector.GetOrdinal("codigo"));
                a.Nombre = lector.GetString(lector.GetOrdinal("nombre"));
                a.Correo = lector.GetString(lector.GetOrdinal("correo"));
                lista.Add(a);
            }
            return lista;
        }


        public int BuscarPorCodigo(string codigo)
        {
            DbParameter[] parametros = new DbParameter[2];

            // Parámetro 1: IN — el código a buscar
            parametros[0] = DBManager.Instance.CreateParam("p_codigo", DbType.String, codigo, ParameterDirection.Input);

            // Parámetro 2: OUT — aquí el SP devolverá el ID encontrado
            parametros[1] = DBManager.Instance.CreateParam("p_id", DbType.Int32, null, ParameterDirection.Output);

            // EjecutarProcedimiento (SIN Lectura) — porque SELECT INTO no retorna filas
            DBManager.Instance.EjecutarProcedimiento("buscar_alumno_por_codigo", parametros);

            // Verificar si encontró algo:
            // - DBNull.Value → el SELECT INTO no encontró ninguna fila (el SET p_id = NULL se mantiene)
            // - null → el parámetro no fue asignado
            if (parametros[1].Value == null || parametros[1].Value == DBNull.Value)
                return 0; // No encontrado

            return Convert.ToInt32(parametros[1].Value);
        }

        public Alumno BuscarPorNombre(String nombre)
        {

        }

        public Alumno BuscarPorApellido(String apellido)
        {

        }
    }
}
