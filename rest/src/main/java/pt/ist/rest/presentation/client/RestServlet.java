package pt.ist.rest.presentation.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pt.ist.rest.exception.core.invalidkey.InvalidPasswordException;
import pt.ist.rest.exception.core.unknowkey.NoSuchClienteException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;
import pt.ist.rest.service.dto.PortalDeRestaurantesDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;
import pt.ist.rest.service.dto.SearchDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface RestServlet
        extends RemoteService {
    void initServer(boolean local);

    void login(ClienteDto cliente) throws InvalidPasswordException, NoSuchClienteException;

    void alterarQuantidade(ItemDto item, ClienteDto cliente);

    List<Integer> pagarTabuleiro(List<String> cheques, ClienteDto cliente);

    void adicionaPratoTabuleiro(ItemDto item, ClienteDto cliente);

    PortalDeRestaurantesDto obterRestaurantes();

    Collection<PratoDto> obterMenu(RestauranteDto rest);

    ArrayList<ItemDto> obterTabuleiro(ClienteDto cliente);

    ArrayList<PratoDto> obterPesquisa(ArrayList<SearchDto> pesquisa);

    void trocarImplementacao(boolean local);

}
