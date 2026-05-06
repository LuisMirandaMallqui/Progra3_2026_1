using SoftProgModel.GestClientes;
using SoftProgModel.RRHH;
using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgModel.Ventas
{
    public class OrdenVenta
    {
        private int _idOrdenVenta;
        private Empleado _empleado;
        private Cliente _cliente;
        private List<LineaOrdenVenta> _lineasOrdenVenta;
        private double _total;
        private DateTime _fecha;
        private bool _activo;

        public OrdenVenta()
        {
            _lineasOrdenVenta = new List<LineaOrdenVenta>();
        }
        public int IdOrdenVenta { get => _idOrdenVenta; set => _idOrdenVenta = value; }
        public Empleado Empleado { get => _empleado; set => _empleado = value; }
        public Cliente Cliente { get => _cliente; set => _cliente = value; }
        public List<LineaOrdenVenta> LineasOrdenVenta { get => _lineasOrdenVenta; set => _lineasOrdenVenta = value; }
        public double Total { get => _total; set => _total = value; }
        public DateTime Fecha { get => _fecha; set => _fecha = value; }
        public bool Activo { get => _activo; set => _activo = value; }
    }
}
