using MySql.Data.MySqlClient;
using SoftProgDBManager;
using SoftProgModel.Almacen;
using SoftProgModel.GestClientes;
using SoftProgPersistencia.GestClientes.DAO;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;

namespace SoftProgPersistencia.GestClientes.Impl
{
    public class ClienteImpl : ClienteDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader lector;
        public Cliente buscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int eliminar(int id)
        {
            throw new NotImplementedException();
        }

        public int insertar(Cliente objeto)
        {
            throw new NotImplementedException();
        }

        public List<Cliente> listarTodos()
        {
            List<Cliente> clientes = null;
            con = DBManager.Instance.GetConnection();
            con.Open();
            cmd = new MySqlCommand();
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Connection = con;
            cmd.CommandText = "LISTAR_CLIENTES_TODOS";
            lector = cmd.ExecuteReader();
            while (lector.Read())
            {
                if (clientes == null) clientes = new List<Cliente>();
                Cliente cliente = new Cliente();
                if (!lector.IsDBNull("id_persona")) cliente.IdPersona = lector.GetInt32("id_persona");
                if (!lector.IsDBNull("DNI")) cliente.DNI = lector.GetString("DNI");
                if (!lector.IsDBNull("nombre")) cliente.Nombre = lector.GetString("nombre");
                if (!lector.IsDBNull("apellido_paterno")) cliente.ApellidoPaterno = lector.GetString("apellido_paterno");
                if (!lector.IsDBNull("genero")) cliente.Genero = lector.GetChar("genero");
                if (!lector.IsDBNull("fecha_nacimiento")) cliente.FechaNacimiento = lector.GetDateTime("fecha_nacimiento");
                if (!lector.IsDBNull("linea_credito")) cliente.LineaCredito = lector.GetDouble("linea_credito");
                if (!lector.IsDBNull("categoria")) cliente.Categoria = (Categoria) Enum.Parse(typeof(Categoria), lector.GetString("categoria"));
                clientes.Add(cliente);
            }
            con.Close();
            return clientes;
        }

        public int modificar(Cliente objeto)
        {
            throw new NotImplementedException();
        }
    }
}
