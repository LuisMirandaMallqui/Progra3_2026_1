using EventMasterSoftModel.Evento;
using EventMasterSoftModel.GestionProductora;
using EventMasterSoftPersistance.GestEvento.DAO;
using SoftProgDBManager;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Text;

namespace EventMasterSoftPersistance.GestEvento.Impl
{
    public class EventoImpl : EventoDAO
    {
        public Evento BuscarPorId(int idEvento)
        {
            Evento? evento = null;
            DbParameter[] parametros = new DbParameter[1];
            parametros[0] = DBManager.Instance.CreateParam("_id_evento", DbType.Int32, idEvento, ParameterDirection.Input);
            using DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("OBTENER_EVENTO_X_ID", parametros);
            if (lector.Read())
            {
                evento = new Evento();
                if (!lector.IsDBNull(lector.GetOrdinal("id_evento"))) evento.IdEvento = lector.GetInt32(lector.GetOrdinal("id_evento"));
                Productora productora = new Productora();
                if (!lector.IsDBNull(lector.GetOrdinal("id_productora"))) productora.IdProductora = lector.GetInt32(lector.GetOrdinal("id_productora"));
                if (!lector.IsDBNull(lector.GetOrdinal("nombre_productora"))) productora.Nombre = lector.GetString(lector.GetOrdinal("nombre_productora"));
                evento.Productora = productora;
                if (!lector.IsDBNull(lector.GetOrdinal("id_clasificacion"))) evento.Clasificacion = lector.GetString(lector.GetOrdinal("id_clasificacion"))[0];
                if (!lector.IsDBNull(lector.GetOrdinal("nombre_evento"))) evento.Nombre = lector.GetString(lector.GetOrdinal("nombre_evento"));
                if (!lector.IsDBNull(lector.GetOrdinal("costo_realizacion"))) evento.CostoRealizacion = Convert.ToDouble(lector["costo_realizacion"]); ;
                if (!lector.IsDBNull(lector.GetOrdinal("tipo_evento"))) evento.TipoEvento = (TipoEvento)Enum.Parse(typeof(TipoEvento), lector.GetString("tipo_evento"));
                if (!lector.IsDBNull(lector.GetOrdinal("fecha_realizacion"))) evento.FechaRealizacion = lector.GetDateTime(lector.GetOrdinal("fecha_realizacion"));
                if (!lector.IsDBNull(lector.GetOrdinal("descripcion"))) evento.Descripcion = lector.GetString(lector.GetOrdinal("descripcion"));
                if (!lector.IsDBNull(lector.GetOrdinal("permite_reingreso"))) evento.PermiteReingreso = lector.GetBoolean(lector.GetOrdinal("permite_reingreso"));
                if (!lector.IsDBNull(lector.GetOrdinal("permite_grabacion"))) evento.PermiteGrabacion = lector.GetBoolean(lector.GetOrdinal("permite_grabacion"));
                if (!lector.IsDBNull(lector.GetOrdinal("banner_promocional"))) evento.BannerPromocional = (byte[])lector["banner_promocional"];
                if (!lector.IsDBNull(lector.GetOrdinal("activo"))) evento.Activo = lector.GetBoolean(lector.GetOrdinal("activo"));
                evento.Activo = true;
            }
            return evento;
        }

        public int Eliminar(Evento objeto)
        {
            throw new NotImplementedException();
        }

        public int Insertar(Evento evento)
        {
            DbParameter[] parametros = new DbParameter[11];
            parametros[0] = DBManager.Instance.CreateParam("_id_evento", DbType.Int32, null, ParameterDirection.Output);
            parametros[1] = DBManager.Instance.CreateParam("_fid_productora", DbType.Int32, evento.Productora.IdProductora, ParameterDirection.Input);
            parametros[2] = DBManager.Instance.CreateParam("_fid_clasificacion", DbType.String, evento.Clasificacion, ParameterDirection.Input);
            parametros[3] = DBManager.Instance.CreateParam("_nombre", DbType.String, evento.Nombre, ParameterDirection.Input);
            parametros[4] = DBManager.Instance.CreateParam("_costo_realizacion", DbType.Double, evento.CostoRealizacion, ParameterDirection.Input);
            parametros[5] = DBManager.Instance.CreateParam("_tipo_evento", DbType.String, evento.TipoEvento, ParameterDirection.Input);
            parametros[6] = DBManager.Instance.CreateParam("_fecha_realizacion", DbType.Date, evento.FechaRealizacion, ParameterDirection.Input);
            parametros[7] = DBManager.Instance.CreateParam("_descripcion", DbType.String, evento.Descripcion, ParameterDirection.Input);
            parametros[8] = DBManager.Instance.CreateParam("_permite_reingreso", DbType.Boolean, evento.PermiteReingreso, ParameterDirection.Input);
            parametros[9] = DBManager.Instance.CreateParam("_permite_grabacion", DbType.Boolean, evento.PermiteGrabacion, ParameterDirection.Input);
            parametros[10] = DBManager.Instance.CreateParam("_banner_promocional", DbType.Binary, evento.BannerPromocional, ParameterDirection.Input);

            DBManager.Instance.EjecutarProcedimiento("INSERTAR_EVENTO", parametros);
            evento.IdEvento = Convert.ToInt32(parametros[0].Value);
            return evento.IdEvento;
        }

        public List<Evento> ListarTodos()
        {
            List<Evento> eventos = new List<Evento>();
            DbParameter[] parametros = new DbParameter[1];
            parametros[0] = DBManager.Instance.CreateParam("_nombre", DbType.String, "", ParameterDirection.Input);
            using DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("LISTAR_EVENTOS_X_NOMBRE", parametros);
            while (lector.Read())
            {
                Evento evento = new Evento();
                if (!lector.IsDBNull(lector.GetOrdinal("id_evento"))) evento.IdEvento = lector.GetInt32(lector.GetOrdinal("id_evento"));
                Productora productora = new Productora();
                if (!lector.IsDBNull(lector.GetOrdinal("id_productora"))) productora.IdProductora = lector.GetInt32(lector.GetOrdinal("id_productora"));
                if (!lector.IsDBNull(lector.GetOrdinal("nombre_productora"))) productora.Nombre = lector.GetString(lector.GetOrdinal("nombre_productora"));
                evento.Productora = productora;
                if (!lector.IsDBNull(lector.GetOrdinal("nombre_evento"))) evento.Nombre = lector.GetString(lector.GetOrdinal("nombre_evento"));
                if (!lector.IsDBNull(lector.GetOrdinal("fecha_realizacion"))) evento.FechaRealizacion = lector.GetDateTime(lector.GetOrdinal("fecha_realizacion"));
                evento.Activo = true;
                eventos.Add(evento);
            }
            return eventos;
        }

        public int Modificar(Evento objeto)
        {
            throw new NotImplementedException();
        }

        public Evento ObtenerEventoPorNombre(String nombre)
        {
            List<Evento> eventos = new List<Evento>();
            DbParameter[] parametros = new DbParameter[1];
            parametros[0] = DBManager.Instance.CreateParam("nombre", DbType.String, nombre, ParameterDirection.Input);
            using DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("LISTAR_EVENTOS_X_NOMBRE", parametros);
            Evento evento = new Evento();
            if (!lector.IsDBNull(lector.GetOrdinal("id_evento"))) evento.IdEvento = lector.GetInt32(lector.GetOrdinal("id_evento"));
            Productora productora = new Productora();
            if (!lector.IsDBNull(lector.GetOrdinal("id_productora"))) productora.IdProductora = lector.GetInt32(lector.GetOrdinal("id_productora"));
            if (!lector.IsDBNull(lector.GetOrdinal("nombre_productora"))) productora.Nombre = lector.GetString(lector.GetOrdinal("nombre_productora"));
            evento.Productora = productora;
            if (!lector.IsDBNull(lector.GetOrdinal("nombre_evento"))) evento.Nombre = lector.GetString(lector.GetOrdinal("nombre_evento"));
            if (!lector.IsDBNull(lector.GetOrdinal("fecha_realizacion"))) evento.FechaRealizacion = lector.GetDateTime(lector.GetOrdinal("fecha_realizacion"));
            evento.Activo = true;
            return evento;
        }

    }
}
