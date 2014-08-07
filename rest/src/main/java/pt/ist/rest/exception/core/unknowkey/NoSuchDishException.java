package pt.ist.rest.exception.core.unknowkey;

import pt.ist.rest.exception.core.UnknownKey;

/**
 * Excepção que indica que o cliente não existe.
 */
public class NoSuchDishException extends
        UnknownKey {

    private static final long serialVersionUID = 1L;

    private String _key;

    public NoSuchDishException(String key) {
        _key = key;
    }

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        return "O Prato '" + _key + "' não existe.";
    }
}
