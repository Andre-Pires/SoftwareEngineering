package pt.ist.rest.service;

import java.util.List;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Compra;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.domain.QuantidadePrato;
import pt.ist.rest.exception.core.unknowkey.NoSuchClienteException;
import pt.ist.rest.service.dto.ClienteDto;

public class EsvaziaTabuleiroComprasService extends
        RestService {

    private final ClienteDto clienteDto;

    public EsvaziaTabuleiroComprasService(ClienteDto cliente) {
        this.clienteDto = cliente;
    }

    /** Esvazia tabuleiro de compras.
     * 
     * @throws NoSuchClienteException quando o cliente não existe.
     * @see pt.ist.rest.service.RestService#dispatch()
     */
    @Override
    public final void dispatch() throws NoSuchClienteException {

        PortalDeRestaurantes portal = FenixFramework.getRoot();

        Cliente cliente = portal.getCliente(clienteDto.getUser());

        if (cliente == null) {
            throw new NoSuchClienteException(clienteDto.getUser());
        }

        Compra compra = cliente.getTabuleiro();

        List<QuantidadePrato> listaQPratos = compra.getQuantidadePrato();

        for (QuantidadePrato qp : listaQPratos) {
            compra.removeQuantidadePrato(qp);
        }
    }
}
