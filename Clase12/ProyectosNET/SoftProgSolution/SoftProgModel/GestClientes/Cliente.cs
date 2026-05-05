
using SoftProgModel.RRHH;
using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgModel.GestClientes
{
    public class Cliente : Persona
    {
        private double _lineaCredito;
        public double LineaCredito { get => _lineaCredito; 
            set => _lineaCredito = value; }
    }
}
