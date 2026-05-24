using SoftProgDBManager;
using TestSoftModel.Pregunta;
using TestSoftPersistance.Preguntas.DAO;
using System.Data;
using System.Data.Common;

namespace TestSoftPersistance.Preguntas.Impl
{
    public class PreguntaImpl : PreguntaDAO
    {
        // SP: insertar_pregunta(IN p_enunciado, OUT p_id)
        public int Insertar(Pregunta pregunta)
        {
            DbParameter[] parametros = new DbParameter[2];
            parametros[0] = DBManager.Instance.CreateParam("p_enunciado", DbType.String, pregunta.Enunciado, ParameterDirection.Input);
            parametros[1] = DBManager.Instance.CreateParam("p_id", DbType.Int32, null, ParameterDirection.Output);

            DBManager.Instance.EjecutarProcedimiento("insertar_pregunta", parametros);
            pregunta.Id = Convert.ToInt32(parametros[1].Value);
            return pregunta.Id;
        }

        // SP: modificar_pregunta(IN p_id, IN p_enunciado)
        public int Modificar(Pregunta pregunta)
        {
            DbParameter[] parametros = new DbParameter[2];
            parametros[0] = DBManager.Instance.CreateParam("p_id", DbType.Int32, pregunta.Id, ParameterDirection.Input);
            parametros[1] = DBManager.Instance.CreateParam("p_enunciado", DbType.String, pregunta.Enunciado, ParameterDirection.Input);
            return DBManager.Instance.EjecutarProcedimiento("modificar_pregunta", parametros);
        }

        // SP: eliminar_pregunta(IN p_id)
        public int Eliminar(int id)
        {
            DbParameter[] parametros = new DbParameter[1];
            parametros[0] = DBManager.Instance.CreateParam("p_id", DbType.Int32, id, ParameterDirection.Input);
            return DBManager.Instance.EjecutarProcedimiento("eliminar_pregunta", parametros);
        }

        public Pregunta BuscarPorId(int id)
        {
            return null; // No hay SP buscar_pregunta_por_id
        }

        // SP: listar_preguntas()
        public List<Pregunta> ListarTodos()
        {
            List<Pregunta> lista = new List<Pregunta>();
            using DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("listar_preguntas", null);
            while (lector.Read())
            {
                Pregunta p = new Pregunta();
                p.Id = lector.GetInt32(lector.GetOrdinal("id"));
                p.Enunciado = lector.GetString(lector.GetOrdinal("enunciado"));
                lista.Add(p);
            }
            return lista;
        }
    }
}
