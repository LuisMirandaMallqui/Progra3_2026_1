using CinestarModel.Pelicula;
using System;
using System.Collections.Generic;
using System.Text;

namespace CinestarModel.CinestarDN
{
    public class VentaDN
    {
        //venta
        private int _idVenta;
        private DateTime _fechaVenta;
        private int _cantidadAsientos;
        private double _totalVenta;
        //cliente
        private String _nombreCliente;
        private String _apellidoCliente;
        private String _emailCliente;
        //pelicula
        private String _nombrePelicula;
        private GeneroPelicula _generoPelicula;
        //sucursal
        private String _nombreSucursal;

        public VentaDN() { }

        public VentaDN(int idVenta, DateTime fechaVenta, int cantidadAsientos, double totalVenta, string nombreCliente, string apellidoCliente, string emailCliente, string nombrePelicula, GeneroPelicula generoPelicula, string nombreSucursal)
        {
            IdVenta = idVenta;
            FechaVenta = fechaVenta;
            CantidadAsientos = cantidadAsientos;
            TotalVenta = totalVenta;
            NombreCliente = nombreCliente;
            ApellidoCliente = apellidoCliente;
            EmailCliente = emailCliente;
            NombrePelicula = nombrePelicula;
            GeneroPelicula = generoPelicula;
            NombreSucursal = nombreSucursal;
        }

        public int IdVenta { get => _idVenta; set => _idVenta = value; }
        public DateTime FechaVenta { get => _fechaVenta; set => _fechaVenta = value; }
        public int CantidadAsientos { get => _cantidadAsientos; set => _cantidadAsientos = value; }
        public double TotalVenta { get => _totalVenta; set => _totalVenta = value; }
        public string NombreCliente { get => _nombreCliente; set => _nombreCliente = value; }
        public string ApellidoCliente { get => _apellidoCliente; set => _apellidoCliente = value; }
        public string EmailCliente { get => _emailCliente; set => _emailCliente = value; }
        public string NombrePelicula { get => _nombrePelicula; set => _nombrePelicula = value; }
        public GeneroPelicula GeneroPelicula { get => _generoPelicula; set => _generoPelicula = value; }
        public string NombreSucursal { get => _nombreSucursal; set => _nombreSucursal = value; }
    }
}
