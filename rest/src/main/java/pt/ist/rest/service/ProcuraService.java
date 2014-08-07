package pt.ist.rest.service;

import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.service.component.SearchComponent;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.SearchDto;
import pt.ist.rest.service.dto.SearchEnum;
import pt.ist.rest.service.procura.ProcurarPratoNome;
import pt.ist.rest.service.procura.ProcurarPratosTipo;

public class ProcuraService extends
        RestService {

    private ArrayList<SearchDto> search;

    private SearchComponent sc;

    private ArrayList<PratoDto> resultado = new ArrayList<PratoDto>();

    public ProcuraService(ArrayList<SearchDto> procura) {
        this.search = procura;

    }

    @Override
    public void dispatch() {

        PortalDeRestaurantes pdr = FenixFramework.getRoot();

        sc = new SearchComponent(pdr);
        for (SearchDto sD : this.search) {
            switch (SearchEnum.fromValue(sD.getEnum())) {
                case NOME:
                    ProcurarPratoNome ppn = new ProcurarPratoNome(sD.getArg());
                    ppn.searchPrato(sc);
                    break;
                case TIPO:
                    ProcurarPratosTipo ppt = new ProcurarPratosTipo(sD.getArg());
                    ppt.searchPrato(sc);
                    break;
                default:
                    System.out.println("nao reconhece o tipo " + sD.getEnum());
                    break;
            }
        }
        for (Prato p : sc.getPratos()) {
            resultado.add(new PratoDto(p.getNome(), p.getId(), p.getPreco(), p.getCalorias(), p
                    .getRestaurante().getNome()));
        }
    }

    public ArrayList<PratoDto> getResultado() {
        return resultado;
    }

}
