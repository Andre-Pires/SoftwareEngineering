package pt.ist.registofatura;

/**
 * Esta classe é uma concretização muito simples da interface RegistoFaturaPortType. Esta
 * interface representa o serviço externo RegistoFatura. Apenas se verifica se o nif do
 * emissor é um nif registado previamente e que o número de serie da fatura corresponde a
 * uma serie já fornecida pelo serviço.
 **/

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import build.classes.pt.registofatura.ws.ClienteInexistente_Exception;
import build.classes.pt.registofatura.ws.EmissorInexistente_Exception;
import build.classes.pt.registofatura.ws.Fatura;
import build.classes.pt.registofatura.ws.FaturaInvalida_Exception;
import build.classes.pt.registofatura.ws.RegistoFaturaPortType;
import build.classes.pt.registofatura.ws.Serie;
import build.classes.pt.registofatura.ws.TotaisIncoerentes_Exception;

public class RegistoFaturaLocal
        implements RegistoFaturaPortType {

    private static int numeroSerie = 1;
    private final int NifEmissorRegistado[] = { 1212 };

    public RegistoFaturaLocal() {
    }

    private void checkValidNifEmissor(int nif) throws EmissorInexistente_Exception {
        for (int n : this.NifEmissorRegistado) {
            if (n == nif) {
                return;
            }
        }

        throw new EmissorInexistente_Exception("NIF do emissor inválido", null);
    }

    /**
     * @param nifEmissor
     * @return returns pt.ist.registofatura.local.Serie
     * @throws EmissorInexistente_Exception
     */
    @Override
    public Serie pedirSerie(int nifEmissor) throws EmissorInexistente_Exception {
        Serie serie;

        checkValidNifEmissor(nifEmissor);
        serie = new Serie();
        serie.setNumSerie(this.numeroSerie++);
        serie.setValidoAte(null);
        return serie;
    }

    /**
     * @param fatura
     * @throws FaturaInvalida_Exception
     * @throws EmissorInexistente_Exception
     * @throws TotaisIncoerentes_Exception
     * @throws ClienteInexistente_Exception
     */
    @Override
    public void comunicarFatura(Fatura fatura) throws ClienteInexistente_Exception,
            EmissorInexistente_Exception,
            FaturaInvalida_Exception,
            TotaisIncoerentes_Exception {
        System.err.println("com: numserie: " + fatura.getNumSerie());
        System.err.println("com: this.numserie: " + RegistoFaturaLocal.numeroSerie);
        if (fatura.getNumSerie() <= 0) {
            throw new FaturaInvalida_Exception("Número de série de fatura inválido", null);
        }
    }

    /**
     * @param nifEmissor
     * @param nifCliente
     * @return returns java.util.List<pt.ist.registofatura.local.Fatura>
     * @throws EmissorInexistente_Exception
     * @throws ClienteInexistente_Exception
     */
    @Override
    public List<Fatura> listarFacturas(Integer nifEmissor, Integer nifCliente) throws ClienteInexistente_Exception,
            EmissorInexistente_Exception {
        checkValidNifEmissor(nifEmissor);
        return new ArrayList<Fatura>();
    }

    /**
     * @param nifEmissor
     * @param ano
     * @return returns int
     * @throws EmissorInexistente_Exception
     */
    @Override
    public int consultarIVADevido(int nifEmissor, XMLGregorianCalendar ano) throws EmissorInexistente_Exception {
        checkValidNifEmissor(nifEmissor);
        return 0;
    }


    public static void main(String[] args) {
        RegistoFaturaLocal registo = new RegistoFaturaLocal();

        Serie serie = null;

        try {
            serie = registo.pedirSerie(0);
            System.err.println("Erro a pedir uma serie de facturas com um nif não registado!");
        } catch (EmissorInexistente_Exception eie) {
            System.out.println("Verificação correcta de nif inválido em pedirSerie");
        }

        try {
            serie = registo.pedirSerie(1212);
            if (serie.getNumSerie() != 1) {
                System.err
                        .println("Erro a pedir uma serie de de facturas. Número de serie não é 1: "
                                + serie.getNumSerie());
            } else {
                System.out.println("Serie emitida correctamente em pedirSerie");
            }
        } catch (EmissorInexistente_Exception eie) {
            System.err.println("Erro a pedir uma serie de de facturas!");
            System.exit(-1);
        }


    }
}
