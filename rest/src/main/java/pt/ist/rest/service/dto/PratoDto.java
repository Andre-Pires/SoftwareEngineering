package pt.ist.rest.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * The Class PratoDto - Representação de um prato na Óptica de um Cliente.
 */
public class PratoDto
        implements IsSerializable {

    /**
     * The nome.
     */
    private String nome;

    /**
     * The id.
     */
    private int id;

    /**
     * The preco.
     */
    private int preco;

    /**
     * The calorias.
     */
    private int calorias;

    /**
     * The lista de alimentos do prato.
     */
    private List<AlimentoDto> alimentos;

    /**
     * The classificacao.
     */
    private int classificacao;

    /**
     * The restaurante.
     */
    private String restaurante;

    /**
     * The tipo.
     */
    private String tipo;

    public PratoDto() {
        // precisa para ser serializável
    }

    /**
     * Instantiates a new prato dto.
     * 
     * @param nome nome do prato
     */
    public PratoDto(String nome) {
        this.nome = nome;
    }

    /**
     * Instantiates a new prato dto.
     * 
     * @param nome the nome do prato
     * @param id the id do prato
     * @param alimentos lista de alimentos que pertencem ao prato
     */
    public PratoDto(String nome, int id, List<AlimentoDto> alimentos) {
        this.nome = nome;
        this.id = id;
        this.alimentos = alimentos;
    }

    /**
     * Instantiates a new prato dto.
     * 
     * @param nome the nome do prato
     * @param id the id do prato
     * @param alimentos lista de alimentos que pertencem ao prato
     */
    public PratoDto(String nome, int id, int preco, int calorias, String restaurante, String tipo) {
        this.nome = nome;
        this.id = id;
        this.preco = preco;
        this.calorias = calorias;
        this.restaurante = restaurante;
        this.tipo = tipo;
    }

    /**
     * Instantiates a new prato dto.
     * 
     * @param nome the nome do prato
     * @param id the id do prato
     * @param preco the preco do prato
     * @param calorias the calorias do prato
     * @param alimentos lista de alimentos que pertencem ao prato
     */
    public PratoDto(String nome, int id, int preco, int calorias, List<AlimentoDto> alimentos) {
        this(nome, id, alimentos);
        this.preco = preco;
        this.calorias = calorias;

    }

    /**
     * Instantiates a new prato dto.
     * 
     * @param nome the nome do prato
     * @param id the id do prato
     * @param preco the preco do prato
     * @param calorias the calorias do prato
     * @param classificacao the classificacao do prato
     * @param alimentos lista de alimentos que pertencem ao prato
     */
    public PratoDto(String nome,
                    int id,
                    int preco,
                    int calorias,
                    int classificacao,
                    List<AlimentoDto> alimentos) {
        this(nome, id, alimentos);
        this.preco = preco;
        this.calorias = calorias;
        this.classificacao = classificacao;

    }

    public PratoDto(String nome, int id, int preco, String Restaurante) {
        this(nome, id, preco);
        this.restaurante = Restaurante;

    }

    /**
     * Instantiates a new prato dto.
     * 
     * @param nome the nome do prato
     * @param id the id do prato
     * @param preco the preco do prato
     * @param calorias the calorias do prato
     * @param restaurante the restaurante que o prato pertence
     */
    public PratoDto(String nome, int id, int preco, int calorias, String restaurante) {
        this.nome = nome;
        this.id = id;
        this.preco = preco;
        this.calorias = calorias;
        this.restaurante = restaurante;
    }

    /**
     * Instantiates a new prato dto.
     * 
     * @param nome nome do prato
     * @param id id do prato
     * @param preco the preco do prato
     */
    public PratoDto(String nome, int id, int preco) {
        this.nome = nome;
        this.id = id;
        this.preco = preco;
    }

    /**
     * Gets the restaurante.
     * 
     * @return restaurante que o prato pertence
     */
    public String getRestaurante() {
        return restaurante;
    }

    /**
     * Sets the restaurante.
     * 
     * @param restaurante restaurante que o prato pertence
     */
    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    /**
     * Gets the nome do prato.
     * 
     * @return nome do prato
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets the id do prato.
     * 
     * @return the id do prato
     */
    public int getId() {
        return id;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }



    /**
     * Gets the preco do prato.
     * 
     * @return the preco do prato
     */
    public int getPreco() {
        return preco;
    }

    /**
     * Gets the calorias do prato.
     * 
     * @return the calorias do prato
     */
    public int getCalorias() {
        return calorias;
    }

    /**
     * Gets the lista de alimentos do prato.
     * 
     * @return lista de alimentos do prato
     */
    public List<AlimentoDto> getAlimento() {
        return alimentos;
    }

    /**
     * Sets the nome.
     * 
     * @param nome nome do prato
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Sets the id.
     * 
     * @param id the new id do prato
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the preco.
     * 
     * @param preco the new preco do prato
     */
    public void setPreco(int preco) {
        this.preco = preco;
    }

    /**
     * Sets the calorias do prato.
     * 
     * @param calorias the new calorias do prato
     */
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    /**
     * Sets the lista de alimentos do prato.
     * 
     * @param alimentos lista de alimentos que o prato é composto
     */
    public void setAlimentos(ArrayList<AlimentoDto> alimentos) {
        this.alimentos = alimentos;
    }

    /**
     * Gets the classificacoes do prato.
     * 
     * @return classificacoes do prato
     */
    public int getClassificacao() {
        return classificacao;
    }

    /**
     * Sets the classificacoes.
     * 
     * @param classificacoes clasificação do prato
     */
    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    /**
     * Gets the lista de alimentos que o prato é composto.
     * 
     * @return the lista de alimentos que o prato é composto.
     */
    public List<AlimentoDto> getAlimentos() {
        return alimentos;
    }


}
