package pe.edu.pucp.pokemones.pokews;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

import pe.edu.pucp.pokemones.bo.TipoPokemonBO;
import pe.edu.pucp.pokemones.bo.implementsBO.TipoPokemonImplementsBO;
import pe.edu.pucp.pokemones.model.TipoPokemon;

// NOTA: sin @WebParam (arg0) para coincidir con el proxy ya generado del front.
@WebService(
        serviceName = "TipoPokemonWS",
        targetNamespace = "http://services.pokemones.pucp.edu.pe/"
)
public class TipoPokemonWS {

    private final TipoPokemonBO tipoPokemonBO;

    public TipoPokemonWS() {
        this.tipoPokemonBO = new TipoPokemonImplementsBO();
    }

    @WebMethod(operationName = "listarTiposPokemon")
    public List<TipoPokemon> listarTiposPokemon() {
        return tipoPokemonBO.listarTodos();
    }

    @WebMethod(operationName = "buscarTipoPokemonPorId")
    public TipoPokemon buscarTipoPokemonPorId(int idTipoPokemon) {
        return tipoPokemonBO.buscarPorId(idTipoPokemon);
    }

    @WebMethod(operationName = "insertarTipoPokemon")
    public int insertarTipoPokemon(TipoPokemon tipoPokemon) {
        return tipoPokemonBO.insertar(tipoPokemon);
    }

    // AGREGADO EN LA MEJORA: CRUD completo por SOAP.
    @WebMethod(operationName = "modificarTipoPokemon")
    public int modificarTipoPokemon(TipoPokemon tipoPokemon) {
        return tipoPokemonBO.modificar(tipoPokemon);
    }

    @WebMethod(operationName = "eliminarTipoPokemon")
    public int eliminarTipoPokemon(int idTipoPokemon) {
        return tipoPokemonBO.eliminar(idTipoPokemon);
    }
}
