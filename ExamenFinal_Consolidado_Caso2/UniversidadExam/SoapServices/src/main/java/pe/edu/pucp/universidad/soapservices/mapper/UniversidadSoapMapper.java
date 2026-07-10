package pe.edu.pucp.universidad.soapservices.mapper;

import pe.edu.pucp.universidad.model.*;
import pe.edu.pucp.universidad.soapservices.dto.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UniversidadSoapMapper {

    private static String toStringDate(LocalDateTime fecha) {
        return fecha == null ? null : fecha.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    private static LocalDateTime toLocalDateTime(String texto) {
        if (texto == null || texto.trim().isEmpty()) return null;
        String valor = texto.trim();
        if (valor.length() == 10) valor = valor + "T00:00:00";
        return LocalDateTime.parse(valor);
    }

    public static AulaDTO toDTO(Aula m) {
        if (m == null) return null;
        AulaDTO d = new AulaDTO();
        d.id = m.getId();
        d.codigoAula = m.getCodigoAula();
        d.ubicacion = m.getUbicacion();
        d.activo = m.isActivo();
        return d;
    }

    public static Aula toModel(AulaDTO d) {
        if (d == null) return null;
        Aula m = new Aula();
        m.setId(d.id);
        m.setCodigoAula(d.codigoAula);
        m.setUbicacion(d.ubicacion);
        m.setActivo(d.activo);
        return m;
    }

    public static FacultadDTO toDTO(Facultad m) {
        if (m == null) return null;
        FacultadDTO d = new FacultadDTO();
        d.id = m.getId();
        d.nombre = m.getNombre();
        d.activo = m.isActivo();
        return d;
    }

    public static Facultad toModel(FacultadDTO d) {
        if (d == null) return null;
        Facultad m = new Facultad();
        m.setId(d.id);
        m.setNombre(d.nombre);
        m.setActivo(d.activo);
        return m;
    }

    public static DepartamentoDTO toDTO(Departamento m) {
        if (m == null) return null;
        DepartamentoDTO d = new DepartamentoDTO();
        d.id = m.getId();
        d.nombre = m.getNombre();
        d.activo = m.isActivo();
        return d;
    }

    public static Departamento toModel(DepartamentoDTO d) {
        if (d == null) return null;
        Departamento m = new Departamento();
        m.setId(d.id);
        m.setNombre(d.nombre);
        m.setActivo(d.activo);
        return m;
    }

    public static EspecialidadDTO toDTO(Especialidad m) {
        if (m == null) return null;
        EspecialidadDTO d = new EspecialidadDTO();
        d.id = m.getId();
        d.facultad = toDTO(m.getFacultad());
        d.nombre = m.getNombre();
        d.activo = m.isActivo();
        return d;
    }

    public static Especialidad toModel(EspecialidadDTO d) {
        if (d == null) return null;
        Especialidad m = new Especialidad();
        m.setId(d.id);
        m.setFacultad(toModel(d.facultad));
        m.setNombre(d.nombre);
        m.setActivo(d.activo);
        return m;
    }

    public static EstudianteDTO toDTO(Estudiante m) {
        if (m == null) return null;
        EstudianteDTO d = new EstudianteDTO();
        d.id = m.getId();
        d.especialidad = toDTO(m.getEspecialidad());
        d.codigoUniversitario = m.getCodigoUniversitario();
        d.nombres = m.getNombres();
        d.apellidos = m.getApellidos();
        d.correoInstitucional = m.getCorreoInstitucional();
        d.activo = m.isActivo();
        return d;
    }

    public static Estudiante toModel(EstudianteDTO d) {
        if (d == null) return null;
        Estudiante m = new Estudiante();
        m.setId(d.id);
        m.setEspecialidad(toModel(d.especialidad));
        m.setCodigoUniversitario(d.codigoUniversitario);
        m.setNombres(d.nombres);
        m.setApellidos(d.apellidos);
        m.setCorreoInstitucional(d.correoInstitucional);
        m.setActivo(d.activo);
        return m;
    }

    public static DocenteDTO toDTO(Docente m) {
        if (m == null) return null;
        DocenteDTO d = new DocenteDTO();
        d.id = m.getId();
        d.departamento = toDTO(m.getDepartamento());
        d.codigo = m.getCodigo();
        d.nombreCompleto = m.getNombreCompleto();
        d.categoria = m.getCategoria();
        d.dedicacion = m.getDedicacion();
        d.activo = m.isActivo();
        return d;
    }

    public static Docente toModel(DocenteDTO d) {
        if (d == null) return null;
        Docente m = new Docente();
        m.setId(d.id);
        m.setDepartamento(toModel(d.departamento));
        m.setCodigo(d.codigo);
        m.setNombreCompleto(d.nombreCompleto);
        m.setCategoria(d.categoria);
        m.setDedicacion(d.dedicacion);
        m.setActivo(d.activo);
        return m;
    }

    public static CursoDTO toDTO(Curso m) {
        if (m == null) return null;
        CursoDTO d = new CursoDTO();
        d.id = m.getId();
        d.especialidad = toDTO(m.getEspecialidad());
        d.codigo = m.getCodigo();
        d.nombre = m.getNombre();
        d.creditos = m.getCreditos();
        d.nivelAcademico = m.getNivelAcademico();
        d.estadoCurso = m.getEstadoCurso();
        d.activo = m.isActivo();
        return d;
    }

    public static Curso toModel(CursoDTO d) {
        if (d == null) return null;
        Curso m = new Curso();
        m.setId(d.id);
        m.setEspecialidad(toModel(d.especialidad));
        m.setCodigo(d.codigo);
        m.setNombre(d.nombre);
        m.setCreditos(d.creditos);
        m.setNivelAcademico(d.nivelAcademico);
        m.setEstadoCurso(d.estadoCurso);
        m.setActivo(d.activo);
        return m;
    }

    public static CursoPrerrequisitoDTO toDTO(CursoPrerrequisito m) {
        if (m == null) return null;
        CursoPrerrequisitoDTO d = new CursoPrerrequisitoDTO();
        d.curso = toDTO(m.getCurso());
        d.cursoPrerreq = toDTO(m.getCursoPrerreq());
        d.activo = m.isActivo();
        return d;
    }

    public static CursoPrerrequisito toModel(CursoPrerrequisitoDTO d) {
        if (d == null) return null;
        CursoPrerrequisito m = new CursoPrerrequisito();
        m.setCurso(toModel(d.curso));
        m.setCursoPrerreq(toModel(d.cursoPrerreq));
        m.setActivo(d.activo);
        return m;
    }

    public static HorarioCursoDTO toDTO(HorarioCurso m) {
        if (m == null) return null;
        HorarioCursoDTO d = new HorarioCursoDTO();
        d.id = m.getId();
        d.curso = toDTO(m.getCurso());
        d.semestre = m.getSemestre();
        d.codigoHorario = m.getCodigoHorario();
        d.docente = toDTO(m.getDocente());
        d.cupoMaximo = m.getCupoMaximo();
        d.estado = m.getEstado();
        return d;
    }

    public static HorarioCurso toModel(HorarioCursoDTO d) {
        if (d == null) return null;
        HorarioCurso m = new HorarioCurso();
        m.setId(d.id);
        m.setCurso(toModel(d.curso));
        m.setSemestre(d.semestre);
        m.setCodigoHorario(d.codigoHorario);
        m.setDocente(toModel(d.docente));
        m.setCupoMaximo(d.cupoMaximo);
        m.setEstado(d.estado);
        return m;
    }

    public static HorarioCursoDetDTO toDTO(HorarioCursoDet m) {
        if (m == null) return null;
        HorarioCursoDetDTO d = new HorarioCursoDetDTO();
        d.id = m.getId();
        d.horarioCurso = toDTO(m.getHorarioCurso());
        d.tipoSesion = m.getTipoSesion();
        d.diaSemana = m.getDiaSemana();
        d.horaInicio = toStringDate(m.getHoraInicio());
        d.horaFin = toStringDate(m.getHoraFin());
        d.aula = toDTO(m.getAula());
        d.frecuencia = m.getFrecuencia();
        return d;
    }

    public static HorarioCursoDet toModel(HorarioCursoDetDTO d) {
        if (d == null) return null;
        HorarioCursoDet m = new HorarioCursoDet();
        m.setId(d.id);
        m.setHorarioCurso(toModel(d.horarioCurso));
        m.setTipoSesion(d.tipoSesion);
        m.setDiaSemana(d.diaSemana);
        m.setHoraInicio(toLocalDateTime(d.horaInicio));
        m.setHoraFin(toLocalDateTime(d.horaFin));
        m.setAula(toModel(d.aula));
        m.setFrecuencia(d.frecuencia);
        return m;
    }

    public static MatriculaDTO toDTO(Matricula m) {
        if (m == null) return null;
        MatriculaDTO d = new MatriculaDTO();
        d.id = m.getId();
        d.estudiante = toDTO(m.getEstudiante());
        d.semestre = m.getSemestre();
        d.fechaInscripcion = toStringDate(m.getFechaInscripcion());
        d.tipoMatricula = m.getTipoMatricula();
        d.estadoMatricula = m.getEstadoMatricula();
        d.modalidad = m.getModalidad();
        return d;
    }

    public static Matricula toModel(MatriculaDTO d) {
        if (d == null) return null;
        Matricula m = new Matricula();
        m.setId(d.id);
        m.setEstudiante(toModel(d.estudiante));
        m.setSemestre(d.semestre);
        m.setFechaInscripcion(toLocalDateTime(d.fechaInscripcion));
        m.setTipoMatricula(d.tipoMatricula);
        m.setEstadoMatricula(d.estadoMatricula);
        m.setModalidad(d.modalidad);
        return m;
    }

    public static MatriculaHorarioDTO toDTO(MatriculaHorario m) {
        if (m == null) return null;
        MatriculaHorarioDTO d = new MatriculaHorarioDTO();
        d.matricula = toDTO(m.getMatricula());
        d.horarioCurso = toDTO(m.getHorarioCurso());
        d.fechaRegistro = toStringDate(m.getFechaRegistro());
        return d;
    }

    public static MatriculaHorario toModel(MatriculaHorarioDTO d) {
        if (d == null) return null;
        MatriculaHorario m = new MatriculaHorario();
        m.setMatricula(toModel(d.matricula));
        m.setHorarioCurso(toModel(d.horarioCurso));
        m.setFechaRegistro(toLocalDateTime(d.fechaRegistro));
        return m;
    }

    public static EvaluacionDTO toDTO(Evaluacion m) {
        if (m == null) return null;
        EvaluacionDTO d = new EvaluacionDTO();
        d.id = m.getId();
        d.horarioCurso = toDTO(m.getHorarioCurso());
        d.tipoEvaluacion = m.getTipoEvaluacion();
        d.peso = m.getPeso();
        d.fechaEvaluacion = toStringDate(m.getFechaEvaluacion());
        return d;
    }

    public static Evaluacion toModel(EvaluacionDTO d) {
        if (d == null) return null;
        Evaluacion m = new Evaluacion();
        m.setId(d.id);
        m.setHorarioCurso(toModel(d.horarioCurso));
        m.setTipoEvaluacion(d.tipoEvaluacion);
        m.setPeso(d.peso);
        m.setFechaEvaluacion(toLocalDateTime(d.fechaEvaluacion));
        return m;
    }

    public static NotaDTO toDTO(Nota m) {
        if (m == null) return null;
        NotaDTO d = new NotaDTO();
        d.id = m.getId();
        d.evaluacion = toDTO(m.getEvaluacion());
        d.matricula = toDTO(m.getMatricula());
        d.horarioCurso = toDTO(m.getHorarioCurso());
        d.calificacion = m.getCalificacion();
        return d;
    }

    public static Nota toModel(NotaDTO d) {
        if (d == null) return null;
        Nota m = new Nota();
        m.setId(d.id);
        m.setEvaluacion(toModel(d.evaluacion));
        m.setMatricula(toModel(d.matricula));
        m.setHorarioCurso(toModel(d.horarioCurso));
        m.setCalificacion(d.calificacion);
        return m;
    }
}
