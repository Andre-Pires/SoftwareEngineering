package pt.ist.rest.domain;

import pt.ist.rest.exception.core.invalidkey.InsuficientCreditException;
import pt.ist.rest.exception.core.invalidkey.InvalidCreditException;
import pt.ist.rest.exception.core.invalidkey.InvalidDishException;

/**
 * Mantém toda a informacao relativa a um cliente: o seu username, password, nome real, a
 * sua morada e o seu email. Mantém também a lista de pratos de que o cliente gosta, que
 * não pode exceder os 15 Pratos. Mantém um registo de todas as compras feitas pelo
 * cliente, bem como os pratos encomendados, mas ainda não comprados.
 */
public class Cliente extends
        Cliente_Base {

    /**
     * Referência para o tabuleiro de compras existente na lista de compras. Evita
     * percorrer a lista sempre que se pretende aceder ao tabuleiro. Não é guardada na
     * base de dados; serve apenas para apontar para o tabuleiro, se ele existir.
     * 
     * @see #getTabuleiro()
     */
    private Compra tab = null;

    /**
     * Instantiates a new cliente.
     * 
     * @param user Username do cliente, utilizado para o identificar.
     * @param pass Password do cliente.
     * @param nome Nome real do cliente.
     * @param morada Actual morada do cliente.
     * @param email Endereco de email do cliente.
     */
    protected Cliente(String user, String pass, String nome, String morada, String email) {
        init(user, pass, nome);
        setMorada(morada);
        setEmail(email);
    }

    /**
     * Instantiates a new cliente.
     * 
     * @param user O username do cliente.
     * @param pass A password do cliente.
     * @param nome O nome do cliente.
     * @param morada A morada do cliente.
     * @param email O email do cliente.
     * @param nif Número de contribuinte do cliente.
     * @see #Cliente(String, String, String, String, String)
     */
    protected Cliente(String user, String pass, String nome, String morada, String email, String nif) {
        this(user, pass, nome, morada, email);
        super.setNif(Integer.parseInt(nif));
    }

    /**
     * Remove um prato da lista de gostos, caso exista e se o prato for um prato
     * pertencente a um restaurante do sistema. Se não pertencer, lança uma excepção.
     * 
     * @param p O prato a remover da lista.
     */
    @Override
    public void removePrato(final Prato p) {
        final String nomeP = p.getNome();
        final String nomeR = p.getRestaurante().getNome();

        if (p.equals(this.getPortal().getPrato(nomeP, nomeR))) {
            super.removePrato(p);
        } else {
            throw new InvalidDishException(nomeP, "Não tinha gostado do prato previamente");
        }
    }

    /**
     * ANTIGO GOSTAPRATO Adiciona um prato a lista de gostos, caso nao ultrapasse o limite
     * de 15 pratos e se o prato for um prato pertencente a um restaurante do sistema. Se
     * não pertencer, o prato nao e adicionado.
     * 
     * @param p O prato a adicionar a lista.
     */
    @Override
    public void addPrato(final Prato p) {
        final int maxGostos = 15;
        final String nomeP = p.getNome();
        final String nomeR = p.getRestaurante().getNome();
        if (this.getPrato().size() < maxGostos && p.equals(this.getPortal().getPrato(nomeP, nomeR))) {
            super.addPrato(p);
        } else {
            throw new InvalidDishException(p.getNome());
        }
    }

    /**
     * Metodo auxiliar para ir buscar o tabuleiro de compras.
     * 
     * @param idCompra the id compra
     * @return O tabuleiro de compras do cliente (compra nao finalizada), caso esta
     *         exista. Caso nao exista, retorna null.
     */
    private Compra getCompraFinalizada(int idCompra) {
        if (this.getCompraSet().size() > 0) {
            for (Compra c : this.getCompra()) {
                if (c.getId() == idCompra) {
                    return c;
                }
            }
        }

        return null;
    }

    /**
     * Gets the tabuleiro.
     * 
     * @return O tabuleiro de compras do cliente, caso este exista. Caso não exista, cria
     *         um novo tabuleiro de compras e retorna-o.
     * @see #tab
     */
    public Compra getTabuleiro() {
        /**
         * tab é null sempre que o programa corre porque não é guardado na db. Vai
         * verificar se algum tabuleiro foi guardado na db (compras do cliente) na última
         * execução.
         */
        if (tab == null) {
            for (Compra c : this.getCompra()) {
                if (!c.isFinalizada()) {
                    tab = c;
                    return tab;
                }
            }

            tab = new Compra(gerarIdCompra());
            this.addCompra(tab);
        }

        assert tab != null : "getTabuleiro(): vazio";
        return tab;
    }

    /**
     * Adiciona o prato ao tabuleiro de compras.
     * 
     * @param p prato a ser adicionado.
     * @param quantidade quantidade do prato a ser adicionada.
     * @throws InvalidDishException the add dish exception
     */
    public void comprarPrato(Prato p, int quantidade) throws InvalidDishException {
        this.getTabuleiro().addQuantidadePrato(new QuantidadePrato(quantidade, p));
    }

    /**
     * Finalizar compra.
     */
    public void finalizarCompra() {
        int saldo = this.getSaldo();
        int preco = this.getTabuleiro().getTotalCompra();
        if (saldo >= preco) {
            this.getTabuleiro().finalizaEncomenda();
            System.out.println("FinalizarCompra(): A actualizar o saldo...");
            this.setSaldo(saldo - preco);
            this.tab = null;
        } else {
            System.out.println("FALTA DE CREDITO! ATIRA EXCEPCAO!");
            throw new InsuficientCreditException(saldo, preco);
        }
    }

    /**
     * Gerar id compra.
     * 
     * @return identificador unico de compra
     */
    public int gerarIdCompra() {
        this.setIdCompra(this.getIdCompra() + 1);
        return this.getIdCompra();
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.ist.fenixframework.pstm.AbstractDomainObject#toString()
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Cliente ").append(this.getUsername()).append(' ').append(this.getPassword())
                .append(' ').append(this.getNome());
        return string.toString();
    }

    //CHECKSTYLE:OFF para evitar queixar-se de não fazer override ao equals de Object
    /**
     * Equals.
     * 
     * @param obj the obj
     * @return true, if successful
     */
    public boolean equals(Cliente obj) {
        if (obj == null) {
            return false;
        }

        if (this.getUsername().equals(obj.getUsername())) {

            assert this.getPassword().equals(obj.getPassword()) : "@equals Cliente: username igual mas password diferente: \n\t"
                    + '('
                    + this.getUsername()
                    + " - "
                    + this.getPassword()
                    + '/'
                    + obj.getPassword() + ")\n";
            assert this.getNome().equals(obj.getNome()) : "@equals Cliente: username igual mas nome diferente: \n\t"
                    + '('
                    + this.getUsername()
                    + " - "
                    + this.getNome()
                    + '/'
                    + obj.getNome()
                    + ")\n";
            assert this.getMorada().equals(obj.getMorada()) : "@equals Cliente: username igual mas morada diferente: \n\t"
                    + '('
                    + this.getUsername()
                    + " - "
                    + this.getMorada()
                    + '/'
                    + obj.getMorada()
                    + ")\n";
            assert this.getEmail().equals(obj.getEmail()) : "@equals Cliente: username igual mas email diferente: \n\t"
                    + '('
                    + this.getUsername()
                    + " - "
                    + this.getEmail()
                    + '/'
                    + obj.getEmail()
                    + ")\n";
            assert this.getNif() == obj.getNif() : "@equals Cliente: username igual mas nif diferente: \n\t"
                    + '(' + this.getUsername() + " - " + this.getNif() + '/' + obj.getNif() + ")\n";

            return true;
        }
        return false;
    }

    //CHECKSTYLE:ON

    @Override
    public void setSaldo(int valor) {

        if (valor >= 0) {
            super.setSaldo(valor);
        } else {
            throw new InvalidCreditException(valor);
        }

    }

    public void actSaldo(int valor) {

        this.setSaldo(this.getSaldo() + valor);

    }
}
