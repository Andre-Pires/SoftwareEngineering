package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.exception.core.unknowkey.NoSuchClienteException;
import pt.ist.rest.exception.core.unknowkey.NoSuchDishException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;

/**
 * The Class OrderDishService.
 */
public class ComprarPratoService extends
        RestService {

    /**
     * The item.
     */
    private final ItemDto item;

    /**
     * The cliente.
     */
    private final ClienteDto cliente;

    /**
     * Cria um novo serviço que adiciona um item ao tabuleiro do cliente.
     * 
     * @param itDto item que se pretende adicionar
     * @param cDto cliente que pretende adicionar o item ao seu tabuleiro
     */
    public ComprarPratoService(ItemDto itDto, ClienteDto cDto) {
        this.item = itDto;
        this.cliente = cDto;
    }

    /**
     * Adiciona o Item passado ao construtor ao Cliente.
     * 
     * @throws NoSuchDishException quando o prato não existe.
     * @throws NoSuchClienteException quando o cliente não existe.
     * @see pt.ist.rest.service.RestService#dispatch()
     */
    @Override
    public void dispatch() throws NoSuchDishException, NoSuchClienteException {
        PortalDeRestaurantes pDR = FenixFramework.getRoot();
        Cliente c = (Cliente) pDR.getCliente(cliente.getUser());
        if (c == null) {
            throw new NoSuchClienteException(cliente.getUser());
        }
        Prato prato = pDR
                .getPrato(item.getPratoDto().getNome(), item.getRestauranteDto().getNome());
        if (prato == null) {
            throw new NoSuchDishException(item.getPratoDto().getNome());
        }
        c.comprarPrato(prato, this.item.getQuantidade());
    }
}
