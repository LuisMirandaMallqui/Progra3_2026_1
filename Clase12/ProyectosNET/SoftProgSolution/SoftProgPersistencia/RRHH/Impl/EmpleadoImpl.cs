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
    public class EmpleadoImpl : EmpleadoDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader lector;
        public Empleado buscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int eliminar(int id)
        {
            throw new NotImplementedException();
        }

        public int insertar(Empleado empleado)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "INSERTAR_EMPLEADO";
                cmd.Parameters.Add("_id_empleado", MySqlDbType.Int32).Direction = ParameterDirection.Output;
                cmd.Parameters.AddWithValue("_fid_area", empleado.Area.IdArea);
                cmd.Parameters.AddWithValue("_DNI", empleado.DNI);
                cmd.Parameters.AddWithValue("_nombre", empleado.Nombre);
                cmd.Parameters.AddWithValue("_apellido_paterno", empleado.ApellidoPaterno);
                cmd.Parameters.AddWithValue("_genero", empleado.Genero);
                cmd.Parameters.AddWithValue("_fecha_nacimiento", empleado.FechaNacimiento);
                cmd.Parameters.AddWithValue("_cargo", empleado.Cargo);
                cmd.Parameters.AddWithValue("_sueldo", empleado.Sueldo);
                cmd.ExecuteNonQuery();
                empleado.IdPersona = Int32.Parse(cmd.Parameters["_id_empleado"].Value.ToString());
                resultado = empleado.IdPersona;
                con.Close();
            }
            catch (Exception ex) 
                { System.Console.WriteLine(ex.Message); }
            return resultado;
        }

        public List<Empleado> listarTodos()
        {
            List<Empleado> empleados = null;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "LISTAR_EMPLEADOS_TODOS";
                lector = cmd.ExecuteReader();
                while (lector.Read())
                {
                    if (empleados == null) empleados = new List<Empleado>();
                    Empleado empleado = new Empleado();
                    if(!lector.IsDBNull("id_persona")) empleado.IdPersona = lector.GetInt32("id_persona");
                    if (!lector.IsDBNull("DNI")) empleado.DNI = lector.GetString("DNI");
                    if (!lector.IsDBNull("nombre")) empleado.Nombre = lector.GetString("nombre");
                    if (!lector.IsDBNull("apellido_paterno")) empleado.ApellidoPaterno = lector.GetString("apellido_paterno");
                    if (!lector.IsDBNull("genero")) empleado.Genero = lector.GetChar("genero");
                    if (!lector.IsDBNull("fecha_nacimiento")) empleado.FechaNacimiento = lector.GetDateTime("fecha_nacimiento");
                    Area area = new Area();
                    if (!lector.IsDBNull("id_area")) area.IdArea = lector.GetInt32("id_area");
                    if (!lector.IsDBNull("nombre_area")) area.Nombre = lector.GetString("nombre_area");
                    empleado.Area = area;
                    if (!lector.IsDBNull("cargo")) empleado.Cargo = lector.GetString("cargo");
                    if (!lector.IsDBNull("sueldo")) empleado.Sueldo = lector.GetDouble("sueldo");
                    empleados.Add(empleado);
                }
                con.Close();
            }
            catch (Exception ex){ 
                System.Console.WriteLine(ex.Message);
            }
            return empleados;
        }

        public int modificar(Empleado objeto)
        {
            throw new NotImplementedException();
        }
    }
}
