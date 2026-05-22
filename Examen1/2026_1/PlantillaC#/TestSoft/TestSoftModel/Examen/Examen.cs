namespace TestSoftModel.Examen
{
    public class Examen
    {
        public int Id { get; set; }
        public int IdAlumno { get; set; }
        public string Titulo { get; set; }
        public DateTime FechaCreacion { get; set; }

        // Relaciones
        public Alumno.Alumno Alumno { get; set; }
        public List<Pregunta.Pregunta> Preguntas { get; set; } = new List<Pregunta.Pregunta>();
    }
}
