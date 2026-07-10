namespace FifaSoftWA.Components.Servicios
{
    public static class DatosPrueba
    {
        public static List<Seleccion> ObtenerSelecciones()
        {
            return new()
        {
            new Seleccion
            {
                IdSeleccion = 1,
                Nombre = "MÉXICO",
                Confederacion = "CONCACAF",
                Grupo = 'A',   // char, NO string (comillas simples)
                RankingFIFA = 15,
                UrlBandera = "https://flagcdn.com/w320/mx.png",
                Clasificado = true,
                DirectorTecnico = new DirectorTecnico
                {
                    IdDirectorTecnico = 1,
                    Nombre = "Javier Aguirre",
                    Nacionalidad = "México",
                    Edad = 67
                }
            },
            new Seleccion
            {
                IdSeleccion = 2,
                Nombre = "BRASIL",
                Confederacion = "CONMEBOL",
                Grupo = 'C',
                RankingFIFA = 5,
                UrlBandera = "https://flagcdn.com/w320/br.png",
                Clasificado = true,
                DirectorTecnico = new DirectorTecnico
                {
                    IdDirectorTecnico = 9,
                    Nombre = "Dorival Júnior",
                    Nacionalidad = "Brasil",
                    Edad = 63
                }
            },
            new Seleccion
            {
                IdSeleccion = 3,
                Nombre = "ARGENTINA",
                Confederacion = "CONMEBOL",
                Grupo = 'J',
                RankingFIFA = 1,
                UrlBandera = "https://flagcdn.com/w320/ar.png",
                Clasificado = true,
                DirectorTecnico = new DirectorTecnico
                {
                    IdDirectorTecnico = 37,
                    Nombre = "Lionel Scaloni",
                    Nacionalidad = "Argentina",
                    Edad = 48
                }
            }
        };
        }
    }
}
