using TestSoftModel.Pregunta;
using TestSoftBusiness.Preguntas.BOI;
using TestSoftPersistance.Preguntas.DAO;
using TestSoftPersistance.Preguntas.Impl;

namespace TestSoftBusiness.Preguntas.BO
{
    public class PreguntaBOImpl : IPreguntaBO
    {
        private readonly PreguntaDAO preguntaDAO = new PreguntaImpl();

        public int Insertar(Pregunta objeto) => preguntaDAO.Insertar(objeto);
        public int Modificar(Pregunta objeto) => preguntaDAO.Modificar(objeto);
        public int Eliminar(int id) => preguntaDAO.Eliminar(id);
        public Pregunta BuscarPorId(int id) => preguntaDAO.BuscarPorId(id);
        public List<Pregunta> ListarTodos() => preguntaDAO.ListarTodos();

        // Selecciona 10 preguntas aleatorias (o menos si hay menos)
        public List<Pregunta> SeleccionarPreguntasAleatorias()
        {
            List<Pregunta> todas = preguntaDAO.ListarTodos();
            if (todas == null || todas.Count == 0) return new List<Pregunta>();

            Random rng = new Random();
            List<Pregunta> mezcladas = todas.OrderBy(x => rng.Next()).ToList();
            int cantidad = Math.Min(10, mezcladas.Count);
            return mezcladas.Take(cantidad).ToList();
        }
    }
}
