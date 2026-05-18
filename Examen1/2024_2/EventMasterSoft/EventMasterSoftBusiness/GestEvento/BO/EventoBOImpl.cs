using EventMasterSoftBusiness.GestEvento.BOI;
using EventMasterSoftModel.Evento;
using EventMasterSoftPersistance.GestEvento.DAO;
using EventMasterSoftPersistance.GestEvento.Impl;
using System;
using System.Collections.Generic;
using System.Text;

namespace EventMasterSoftBusiness.GestEvento.BO
{
    public class EventoBOImpl : IEventoBO
    {
        private EventoDAO daoEvento;

        public EventoBOImpl()
        {
            this.daoEvento = new EventoImpl();
        }

        public Evento buscarPorId(int idEvento)
        {
            return daoEvento.BuscarPorId(idEvento);

        }

        public int Eliminar(int idObjeto)
        {
            throw new NotImplementedException();
        }

        public int Insertar(Evento evento)
        {
            return daoEvento.Insertar(evento);
        }

        public Evento ObtenerEventoPorNombre(string nombre)
        {
            return daoEvento.ObtenerEventoPorNombre(nombre);
        }

        public List<Evento> ListarTodos()
        {
            return daoEvento.ListarTodos();
        }

        public int Modificar(Evento objeto)
        {
            throw new NotImplementedException();
        }
    }
}
