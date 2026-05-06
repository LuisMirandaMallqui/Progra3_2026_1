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

        // SP: INSERTAR_CLIENTE(OUT _id_cliente, IN _nombre_cliente, IN _apellido_cliente, IN _email_cliente)
        public int Insertar(Cliente cliente)
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
                cmd.Parameters.AddWithValue("_nombre_cliente", cliente.NombreCliente);
                cmd.Parameters.AddWithValue("_apellido_cliente", cliente.ApellidoCliente);
                cmd.Parameters.AddWithValue("_email_cliente", cliente.EmailCliente);
                cmd.ExecuteNonQuery();
                cliente.IdCliente = Int32.Parse(cmd.Parameters["_id_cliente"].Value.ToString());
                resultado = cliente.IdCliente;
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al insertar cliente: " + ex.Message); }
            return resultado;
        }

        // SP: MODIFICAR_CLIENTE(IN _id_cliente, IN _nombre_cliente, IN _apellido_cliente, IN _email_cliente)
        public int Modificar(Cliente cliente)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "MODIFICAR_CLIENTE";
                cmd.Parameters.AddWithValue("_id_cliente", cliente.IdCliente);
                cmd.Parameters.AddWithValue("_nombre_cliente", cliente.NombreCliente);
                cmd.Parameters.AddWithValue("_apellido_cliente", cliente.ApellidoCliente);
                cmd.Parameters.AddWithValue("_email_cliente", cliente.EmailCliente);
                resultado = cmd.ExecuteNonQuery();
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al modificar cliente: " + ex.Message); }
            return resultado;
        }

        // SP: ELIMINAR_CLIENTE(IN _id_cliente)
        // Si la tabla tuviera campo 'activo', el SP haria UPDATE SET activo = 0 (eliminacion logica)
        // En este caso como no hay campo activo, seria un DELETE fisico
        public int Eliminar(int id)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "ELIMINAR_CLIENTE";
                cmd.Parameters.AddWithValue("_id_cliente", id);
                resultado = cmd.ExecuteNonQuery();
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al eliminar cliente: " + ex.Message); }
            return resultado;
            // --- EJEMPLO: Si existiera campo 'activo' en la tabla ---
            // El SP seria: UPDATE cliente SET activo = 0 WHERE id_cliente = _id_cliente;
            // El codigo C# es identico, solo cambia el nombre del SP
        }

        // SP: BUSCAR_CLIENTE_POR_ID(IN _id_cliente)
        public Cliente BuscarPorId(int id)
        {
            Cliente cliente = null;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "BUSCAR_CLIENTE_POR_ID";
                cmd.Parameters.AddWithValue("_id_cliente", id);
                lector = cmd.ExecuteReader();
                if (lector.Read())
                {
                    cliente = new Cliente();
                    cliente.IdCliente = lector.GetInt32("id_cliente");
                    cliente.NombreCliente = lector.GetString("nombre_cliente");
                    cliente.ApellidoCliente = lector.GetString("apellido_cliente");
                    cliente.EmailCliente = lector.GetString("email_cliente");
                }
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al buscar cliente: " + ex.Message); }
            return cliente;
        }

        // SP: LISTAR_CLIENTES_TODOS()
        public List<Cliente> ListarTodos()
        {
            List<Cliente> lista = null;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "LISTAR_CLIENTES_TODOS";
                lector = cmd.ExecuteReader();
                while (lector.Read())
                {
                    if (lista == null) lista = new List<Cliente>();
                    Cliente cliente = new Cliente();
                    cliente.IdCliente = lector.GetInt32("id_cliente");
                    cliente.NombreCliente = lector.GetString("nombre_cliente");
                    cliente.ApellidoCliente = lector.GetString("apellido_cliente");
                    cliente.EmailCliente = lector.GetString("email_cliente");
                    lista.Add(cliente);
                }
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al listar clientes: " + ex.Message); }
            return lista;
        }
    }
}
