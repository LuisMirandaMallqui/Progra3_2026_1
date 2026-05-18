using System;
using System.Collections.Generic;
using System.Text;

namespace EventMasterSoftModel.GestionProductora
{
    public class Productora
    {
        private int _idProductora;
        private String _nombre;
        private bool _activa;

        public Productora()
        {
        }

        public Productora(int idProductora, string nombre, bool activa)
        {
            IdProductora = idProductora;
            Nombre = nombre;
            Activa = activa;
        }

        public int IdProductora { get => _idProductora; set => _idProductora = value; }
        public string Nombre { get => _nombre; set => _nombre = value; }
        public bool Activa { get => _activa; set => _activa = value; }
    }
}
