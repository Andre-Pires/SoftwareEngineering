package pt.ist.chequerefeicao;

import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

import java.util.Map;

import javax.xml.registry.JAXRException;
import javax.xml.ws.BindingProvider;

import pt.chequerefeicao.ws.ChequeRefeicaoPortType;
import pt.chequerefeicao.ws.ChequeRefeicaoService;
import chequerefeicao.ws.uddi.UDDINaming;

public class ChequeRefeicao extends
        ChequeRefeicaoAbs {

    /**
     * Instancia um serviço ChequeRefeicao. {@link ChequeRefeicaoAbs},
     * {@link ChequeRefeicaoImpl}, {@link ChequeRefeicaoLocal},
     * {@link ChequeRefeicaoRemote}.
     */
    public ChequeRefeicao() {
        super();
        System.out.println("nova instancia de chequerefeicao...");
        setLocal();
    }

    public void setRemote() {
        System.out.println("setting as remote...");
        String uddiURL = "http://localhost:8081";
        String nome = "ChequeRefeicao";
        UDDINaming uddiNaming = null;
        String endpointAddress = null;
        try {
            uddiNaming = new UDDINaming(uddiURL);
            System.out.println("Set uddiNaming.");
        } catch (JAXRException e) {
            System.out
                    .println("--------\nNão conseguiu iniciar o UddiNaming:  A usar a versão local.");
            e.printStackTrace();
            return;
        }

        System.out.printf("Contacting UDDI at %s%n", uddiURL);

        System.out.printf("Looking for '%s'%n", nome);
        try {
            endpointAddress = uddiNaming.lookup(nome);
            System.out.println("endpoint is '" + endpointAddress + "'");
        } catch (JAXRException e) {
            System.out.println("looking up has failed. exception message: " + e.getMessage());
            e.printStackTrace();
        }

        if (endpointAddress == null) {
            System.out.println("Not found!");
            return;
        } else {
            System.out.printf("Found %s%n", endpointAddress);
        }

        System.out.println("[ ChequeRefeicao Remoto ]");


        ChequeRefeicaoService service = new ChequeRefeicaoService();
        ChequeRefeicaoPortType port = service.getChequeRefeicaoPort();

        System.out.println("Setting endpoint address ...");
        BindingProvider bindingProvider = (BindingProvider) port;
        Map<String, Object> requestContext = bindingProvider.getRequestContext();
        requestContext.put(ENDPOINT_ADDRESS_PROPERTY, endpointAddress);

        System.out.println("setting remote port...");
        setImplementacao(port);
    }

    public void setLocal() {
        System.out.println("setting as local - cheque");
        setImplementacao(new ChequeRefeicaoLocal());
    }

}
