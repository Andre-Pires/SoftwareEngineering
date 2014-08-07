package pt.ist.rest.exception.core.invalidkey;

import pt.ist.rest.exception.core.InvalidKeyException;

/**
 * Excepção que indica falta de saldo para efectuar pagamento.
 */
public class InsuficientCreditException extends
        InvalidKeyException {

    private static final long serialVersionUID = 1L;
    private final int _saldo;
    private final int _preco;

    public InsuficientCreditException(int saldo, int preco) {
        _saldo = saldo;
        _preco = preco;
    }

    @Override
    @SuppressWarnings("nls")
    public String getMessage() {
        return "Não tem saldo suficiente para efectuar a compra. Saldo -> " + _saldo + "Preço -> "
                + _preco;
    }
}
