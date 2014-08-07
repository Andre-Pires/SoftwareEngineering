package pt.ist.rest.exception.core;

import pt.ist.rest.exception.CoreException;

/**
 * Classe principal de excepções cujo valor já existe.
 */
public class DuplicateKey extends
        CoreException {

    private static final long serialVersionUID = 1L;

    public DuplicateKey() {
    }

    public DuplicateKey(String msg) {
        super(msg);
    }

}
