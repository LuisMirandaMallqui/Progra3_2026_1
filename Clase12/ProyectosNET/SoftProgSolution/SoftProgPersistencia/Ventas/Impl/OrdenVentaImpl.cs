using MySql.Data.MySqlClient;
using SoftProgDBManager;
using SoftProgModel.Ventas;
using SoftProgPersistencia.Ventas.DAO;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;

namespace SoftProgPersistencia.Ventas.Impl
{
    public class OrdenVentaImpl : OrdenVentaDAO
    {
        private MySqlTransaction transaccion;
        private MySqlConnection con;
        private MySqlCommand cmd;
        public OrdenVenta buscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int eliminar(int id)
        {
            throw new NotImplementedException();
        }

        public int insertar(OrdenVenta ordenVenta)
        {
            int resultado = 0;
            try {
                con = DBManager.Instance.GetConnection();
                con.Open();
                transaccion = con.BeginTransaction();
                transaccion.Connection = con;
                cmd = con.CreateCommand();
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "INSERTAR_ORDEN_VENTA";
                cmd.Parameters.Add("_id_orden_venta", MySqlDbType.Int32)
                    .Direction = ParameterDirection.Output;
                cmd.Parameters.AddWithValue("_fid_empleado", ordenVenta.Empleado.IdPersona);
                cmd.Parameters.AddWithValue("_fid_cliente", ordenVenta.Cliente.IdPersona);
                cmd.Parameters.AddWithValue("_total", ordenVenta.Total);
                cmd.ExecuteNonQuery();
                ordenVenta.IdOrdenVenta = Int32.Parse(
                cmd.Parameters["_id_orden_venta"].Value.ToString());
                foreach(LineaOrdenVenta lov in ordenVenta.LineasOrdenVenta)
                {
                    cmd = con.CreateCommand();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.CommandText = "INSERTAR_LINEA_ORDEN_VENTA";
                    cmd.Parameters.Add("_id_linea_orden_venta",MySqlDbType.Int32)
                        .Direction = ParameterDirection.Output;
                    cmd.Parameters.AddWithValue("_fid_orden_venta", ordenVenta.IdOrdenVenta);
                    cmd.Parameters.AddWithValue("_fid_producto", lov.Producto.IdProducto);
                    cmd.Parameters.AddWithValue("_cantidad_unidades", lov.CantidadUnidades);
                    cmd.Parameters.AddWithValue("_subtotal", lov.Subtotal);
                    cmd.ExecuteNonQuery();
                }
                transaccion.Commit();
            }
            catch(Exception ex)
            {
                try { transaccion.Rollback(); } catch (Exception ex2) { System.Console.WriteLine(ex2.Message); };
                    
                System.Console.WriteLine(ex.Message);
            }
            finally
            {
                con.Close();
            }
            return resultado;
        }

        public List<OrdenVenta> listarTodos()
        {
            throw new NotImplementedException();
        }

        public int modificar(OrdenVenta objeto)
        {
            throw new NotImplementedException();
        }
    }
}
