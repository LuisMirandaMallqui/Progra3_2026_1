using System.Collections.Generic;
namespace CSharpRestClient
{
    public class UniversidadRSManager
    {
        private const string BASE_URL = "http://localhost:8080/RestServicesCaso2/webresources";

        public List<FacultadDTO> ListarFacultads()
        {
            return new HttpClientUtils<List<FacultadDTO>>().get($"{BASE_URL}/facultades");
        }

        public FacultadDTO BuscarFacultadPorId(int id)
        {
            return new HttpClientUtils<FacultadDTO>().get($"{BASE_URL}/facultades/{id}");
        }

        public int InsertarFacultad(FacultadDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/facultades", elemento);
        }

        public int ModificarFacultad(FacultadDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/facultades/{elemento.Id}", elemento);
        }

        public int EliminarFacultad(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/facultades/{id}");
        }

        public List<EspecialidadDTO> ListarEspecialidads()
        {
            return new HttpClientUtils<List<EspecialidadDTO>>().get($"{BASE_URL}/especialidades");
        }

        public EspecialidadDTO BuscarEspecialidadPorId(int id)
        {
            return new HttpClientUtils<EspecialidadDTO>().get($"{BASE_URL}/especialidades/{id}");
        }

        public int InsertarEspecialidad(EspecialidadDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/especialidades", elemento);
        }

        public int ModificarEspecialidad(EspecialidadDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/especialidades/{elemento.Id}", elemento);
        }

        public int EliminarEspecialidad(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/especialidades/{id}");
        }

        public List<EstudianteDTO> ListarEstudiantes()
        {
            return new HttpClientUtils<List<EstudianteDTO>>().get($"{BASE_URL}/estudiantes");
        }

        public EstudianteDTO BuscarEstudiantePorId(int id)
        {
            return new HttpClientUtils<EstudianteDTO>().get($"{BASE_URL}/estudiantes/{id}");
        }

        public int InsertarEstudiante(EstudianteDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/estudiantes", elemento);
        }

        public int ModificarEstudiante(EstudianteDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/estudiantes/{elemento.Id}", elemento);
        }

        public int EliminarEstudiante(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/estudiantes/{id}");
        }

        public List<DepartamentoDTO> ListarDepartamentos()
        {
            return new HttpClientUtils<List<DepartamentoDTO>>().get($"{BASE_URL}/departamentos");
        }

        public DepartamentoDTO BuscarDepartamentoPorId(int id)
        {
            return new HttpClientUtils<DepartamentoDTO>().get($"{BASE_URL}/departamentos/{id}");
        }

        public int InsertarDepartamento(DepartamentoDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/departamentos", elemento);
        }

        public int ModificarDepartamento(DepartamentoDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/departamentos/{elemento.Id}", elemento);
        }

        public int EliminarDepartamento(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/departamentos/{id}");
        }

        public List<DocenteDTO> ListarDocentes()
        {
            return new HttpClientUtils<List<DocenteDTO>>().get($"{BASE_URL}/docentes");
        }

        public DocenteDTO BuscarDocentePorId(int id)
        {
            return new HttpClientUtils<DocenteDTO>().get($"{BASE_URL}/docentes/{id}");
        }

        public int InsertarDocente(DocenteDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/docentes", elemento);
        }

        public int ModificarDocente(DocenteDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/docentes/{elemento.Id}", elemento);
        }

        public int EliminarDocente(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/docentes/{id}");
        }

        public List<AulaDTO> ListarAulas()
        {
            return new HttpClientUtils<List<AulaDTO>>().get($"{BASE_URL}/aulas");
        }

        public AulaDTO BuscarAulaPorId(int id)
        {
            return new HttpClientUtils<AulaDTO>().get($"{BASE_URL}/aulas/{id}");
        }

        public int InsertarAula(AulaDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/aulas", elemento);
        }

        public int ModificarAula(AulaDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/aulas/{elemento.Id}", elemento);
        }

        public int EliminarAula(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/aulas/{id}");
        }

        public List<CursoDTO> ListarCursos()
        {
            return new HttpClientUtils<List<CursoDTO>>().get($"{BASE_URL}/cursos");
        }

        public CursoDTO BuscarCursoPorId(int id)
        {
            return new HttpClientUtils<CursoDTO>().get($"{BASE_URL}/cursos/{id}");
        }

        public int InsertarCurso(CursoDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/cursos", elemento);
        }

        public int ModificarCurso(CursoDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/cursos/{elemento.Id}", elemento);
        }

        public int EliminarCurso(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/cursos/{id}");
        }

        public List<CursoPrerrequisitoDTO> ListarCursoPrerrequisitos()
        {
            return new HttpClientUtils<List<CursoPrerrequisitoDTO>>().get($"{BASE_URL}/cursos-prerrequisitos");
        }

        public CursoPrerrequisitoDTO BuscarCursoPrerrequisitoPorId(int id)
        {
            return new HttpClientUtils<CursoPrerrequisitoDTO>().get($"{BASE_URL}/cursos-prerrequisitos/{id}");
        }

        public int InsertarCursoPrerrequisito(CursoPrerrequisitoDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/cursos-prerrequisitos", elemento);
        }

        public int ModificarCursoPrerrequisito(CursoPrerrequisitoDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/cursos-prerrequisitos/{elemento.Id}", elemento);
        }

        public int EliminarCursoPrerrequisito(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/cursos-prerrequisitos/{id}");
        }

        public List<HorarioCursoDTO> ListarHorarioCursos()
        {
            return new HttpClientUtils<List<HorarioCursoDTO>>().get($"{BASE_URL}/horarios-curso");
        }

        public HorarioCursoDTO BuscarHorarioCursoPorId(int id)
        {
            return new HttpClientUtils<HorarioCursoDTO>().get($"{BASE_URL}/horarios-curso/{id}");
        }

        public int InsertarHorarioCurso(HorarioCursoDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/horarios-curso", elemento);
        }

        public int ModificarHorarioCurso(HorarioCursoDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/horarios-curso/{elemento.Id}", elemento);
        }

        public int EliminarHorarioCurso(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/horarios-curso/{id}");
        }

        public List<HorarioCursoDetDTO> ListarHorarioCursoDets()
        {
            return new HttpClientUtils<List<HorarioCursoDetDTO>>().get($"{BASE_URL}/horarios-curso-det");
        }

        public HorarioCursoDetDTO BuscarHorarioCursoDetPorId(int id)
        {
            return new HttpClientUtils<HorarioCursoDetDTO>().get($"{BASE_URL}/horarios-curso-det/{id}");
        }

        public int InsertarHorarioCursoDet(HorarioCursoDetDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/horarios-curso-det", elemento);
        }

        public int ModificarHorarioCursoDet(HorarioCursoDetDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/horarios-curso-det/{elemento.Id}", elemento);
        }

        public int EliminarHorarioCursoDet(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/horarios-curso-det/{id}");
        }

        public List<MatriculaDTO> ListarMatriculas()
        {
            return new HttpClientUtils<List<MatriculaDTO>>().get($"{BASE_URL}/matriculas");
        }

        public MatriculaDTO BuscarMatriculaPorId(int id)
        {
            return new HttpClientUtils<MatriculaDTO>().get($"{BASE_URL}/matriculas/{id}");
        }

        public int InsertarMatricula(MatriculaDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/matriculas", elemento);
        }

        public int ModificarMatricula(MatriculaDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/matriculas/{elemento.Id}", elemento);
        }

        public int EliminarMatricula(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/matriculas/{id}");
        }

        public List<MatriculaHorarioDTO> ListarMatriculaHorarios()
        {
            return new HttpClientUtils<List<MatriculaHorarioDTO>>().get($"{BASE_URL}/matriculas-horarios");
        }

        public MatriculaHorarioDTO BuscarMatriculaHorarioPorId(int id)
        {
            return new HttpClientUtils<MatriculaHorarioDTO>().get($"{BASE_URL}/matriculas-horarios/{id}");
        }

        public int InsertarMatriculaHorario(MatriculaHorarioDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/matriculas-horarios", elemento);
        }

        public int ModificarMatriculaHorario(MatriculaHorarioDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/matriculas-horarios/{elemento.Id}", elemento);
        }

        public int EliminarMatriculaHorario(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/matriculas-horarios/{id}");
        }

        public List<EvaluacionDTO> ListarEvaluacions()
        {
            return new HttpClientUtils<List<EvaluacionDTO>>().get($"{BASE_URL}/evaluaciones");
        }

        public EvaluacionDTO BuscarEvaluacionPorId(int id)
        {
            return new HttpClientUtils<EvaluacionDTO>().get($"{BASE_URL}/evaluaciones/{id}");
        }

        public int InsertarEvaluacion(EvaluacionDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/evaluaciones", elemento);
        }

        public int ModificarEvaluacion(EvaluacionDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/evaluaciones/{elemento.Id}", elemento);
        }

        public int EliminarEvaluacion(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/evaluaciones/{id}");
        }

        public List<NotaDTO> ListarNotas()
        {
            return new HttpClientUtils<List<NotaDTO>>().get($"{BASE_URL}/notas");
        }

        public NotaDTO BuscarNotaPorId(int id)
        {
            return new HttpClientUtils<NotaDTO>().get($"{BASE_URL}/notas/{id}");
        }

        public int InsertarNota(NotaDTO elemento)
        {
            return new HttpClientUtils<int>().post($"{BASE_URL}/notas", elemento);
        }

        public int ModificarNota(NotaDTO elemento)
        {
            return new HttpClientUtils<int>().put($"{BASE_URL}/notas/{elemento.Id}", elemento);
        }

        public int EliminarNota(int id)
        {
            return new HttpClientUtils<int>().delete($"{BASE_URL}/notas/{id}");
        }
    }
}
