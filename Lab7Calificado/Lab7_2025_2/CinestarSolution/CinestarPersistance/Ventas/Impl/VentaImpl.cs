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
        // SP: INSERTAR_VENTA(OUT _id_venta, IN _fid_cliente, IN _fid_pelicula, IN _fid_sucursal, IN _fecha_venta, IN _cantidad_asientos, IN _total_venta)
        public int Insertar(Venta venta)
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
                venta.IdVenta = Int32.Parse(cmd.Parameters["_id_venta"].Value.ToString());
                resultado = venta.IdVenta;
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al insertar venta: " + ex.Message); }
            return resultado;
        }

        // SP: MODIFICAR_VENTA(IN _id_venta, IN _fid_cliente, IN _fid_pelicula, IN _fid_sucursal, IN _fecha_venta, IN _cantidad_asientos, IN _total_venta)
        public int Modificar(Venta venta)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "MODIFICAR_VENTA";
                cmd.Parameters.AddWithValue("_id_venta", venta.IdVenta);
                cmd.Parameters.AddWithValue("_fid_cliente", venta.IdCliente);
                cmd.Parameters.AddWithValue("_fid_pelicula", venta.IdPelicula);
                cmd.Parameters.AddWithValue("_fid_sucursal", venta.IdSucursal);
                cmd.Parameters.AddWithValue("_fecha_venta", venta.FechaVenta);
                cmd.Parameters.AddWithValue("_cantidad_asientos", venta.CantidadAsientos);
                cmd.Parameters.AddWithValue("_total_venta", venta.TotalVenta);
                resultado = cmd.ExecuteNonQuery();
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al modificar venta: " + ex.Message); }
            return resultado;
        }

        // SP: ELIMINAR_VENTA(IN _id_venta)
        // --- EJEMPLO ELIMINACION LOGICA (si la tabla tuviera campo 'activo') ---
        // El SP haria: UPDATE venta SET activo = 0 WHERE id_venta = _id_venta;
        // El codigo C# seria identico al de abajo, solo cambia lo que hace el SP internamente
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
                cmd.CommandText = "ELIMINAR_VENTA";
                cmd.Parameters.AddWithValue("_id_venta", id);
                resultado = cmd.ExecuteNonQuery();
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al eliminar venta: " + ex.Message); }
            return resultado;
        }

        // SP: BUSCAR_VENTA_POR_ID(IN _id_venta)
        public Venta BuscarPorId(int id)
        {
            Venta venta = null;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "BUSCAR_VENTA_POR_ID";
                cmd.Parameters.AddWithValue("_id_venta", id);
                lector = cmd.ExecuteReader();
                if (lector.Read())
                {
                    venta = new Venta();
                    venta.IdVenta = lector.GetInt32("id_venta");
                    venta.IdCliente = lector.GetInt32("fid_cliente");
                    venta.IdPelicula = lector.GetInt32("fid_pelicula");
                    venta.IdSucursal = lector.GetInt32("fid_sucursal");
                    venta.FechaVenta = lector.GetDateTime("fecha_venta");
                    venta.CantidadAsientos = lector.GetInt32("cantidad_asientos");
                    venta.TotalVenta = lector.GetDecimal("total_venta");
                }
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al buscar venta: " + ex.Message); }
            return venta;
        }

        // SP: LISTAR_VENTAS_TODAS() -- en cinestar_n
        public List<Venta> ListarTodos()
        {
            List<Venta> lista = null;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "LISTAR_VENTAS";
                lector = cmd.ExecuteReader();
                while (lector.Read())
                {
                    if (lista == null) lista = new List<Venta>();
                    Venta venta = new Venta();
                    venta.IdVenta = lector.GetInt32("id_venta");
                    venta.IdCliente = lector.GetInt32("fid_cliente");
                    venta.IdPelicula = lector.GetInt32("fid_pelicula");
                    venta.IdSucursal = lector.GetInt32("fid_sucursal");
                    venta.FechaVenta = lector.GetDateTime("fecha_venta");
                    venta.CantidadAsientos = lector.GetInt32("cantidad_asientos");
                    venta.TotalVenta = lector.GetDecimal("total_venta");
                    lista.Add(venta);
                }
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al listar ventas: " + ex.Message); }
            return lista;
        }
    }
}
