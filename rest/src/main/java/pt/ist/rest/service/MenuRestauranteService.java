/**
 * 
 */
package pt.ist.rest.service;

import java.util.ArrayList;
import java.util.Collection;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Alimento;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.exception.core.unknowkey.NoSuchRestauranteException;
import pt.ist.rest.service.dto.AlimentoDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;

public class MenuRestauranteService extends
        RestService {
    private final RestauranteDto restaurante;
    private Collection<PratoDto> pratos;

    public MenuRestauranteService(RestauranteDto rest) {
        this.restaurante = rest;
    }

    /**
     * Reúne os pratos todos de um dado restaurante.
     * 
     * @throws NoSuchRestauranteException quando o restaurante não existe.
     * @see pt.ist.rest.service.RestService#dispatch()
     */
    @Override
    public void dispatch() throws NoSuchRestauranteException {
        final PortalDeRestaurantes pdr = FenixFramework.getRoot();
        final Restaurante r = pdr.getRestaurante(restaurante.getNome());
        if (r == null) {
            throw new NoSuchRestauranteException(restaurante.getNome());
        }
        pratos = new ArrayList<PratoDto>();

        for (Prato p : r.getPratoSet()) {
            ArrayList<AlimentoDto> als = new ArrayList<AlimentoDto>();

            for (Alimento a : p.getAlimento()) {
                als.add(new AlimentoDto(a.getDescricao(), a.getTipo()));
            }
            assert als.size() > 0 : "PratosDeRestaurante: dispatch: prato com alimentos vazios";

            pratos.add(new PratoDto(p.getNome(), p.getId(), p.getPreco(), p.getCalorias(), p
                    .getRating(), als));
        }
    }

    /**
     * @return pratos lista de pratos que existem no restaurante.
     */
    public Collection<PratoDto> getPratos() {
        return pratos;
    }
}
