using CinestarDBManager;
using CinestarModel.CinestarDN;
using CinestarModel.Cliente;
using CinestarModel.Pelicula;
using CinestarPersistance.CinestarDN.DAO;
using MySql.Data.MySqlClient;
using MySqlX.XDevAPI;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;

namespace CinestarPersistance.CinestarDN.Impl
{
    public class VentaDNImpl : VentaDNDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader lector;
        private string _connectionString;

        public VentaDNImpl()
        {
        }

        public VentaDNImpl(string connectionString)
        {
            _connectionString = connectionString;
        }


        public VentaDN BuscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int Eliminar(int id)
        {
            throw new NotImplementedException();
        }

        public int Insertar(VentaDN objeto)
        {
            throw new NotImplementedException();
        }

        public List<VentaDN> ListarTodos() // PROCEDURE
        {
            List<VentaDN> ventasDN = null;
            con = new MySqlConnection(_connectionString);
            con.Open();
            cmd = new MySqlCommand();
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Connection = con;
            cmd.CommandText = "LISTAR_VENTAS_TODAS";
            lector = cmd.ExecuteReader();
            //id_venta, nombre_cliente, apellido_cliente, email_cliente, 
            //nombre_pelicula, genero_pelicula, nombre_sucursal, fecha_venta, 
            //cantidad_asientos, total_venta FROM venta_dn;
            while (lector.Read())
            {
                if (ventasDN == null) ventasDN = new List<VentaDN>();
                VentaDN ventaDN = new VentaDN();
                if (!lector.IsDBNull("id_venta")) ventaDN.IdVenta = lector.GetInt32("id_venta");
                if (!lector.IsDBNull("nombre_cliente")) ventaDN.NombreCliente = lector.GetString("nombre_cliente");
                if (!lector.IsDBNull("apellido_cliente")) ventaDN.ApellidoCliente = lector.GetString("apellido_cliente");
                if (!lector.IsDBNull("email_cliente")) ventaDN.EmailCliente = lector.GetString("email_cliente");
                //if (!lector.IsDBNull("genero")) cliente.Genero = lector.GetChar("genero"); para recordar como es con char
                if (!lector.IsDBNull("nombre_pelicula")) ventaDN.NombrePelicula = lector.GetString("nombre_pelicula");
                if (!lector.IsDBNull("genero_pelicula")) ventaDN.GeneroPelicula = (GeneroPelicula)Enum.Parse(typeof(GeneroPelicula), lector.GetString("genero_pelicula").Replace(" ", "_"));
                if (!lector.IsDBNull("nombre_sucursal")) ventaDN.NombreSucursal = lector.GetString("nombre_sucursal");
                if (!lector.IsDBNull("fecha_venta")) ventaDN.FechaVenta = lector.GetDateTime("fecha_venta");
                if (!lector.IsDBNull("cantidad_asientos")) ventaDN.CantidadAsientos = lector.GetInt32("cantidad_asientos");
                if (!lector.IsDBNull("total_venta")) ventaDN.TotalVenta = lector.GetDouble("total_venta");
                ventasDN.Add(ventaDN);
            }
            con.Close();
            return ventasDN;
        }

        public int Modificar(VentaDN objeto)
        {
            throw new NotImplementedException();
        }
    }
}
