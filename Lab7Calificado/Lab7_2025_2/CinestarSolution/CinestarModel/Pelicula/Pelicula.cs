using System;
using System.Collections.Generic;
using System.Text;

namespace CinestarModel.Pelicula
{
    public class Pelicula
    {
        private int _idPelicula;
        private String _nombrePelicula;
        private GeneroPelicula _generoPelicula;

        public Pelicula() { }

        public Pelicula(int idPelicula, string nombrePelicula, GeneroPelicula generoPelicula)
        {
            IdPelicula = idPelicula;
            NombrePelicula = nombrePelicula;
            GeneroPelicula = generoPelicula;
        }

        public int IdPelicula { get => _idPelicula; set => _idPelicula = value; }
        public string NombrePelicula { get => _nombrePelicula; set => _nombrePelicula = value; }
        public GeneroPelicula GeneroPelicula { get => _generoPelicula; set => _generoPelicula = value; }
    }
}
