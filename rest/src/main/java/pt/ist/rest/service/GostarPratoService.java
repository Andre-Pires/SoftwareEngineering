package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.exception.core.unknowkey.NoSuchClienteException;
import pt.ist.rest.exception.core.unknowkey.NoSuchDishException;
import pt.ist.rest.exception.core.unknowkey.NoSuchRestauranteException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;

public class GostarPratoService extends
        RestService {

    private final ClienteDto clienteDto;
    private final PratoDto pratoDto;
    private final RestauranteDto restauranteDto;

    public GostarPratoService(ClienteDto cliente, PratoDto prato, RestauranteDto restaurante) {
        this.clienteDto = cliente;
        this.pratoDto = prato;
        this.restauranteDto = restaurante;

    }

    /**
     * Executa a acção de um cliente gostar de um prato.
     * 
     * @throws NoSuchClienteException quando não existe cliente.
     * @throws NoSuchDishException quando o prato não existe.
     * @throws NoSuchRestauranteException quando o restaurante não existe.
     * @see pt.ist.rest.service.RestService#dispatch()
     */
    @Override
    public final void dispatch() throws NoSuchClienteException,
            NoSuchDishException,
            NoSuchRestauranteException {

        PortalDeRestaurantes portal = FenixFramework.getRoot();

        Cliente cliente = portal.getCliente(clienteDto.getUser());
        if (cliente == null) {
            throw new NoSuchClienteException(clienteDto.getUser());
        }
        Restaurante restaurante = portal.getRestaurante(restauranteDto.getNome());
        if (restaurante == null) {
            throw new NoSuchRestauranteException(restauranteDto.getNome());
        }
        Prato prato = restaurante.getPrato(pratoDto.getNome());
        if (prato == null) {
            throw new NoSuchDishException(pratoDto.getNome());
        }
        cliente.addPrato(prato);

    }
}
