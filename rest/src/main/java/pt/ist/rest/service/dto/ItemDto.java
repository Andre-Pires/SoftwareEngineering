package pt.ist.rest.service.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * The Class ItemDto - representação do conteudo de uma Compra, do ponto da apresentação.
 */
public class ItemDto
        implements IsSerializable {

    /**
     * Prato que se pretende adicionar.
     */
    private PratoDto pratoDto;

    /**
     * Quantidade do prato que se pretende adicionar.
     */
    private int quantidade;

    /**
     * The restaurante dto.
     */
    private RestauranteDto restauranteDto;

    public ItemDto() {
        // precisa do construtor default para serializar
    }

    /**
     * Cria um novo item de uma Compra.
     * 
     * @param pDto prato que se pretende adicionar
     * @param quant quantidade que se pretende adicionar
     * @param rDto restaurante que o prato pertence
     */
    public ItemDto(PratoDto pDto, int quant, RestauranteDto rDto) {
        pratoDto = pDto;
        quantidade = quant;
        restauranteDto = rDto;
    }

    /**
     * Cria um novo item de uma Compra.
     * 
     * @param pDto prato que se pretende adicionar
     * @param quant quantidade que se pretende adicionar
     */
    public ItemDto(PratoDto pDto, int quant) {
        pratoDto = pDto;
        quantidade = quant;
        if (pDto.getRestaurante() == null)
            restauranteDto = null;
        else
            restauranteDto = new RestauranteDto(pDto.getRestaurante());
    }

    /**
     * Gets the restaurante dto.
     * 
     * @return restaurante que o prato pertence
     */
    public RestauranteDto getRestauranteDto() {
        return restauranteDto;
    }


    /**
     * Gets the prato dto.
     * 
     * @return the prato dto
     */
    public PratoDto getPratoDto() {
        return pratoDto;
    }

    /**
     * Gets the quantidade.
     * 
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

}
