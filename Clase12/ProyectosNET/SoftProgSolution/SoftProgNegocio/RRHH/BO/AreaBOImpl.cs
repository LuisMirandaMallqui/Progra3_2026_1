using SoftProgModel.RRHH;
using SoftProgNegocio.RRHH.BOI;
using SoftProgPersistencia.RRHH.DAO;
using SoftProgPersistencia.RRHH.Impl;
using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgNegocio.RRHH.BO
{
    public class AreaBOImpl : IAreaBO
    {
        private AreaDAO daoArea;
        public AreaBOImpl()
        {
            daoArea = new AreaImpl();
        }
        public Area buscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int eliminar(int idObjeto)
        {
            throw new NotImplementedException();
        }

        public int insertar(Area area)
        {
            //validaciones
            return daoArea.insertar(area);
        }

        public List<Area> listarTodos()
        {
            return daoArea.listarTodos();
        }

        public int modificar(Area objeto)
        {
            throw new NotImplementedException();
        }
    }
}
