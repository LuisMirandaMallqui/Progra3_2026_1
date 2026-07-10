using Newtonsoft.Json;

namespace CSharpRestClient
{

    public class FacultadDTO
    {
        private int id;
        private string nombre;
        private bool activo;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("nombre")]
        public string Nombre { get => nombre; set => nombre = value; }

        [JsonProperty("activo")]
        public bool Activo { get => activo; set => activo = value; }

    }

    public class EspecialidadDTO
    {
        private int id;
        private FacultadDTO facultad;
        private string nombre;
        private bool activo;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("facultad")]
        public FacultadDTO Facultad { get => facultad; set => facultad = value; }

        [JsonProperty("nombre")]
        public string Nombre { get => nombre; set => nombre = value; }

        [JsonProperty("activo")]
        public bool Activo { get => activo; set => activo = value; }

    }

    public class EstudianteDTO
    {
        private int id;
        private EspecialidadDTO especialidad;
        private string codigoUniversitario;
        private string nombres;
        private string apellidos;
        private string correoInstitucional;
        private bool activo;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("especialidad")]
        public EspecialidadDTO Especialidad { get => especialidad; set => especialidad = value; }

        [JsonProperty("codigoUniversitario")]
        public string CodigoUniversitario { get => codigoUniversitario; set => codigoUniversitario = value; }

        [JsonProperty("nombres")]
        public string Nombres { get => nombres; set => nombres = value; }

        [JsonProperty("apellidos")]
        public string Apellidos { get => apellidos; set => apellidos = value; }

        [JsonProperty("correoInstitucional")]
        public string CorreoInstitucional { get => correoInstitucional; set => correoInstitucional = value; }

        [JsonProperty("activo")]
        public bool Activo { get => activo; set => activo = value; }

    }

    public class DepartamentoDTO
    {
        private int id;
        private string nombre;
        private bool activo;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("nombre")]
        public string Nombre { get => nombre; set => nombre = value; }

        [JsonProperty("activo")]
        public bool Activo { get => activo; set => activo = value; }

    }

    public class DocenteDTO
    {
        private int id;
        private DepartamentoDTO departamento;
        private string codigo;
        private string nombreCompleto;
        private string categoria;
        private string dedicacion;
        private bool activo;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("departamento")]
        public DepartamentoDTO Departamento { get => departamento; set => departamento = value; }

        [JsonProperty("codigo")]
        public string Codigo { get => codigo; set => codigo = value; }

        [JsonProperty("nombreCompleto")]
        public string NombreCompleto { get => nombreCompleto; set => nombreCompleto = value; }

        [JsonProperty("categoria")]
        public string Categoria { get => categoria; set => categoria = value; }

        [JsonProperty("dedicacion")]
        public string Dedicacion { get => dedicacion; set => dedicacion = value; }

        [JsonProperty("activo")]
        public bool Activo { get => activo; set => activo = value; }

    }

    public class AulaDTO
    {
        private int id;
        private string codigoAula;
        private string ubicacion;
        private bool activo;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("codigoAula")]
        public string CodigoAula { get => codigoAula; set => codigoAula = value; }

        [JsonProperty("ubicacion")]
        public string Ubicacion { get => ubicacion; set => ubicacion = value; }

        [JsonProperty("activo")]
        public bool Activo { get => activo; set => activo = value; }

    }

    public class CursoDTO
    {
        private int id;
        private EspecialidadDTO especialidad;
        private string codigo;
        private string nombre;
        private int creditos;
        private int nivelAcademico;
        private string estadoCurso;
        private bool activo;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("especialidad")]
        public EspecialidadDTO Especialidad { get => especialidad; set => especialidad = value; }

        [JsonProperty("codigo")]
        public string Codigo { get => codigo; set => codigo = value; }

        [JsonProperty("nombre")]
        public string Nombre { get => nombre; set => nombre = value; }

        [JsonProperty("creditos")]
        public int Creditos { get => creditos; set => creditos = value; }

        [JsonProperty("nivelAcademico")]
        public int NivelAcademico { get => nivelAcademico; set => nivelAcademico = value; }

        [JsonProperty("estadoCurso")]
        public string EstadoCurso { get => estadoCurso; set => estadoCurso = value; }

        [JsonProperty("activo")]
        public bool Activo { get => activo; set => activo = value; }

    }

    public class CursoPrerrequisitoDTO
    {
        private int id;
        private CursoDTO curso;
        private CursoDTO prerrequisito;
        private bool activo;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("curso")]
        public CursoDTO Curso { get => curso; set => curso = value; }

        [JsonProperty("prerrequisito")]
        public CursoDTO Prerrequisito { get => prerrequisito; set => prerrequisito = value; }

        [JsonProperty("activo")]
        public bool Activo { get => activo; set => activo = value; }

    }

    public class HorarioCursoDTO
    {
        private int id;
        private CursoDTO curso;
        private string semestre;
        private string codigoHorario;
        private DocenteDTO docente;
        private int cupoMaximo;
        private string estado;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("curso")]
        public CursoDTO Curso { get => curso; set => curso = value; }

        [JsonProperty("semestre")]
        public string Semestre { get => semestre; set => semestre = value; }

        [JsonProperty("codigoHorario")]
        public string CodigoHorario { get => codigoHorario; set => codigoHorario = value; }

        [JsonProperty("docente")]
        public DocenteDTO Docente { get => docente; set => docente = value; }

        [JsonProperty("cupoMaximo")]
        public int CupoMaximo { get => cupoMaximo; set => cupoMaximo = value; }

        [JsonProperty("estado")]
        public string Estado { get => estado; set => estado = value; }

    }

    public class HorarioCursoDetDTO
    {
        private int id;
        private HorarioCursoDTO horarioCurso;
        private string tipoSesion;
        private string diaSemana;
        private string horaInicio;
        private string horaFin;
        private AulaDTO aula;
        private string frecuencia;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("horarioCurso")]
        public HorarioCursoDTO HorarioCurso { get => horarioCurso; set => horarioCurso = value; }

        [JsonProperty("tipoSesion")]
        public string TipoSesion { get => tipoSesion; set => tipoSesion = value; }

        [JsonProperty("diaSemana")]
        public string DiaSemana { get => diaSemana; set => diaSemana = value; }

        [JsonProperty("horaInicio")]
        public string HoraInicio { get => horaInicio; set => horaInicio = value; }

        [JsonProperty("horaFin")]
        public string HoraFin { get => horaFin; set => horaFin = value; }

        [JsonProperty("aula")]
        public AulaDTO Aula { get => aula; set => aula = value; }

        [JsonProperty("frecuencia")]
        public string Frecuencia { get => frecuencia; set => frecuencia = value; }

    }

    public class MatriculaDTO
    {
        private int id;
        private EstudianteDTO estudiante;
        private string semestre;
        private string fechaInscripcion;
        private string tipoMatricula;
        private string estadoMatricula;
        private string modalidad;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("estudiante")]
        public EstudianteDTO Estudiante { get => estudiante; set => estudiante = value; }

        [JsonProperty("semestre")]
        public string Semestre { get => semestre; set => semestre = value; }

        [JsonProperty("fechaInscripcion")]
        public string FechaInscripcion { get => fechaInscripcion; set => fechaInscripcion = value; }

        [JsonProperty("tipoMatricula")]
        public string TipoMatricula { get => tipoMatricula; set => tipoMatricula = value; }

        [JsonProperty("estadoMatricula")]
        public string EstadoMatricula { get => estadoMatricula; set => estadoMatricula = value; }

        [JsonProperty("modalidad")]
        public string Modalidad { get => modalidad; set => modalidad = value; }

    }

    public class MatriculaHorarioDTO
    {
        private int id;
        private MatriculaDTO matricula;
        private HorarioCursoDTO horarioCurso;
        private string fechaRegistro;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("matricula")]
        public MatriculaDTO Matricula { get => matricula; set => matricula = value; }

        [JsonProperty("horarioCurso")]
        public HorarioCursoDTO HorarioCurso { get => horarioCurso; set => horarioCurso = value; }

        [JsonProperty("fechaRegistro")]
        public string FechaRegistro { get => fechaRegistro; set => fechaRegistro = value; }

    }

    public class EvaluacionDTO
    {
        private int id;
        private HorarioCursoDTO horarioCurso;
        private string tipoEvaluacion;
        private double peso;
        private string fechaEvaluacion;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("horarioCurso")]
        public HorarioCursoDTO HorarioCurso { get => horarioCurso; set => horarioCurso = value; }

        [JsonProperty("tipoEvaluacion")]
        public string TipoEvaluacion { get => tipoEvaluacion; set => tipoEvaluacion = value; }

        [JsonProperty("peso")]
        public double Peso { get => peso; set => peso = value; }

        [JsonProperty("fechaEvaluacion")]
        public string FechaEvaluacion { get => fechaEvaluacion; set => fechaEvaluacion = value; }

    }

    public class NotaDTO
    {
        private int id;
        private EvaluacionDTO evaluacion;
        private MatriculaDTO matricula;
        private HorarioCursoDTO horarioCurso;
        private double calificacion;

        [JsonProperty("id")]
        public int Id { get => id; set => id = value; }

        [JsonProperty("evaluacion")]
        public EvaluacionDTO Evaluacion { get => evaluacion; set => evaluacion = value; }

        [JsonProperty("matricula")]
        public MatriculaDTO Matricula { get => matricula; set => matricula = value; }

        [JsonProperty("horarioCurso")]
        public HorarioCursoDTO HorarioCurso { get => horarioCurso; set => horarioCurso = value; }

        [JsonProperty("calificacion")]
        public double Calificacion { get => calificacion; set => calificacion = value; }

    }

}
