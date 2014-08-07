package pt.ist.rest.exception.core.unknowkey;

import pt.ist.rest.exception.core.UnknownKey;

/**
 * Excepção que indica que o cliente não existe.
 */
public class NoSuchManagerException extends
        UnknownKey {

    private static final long serialVersionUID = 1L;

    private String _key;

    public NoSuchManagerException(String key) {
        _key = key;
    }

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        return "O Gestor '" + _key + "' não existe.";
    }
}
