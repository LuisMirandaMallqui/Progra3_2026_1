using System;
using System.Collections.Generic;
using System.Text;
using EventMasterSoftModel.GestionProductora;

namespace EventMasterSoftModel.Evento
{
    public class Evento
    {
        private int _idEvento;
        private Productora _productora;
        private char _clasificacion;
        private String _nombre;
        private double _costoRealizacion;
        private TipoEvento _tipoEvento;
        private DateTime _fechaRealizacion;
        private String _descripcion;
        private bool _permiteReingreso;
        private bool _permiteGrabacion;
        private byte[] _bannerPromocional;
        private bool _activo;

        public Evento()
        {
        }

        public Evento(int idEvento, Productora productora, char clasificacion, string nombre, double costoRealizacion, TipoEvento tipoEvento, DateTime fechaRealizacion, string descripcion, bool permiteReingreso, bool permiteGrabacion, byte[] bannerPromocional, bool activo)
        {
            _productora = productora;
            _idEvento = idEvento;
            _clasificacion = clasificacion;
            _nombre = nombre;
            _costoRealizacion = costoRealizacion;
            _tipoEvento = tipoEvento;
            _fechaRealizacion = fechaRealizacion;
            _descripcion = descripcion;
            _permiteReingreso = permiteReingreso;
            _permiteGrabacion = permiteGrabacion;
            _bannerPromocional = bannerPromocional;
            _activo = activo;
        }

        public int IdEvento { get => _idEvento; set => _idEvento = value; }
        public char Clasificacion { get => _clasificacion; set => _clasificacion = value; }
        public string Nombre { get => _nombre; set => _nombre = value; }
        public double CostoRealizacion { get => _costoRealizacion; set => _costoRealizacion = value; }
        public TipoEvento TipoEvento { get => _tipoEvento; set => _tipoEvento = value; }
        public DateTime FechaRealizacion { get => _fechaRealizacion; set => _fechaRealizacion = value; }
        public string Descripcion { get => _descripcion; set => _descripcion = value; }
        public bool PermiteReingreso { get => _permiteReingreso; set => _permiteReingreso = value; }
        public bool PermiteGrabacion { get => _permiteGrabacion; set => _permiteGrabacion = value; }
        public byte[] BannerPromocional { get => _bannerPromocional; set => _bannerPromocional = value; }
        public bool Activo { get => _activo; set => _activo = value; }
        public Productora Productora { get => _productora; set => _productora = value; }
    }
}
