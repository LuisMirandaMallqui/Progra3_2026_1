using SoftProgModel.RRHH;
using SoftProgNegocio.RRHH.BOI;
using SoftProgPersistencia.RRHH.DAO;
using SoftProgPersistencia.RRHH.Impl;
using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgNegocio.RRHH.BO
{
    public class EmpleadoBOImpl : IEmpleadoBO
    {
        private EmpleadoDAO daoEmpleado;
        public EmpleadoBOImpl()
        {
            daoEmpleado = new EmpleadoImpl();
        }
        public Empleado buscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int eliminar(int idObjeto)
        {
            throw new NotImplementedException();
        }

        public int insertar(Empleado empleado)
        {
            //validaciones
            return daoEmpleado.insertar(empleado);
        }

        public List<Empleado> listarTodos()
        {
            return daoEmpleado.listarTodos();
        }

        public int modificar(Empleado objeto)
        {
            throw new NotImplementedException();
        }
    }
}
