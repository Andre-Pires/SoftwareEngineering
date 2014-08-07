package pt.ist.rest.domain;


/**
 * A classe Alimento.
 */
public class Alimento extends
        Alimento_Base {

    /**
     * Instancia um novo alimento.
     * 
     * @param nome nome do alimento
     * @param tipo tipo de alimento
     */
    public Alimento(String nome, String tipo) {
        super();
        this.setTipo(tipo);
        this.setDescricao(nome);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Alimento ").append(this.getDescricao()).append(' ').append(this.getTipo());
        return string.toString();
    }

    //CHECKSTYLE:OFF para evitar queixar-se de não fazer override ao equals de Object
    public boolean equals(Alimento obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Alimento)) {
            return false;
        }
        if (this.getDescricao().equals(obj.getDescricao())) {
            /*debug code*/
            assert this.getTipo().equals(obj.getTipo()) : "@equals Alimento: descricao igual mas tipo diferente: \n\t"
                    + '('
                    + this.getDescricao()
                    + " - "
                    + this.getTipo()
                    + '/'
                    + obj.getTipo()
                    + ")\n";
            /*end of debug code*/
            return true;
        }
        return false;
    }
    //CHECKSTYLE:ON
}
