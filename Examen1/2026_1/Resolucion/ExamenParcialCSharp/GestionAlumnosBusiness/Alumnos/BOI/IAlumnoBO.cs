using GestionAlumnosBusiness.BO;
using GestionAlumnosModel.Alumno;

namespace GestionAlumnosBusiness.Alumnos.BOI
{
    public interface IAlumnoBO : IBaseBO<Alumno>
    {
        int BuscarPorCodigo(string codigo);
        Alumno BuscarPorNombre(string nombre);
        Alumno BuscarPorApellido(string apellido);
    }
}
