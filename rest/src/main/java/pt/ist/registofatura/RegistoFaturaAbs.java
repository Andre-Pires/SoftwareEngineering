package pt.ist.registofatura;

import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import build.classes.pt.registofatura.ws.ClienteInexistente_Exception;
import build.classes.pt.registofatura.ws.EmissorInexistente_Exception;
import build.classes.pt.registofatura.ws.Fatura;
import build.classes.pt.registofatura.ws.FaturaInvalida_Exception;
import build.classes.pt.registofatura.ws.RegistoFaturaPortType;
import build.classes.pt.registofatura.ws.Serie;
import build.classes.pt.registofatura.ws.TotaisIncoerentes_Exception;


public abstract class RegistoFaturaAbs {

    private RegistoFaturaPortType implementacao;

    public void setImplementacao(RegistoFaturaPortType newImplementacao) {
        implementacao = newImplementacao;
    }

    public Serie pedirSerie(int nifEmissor) throws EmissorInexistente_Exception {
        return implementacao.pedirSerie(nifEmissor);
    }

    public void comunicarFatura(Fatura parameters) throws ClienteInexistente_Exception,
            EmissorInexistente_Exception,
            FaturaInvalida_Exception,
            TotaisIncoerentes_Exception {
        System.err.println("DENTRO COMUNICAR FATURA!");
        implementacao.comunicarFatura(parameters);
        System.err.println("Fora comunicar Fatura!");
    }

    public List<Fatura> listarFacturas(Integer nifEmissor, Integer nifCliente) throws ClienteInexistente_Exception,
            EmissorInexistente_Exception {
        return implementacao.listarFacturas(nifEmissor, nifCliente);
    }

    public int consultarIVADevido(int nifEmissor, XMLGregorianCalendar ano) throws EmissorInexistente_Exception {
        return implementacao.consultarIVADevido(nifEmissor, ano);
    }


}
