package pt.ist.rest.service.procura;

import java.util.Iterator;

import pt.ist.rest.domain.Prato;
import pt.ist.rest.service.component.SearchComponent;




public class ProcurarPratoNome extends
        ProcurarPrato {

    private final String nomePrato;


    public ProcurarPratoNome(String prato) {
        this.nomePrato = prato;
    }

    /**
     * Procura pratos no portal que correspondam a um nome específico.
     */
    @Override
    public final void searchPrato(SearchComponent c) {

        Iterator<Prato> iter = c.getPratos().iterator();
        while (iter.hasNext()) {
            Prato p = iter.next();
            if (!p.getNome().contains(nomePrato)) {
                iter.remove();
            }
        }

    }



}
