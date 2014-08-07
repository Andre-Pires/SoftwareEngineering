package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.exception.core.invalidkey.InvalidPasswordException;
import pt.ist.rest.exception.core.unknowkey.NoSuchClienteException;
import pt.ist.rest.service.dto.ClienteDto;

public class VerificarPasswordService extends
        RestService {

    private final ClienteDto clienteDto;

    public VerificarPasswordService(ClienteDto cliente) {
        this.clienteDto = cliente;
    }
    
    /**
     * Verifica os dados de entrada de um utilizador - nome e password.
     * 
     * @throws NoSuchClienteException caso o utilizador não esteja registado no sistema.
     * @throws InvalidPasswordException caso a password introduzida não corresponda à
     *             indicada.
     *             
     * @see pt.ist.rest.service.RestService#dispatch()
     */
    @Override
    public void dispatch() throws NoSuchClienteException, InvalidPasswordException {
        PortalDeRestaurantes portal = FenixFramework.getRoot();

        final Cliente c = portal.getCliente(clienteDto.getUser());
        if (c == null) {
            throw new NoSuchClienteException(clienteDto.getUser());
        }

        final boolean passwordValida = c.getPassword().equals(clienteDto.getPass());
        if (!passwordValida) {
            throw new InvalidPasswordException();
        }

    }

}
