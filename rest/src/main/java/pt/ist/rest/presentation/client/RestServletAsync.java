package pt.ist.rest.presentation.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;
import pt.ist.rest.service.dto.PortalDeRestaurantesDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;
import pt.ist.rest.service.dto.SearchDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RestServletAsync {
    void initServer(boolean local, AsyncCallback<Void> callback);

    void login(ClienteDto cliente, AsyncCallback<Void> callback) throws IllegalArgumentException;

    void obterRestaurantes(AsyncCallback<PortalDeRestaurantesDto> callback);

    void obterMenu(RestauranteDto rest, AsyncCallback<Collection<PratoDto>> callback);

    void obterTabuleiro(ClienteDto cliente, AsyncCallback<ArrayList<ItemDto>> asyncCallback);

    void alterarQuantidade(ItemDto item, ClienteDto cliente, AsyncCallback<Void> asyncCallback);

    void pagarTabuleiro(List<String> cheques,
                        ClienteDto cliente,
                        AsyncCallback<List<Integer>> asyncCallback);

    void adicionaPratoTabuleiro(ItemDto item, ClienteDto cliente, AsyncCallback<Void> asyncCallback);

    void obterPesquisa(ArrayList<SearchDto> pesquisa,
                       AsyncCallback<ArrayList<PratoDto>> asyncCallback);

    void trocarImplementacao(boolean local, AsyncCallback<Void> callback);
}
