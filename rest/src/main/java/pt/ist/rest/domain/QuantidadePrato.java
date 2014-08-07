package pt.ist.rest.domain;

/**
 * Representa a quantidade de um prato que é comprada por um Cliente. Caracteriza a
 * ligação entre o Prato e a Compra.
 */
public class QuantidadePrato extends
        QuantidadePrato_Base {

    /**
     * Nova quantidade de prato a ser adicionada a uma compra.
     * 
     * @param quantidade Número de pratos que vao ser compradas.
     * @param p O prato a associar à compra.
     */
    public QuantidadePrato(int quantidade, Prato p) {
        super();
        this.setQuantidade(quantidade);
        this.setPrato(p);
    }

    /**
     * Preco total.
     * 
     * @return preco total do prato a encomendar, para uma dada compra.
     */
    public int getPrecoTotal() {
        return this.getPrato().getPreco() * this.getQuantidade();
    }


    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("Prato ->" + this.getPrato().getNome());
        string.append(" " + this.getPrato().getPreco() + "€");
        string.append(" ID ->" + this.getPrato().getId());
        string.append(" Quantidade -> " + this.getQuantidade());


        return string.toString();

    }

    public boolean equals(QuantidadePrato obj) {

        if (obj == null) {
            return false;
        }

        if (this.getQuantidade() == obj.getQuantidade() && this.getPrato().equals(obj.getPrato())
                && this.getCompra().getId() == obj.getCompra().getId()) {
            return true;
        }
        return false;

    }
    //CHECKSTYLE:ON
}
