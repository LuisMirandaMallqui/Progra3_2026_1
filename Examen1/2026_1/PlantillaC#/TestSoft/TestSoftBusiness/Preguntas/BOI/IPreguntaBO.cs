using TestSoftModel.Pregunta;
using TestSoftBusiness.BO;

namespace TestSoftBusiness.Preguntas.BOI
{
    public interface IPreguntaBO : IBaseBO<Pregunta>
    {
        List<Pregunta> SeleccionarPreguntasAleatorias();
    }
}
