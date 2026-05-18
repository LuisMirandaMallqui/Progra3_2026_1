using EventMasterSoftModel.Evento;
using EventMasterSoftPersistance.DAO;
using System;
using System.Collections.Generic;
using System.Text;

namespace EventMasterSoftPersistance.GestEvento.DAO
{
    public interface EventoDAO : IDAO<Evento>
    {
        Evento ObtenerEventoPorNombre(String nombre);
    }
}
