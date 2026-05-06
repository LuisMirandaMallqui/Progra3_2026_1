using System;
using System.Collections.Generic;
using System.Text;

namespace PokeSoftModel
{
    public class PokemonDTO
    {
        private int _idRaw;
        private String? _nombrePokemon;
        private double _altura;
        private double _peso;
        private EstadoEvolutivo _estadoEvolutivo;
        private String? _nombreTipo;
        private String? _descripcionPokemon;

        public PokemonDTO() { }
        public PokemonDTO(int idRaw, string? nombrePokemon, double altura, double peso, EstadoEvolutivo estadoEvolutivo, string? nombreTipo, string? descripcionPokemon)
        {
            IdRaw = idRaw;
            NombrePokemon = nombrePokemon;
            Altura = altura;
            Peso = peso;
            EstadoEvolutivo = estadoEvolutivo;
            NombreTipo = nombreTipo;
            DescripcionPokemon = descripcionPokemon;
        }
        public PokemonDTO(string? nombrePokemon, double altura, double peso, EstadoEvolutivo estadoEvolutivo, string? nombreTipo, string? descripcionPokemon)
        {
            NombrePokemon = nombrePokemon;
            Altura = altura;
            Peso = peso;
            EstadoEvolutivo = estadoEvolutivo;
            NombreTipo = nombreTipo;
            DescripcionPokemon = descripcionPokemon;
        }

        public int IdRaw { get => _idRaw; set => _idRaw = value; }
        public string? NombrePokemon { get => _nombrePokemon; set => _nombrePokemon = value; }
        public double Altura { get => _altura; set => _altura = value; }
        public double Peso { get => _peso; set => _peso = value; }
        public EstadoEvolutivo EstadoEvolutivo { get => _estadoEvolutivo; set => _estadoEvolutivo = value; }
        public string? NombreTipo { get => _nombreTipo; set => _nombreTipo = value; }
        public string? DescripcionPokemon { get => _descripcionPokemon; set => _descripcionPokemon = value; }
    }
}
