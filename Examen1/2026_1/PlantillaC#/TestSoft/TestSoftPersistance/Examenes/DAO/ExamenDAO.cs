using TestSoftModel.Examen;
using TestSoftModel.Pregunta;
using TestSoftPersistance.DAO;

namespace TestSoftPersistance.Examenes.DAO
{
    public interface ExamenDAO : IDAO<Examen>
    {
        List<Pregunta> ListarPreguntasPorExamen(int idExamen);
    }
}
