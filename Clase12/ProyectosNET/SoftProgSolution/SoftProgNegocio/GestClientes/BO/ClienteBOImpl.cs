using SoftProgModel.GestClientes;
using SoftProgNegocio.GestClientes.BOI;
using SoftProgPersistencia.GestClientes.DAO;
using SoftProgPersistencia.GestClientes.Impl;
using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgNegocio.GestClientes.BO
{
    public class ClienteBOImpl : IClienteBO
    {
        private ClienteDAO daoCliente;
        public ClienteBOImpl()
        {
            daoCliente = new ClienteImpl();
        }
        public Cliente buscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int eliminar(int idObjeto)
        {
            throw new NotImplementedException();
        }

        public int insertar(Cliente cliente)
        {
            throw new NotImplementedException();
        }

        public List<Cliente> listarTodos()
        {
            return daoCliente.listarTodos();
        }

        public int modificar(Cliente objeto)
        {
            throw new NotImplementedException();
        }
    }
}
