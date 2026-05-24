using TestSoftModel.Alumno;
using TestSoftModel.Examen;
using TestSoftModel.Pregunta;
using TestSoftBusiness.BO;

namespace TestSoftBusiness.Examenes.BOI
{
    public interface IExamenBO : IBaseBO<Examen>
    {
        void CrearExamenConPreguntas(Alumno alumno, string titulo, List<Pregunta> preguntas);
    }
}
