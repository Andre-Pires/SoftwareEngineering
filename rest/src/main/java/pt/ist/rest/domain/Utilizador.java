package pt.ist.rest.domain;


/**
 * Representa um utilizador do sistema que tem um username unico, uma palavra-passe e um
 * nome.
 */
public abstract class Utilizador extends
        Utilizador_Base {

    /**
     * Instancia um novo utilizador.
     */
    public Utilizador() {
        super();
    }

    /**
     * Usado para fazer o bypass das classes abstratas geradas na heranca. Inicializa os
     * atributos do utilizador (username, password e nome).
     * 
     * @param user username do utilizador.
     * @param pass password do utilizador.
     * @param nome nome real do utilizador.
     */
    protected void init(String user, String pass, String nome) {
        setUsername(user);
        setPassword(pass);
        setNome(nome);

    }



}
