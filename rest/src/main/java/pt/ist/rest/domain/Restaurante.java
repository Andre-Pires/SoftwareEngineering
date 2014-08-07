package pt.ist.rest.domain;

import java.util.ArrayList;

import pt.ist.rest.exception.core.MaxPriceExceededException;


/**
 * A classe Restaurante
 */
public class Restaurante extends
        Restaurante_Base {

    /**
     * Instancia um novo restaurante.
     * 
     * @param nome nome do Restaurante
     * @param morada morada do Restaurante
     * @param gestor Um Gestor que fica associado ao restaurante.
     */
    public Restaurante(String nome, String morada, Gestor gestor) {
        super();
        setNome(nome);
        setMorada(morada);
        addGestor(gestor);
    }

    /**
     * Rating.
     * 
     * @return O rating do restaurante. Caso nao tenha um minimo de 3 pratos retorna 0.
     */
    public float rating() {
        final int minPratos = 3;
        if (this.getPrato().size() >= minPratos) {
            float total = 0;
            int numPratos = 0;

            for (Prato p : getPrato()) {
                if (p.getRating() >= 1) {
                    numPratos++;
                }
                total += p.getRating();
            }

            if (numPratos >= minPratos) {
                return total / getPrato().size();
            }
        }

        return 0;
    }


    /**
     * Adiciona prato.
     * 
     * @param nome nome do prato que vai ser adicionado ao restaurante.
     * @param alimentos lista de ingredientes do prato que vai ser adicionado.
     */
    public void adicionaPrato(String nome, int preco, int calorias, ArrayList<Alimento> alimentos) throws MaxPriceExceededException {

        if (this.getPortal().getPrecoMaximo() == -1 || preco <= this.getPortal().getPrecoMaximo()) {
            this.addPrato(new Prato(getPortal().novoIdPrato(), nome, preco, calorias, alimentos));
        } else {
            throw new MaxPriceExceededException();
        }

    }

    /**
     * Retorna um prato com um dado nome.
     * 
     * @param nome , nome do prato a procurar
     * @return O prato com o nome passado, caso nao exista retorna null.
     */
    public Prato getPrato(String nome) {
        for (Prato p : this.getPrato()) {
            if (nome.equals(p.getNome())) {
                return p;
            }
        }
        return null;
    }

    /*
     * Método toString 'overridden' para retornar uma String composta dos atributos do
     * restaurante e os seus pratos.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Restaurante ").append(this.getNome()).append(' ').append(this.getMorada());

        for (Prato p : this.getPrato()) {
            string.append("\n\t").append(p.getNome());
        }
        return string.toString();
    }


    public boolean equals(Restaurante obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Restaurante)) {
            return false;
        }

        if (this.getNome().equals(obj.getNome())) {

            /* debug code */
            assert this.getMorada().equals(obj.getMorada()) : "@equals Restaurante: nome igual mas morada diferente: \n\t"
                    + '('
                    + this.getNome()
                    + " - "
                    + this.getMorada()
                    + '/'
                    + obj.getMorada()
                    + ")\n";
            assert this.getGestor().equals(obj.getGestor()) : "@equals Restaurante: nome igual mas lista gestor diferente: \n\t"
                    + '('
                    + this.getNome()
                    + " - "
                    + this.getGestor().toString()
                    + '/'
                    + obj.getGestor().toString() + ")\n";
            assert this.getPrato().equals(obj.getPrato()) : "@equals Restaurante: nome igual mas lista pratos diferente: \n\t"
                    + '('
                    + this.getNome()
                    + " - "
                    + this.getPrato().toString()
                    + '/'
                    + obj.getPrato().toString() + ")\n";

            /* end of debug code */

            return true;
        }
        return false;
    }

}
