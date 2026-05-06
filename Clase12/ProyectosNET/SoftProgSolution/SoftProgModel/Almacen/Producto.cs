using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgModel.Almacen
{
    public class Producto
    {
        private int _idProducto;
        private String _nombre;
        private String _unidadMedida;
        private double _precio;
        private bool _activo;

        public int IdProducto { get => _idProducto; set => _idProducto = value; }
        public string Nombre { get => _nombre; set => _nombre = value; }
        public string UnidadMedida { get => _unidadMedida; set => _unidadMedida = value; }
        public double Precio { get => _precio; set => _precio = value; }
        public bool Activo { get => _activo; set => _activo = value; }
    }
}
