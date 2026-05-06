using System;
using System.Collections.Generic;
using System.Text;

namespace CinestarModel.Sucursal
{
    public class Sucursal
    {
        private int _idSucursal;
        private String _nombreSucursal;
        public Sucursal() { }

        public Sucursal(int idSucursal, string nombreSucursal)
        {
            IdSucursal = idSucursal;
            NombreSucursal = nombreSucursal;
        }

        public int IdSucursal { get => _idSucursal; set => _idSucursal = value; }
        public string NombreSucursal { get => _nombreSucursal; set => _nombreSucursal = value; }
    }
}
