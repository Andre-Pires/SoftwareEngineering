package pt.ist.rest.exception;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * Classe principal das excepções. Todas as outras herdam desta.
 */
public class CoreException extends
        RuntimeException implements IsSerializable {

    private static final long serialVersionUID = 1L;

    public CoreException() {
    }

    public CoreException(String msg) {
        super(msg);
    }

}
