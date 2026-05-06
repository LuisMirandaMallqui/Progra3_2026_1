using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgModel.RRHH
{
    public class Persona
    {
        private int _idPersona;
        private String _DNI;
        private String _nombre;
        private String _apellidoPaterno;
        private char _genero;
        private DateTime _fechaNacimiento;
        public Persona() { }
        public Persona(string DNI, string nombre, string apellidoPaterno, char genero, DateTime fechaNacimiento)
        {
            _DNI = DNI;
            _nombre = nombre;
            _apellidoPaterno = apellidoPaterno;
            _genero = genero;
            _fechaNacimiento = fechaNacimiento;
        }
        public int IdPersona { get => _idPersona; set => _idPersona = value; }
        public string DNI { get => _DNI; set => _DNI = value; }
        public string Nombre { get => _nombre; set => _nombre = value; }
        public string ApellidoPaterno { get => _apellidoPaterno; set => _apellidoPaterno = value; }
        public char Genero { get => _genero; set => _genero = value; }
        public DateTime FechaNacimiento { get => _fechaNacimiento; set => _fechaNacimiento = value; }
    }
}
