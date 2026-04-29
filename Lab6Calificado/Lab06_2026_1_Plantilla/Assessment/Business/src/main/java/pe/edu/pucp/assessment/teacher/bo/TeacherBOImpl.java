package pe.edu.pucp.assessment.teacher.bo;

import pe.edu.pucp.assessment.teacher.boi.ITeacherBO;
import pe.edu.pucp.assessment.teacher.model.Teacher;
import pe.edu.pucp.assessment.teacher.persistance.dao.TeacherDao;
import pe.edu.pucp.assessment.teacher.persistance.daoImpl.TeacherImpl;

import java.util.List;

public class TeacherBOImpl implements ITeacherBO {

    private TeacherDao daoTeacher;

    public TeacherBOImpl() {
        daoTeacher = new TeacherImpl();
    }

    @Override
    public int insertar(Teacher teacher) throws Exception {
        validar(teacher, false);
        return daoTeacher.insertar(teacher);
    }

    @Override
    public int modificar(Teacher teacher) throws Exception {
        validar(teacher, true);
        return daoTeacher.modificar(teacher);
    }

    @Override
    public int eliminar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del teacher debe ser mayor que cero.");
        }
        return daoTeacher.eliminar(id);
    }

    @Override
    public List<Teacher> listarTodos() throws Exception {
        return daoTeacher.listarTodos();
    }

    @Override
    public Teacher buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del teacher debe ser mayor que cero.");
        }
        return daoTeacher.buscarPorId(id);
    }

    private void validar(Teacher teacher, boolean esModificacion) throws Exception {
        if (teacher == null) {
            throw new Exception("El teacher no puede ser nulo.");
        }
        if (esModificacion && teacher.getIdTeacher() <= 0) {
            throw new Exception("El id del teacher es obligatorio para la modificacion.");
        }
        validarPucpCode(teacher.getPucpCode());
        validarTexto(teacher.getFirstName(), "El nombre");
        validarTexto(teacher.getLastName(), "El apellido");
    }

    private void validarPucpCode(String pucpCode) throws Exception {
        if (pucpCode == null || pucpCode.trim().isEmpty()) {
            throw new Exception("El codigo PUCP es obligatorio.");
        }
        if (pucpCode.trim().length() != 8) {
            throw new Exception("El codigo PUCP debe tener 8 caracteres.");
        }
    }

    private void validarTexto(String texto, String campo) throws Exception {
        if (texto == null || texto.trim().isEmpty()) {
            throw new Exception(campo + " es obligatorio.");
        }
        if (texto.trim().length() > 100) {
            throw new Exception(campo + " no puede exceder los 100 caracteres.");
        }
    }
}
