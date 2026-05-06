using MySql.Data.MySqlClient;
using SoftProgDBManager;
using SoftProgModel.RRHH;
using SoftProgPersistencia.RRHH.DAO;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;

namespace SoftProgPersistencia.RRHH.Impl
{
    public class AreaImpl : AreaDAO
    {
        private MySqlConnection con;
        private MySqlCommand comando;
        private MySqlDataReader lector;
        public Area buscarPorId(int idArea)
        {
            throw new NotImplementedException();
        }

        public int eliminar(int idArea)
        {
            throw new NotImplementedException();
        }

        public int insertar(Area area)
        {
            int resultado = 0;
            con = DBManager.Instance.GetConnection();
            con.Open();
            comando = new MySqlCommand();
            comando.Connection = con;
            comando.CommandText = "INSERT INTO area(nombre,activa) VALUES(@nombre,1)";
            comando.Parameters.AddWithValue("@nombre",area.Nombre);
            resultado = comando.ExecuteNonQuery();
            con.Close();
            return resultado;
        }

        public List<Area> listarTodos()
        {
            List<Area> areas = null;
            con = DBManager.Instance.GetConnection();
            con.Open();
            comando = new MySqlCommand();
            comando.Connection = con;
            comando.CommandText = "SELECT id_area, nombre FROM area WHERE activa = 1";
            lector = comando.ExecuteReader();
            while (lector.Read())
            {
                if (areas == null) areas = new List<Area>();
                Area area = new Area();
                if (!lector.IsDBNull("id_area")) area.IdArea = lector.GetInt32("id_area");
                if (!lector.IsDBNull("nombre")) area.Nombre = lector.GetString("nombre");
                area.Activa = true;
                areas.Add(area);            
            }
            con.Close();
            return areas;
        }

        public int modificar(Area area)
        {
            throw new NotImplementedException();
        }
    }
}
