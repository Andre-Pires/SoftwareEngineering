package pt.ist.rest.exception;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RegistoFaturaException extends
        RuntimeException
        implements IsSerializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RegistoFaturaException() {
        // Necessário para ser serializável.
    }

    public RegistoFaturaException(String message) {
        super(message);
    }
}
