using CinestarBusiness.Migracion.BOI;
using CinestarModel.CinestarDN;
using CinestarModel.Cliente;
using CinestarModel.Pelicula;
using CinestarModel.Sucursal;
using CinestarModel.Venta;
using CinestarPersistance.CinestarDN.DAO;
using CinestarPersistance.CinestarDN.Impl;
using CinestarPersistance.Clientes.DAO;
using CinestarPersistance.Clientes.Impl;
using CinestarPersistance.Peliculas.DAO;
using CinestarPersistance.Peliculas.Impl;
using CinestarPersistance.Sucursales.DAO;
using CinestarPersistance.Sucursales.Impl;
using CinestarPersistance.Ventas.DAO;
using CinestarPersistance.Ventas.Impl;
using System;
using System.Collections.Generic;
using System.Text;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace CinestarBusiness.Migracion.BO
{
    public class MigracionBOImpl : IMigracionBO
    {
        private string _connDN;

        public MigracionBOImpl(string connectionStringDN)
        {
            _connDN = connectionStringDN;
        }

        public void Migrar()
        {
            // Leer origen con su propia cadena de conexión
            VentaDNDAO ventaDNDAO = new VentaDNImpl(_connDN);
            List<VentaDN> ventasDN = ventaDNDAO.ListarTodos();
            Dictionary<string, int> clientesMap = new Dictionary<string, int>();
            Dictionary<string, int> peliculasMap = new Dictionary<string, int>();
            Dictionary<string, int> sucursalMap = new Dictionary<string, int>();
            ClienteDAO clienteDAO = new ClienteImpl();
            PeliculaDAO peliculaDAO = new PeliculaImpl();
            SucursalDAO sucursalDAO = new SucursalImpl();
            VentaDAO ventaDAO = new VentaImpl();
            foreach (VentaDN ventaDN in ventasDN)
            {
                Console.WriteLine($"Migrando venta: {ventaDN.IdVenta} - {ventaDN.NombreCliente} {ventaDN.ApellidoCliente} - {ventaDN.NombrePelicula} - {ventaDN.NombreSucursal}");
                // cliente
                int idCliente;
                if (clientesMap.ContainsKey(ventaDN.EmailCliente))
                {
                    // Ya existe, uso el id que guardé
                    idCliente = clientesMap[ventaDN.EmailCliente];
                }
                else
                {
                    // Primera vez, inserto y guardo el id
                    Cliente cliente = new Cliente();
                    cliente.EmailCliente = ventaDN.EmailCliente;
                    cliente.NombreCliente = ventaDN.NombreCliente;
                    cliente.ApellidoCliente = ventaDN.ApellidoCliente;
                    cliente.IdCliente = clienteDAO.Insertar(cliente);
                    idCliente = cliente.IdCliente;
                    clientesMap[cliente.EmailCliente] = cliente.IdCliente;
                }
                // pelicula
                int idPelicula;
                if (peliculasMap.ContainsKey(ventaDN.NombrePelicula))
                {
                    // Ya existe, uso el id que guardé
                    idPelicula = peliculasMap[ventaDN.NombrePelicula];
                }
                else
                {
                    // Primera vez, inserto y guardo el id
                    Pelicula pelicula = new Pelicula();
                    pelicula.NombrePelicula = ventaDN.NombrePelicula;
                    pelicula.GeneroPelicula = ventaDN.GeneroPelicula;
                    pelicula.IdPelicula= peliculaDAO.Insertar(pelicula);
                    idPelicula = pelicula.IdPelicula;
                    peliculasMap[pelicula.NombrePelicula] = pelicula.IdPelicula;
                }
                // sucursal
                int idSucursal;
                if (sucursalMap.ContainsKey(ventaDN.NombreSucursal))
                {
                    // Ya existe, uso el id que guardé
                    idSucursal = sucursalMap[ventaDN.NombreSucursal];
                }
                else
                {
                    // Primera vez, inserto y guardo el id
                    Sucursal sucursal= new Sucursal();
                    //nombre_sucursal VARCHAR(150)
                    sucursal.NombreSucursal = ventaDN.NombreSucursal;
                    sucursal.IdSucursal= sucursalDAO.Insertar(sucursal);
                    idSucursal = sucursal.IdSucursal;
                    sucursalMap[sucursal.NombreSucursal] = sucursal.IdSucursal;
                }

                //id_venta INT PRIMARY KEY AUTO_INCREMENT,
                //fid_cliente INT,
                //fid_pelicula INT,
                //fid_sucursal INT,
                //fecha_venta DATE,
                //cantidad_asientos INT,
                //total_venta DECIMAL(10, 2),
                //FOREIGN KEY(fid_cliente) REFERENCES cliente(id_cliente),
                //FOREIGN KEY(fid_pelicula) REFERENCES pelicula(id_pelicula),
                //FOREIGN KEY(fid_sucursal) REFERENCES sucursal(id_sucursal)
                Venta venta = new Venta();
                venta.IdCliente = idCliente;
                venta.IdPelicula = idPelicula;
                venta.IdSucursal = idSucursal;
                venta.FechaVenta = ventaDN.FechaVenta;
                venta.CantidadAsientos = ventaDN.CantidadAsientos;
                venta.TotalVenta = ventaDN.TotalVenta;
                venta.IdVenta = ventaDAO.Insertar(venta);
            }
        }
    }
}
