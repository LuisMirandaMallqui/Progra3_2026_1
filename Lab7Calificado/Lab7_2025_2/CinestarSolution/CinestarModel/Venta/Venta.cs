using System;
using System.Collections.Generic;
using System.Text;

namespace CinestarModel.Venta
{
    public class Venta
    {
        private int _idVenta;
        private int _idCliente;
        private int _idPelicula;
        private int _idSucursal;
        private DateTime _fechaVenta;
        private int _cantidadAsientos;
        private double _totalVenta;
        
        public Venta() { }

        public Venta(int idVenta, int idCliente, int idPelicula, int idSucursal, DateTime fechaVenta, int cantidadAsientos, double totalVenta)
        {
            _idVenta = idVenta;
            _idCliente = idCliente;
            _idPelicula = idPelicula;
            _idSucursal = idSucursal;
            _fechaVenta = fechaVenta;
            _cantidadAsientos = cantidadAsientos;
            _totalVenta = totalVenta;
        }

        public int IdVenta { get => _idVenta; set => _idVenta = value; }
        public int IdCliente { get => _idCliente; set => _idCliente = value; }
        public int IdPelicula { get => _idPelicula; set => _idPelicula = value; }
        public int IdSucursal { get => _idSucursal; set => _idSucursal = value; }
        public DateTime FechaVenta { get => _fechaVenta; set => _fechaVenta = value; }
        public int CantidadAsientos { get => _cantidadAsientos; set => _cantidadAsientos = value; }
        public double TotalVenta { get => _totalVenta; set => _totalVenta = value; }
    }
}
