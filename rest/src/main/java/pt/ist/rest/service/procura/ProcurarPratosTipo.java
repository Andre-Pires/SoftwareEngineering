package pt.ist.rest.service.procura;

import java.util.Iterator;

import pt.ist.rest.domain.Prato;
import pt.ist.rest.exception.core.unknowkey.NoSuchTypeException;
import pt.ist.rest.service.component.SearchComponent;

public class ProcurarPratosTipo extends
        ProcurarPrato {

    private String tipo;

    public ProcurarPratosTipo(String s) {
        this.tipo = s;
    }

    /**
     * Procura pratos de um tipo específico no Portal.
     * 
     * @throws NoSuchTypeException caso seja dado um tipo inexistente.
     */
    @Override
    public final void searchPrato(SearchComponent c) {

        if (!(tipo.equals("Vegetariano") || tipo.equals("Peixe") || tipo.equals("Carne"))) {
            throw new NoSuchTypeException(tipo);
        }


        Iterator<Prato> iter = c.getPratos().iterator();
        while (iter.hasNext()) {
            Prato p = iter.next();
            boolean checkTipoPrato;
            if (tipo.equals("Vegetariano")) {
                checkTipoPrato = p.isVegetal();
            } else if (tipo.equals("Carne")) {
                checkTipoPrato = p.isCarne();
            } else {
                checkTipoPrato = p.isPeixe();
            }
            if (!checkTipoPrato) {
                iter.remove();
            }
        }
    }
}
