using EventMasterSoftBusiness.BO;
using EventMasterSoftModel.Evento;
using System;
using System.Collections.Generic;
using System.Text;

namespace EventMasterSoftBusiness.GestEvento.BOI
{
    public interface IEventoBO : IBaseBO<Evento>
    {
        Evento ObtenerEventoPorNombre(String nombre);
    }
}
