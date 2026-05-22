using TestSoftModel.Alumno;
using TestSoftBusiness.BO;

namespace TestSoftBusiness.Alumnos.BOI
{
    public interface IAlumnoBO : IBaseBO<Alumno>
    {
        int BuscarPorCodigo(string codigo);
    }
}
