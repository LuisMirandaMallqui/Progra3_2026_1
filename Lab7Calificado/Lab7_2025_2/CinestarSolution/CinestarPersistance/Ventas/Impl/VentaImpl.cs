using CinestarDBManager;
using CinestarModel.Sucursal;
using CinestarModel.Venta;
using CinestarPersistance.Ventas.DAO;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;

namespace CinestarPersistance.Ventas.Impl
{
    public class VentaImpl : VentaDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader lector;
        public Venta BuscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int Eliminar(int id)
        {
            throw new NotImplementedException();
        }

        //OUT _id_venta INT,
        //IN _fid_cliente INT,
        //IN _fid_pelicula INT,
        //IN _fid_sucursal INT,
        //IN _fecha_venta DATE,
        //IN _cantidad_asientos INT,
        //IN _total_venta DECIMAL(10, 2)
        public int Insertar(Venta venta) // PROCEDURE
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "INSERTAR_VENTA";
                cmd.Parameters.Add("_id_venta", MySqlDbType.Int32).Direction = ParameterDirection.Output;
                cmd.Parameters.AddWithValue("_fid_cliente", venta.IdCliente);
                cmd.Parameters.AddWithValue("_fid_pelicula", venta.IdPelicula);
                cmd.Parameters.AddWithValue("_fid_sucursal", venta.IdSucursal);
                cmd.Parameters.AddWithValue("_fecha_venta", venta.FechaVenta);
                cmd.Parameters.AddWithValue("_cantidad_asientos", venta.CantidadAsientos);
                cmd.Parameters.AddWithValue("_total_venta", venta.TotalVenta);
                cmd.ExecuteNonQuery();
                venta.IdSucursal = Int32.Parse(cmd.Parameters["_id_venta"].Value.ToString());
                resultado = venta.IdVenta;
                con.Close();
            }
            catch (Exception ex)
            { System.Console.WriteLine(ex.Message); }
            return resultado;
        }

        public List<Venta> ListarTodos()
        {
            throw new NotImplementedException();
        }

        public int Modificar(Venta objeto)
        {
            throw new NotImplementedException();
        }
    }
}
