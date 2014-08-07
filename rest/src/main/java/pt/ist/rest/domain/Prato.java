package pt.ist.rest.domain;

import java.util.ArrayList;


/**
 * A classe Prato.
 */
public class Prato extends
        Prato_Base {

    /**
     * Instancia um novo prato.
     * 
     * @param id atribuido pelo portal de restaurantes
     * @param nome nome do prato
     * @param alimentos lista de ingredientes do prato
     */
    public Prato(int id, String nome, ArrayList<Alimento> alimentos) {
        for (Alimento a : alimentos) {
            this.addAlimento(a);
        }
        this.setNome(nome);
        this.setId(id);
    }

    /**
     * Instancia um novo prato.
     * 
     * @param id atribuido pelo portal de restaurantes
     * @param nome nome do prato
     * @param preco preco do prato
     * @param calorias valor energetico do prato
     * @param alimentos lista de ingredientes do prato
     */
    public Prato(int id, String nome, int preco, int calorias, ArrayList<Alimento> alimentos) {
        this(id, nome, alimentos);
        this.setPreco(preco);
        this.setCalorias(calorias);
    }

    /**
     * Rating.
     * 
     * @return Retorna o rating, calculado pelo numero de clientes que gostam do prato.
     */
    public int getRating() {
        return this.getCliente().size();
    }

    public boolean isVegetal() {
        for (Alimento a : this.getAlimento()) {
            if (a.getTipo().equals("Peixe") || a.getTipo().equals("Carne")) {
                return false;
            }
        }
        return true;
    }

    public boolean isPeixe() {
        for (Alimento a : this.getAlimento()) {
            if (a.getTipo().equals("Peixe")) {
                return true;
            }
        }
        return false;
    }

    public boolean isCarne() {
        for (Alimento a : this.getAlimento()) {
            if (a.getTipo().equals("Carne")) {
                return true;
            }
        }
        return false;
    }



    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Prato ").append(this.getNome()).append(' ').append(this.getPreco())
                .append(' ').append(this.getCalorias()).append(' ')
                .append(this.getRestaurante().toString());
        return string.toString();
    }

    //CHECKSTYLE:OFF 
    public boolean equals(Prato obj) {
        if (obj == null) {
            return false;
        }

        if (this.getNome().equals(obj.getNome())) {

            /* debug code */
            assert this.getPreco() == obj.getPreco() : "@equals Prato: nome igual mas preco diferente: \n\t"
                    + '(' + this.getNome() + " - " + this.getPreco() + '/' + obj.getPreco() + ")\n";
            assert this.getCalorias() == obj.getCalorias() : "@equals Prato: nome igual mas calorias diferente: \n\t"
                    + '('
                    + this.getNome()
                    + " - "
                    + this.getCalorias()
                    + '/'
                    + obj.getCalorias()
                    + ")\n";
            assert this.getRestaurante().equals(obj.getRestaurante()) : "@equals Prato: nome igual mas restaurante diferente: \n\t"
                    + '('
                    + this.getNome()
                    + " - "
                    + this.getRestaurante().getNome()
                    + '/'
                    + obj.getRestaurante().getNome() + ")\n";

            /* end of debug code */

            return true;
        }
        return false;
    }
    //CHECKSTYLE:ON
}
