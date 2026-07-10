namespace PokemonWebViewer.Code.Managers;

// ── PASO 1 del flujo del FRONT: EL CONTRATO ────────────────────────────────────
// Home.razor depende de ESTA interfaz, no de una clase concreta. Así la vista no
// sabe (ni le importa) de dónde salen los datos. Hay dos implementaciones:
//   • PokemonWSManager       → consume el Web Service SOAP  ← la que pide el lab
//   • PokemonMemoryManager   → lee un CSV local            ← alternativa, no usada
public interface PokemonManager
{
    int GetTotalCount();       // total de pokémon disponibles
    Pokemon GetPokemon(int index);   // un pokémon por posición (0 .. total-1)
}
