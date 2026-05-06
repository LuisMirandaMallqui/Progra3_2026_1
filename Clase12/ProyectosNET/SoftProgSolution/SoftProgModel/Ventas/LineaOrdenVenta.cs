using SoftProgModel.Almacen;
using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgModel.Ventas
{
    public class LineaOrdenVenta
    {
        private int _idLineaOrdenVenta;
        private Producto _producto;
        private int _cantidadUnidades;
        private double _subtotal;
        private bool _activa;

        public int IdLineaOrdenVenta { get => _idLineaOrdenVenta; set => _idLineaOrdenVenta = value; }
        public Producto Producto { get => _producto; set => _producto = value; }
        public int CantidadUnidades { get => _cantidadUnidades; set => _cantidadUnidades = value; }
        public double Subtotal { get => _subtotal; set => _subtotal = value; }
        public bool Activa { get => _activa; set => _activa = value; }
    }
}
