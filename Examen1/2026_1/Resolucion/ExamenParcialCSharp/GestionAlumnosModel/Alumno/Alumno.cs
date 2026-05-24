namespace GestionAlumnosModel.Alumno
{
    public class Alumno
    {

        private int id;
        private String codigo;
        private String nombre;
        private String apellidos;
        private String correo;
        private char estado;

        public int Id { get => id; set => id = value; }
        public string Codigo { get => codigo; set => codigo = value; }
        public string Nombre { get => nombre; set => nombre = value; }
        public string Apellidos { get => apellidos; set => apellidos = value; }
        public string Correo { get => correo; set => correo = value; }
        public char Estado { get => estado; set => estado = value; }
    }
}
