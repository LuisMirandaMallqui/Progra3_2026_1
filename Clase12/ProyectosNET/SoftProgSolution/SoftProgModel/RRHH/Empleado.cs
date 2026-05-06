using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgModel.RRHH
{
    public class Empleado : Persona
    {
        private Area _area;
        private String _cargo;
        private double _sueldo;
        private bool _activo;
        public Empleado() { }
        public Empleado(string DNI, string nombre, string apellidoPaterno, char genero, DateTime fechaNacimiento, string cargo, double sueldo) : base(DNI, nombre, apellidoPaterno, genero, fechaNacimiento)
        {
            _cargo = cargo;
            _sueldo = sueldo;
        }

        public string Cargo { get => _cargo; set => _cargo = value; }
        public double Sueldo { get => _sueldo; set => _sueldo = value; }
        public bool Activo { get => _activo; set => _activo = value; }
        public Area Area { get => _area; set => _area = value; }
    }
}
