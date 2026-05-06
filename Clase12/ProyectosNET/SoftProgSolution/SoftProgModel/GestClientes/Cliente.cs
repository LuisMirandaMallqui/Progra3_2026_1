
using SoftProgModel.RRHH;
using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgModel.GestClientes
{
    public class Cliente : Persona
    {
        private double _lineaCredito;
        private Categoria _categoria;
        public double LineaCredito { get => _lineaCredito; 
            set => _lineaCredito = value; }
        public Categoria Categoria { get => _categoria; set => _categoria = value; }
    }
}
