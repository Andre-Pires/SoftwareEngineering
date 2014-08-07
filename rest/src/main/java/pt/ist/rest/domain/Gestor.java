package pt.ist.rest.domain;

import java.util.ArrayList;

import pt.ist.rest.exception.core.invalidkey.InvalidDishException;
import pt.ist.rest.exception.core.unknowkey.NoSuchRestauranteException;

/**
 * A classe Gestor.
 */
public class Gestor extends
        Gestor_Base {

    public Gestor(String user, String pass, String nome) {
        super();
        init(user, pass, nome);

    }

    /**
     * Adiciona um novo prato aos pratos disponiveis no restaurante, se possivel.
     * 
     * @param restaurante O restaurante ao qual vai ser adicionado o prato.
     * @param nome O nome do prato.
     * @param alimentos Os alimentos que compoem o prato.
     */
    public void adicionaPrato(Restaurante restaurante,
                              String nome,
                              int preco,
                              int calorias,
                              ArrayList<Alimento> alimentos) {
        final int maxPratos = 15;
        final int numPratosRest = this.getRestaurante().getPrato().size();
        if (hasRestaurante() && this.getRestaurante().equals(restaurante)
                && numPratosRest < maxPratos) {
            this.getRestaurante().adicionaPrato(nome, preco, calorias, alimentos);
        } else if (numPratosRest == 15) {
            throw new InvalidDishException(restaurante.getNome(),
                    "Já atingiu o número máximo de pratos para venda.");
        } else {
            throw new NoSuchRestauranteException(restaurante.getNome());
        }
    }

    /**
     * Remove o prato dos pratos disponiveis no restaurante, se existir.
     * 
     * @param restaurante O restaurante a procurar.
     * @param prato O prato a remover.
     */
    public void removePrato(Restaurante restaurante, final Prato prato) {
        if (hasRestaurante() && this.getRestaurante().equals(restaurante)) {
            this.getRestaurante().getPrato().remove(prato);
        }
    }

    /*
     * Método toString 'overridden' para retornar uma String composta dos atributos do
     * gestor.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Gestor ").append(this.getUsername()).append(' ').append(this.getPassword())
                .append(' ').append(this.getNome());
        return string.toString();
    }

    //CHECKSTYLE:OFF para evitar queixar-se de não fazer override ao equals de Object
    public boolean equals(Gestor obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Gestor)) {
            return false;
        }

        if (this.getUsername().equals(obj.getUsername())) {

            /* debug code */
            assert this.getPassword().equals(obj.getPassword()) : "@equals Gestor: username igual mas password diferente: \n\t"
                    + '('
                    + this.getUsername()
                    + " - "
                    + this.getPassword()
                    + '/'
                    + obj.getPassword() + ")\n";
            assert this.getNome().equals(obj.getNome()) : "@equals Gestor: username igual mas nome diferente: \n\t"
                    + '('
                    + this.getUsername()
                    + " - "
                    + this.getNome()
                    + '/'
                    + obj.getNome()
                    + ")\n";
            assert this.getRestaurante().toString().equals(obj.getRestaurante().toString()) : "@equals Gestor: username igual mas restaurante diferente: \n\t"
                    + '('
                    + this.getUsername()
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
