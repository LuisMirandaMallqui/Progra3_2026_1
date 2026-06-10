using Microsoft.Data.SqlClient;
using MySql.Data.MySqlClient;
using System.Data;
using System.Data.Common;

namespace GestionAlumnosDBManager
{
    public class DBManager
    {
        private readonly String _connectionString;
        private static DBManager? _instance;
		
        private static readonly string libreriaMySQL = "MySql.Data.MySqlClient";
        private static readonly string libreriaMSSQL = "Microsoft.Data.SqlClient";

        private DbProviderFactory Factory => DbProviderFactories.GetFactory(_tipoMotorBD == "mysql" ? libreriaMySQL : libreriaMSSQL);

        private static String? _tipoMotorBD;

        private DBManager(String connectionString)
        {
            _connectionString = connectionString;
        }

        public static void Initialize(String connectionString, String tipoMotorBD)
        {
            if (_instance == null)
            {
                _tipoMotorBD = tipoMotorBD;
                if (tipoMotorBD.Equals("mysql"))
                    DbProviderFactories.RegisterFactory(libreriaMySQL, MySqlClientFactory.Instance);
                else if (tipoMotorBD.Equals("mssql"))
                    DbProviderFactories.RegisterFactory(libreriaMSSQL, SqlClientFactory.Instance);
                _instance = new DBManager(connectionString);
            }
        }

        public static DBManager Instance => _instance ?? throw new Exception("DBManager no ha sido inicializado.");

        public DbConnection AbrirConexion()
        {
            DbConnection? con = Factory.CreateConnection();

            if (con == null)
                throw new Exception("No se pudo crear la conexión.");

            con.ConnectionString = _connectionString;
            if (con.State != ConnectionState.Open)
                con.Open();

            return con;
        }

        //Métodos para llamadas a Procedimientos Almacenados
        public DbParameter CreateParam(string nombreLogico, DbType tipo, object? valor, ParameterDirection direccion)
        {
            DbParameter? p = Factory.CreateParameter();
            if (p == null)
                throw new Exception("No se pudo crear el parámetro.");
            p.ParameterName = nombreLogico;
            p.DbType = tipo;
            p.Direction = direccion;
            p.Value = valor ?? DBNull.Value;
            return p;
        }

        public string P(string nombreLogico)
        {
            return _tipoMotorBD == "mssql" ? "@" + nombreLogico : nombreLogico;
        }

        public int EjecutarProcedimiento(string nombreSP, IList<DbParameter> parametros)
        {
            using DbConnection con = AbrirConexion();
            using DbCommand cmd = con.CreateCommand();

            cmd.CommandType = CommandType.StoredProcedure;
            cmd.CommandText = nombreSP;

            if (parametros != null && parametros.Count > 0)
            {
                foreach (DbParameter p in parametros)
                {
                    p.ParameterName = P(p.ParameterName);
                    cmd.Parameters.Add(p);
                }
            }

            return cmd.ExecuteNonQuery();
        }

        public DbDataReader EjecutarProcedimientoLectura(string nombreSP, IList<DbParameter> parametros)
        {
            DbConnection con = AbrirConexion();
            DbCommand cmd = con.CreateCommand();

            cmd.CommandType = CommandType.StoredProcedure;
            cmd.CommandText = nombreSP;

            if (parametros != null && parametros.Count > 0)
            {
                foreach (DbParameter p in parametros)
                {
                    p.ParameterName = P(p.ParameterName);
                    cmd.Parameters.Add(p);
                }
            }

            return cmd.ExecuteReader(CommandBehavior.CloseConnection);
        }

        public int EjecutarProcedimientoTransaccion(string nombreSP, IList<DbParameter> parametros, DbTransaction transaccion)
        {
            using DbCommand cmd = transaccion.Connection!.CreateCommand();

            cmd.CommandType = CommandType.StoredProcedure;
            cmd.CommandText = nombreSP;
            cmd.Transaction = transaccion;

            if (parametros != null && parametros.Count > 0)
            {
                foreach (DbParameter p in parametros)
                {
                    p.ParameterName = P(p.ParameterName);
                    cmd.Parameters.Add(p);
                }
            }

            return cmd.ExecuteNonQuery();
        }
    }
}
