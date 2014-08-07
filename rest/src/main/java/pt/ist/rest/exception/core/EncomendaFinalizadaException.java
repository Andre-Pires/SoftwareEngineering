package pt.ist.rest.exception.core;

import pt.ist.rest.exception.CoreException;


/**
 * Excepção que indica que não se pode finalizar encomenda.
 */
public class EncomendaFinalizadaException extends
        CoreException {

    private static final long serialVersionUID = 1L;

    public EncomendaFinalizadaException() {
    }

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        return "Não é possível finalizar uma encomenda previamente finalizada.";
    }
}
