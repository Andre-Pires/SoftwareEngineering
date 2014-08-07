package pt.ist.rest.exception.core.invalidkey;

import pt.ist.rest.exception.CoreException;

/**
 * Excepção que indica que o saldo do cliente é negativo.
 */
public class InvalidCreditException extends
        CoreException {

    private int saldo;
    private static final long serialVersionUID = 1L;

    public InvalidCreditException() {
        // Existe para ser serializavel
    }

    public InvalidCreditException(int saldo) {
        this.saldo = saldo;
    }

    @Override
    public String getMessage() {
        return "O crédito é negativo ao actualizar: " + saldo + " .";
    }


}
