using MySql.Data.MySqlClient;
using SoftProgDBManager;
using SoftProgModel.Almacen;
using SoftProgModel.RRHH;
using SoftProgPersistencia.Almacen.DAO;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;

namespace SoftProgPersistencia.Almacen.Impl
{
    public class ProductoImpl : ProductoDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader lector;
        public Producto buscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int eliminar(int id)
        {
            throw new NotImplementedException();
        }

        public int insertar(Producto objeto)
        {
            throw new NotImplementedException();
        }

        public List<Producto> listarTodos()
        {
            List<Producto> productos = null;
            con = DBManager.Instance.GetConnection();
            con.Open();
            cmd = new MySqlCommand();
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Connection = con;
            cmd.CommandText = "LISTAR_PRODUCTOS_TODOS";
            lector = cmd.ExecuteReader();
            while (lector.Read())
            {
                if (productos == null) productos = new List<Producto>();
                Producto producto = new Producto();
                if (!lector.IsDBNull("id_producto")) producto.IdProducto = lector.GetInt32("id_producto");
                if (!lector.IsDBNull("nombre")) producto.Nombre = lector.GetString("nombre");
                if (!lector.IsDBNull("unidad_medida")) producto.UnidadMedida = lector.GetString("unidad_medida");
                if (!lector.IsDBNull("precio")) producto.Precio = lector.GetDouble("precio");
                producto.Activo = true;
                productos.Add(producto);
            }
            con.Close();
            return productos;
        }

        public int modificar(Producto objeto)
        {
            throw new NotImplementedException();
        }
    }
}
