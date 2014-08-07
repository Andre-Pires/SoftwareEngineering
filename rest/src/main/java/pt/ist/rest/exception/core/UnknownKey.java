package pt.ist.rest.exception.core;

import pt.ist.rest.exception.CoreException;

/**
 * Classe principal de excepções cujo valor não existe.
 */
public class UnknownKey extends
        CoreException {

    private static final long serialVersionUID = 1L;

    public UnknownKey() {
    }

    public UnknownKey(String msg) {
        super(msg);
    }

}
