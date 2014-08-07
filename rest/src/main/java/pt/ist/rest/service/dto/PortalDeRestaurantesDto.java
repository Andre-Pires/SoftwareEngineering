package pt.ist.rest.service.dto;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PortalDeRestaurantesDto
        implements IsSerializable {

    private List<RestauranteDto> restauranteList;

    public PortalDeRestaurantesDto() {
        // precisa do construtor default para ser serializável
    }

    public PortalDeRestaurantesDto(List<RestauranteDto> restauranteList) {
        this.restauranteList = restauranteList;
    }

    public List<RestauranteDto> getListaRestaurantes() {
        return this.restauranteList;
    }


}
