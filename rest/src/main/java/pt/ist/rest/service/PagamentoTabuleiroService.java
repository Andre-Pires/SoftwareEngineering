package pt.ist.rest.service;

import java.util.List;

import jvstm.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.exception.RegistoFaturaException;
import pt.ist.rest.exception.core.unknowkey.NoSuchClienteException;
import pt.ist.rest.service.dto.ClienteDto;

public class PagamentoTabuleiroService extends
        RestService {

    private final ClienteDto cliente;

    private List<Integer> id;

    /**
     * 
     */
    public PagamentoTabuleiroService(ClienteDto c) {
        this.cliente = c;
    }

    /**
     * Efectua o pagamento de uma compra.
     * 
     * @throws NoSuchClienteException quando o cliente não existe.
     * @throws RegistoFaturaException
     * @see pt.ist.rest.service.RestService#dispatch()
     */
    @Override
    public void dispatch() throws NoSuchClienteException, RegistoFaturaException {
        PortalDeRestaurantes p = FenixFramework.getRoot();
        Cliente c = p.getCliente(cliente.getUser());
        if (c == null) {
            throw new NoSuchClienteException(cliente.getUser());
        }

        id = p.emitirFatura(c);
        System.out.println("Emitiu a fatura. A finalizar a compra...");
        c.finalizarCompra();
        System.out.println("Finalizou a compra...");
    }

    @Atomic
    public List<Integer> getId() {
        return id;
    }
}
