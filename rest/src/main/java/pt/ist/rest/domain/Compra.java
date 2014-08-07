package pt.ist.rest.domain;

import pt.ist.rest.exception.core.invalidkey.InvalidDishException;

/**
 * Mantém o conjunto dos pratos encomendados por um cliente. Pode ser usada como um
 * registo de de uma determinada compra já feita (paga) por um cliente ou como tabuleiro
 * de compras, onde o cliente ainda não pagou o que pretende encomendar e pode alterar os
 * pratos já adicionados ao tabuleiro ou acrescentar novos. Cada compra possui um
 * identificador único.
 */
public class Compra extends
        Compra_Base {

    /**
     * Cria uma nova compra sem pratos associados à sua compra.
     * 
     * @param id O id da compra.
     */
    public Compra(int id) {
        super();
        this.setId(id);
    }

    /**
     * Checks if is finalizada.
     * 
     * @return true, if is finalizada
     */
    public boolean isFinalizada() {
        return getIsFinalizada();
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.ist.rest.domain.Compra_Base#getTotalCompra()
     */
    @Override
    public int getTotalCompra() {
        if (this.isFinalizada()) {
            return super.getTotalCompra();
        } else {
            int total = 0;
            for (QuantidadePrato qP : this.getQuantidadePrato()) {
                total += qP.getPrecoTotal();
            }
            return total;
        }
    }

    /**
     * Finaliza a encomenda.
     */
    public void finalizaEncomenda() {
        if (!isFinalizada()) {
            int total = 0;
            for (QuantidadePrato qp : this.getQuantidadePrato()) {
                total += qp.getPrecoTotal();
            }

            this.setTotalCompra(total);
            this.setIsFinalizada(true);
        }
    }

    /**
     * Auxilia a alteração da quantidade de um prato dentro do tabuleiro. Procura e
     * retorna a QuantidadePrato do tabuleiro de compras para alterar a quantidade do
     * prato.
     * 
     * @param p O prato do tabuleiro do qual se quer encontrar a QuantidadePrato.
     * @return A QuantidadePrato associada ao prato passado. Caso não encontre, retorna
     *         null e não lança uma excepção por não ter sido encontrado.
     */
    private QuantidadePrato findQuantidadePrato(Prato p) {
        if (hasAnyQuantidadePrato()) {
            for (QuantidadePrato q : getQuantidadePrato()) {
                if (q.getPrato().equals(p)
                        && q.getPrato().getRestaurante().getNome()
                                .equals(p.getRestaurante().getNome())) {
                    return q;
                }
            }
        }

        return null;
    }


    /**
     * Compra a quantidade recebida do prato indicado. Verifica se o prato já existe no
     * tabuleiro e, caso exista, limita-se a alterar a quantidade encomendada. Caso
     * contrário, acrescenta o prato com a quantidade indicada ao tabuleiro.
     * 
     * @param quantPrato Quantidade do Prato a adicionar à compra
     */
    @Override
    public void addQuantidadePrato(QuantidadePrato quantPrato) {
        final QuantidadePrato qp = findQuantidadePrato(quantPrato.getPrato());
        if (qp == null) {
            if (quantPrato.getQuantidade() <= 0) {
                throw new InvalidDishException(
                        "Só pode adicionar um novo prato com quantidade positiva ao tabuleiro");
            }
            super.addQuantidadePrato(quantPrato);

        } else {
            if (quantPrato.getQuantidade() > 0) {
                assert qp.getQuantidade() < Integer.MAX_VALUE - qp.getQuantidade() : "quantidade deu overflow de inteiros";
            } else {
                assert qp.getQuantidade() > Integer.MIN_VALUE - qp.getQuantidade() : "quantidade deu underflow de inteiros";
            }


            qp.setQuantidade(qp.getQuantidade() + quantPrato.getQuantidade());
            if (qp.getQuantidade() < 1) {
                removeQuantidadePrato(qp);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.ist.fenixframework.pstm.AbstractDomainObject#toString()
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (QuantidadePrato qp : this.getQuantidadePrato()) {
            string.append(qp.toString());
        }
        string.append("\n\t\t\t");
        string.append("Preço total -> " + this.getTotalCompra());
        string.append(" Saldo disponível -> " + this.getCliente().getSaldo());
        return string.toString();
    }

    //CHECKSTYLE:OFF para evitar queixar-se de não fazer override ao equals de Object e de não usar hashcodes
    /**
     * Equals.
     * 
     * @param obj the obj
     * @return true, if successful
     */
    public boolean equals(Compra obj) {
        if (obj == null) {
            return false;
        }

        if (this.getId() == obj.getId()) {

            final Cliente thisC = this.getCliente();
            final Cliente otherC = obj.getCliente();

            assert this.getIsFinalizada() == obj.getIsFinalizada() : "@equals Compra: id igual mas finalizada diferente: \n\t"
                    + '('
                    + this.getId()
                    + " - "
                    + this.getIsFinalizada()
                    + '/'
                    + obj.getIsFinalizada() + ")\n";
            assert thisC.equals(otherC) : "@equals Compra: id igual mas cliente diferente: \n\t"
                    + '(' + this.getId() + " - " + thisC.toString() + '/' + otherC.toString()
                    + ")\n";
            assert this.getQuantidadePrato().equals(obj.getQuantidadePrato()) : "@equals Compra: id igual mas lista quantidadePrato diferente: \n\t"
                    + '('
                    + this.getId()
                    + " - "
                    + this.getQuantidadePrato().toString()
                    + '/'
                    + obj.getQuantidadePrato().toString() + ")\n";

            return true;
        }

        return false;
    }

}
