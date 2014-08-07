package pt.ist.rest.service;

import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.service.dto.PortalDeRestaurantesDto;
import pt.ist.rest.service.dto.RestauranteDto;

public class ObterRestaurantesService extends
        RestService {

    private PortalDeRestaurantesDto resultado;

    public ObterRestaurantesService() {

    }

    /**
     * Obtém restaurantes do Portal.
     * 
     * @see pt.ist.rest.service.RestService#dispatch()
     */
    @Override
    public final void dispatch() {
        PortalDeRestaurantes pdr = FenixFramework.getRoot();
        List<RestauranteDto> restauranteList = new ArrayList<RestauranteDto>();

        for (Restaurante r : pdr.getRestaurante()) {
            RestauranteDto toAdd = new RestauranteDto(r.getNome(), r.getMorada(), r.rating());
            restauranteList.add(toAdd);
        }

        resultado = new PortalDeRestaurantesDto(restauranteList);
    }

    /**
     * @return resultado lista de restaurantes do portal.
     */
    public PortalDeRestaurantesDto getResultado() {
        return resultado;
    }

}
