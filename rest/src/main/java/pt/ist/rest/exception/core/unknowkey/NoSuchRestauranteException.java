package pt.ist.rest.exception.core.unknowkey;

import pt.ist.rest.exception.core.UnknownKey;

/**
 * Excepção que indica que o cliente não existe.
 */
public class NoSuchRestauranteException extends
        UnknownKey {

    private static final long serialVersionUID = 1L;

    private String _key;

    public NoSuchRestauranteException(String key) {
        _key = key;
    }

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        return "O Restaurante '" + _key + "' não existe.";
    }
}
