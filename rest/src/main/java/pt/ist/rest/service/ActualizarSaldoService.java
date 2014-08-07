package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.exception.core.unknowkey.NoSuchClienteException;
import pt.ist.rest.service.dto.ClienteDto;

public class ActualizarSaldoService extends
        RestService {

    private final ClienteDto clientDto;
    private final int valor;

    public ActualizarSaldoService(int valor, ClienteDto cliente) {
        this.clientDto = cliente;
        this.valor = valor;
    }

    /**
     * Substrai ao saldo do cliente o valor dado.
     * 
     * @throws NoSuchClienteException quando o cliente não existe.
     * @see pt.ist.rest.service.RestService#dispatch()
     */
    @Override
    public final void dispatch() throws NoSuchClienteException {

        PortalDeRestaurantes portal = FenixFramework.getRoot();
        Cliente cliente = portal.getCliente(clientDto.getUser());

        if (cliente == null) {
            throw new NoSuchClienteException(clientDto.getUser());
        }

        cliente.actSaldo(valor);
        System.out.println("O saldo do cliente: " + cliente.getSaldo());

    }
}
