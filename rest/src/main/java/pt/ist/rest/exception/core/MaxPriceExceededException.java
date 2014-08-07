package pt.ist.rest.exception.core;

import pt.ist.rest.exception.CoreException;

public class MaxPriceExceededException extends
        CoreException {


    private static final long serialVersionUID = 1L;

    public MaxPriceExceededException() {
    }

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        return "Preço máximo do Portal de Restaurantes excedido.";
    }
}
