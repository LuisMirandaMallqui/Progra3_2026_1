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

using SoftProgDBManager;
using TestSoftModel.Alumno;
using TestSoftPersistance.Alumnos.DAO;
using System.Data;
using System.Data.Common;

namespace TestSoftPersistance.Alumnos.Impl
{
    public class AlumnoImpl : AlumnoDAO
    {
        // =============================================================
        // INSERTAR CON PARAMETRO OUTPUT
        // =============================================================
        // ¿COMO FUNCIONA?
        //   El SP inserta el registro y la BD genera un ID automático
        //   (auto_increment). Necesitamos recuperar ese ID.
        //
        // SOLUCION:
        //   El SP tiene un parámetro OUT (p_id) que recibe el
        //   LAST_INSERT_ID() después del INSERT.
        //
        // EN CODIGO:
        //   1. Crear DbParameter con ParameterDirection.Output
        //   2. Llamar EjecutarProcedimiento (NO EjecutarProcedimientoLectura)
        //   3. Después de ejecutar, leer parametros[3].Value → el ID generado
        //
        // DIFERENCIA CON JAVA:
        //   Java: Map<Integer,Object> salida con clave = posición
        //   C#:   DbParameter[] con ParameterDirection.Output
        //
        // SP: insertar_alumno(IN p_codigo, IN p_nombre, IN p_correo, OUT p_id)
        // =============================================================
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

        // =============================================================
        // MODIFICAR — Operación simple, solo parámetros IN
        // =============================================================
        // No necesita OUT porque no genera ningún ID nuevo.
        // Retorna el número de filas afectadas (0 si no encontró el registro).
        //
        // SP: modificar_alumno(IN p_id, IN p_codigo, IN p_nombre, IN p_correo)
        // =============================================================
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

        // =============================================================
        // BUSCAR POR ID — Usando DataReader
        // =============================================================
        // ¿QUE ES EjecutarProcedimientoLectura?
        //   Ejecuta un SP que retorna filas (SELECT) y devuelve un
        //   DbDataReader para leerlas fila por fila.
        //
        // ¿QUE ES GetOrdinal?
        //   lector.GetOrdinal("nombre") → devuelve el índice (número)
        //   de la columna "nombre" en el resultado.
        //   Luego GetString(indice) lee el valor como string.
        //   Es más seguro que usar índices numéricos directos.
        //
        // IMPORTANTE — 'using':
        //   'using DbDataReader lector' cierra automáticamente el
        //   DataReader (y la conexión) cuando termina el bloque.
        //   Si no usas 'using', la conexión queda abierta → leak.
        //
        // NOTA: Alumno no tiene relaciones, así que NO necesita
        //   patrón 2 fases. Si tuviera (ej: Alumno tiene una Carrera),
        //   habría que leer los datos planos, cerrar el DataReader,
        //   y LUEGO cargar la Carrera con otra conexión.
        //   Ver ExamenImpl.cs para ejemplo de 2 fases.
        //
        // SP: buscar_alumno_por_id(IN p_id) → retorna fila con id, codigo, nombre, correo
        // =============================================================
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

        // =============================================================
        // LISTAR TODOS — DataReader con múltiples filas
        // =============================================================
        // Igual que BuscarPorId pero usa 'while' para recorrer TODAS
        // las filas retornadas por el SP.
        //
        // SP: listar_alumnos() → retorna todas las filas de la tabla alumno
        // =============================================================
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

        // =============================================================
        // SELECT INTO — Buscar sin DataReader (PATRON IMPORTANTE)
        // =============================================================
        // ¿QUE ES SELECT INTO?
        //   Es cuando el SP NO retorna un ResultSet/DataReader.
        //   En vez de hacer: SELECT * FROM alumno WHERE codigo = p_codigo
        //   hace:            SELECT id INTO p_id FROM alumno WHERE codigo = p_codigo
        //
        //   La diferencia es que el resultado va DIRECTO a un parámetro OUT,
        //   no a un cursor/DataReader que tengas que recorrer.
        //
        // ¿CUANDO USARLO?
        //   Cuando solo necesitas UN valor (como un ID) y no toda la fila.
        //   Es más eficiente que abrir un DataReader solo para leer un int.
        //
        // ¿COMO SE LLAMA EN CODIGO?
        //   Se usa EjecutarProcedimiento (NO EjecutarProcedimientoLectura)
        //   porque NO retorna filas. El resultado viene por el parámetro OUT.
        //
        // EQUIVALENTE EN JAVA (Lab7 - buscar_tipo_pokemon_por_nombre):
        //   Map<Integer,Object> entrada = new HashMap<>();
        //   Map<Integer,Object> salida = new HashMap<>();
        //   entrada.put(1, codigo);
        //   salida.put(2, Types.INTEGER);    // posición 2 = OUT
        //   dbManager.ejecutarProcedimiento("buscar_alumno_por_codigo", entrada, salida);
        //   Object resultado = salida.get(2);
        //
        // SP SQL:
        //   CREATE PROCEDURE buscar_alumno_por_codigo(
        //       IN p_codigo VARCHAR(20),
        //       OUT p_id INT
        //   )
        //   BEGIN
        //       SET p_id = NULL;
        //       SELECT id INTO p_id FROM alumno WHERE codigo = p_codigo;
        //   END
        // =============================================================
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
    }
}
