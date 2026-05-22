namespace TestSoftPersistance.DAO
{
    public interface IDAO<T>
    {
        int Insertar(T objeto);
        int Modificar(T objeto);
        int Eliminar(int id);
        T BuscarPorId(int id);
        List<T> ListarTodos();
    }
}
