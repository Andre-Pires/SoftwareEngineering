package pt.ist.rest.exception.core.unknowkey;

import pt.ist.rest.exception.core.UnknownKey;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Excepção que indica que o cliente não existe.
 */
public class NoSuchClienteException extends
        UnknownKey
        implements IsSerializable {

    private static final long serialVersionUID = 1L;

    private String _key = null;

    public NoSuchClienteException() {
        // Objectos Serializáveis precisam do construtor vazio
    }

    public NoSuchClienteException(String key) {
        _key = key;
    }

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        if (_key != null) {
            return "O Cliente '" + _key + "' não existe.";
        }

        return "O cliente não existe";
    }
}
