package pt.ist.rest.service.component;

import java.util.ArrayList;
import java.util.Collection;

import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Restaurante;

public class SearchComponent {

    /*
     * Lista de argumentos recebidos pela interface, pode ser extendido para varios tipos
     * de procuras
     */
    Collection<Prato> pratos = new ArrayList<Prato>();

    public SearchComponent(PortalDeRestaurantes portal) {
        for (Restaurante r : portal.getRestaurante()) {
            for (Prato p : r.getPrato())
                this.pratos.add(p);
        }

    }


    public Collection<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(Collection<Prato> array) {
        this.pratos = array;
    }
}
