using System;
using System.Collections.Generic;
using System.Text;

namespace CinestarModel.Cliente
{
    public class Cliente
    {
        private int _idCliente;
        private String _nombreCliente;
        private String _apellidoCliente;
        private String _emailCliente;
        public Cliente() { }

        public Cliente(int idCliente, string nombreCliente, string apellidoCliente, string emailCliente)
        {
            IdCliente = idCliente;
            NombreCliente = nombreCliente;
            ApellidoCliente = apellidoCliente;
            EmailCliente = emailCliente;
        }

        public int IdCliente { get => _idCliente; set => _idCliente = value; }
        public string NombreCliente { get => _nombreCliente; set => _nombreCliente = value; }
        public string ApellidoCliente { get => _apellidoCliente; set => _apellidoCliente = value; }
        public string EmailCliente { get => _emailCliente; set => _emailCliente = value; }
    }
}
