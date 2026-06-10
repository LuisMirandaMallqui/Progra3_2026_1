using GestionAlumnosBusiness.Alumnos.BOI;
using GestionAlumnosModel.Alumno;
using GestionAlumnosPersistance.Alumnos.DAO;
using GestionAlumnosPersistance.Alumnos.Impl;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace GestionAlumnosBusiness.Alumnos.BO
{
    public class AlumnoBOImpl : IAlumnoBO
    {
        private readonly AlumnoDAO alumnoDAO = new AlumnoImpl();

        public int Insertar(Alumno objeto) => alumnoDAO.Insertar(objeto);
        public int Modificar(Alumno objeto) => alumnoDAO.Modificar(objeto);
        public int Eliminar(int id) => alumnoDAO.Eliminar(id);
        public Alumno BuscarPorId(int id) => alumnoDAO.BuscarPorId(id);
        public List<Alumno> ListarTodos() => alumnoDAO.ListarTodos();
        public int BuscarPorCodigo(string codigo) => alumnoDAO.BuscarPorCodigo(codigo);
        public List<Alumno> BuscarPorNombreApellido(string texto)
        {
            return alumnoDAO.BuscarPorNombreApellido(texto); 
        }
        

    }
}
