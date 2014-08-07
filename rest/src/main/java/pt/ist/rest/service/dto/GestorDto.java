package pt.ist.rest.service.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GestorDto
        implements IsSerializable {

    private String user;
    private String pass;
    private String nome;

    public GestorDto() {
        // precisa para ser serializável
    }

    public GestorDto(String user, String pass, String nome) {
        this.user = user;
        this.pass = pass;
        this.nome = nome;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
