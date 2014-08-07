package pt.ist.rest.exception.core.unknowkey;

import pt.ist.rest.exception.core.UnknownKey;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Excepção que indica que o cliente não existe.
 */
public class NoSuchTypeException extends
        UnknownKey
        implements IsSerializable {

    private static final long serialVersionUID = 1L;

    private String _key;

    public NoSuchTypeException(String key) {
        _key = key;
    }

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        return "O tipo '" + _key + "' não existe.";
    }

}
