package pt.ist.rest.exception.core.invalidkey;

import pt.ist.rest.exception.core.InvalidKeyException;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Excepção que indica que não se pode adicionar um prato ao tabuleiro com quantidade
 * negativa.
 */
public class InvalidDishException extends
        InvalidKeyException
        implements IsSerializable {

    private static final long serialVersionUID = 1L;
    private String _key = null;
    private String message = null;

    public InvalidDishException(String key) {
        _key = key;
    }

    public InvalidDishException(String key, String message) {
        this(key);
        this.message = message;
    }

    public InvalidDishException() {
        //Tem de existir para ser serializavel.
    }

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        if (message == null) {
            return "O prato '"
                    + _key
                    + "' não existe no carrinho de compras, para adicionar um prato no carrinho, insira uma quantidade positiva.";
        } else {
            return "" + _key + "-> " + message;
        }
    }
}
