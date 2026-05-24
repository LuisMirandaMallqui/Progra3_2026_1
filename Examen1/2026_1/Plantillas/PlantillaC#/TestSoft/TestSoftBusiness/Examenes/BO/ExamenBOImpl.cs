using TestSoftModel.Alumno;
using TestSoftModel.Examen;
using TestSoftModel.Pregunta;
using TestSoftBusiness.Examenes.BOI;
using TestSoftPersistance.Examenes.DAO;
using TestSoftPersistance.Examenes.Impl;

namespace TestSoftBusiness.Examenes.BO
{
    public class ExamenBOImpl : IExamenBO
    {
        private readonly ExamenDAO examenDAO = new ExamenImpl();

        public int Insertar(Examen objeto) => examenDAO.Insertar(objeto);
        public int Modificar(Examen objeto) => examenDAO.Modificar(objeto);
        public int Eliminar(int id) => examenDAO.Eliminar(id);
        public Examen BuscarPorId(int id) => examenDAO.BuscarPorId(id);
        public List<Examen> ListarTodos() => examenDAO.ListarTodos();

        // Crea un examen con sus preguntas (transaccion en la capa DAO)
        public void CrearExamenConPreguntas(Alumno alumno, string titulo, List<Pregunta> preguntas)
        {
            Examen examen = new Examen();
            examen.IdAlumno = alumno.Id;
            examen.Titulo = titulo;
            examen.Preguntas = preguntas;

            int idExamen = examenDAO.Insertar(examen);
            Console.WriteLine($"Examen creado: {idExamen} para {alumno.Codigo}");
        }
    }
}
