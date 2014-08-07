package pt.ist.rest;

import java.util.ArrayList;

import jvstm.Atomic;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Alimento;
import pt.ist.rest.domain.Gestor;
import pt.ist.rest.domain.PortalDeRestaurantes;

/**
 * A classe RestSetup.
 */
public class RestSetup {

    /**
     * Método main, encarregue da ligação à base de dados "restdb".
     * 
     * @param args the arguments
     */
    public static void main(String[] args) {

        FenixFramework.initialize(new Config() {
            {
                dbAlias = "//localhost:3306/restdb";
                dbUsername = "rest";
                dbPassword = "r3st";
                domainModelPath = "src/main/dml/domain.dml";
                rootClass = PortalDeRestaurantes.class;
            }
        });

        populateDomain();
    }

    /**
     * Adiciona dados iniciais à base de dados, de forma a criar um cenário de teste
     * inicial.
     */

    @Atomic
    protected static void populateDomain() {

        PortalDeRestaurantes portal = FenixFramework.getRoot();

        registaGestores(portal);

        Gestor gestorMng = portal.getGestor("mng");
        Gestor gestorPP = portal.getGestor("pp");

        registaRestaurantes(portal, gestorMng, gestorPP);

        registaClientes(portal);

        adicionaPratosRestaurantes(portal, gestorMng, gestorPP);

        portal.setPrecoMaximo(7);
    }

    /**
     * Adiciona os pratos pedidos aos restaurantes Barriga Feliz e Barriga Cheia.
     * 
     * @param portal o portal da aplicação.
     * @param mng o gestor com username mng
     * @param pp o gestor com username pp
     */
    private static void adicionaPratosRestaurantes(PortalDeRestaurantes portal,
                                                   Gestor mng,
                                                   Gestor pp) {

        Alimento alimento1 = new Alimento("Bacalhau", "Peixe");
        Alimento alimento2 = new Alimento("Batatas", "Vegetariano");
        Alimento alimento3 = new Alimento("Bife", "Carne");
        Alimento alimento4 = new Alimento("Galinha", "Carne");

        ArrayList<Alimento> alimentos1 = new ArrayList<Alimento>();
        ArrayList<Alimento> alimentos2 = new ArrayList<Alimento>();
        ArrayList<Alimento> alimentos3 = new ArrayList<Alimento>();

        alimentos1.add(alimento1);
        alimentos1.add(alimento2);

        alimentos2.add(alimento3);
        alimentos3.add(alimento4);

        mng.adicionaPrato(portal.getRestaurante("Barriga Cheia"), "Bacalhau com batatas", 43, 3,
                alimentos1);
        pp.adicionaPrato(portal.getRestaurante("Barriga Feliz"), "Bacalhau com batatas", 3, 23,
                alimentos1);
        pp.adicionaPrato(portal.getRestaurante("Barriga Feliz"), "Bitoque", 2, 4, alimentos2);
        pp.adicionaPrato(portal.getRestaurante("Barriga Feliz"), "Canja de Galinha", 5, 6,
                alimentos3);
        portal.getRestaurante("Barriga Feliz").getPrato("Bacalhau com batatas").setPreco(14);
        portal.getRestaurante("Barriga Feliz").getPrato("Bitoque").setPreco(15);
        portal.getRestaurante("Barriga Feliz").getPrato("Canja de Galinha").setPreco(16);
        portal.getRestaurante("Barriga Cheia").getPrato("Bacalhau com batatas").setPreco(19);
    }

    /**
     * Regista os clientes 'Zé Ninguém' e 'Maria Silva'.
     * 
     * @param portal o portal da aplicação.
     */
    private static void registaClientes(PortalDeRestaurantes portal) {

        portal.registaCliente("zeze", "z3z3", "Zé Ninguém", "Lisboa, Portugal",
                "ze.ninguem@ist.utl.pt", "1111");

        portal.registaCliente("mariazinha", "****", "Maria Silva", "Porto, Portugal",
                "maria.silva@ist.utl.pt", "2222");

        portal.registaCliente("alice", "aaa", "Alice Alves", "Aveiro, Portugal",
                "alice.alves@sonet.pt", "1001");

        portal.registaCliente("bruno", "bbb", "Bruno Bento", "Beja, Portugal",
                "bruno.bento@sonet.pt", "1002");

        portal.registaCliente("carlos", "ccc", "Carlos Calado", "Coimbra, Portugal",
                "carlos.calado@sonet.pt", "1003");

    }

    /**
     * Regista restaurantes Barriga Cheia e Barriga Feliz.
     * 
     * @param portal o portal da aplicação.
     * @param mng o gestor com username mng
     * @param pp o gestor com username pp
     */
    private static void registaRestaurantes(PortalDeRestaurantes portal, Gestor mng, Gestor pp) {
        portal.registaRestaurante("Barriga Cheia", "Lisboa, Portugal", mng);
        portal.registaRestaurante("Barriga Feliz", "Lisboa, Portugal", pp);
    }

    /**
     * Regista os clientes 'Passos Lebre' e 'Paulo Portão'.
     * 
     * @param portal o portal da aplicação.
     */
    private static void registaGestores(PortalDeRestaurantes portal) {
        portal.registaGestor("mng", "nm8", "Passos Lebre");
        portal.registaGestor("pp", "pp", "Paulo Portão");
    }
}
