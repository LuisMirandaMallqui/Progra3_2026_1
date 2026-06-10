namespace GestionAlumnosBusiness.BO
{
    public interface IBaseBO<T>
    {
        int Insertar(T objeto);
        int Modificar(T objeto);
        int Eliminar(int id);
        T BuscarPorId(int id);
        List<T> ListarTodos();
    }
}
