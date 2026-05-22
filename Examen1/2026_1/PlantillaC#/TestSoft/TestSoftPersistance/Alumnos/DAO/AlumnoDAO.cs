using TestSoftModel.Alumno;
using TestSoftPersistance.DAO;

namespace TestSoftPersistance.Alumnos.DAO
{
    public interface AlumnoDAO : IDAO<Alumno>
    {
        // Patron SELECT INTO: busca por codigo y retorna el id (0 si no existe)
        int BuscarPorCodigo(string codigo);
    }
}
