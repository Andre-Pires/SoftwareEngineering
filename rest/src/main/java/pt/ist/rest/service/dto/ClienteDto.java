package pt.ist.rest.service.dto;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ClienteDto
        implements IsSerializable {

    private String user;
    private String pass;
    private String morada;
    private String email;
    private String nif;
    private int saldo;
    private List<PratoDto> pratos;

    public ClienteDto() {
        // precisa para ser serializável
    }

    public ClienteDto(String user) {
        this.user = user;
    }


    public ClienteDto(String user, String pass) {
        this(user);
        this.pass = pass;
    }

    public ClienteDto(String user, String pass, String morada, String email, int saldo) {
        this(user, pass);
        this.morada = morada;
        this.email = email;
        this.saldo = saldo;
    }

    public ClienteDto(String user, String pass, String morada, String email, int saldo, String nif) {
        this(user, pass, morada, email, saldo);
        this.nif = nif;
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

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public List<PratoDto> getPratos() {
        return pratos;
    }

    public void setPratos(List<PratoDto> pratos) {
        this.pratos = pratos;
    }



}
