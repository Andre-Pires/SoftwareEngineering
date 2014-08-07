package pt.ist.rest.service.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AlimentoDto
        implements IsSerializable {

    private String nome;
    private String tipo;


    public AlimentoDto() {
        // precisa para ser serializável
    }

    public AlimentoDto(String nome, String tipo) {
        this.tipo = tipo;
        this.nome = nome;
    }


    public String getDescricao() {
        return nome;
    }


    public String getTipo() {
        return tipo;
    }


    public void setDescricao(String nome) {
        this.nome = nome;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}
