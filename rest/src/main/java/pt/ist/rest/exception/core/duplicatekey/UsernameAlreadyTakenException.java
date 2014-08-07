package pt.ist.rest.exception.core.duplicatekey;

import pt.ist.rest.exception.core.DuplicateKey;

/**
 * Excepção que indica que o nome de utilizador já existe.
 */
public class UsernameAlreadyTakenException extends
        DuplicateKey {

    private static final long serialVersionUID = 1L;

    private String _key;

    public UsernameAlreadyTakenException(String key) {
        _key = key;
    }

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        return "O Username '" + _key + "' já existe.";
    }
}
