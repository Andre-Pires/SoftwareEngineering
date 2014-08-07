package pt.ist.rest.exception.core.duplicatekey;

import pt.ist.rest.exception.core.DuplicateKey;

/**
 * Excepção que indica que o nome já existe.
 */
public class NameAlreadyTakenException extends
        DuplicateKey {

    private static final long serialVersionUID = 1L;

    private String _key;

    public NameAlreadyTakenException(String key) {
        _key = key;
    }

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        return "O Nome '" + _key + "' já existe.";
    }
}
