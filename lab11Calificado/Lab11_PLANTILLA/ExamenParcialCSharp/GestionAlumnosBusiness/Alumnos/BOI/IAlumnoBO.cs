using GestionAlumnosBusiness.BO;
using GestionAlumnosModel.Alumno;

namespace GestionAlumnosBusiness.Alumnos.BOI
{
    public interface IAlumnoBO : IBaseBO<Alumno>
    {
        int BuscarPorCodigo(string codigo);
        List<Alumno> BuscarPorNombreApellido(string texto);
    }
}
