using CinestarDBManager;
using CinestarModel.Cliente;
using CinestarPersistance.Clientes.DAO;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;

namespace CinestarPersistance.Clientes.Impl
{
    public class ClienteImpl : ClienteDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader lector;

        public Cliente BuscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int Eliminar(int id)
        {
            throw new NotImplementedException();
        }

        //  CREATE PROCEDURE INSERTAR_CLIENTE(
        //    OUT _id_cliente INT,
        //    IN _nombre_cliente VARCHAR(50),
        //    IN _apellido_cliente VARCHAR(50),
        //    IN _email_cliente VARCHAR(75)
        //  )
        public int Insertar(Cliente cliente) // PROCEDURE
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "INSERTAR_CLIENTE";
                cmd.Parameters.Add("_id_cliente", MySqlDbType.Int32).Direction = ParameterDirection.Output;
                cmd.Parameters.AddWithValue("_email_cliente", cliente.EmailCliente);
                cmd.Parameters.AddWithValue("_nombre_cliente", cliente.NombreCliente); //
                cmd.Parameters.AddWithValue("_apellido_cliente", cliente.ApellidoCliente); //
                cmd.ExecuteNonQuery();
                cliente.IdCliente= Int32.Parse(cmd.Parameters["_id_cliente"].Value.ToString());
                resultado = cliente.IdCliente;
                con.Close();
            }
            catch (Exception ex)
            { System.Console.WriteLine(ex.Message); }
            return resultado;
        }

        public List<Cliente> ListarTodos()
        {
            throw new NotImplementedException();
        }

        public int Modificar(Cliente objeto)
        {
            throw new NotImplementedException();
        }
    }
}
