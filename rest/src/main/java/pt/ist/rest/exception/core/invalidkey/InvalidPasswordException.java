package pt.ist.rest.exception.core.invalidkey;

import pt.ist.rest.exception.CoreException;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Excepção que indica que a password é inválida.
 */
public class InvalidPasswordException extends
        CoreException
        implements IsSerializable {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        return "A password inserida é inválida.";
    }
}
