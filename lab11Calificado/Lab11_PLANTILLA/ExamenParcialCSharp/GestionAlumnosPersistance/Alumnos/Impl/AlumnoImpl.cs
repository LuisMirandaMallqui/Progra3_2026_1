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
            DbParameter[] parametros = new DbParameter[6];

            // Los nombres DEBEN coincidir con los del SP en MySQL
            parametros[1] = DBManager.Instance.CreateParam("p_codigo", DbType.String, alumno.Codigo, ParameterDirection.Input);
            parametros[2] = DBManager.Instance.CreateParam("p_nombre", DbType.String, alumno.Nombre, ParameterDirection.Input);
            parametros[3] = DBManager.Instance.CreateParam("p_apellidos", DbType.String, alumno.Apellidos, ParameterDirection.Input);
            parametros[4] = DBManager.Instance.CreateParam("p_correo", DbType.String, alumno.Correo, ParameterDirection.Input);
            parametros[5] = DBManager.Instance.CreateParam("p_estado", DbType.String, alumno.Estado, ParameterDirection.Input);

            // Parámetro OUTPUT: valor = null, dirección = Output
            // Después de ejecutar el SP, este parámetro contendrá el ID generado
            parametros[0] = DBManager.Instance.CreateParam("p_id", DbType.Int32, null, ParameterDirection.Output);

            // EjecutarProcedimiento → para INSERT/UPDATE/DELETE (no retorna DataReader)
            DBManager.Instance.EjecutarProcedimiento("SP_INSERTAR_ALUMNO", parametros);

            // Leer el valor OUT: el ID que la BD generó con auto_increment
            alumno.Id = Convert.ToInt32(parametros[0].Value);
            return alumno.Id;
        }

        public int Modificar(Alumno alumno)
        {
            DbParameter[] parametros = new DbParameter[6];
            parametros[0] = DBManager.Instance.CreateParam("p_id", DbType.Int32, alumno.Id, ParameterDirection.Input);
            parametros[1] = DBManager.Instance.CreateParam("p_codigo", DbType.String, alumno.Codigo, ParameterDirection.Input);
            parametros[2] = DBManager.Instance.CreateParam("p_nombre", DbType.String, alumno.Nombre, ParameterDirection.Input);
            parametros[3] = DBManager.Instance.CreateParam("p_apellidos", DbType.String, alumno.Apellidos, ParameterDirection.Input);
            parametros[4] = DBManager.Instance.CreateParam("p_correo", DbType.String, alumno.Correo, ParameterDirection.Input);
            parametros[5] = DBManager.Instance.CreateParam("p_estado", DbType.String, alumno.Estado, ParameterDirection.Input);

            return DBManager.Instance.EjecutarProcedimiento("SP_MODIFICAR_ALUMNO", parametros);
        }

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
            using DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("SP_OBTENER_ALUMNO_POR_ID", parametros);

            // Para BuscarPorId, esperamos 0 o 1 fila, por eso usamos 'if' en vez de 'while'.
            if (lector.Read())
            {
                alumno = new Alumno();
                alumno.Id = lector.GetInt32(lector.GetOrdinal("id"));
                alumno.Codigo = lector.GetString(lector.GetOrdinal("codigo"));
                alumno.Nombre = lector.GetString(lector.GetOrdinal("nombre"));
                alumno.Apellidos = lector.GetString(lector.GetOrdinal("apellidos"));
                alumno.Correo = lector.GetString(lector.GetOrdinal("correo"));
                alumno.Estado = lector.GetChar(lector.GetOrdinal("estado"));
                return alumno;
            }
            return null;
        }

        public List<Alumno> ListarTodos()
        {
            List<Alumno> lista = new List<Alumno>();
            
            DbParameter[] parametros = new DbParameter[1];
            // Parámetro 1: IN — el nombre a buscar, en este caso vacio para listar todos
            String artificio = ""; 
            parametros[0] = DBManager.Instance.CreateParam("p_texto", DbType.String, artificio, ParameterDirection.Input);
            // null como parámetros → el SP no recibe parámetros
            using DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("SP_LISTAR_ALUMNOS_X_NOMBRE_APELLIDO", parametros);

            // while → recorre TODAS las filas
            while (lector.Read())
            {
                Alumno alumno = new Alumno();
                alumno.Id = lector.GetInt32(lector.GetOrdinal("id"));
                alumno.Codigo = lector.GetString(lector.GetOrdinal("codigo"));
                alumno.Nombre = lector.GetString(lector.GetOrdinal("nombre"));
                alumno.Apellidos = lector.GetString(lector.GetOrdinal("apellidos"));
                alumno.Correo = lector.GetString(lector.GetOrdinal("correo"));
                alumno.Estado= lector.GetChar(lector.GetOrdinal("estado"));

                lista.Add(alumno);
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

        public List<Alumno> BuscarPorNombreApellido(String texto)
        {
            List<Alumno> alumnos = new List<Alumno>();

            DbParameter[] parametros = new DbParameter[1];
            parametros[0] = DBManager.Instance.CreateParam("p_texto", DbType.String, texto, ParameterDirection.Input);

            // 'using' cierra el DataReader al salir del bloque
            using DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("SP_LISTAR_ALUMNOS_X_NOMBRE_APELLIDO", parametros);

            // lector.Read() → avanza a la siguiente fila. Retorna false si no hay más.
            while (lector.Read())
            {
                Alumno alumno = new Alumno();
                alumno = new Alumno();
                alumno.Id = lector.GetInt32(lector.GetOrdinal("id"));
                alumno.Codigo = lector.GetString(lector.GetOrdinal("codigo"));
                alumno.Nombre = lector.GetString(lector.GetOrdinal("nombre"));
                alumno.Apellidos = lector.GetString(lector.GetOrdinal("apellidos"));
                alumno.Correo = lector.GetString(lector.GetOrdinal("correo"));
                alumno.Estado = lector.GetChar(lector.GetOrdinal("estado"));
                alumnos.Add(alumno);
            }
            return alumnos;
        }
    }
}
