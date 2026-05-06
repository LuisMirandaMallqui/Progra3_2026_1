namespace CinestarModel.Pelicula
{
    public enum GeneroPelicula
    {
        ACCION, CIENCIA_FICCION, ANIMACION, DRAMA
    }
}



/*
PARA CIENCIA FICCION en MYsql
string valorDb = "CIENCIA FICCION";

// Reemplazamos espacios y lo convertimos
Genero genero = (Genero)Enum.Parse(typeof(Genero), valorDb.Replace(" ", "_"), true);

Console.WriteLine(genero); // CienciaF 
*/