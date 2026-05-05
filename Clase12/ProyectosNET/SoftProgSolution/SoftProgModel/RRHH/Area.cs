using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgModel.RRHH
{
    public class Area
    {
        private int _idArea;
        private String _nombre;
        private bool _activa;
        public Area() { }
        public Area(String nombre)
        {
            _nombre = nombre;
        }
        public int IdArea { get => _idArea; set => _idArea = value; }
        public string Nombre { get => _nombre; set => _nombre = value; }
        public bool Activa { get => _activa; set => _activa = value; }

        public override string ToString()
        {
            return _idArea + ". " + _nombre;
        }

    }
}
