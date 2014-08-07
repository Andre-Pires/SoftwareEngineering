package pt.ist.rest.exception.core;

import pt.ist.rest.exception.CoreException;

/**
 * Classe principal de excepções cujo valor é inválido.
 */
public class InvalidKeyException extends
        CoreException {

    private static final long serialVersionUID = 1L;

    public InvalidKeyException() {
    }

    public InvalidKeyException(String msg) {
        super(msg);
    }

}
