package pt.ist.registofatura;

import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

import java.util.Map;

import javax.xml.registry.JAXRException;
import javax.xml.ws.BindingProvider;

import pt.registofatura.ws.RegistoFaturaPortType;
import pt.registofatura.ws.RegistoFaturaService;
import registofatura.ws.uddi.UDDINaming;

public class RegistoFatura extends
        RegistoFaturaAbs {

    /**
     * Instancia um serviço RegistoFatura. {@link RegistoFaturaAbs},
     * {@link RegistoFaturaPortType}, {@link RegistoFaturaLocal},
     * {@link RegistoFaturaRemote}.
     */
    public RegistoFatura() {
        super();
        System.out.println("nova instancia de registoFatura...");
        setLocal();
    }

    public void setRemote() {
        System.out.println("setting as remote...");
        String uddiURL = "http://localhost:8081";
        String nome = "RegistoFatura";
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

        RegistoFaturaService service = new RegistoFaturaService();
        RegistoFaturaPortType port = service.getRegistoFaturaPort();

        System.out.println("Setting endpoint address ...");
        BindingProvider bindingProvider = (BindingProvider) port;
        Map<String, Object> requestContext = bindingProvider.getRequestContext();
        requestContext.put(ENDPOINT_ADDRESS_PROPERTY, endpointAddress);

        System.out.println("setting remote port...");
        setImplementacao(port);

    }

    public void setLocal() {
        System.out.println("setting as local");
        setImplementacao(new RegistoFaturaLocal());
    }

}
