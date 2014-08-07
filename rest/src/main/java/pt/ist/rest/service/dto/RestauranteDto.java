package pt.ist.rest.service.dto;

import com.google.gwt.user.client.rpc.IsSerializable;


public class RestauranteDto
        implements IsSerializable {

    private String nome;
    private String morada;
    private GestorDto gestorDto;
    private float rating;

    public RestauranteDto() {
        // precisa para ser serializável
    }

    public RestauranteDto(String nome) {
        this.nome = nome;
    }

    public RestauranteDto(String nome, String morada, GestorDto gestor) {
        this.nome = nome;
        this.morada = morada;
        this.gestorDto = new GestorDto(gestor.getUser(), gestor.getPass(), gestor.getNome());
        this.rating = 0;
    }

    public RestauranteDto(String nome, String morada, float rating) {
        this.nome = nome;
        this.morada = morada;
        this.gestorDto = null;
        this.rating = rating;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public GestorDto getGestorDto() {
        return gestorDto;
    }

    public void setGestorDto(GestorDto gestorDto) {
        this.gestorDto = gestorDto;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
