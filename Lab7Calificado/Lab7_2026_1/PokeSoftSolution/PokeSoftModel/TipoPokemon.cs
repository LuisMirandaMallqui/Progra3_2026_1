using System;
using System.Collections.Generic;
using System.Text;

namespace PokeSoftModel
{
    public class TipoPokemon
    {
        private int _idTipoPokemon;
        private String? _nombre;
        public TipoPokemon() { }
        public TipoPokemon(String? nombre)
        {
            _nombre = nombre;
        }
        public TipoPokemon(int idTipoPokemon, String? nombre)
        {
            _idTipoPokemon = idTipoPokemon;
            _nombre = nombre;
        }
        public int IdTipoPokemon { get => _idTipoPokemon; set => _idTipoPokemon = value; }
        public String? Nombre { get => _nombre; set => _nombre = value; }
    }
}
