import java.util.ArrayList;
import java.util.Set;

import jvstm.Atomic;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.rest.domain.Alimento;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Gestor;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.domain.Utilizador;


@RunWith(JUnit4.class)
public abstract class RestTest {

    protected static final String GESTOR_USER = "gestor1";
    protected static final String GESTOR_PASS = "123456";
    protected static final String GESTOR_NOME = "NomeDoGestorDaPassInsegura";

    protected static final String CLIENTE_USER = "francois131";
    protected static final String CLIENTE_PASS = "douche";
    protected static final String CLIENTE_NOME = "Francois Hollande";
    protected static final String CLIENTE_MORADA = "Champs Elysees";
    protected static final String CLIENTE_EMAIL = "francois131@republique.fr";
    protected static final int CLIENTE_SALDO = 999999;

    protected static final String REST_NOME = "EnfartaBrutos";
    protected static final String REST_MORADA = "Rua do Prazer";

    protected static final String ALIMENTO_NOME = "Bife";
    protected static final String ALIMENTO_TIPO = "Carne";

    protected static final String PRATO_NOME = "Bitoque";


    protected static PortalDeRestaurantes portal;

    static {
        if (FenixFramework.getConfig() == null) {
            FenixFramework.initialize(new Config() {
                {
                    domainModelPath = "src/main/dml/domain.dml";
                    dbAlias = "//localhost:3306/restdb";
                    dbUsername = "rest";
                    dbPassword = "r3st";
                    rootClass = PortalDeRestaurantes.class;
                }
            });
        }
    }

    public RestTest() {
        Transaction.begin();
        portal = FenixFramework.getRoot();
        Transaction.commit();
    }

    @Atomic
    @Before
    public void setUp() {
        cleanPortal();
        this.addCliente(CLIENTE_USER, CLIENTE_PASS, CLIENTE_NOME, CLIENTE_MORADA, CLIENTE_EMAIL);
        this.addGestor(GESTOR_USER, GESTOR_PASS, GESTOR_NOME);
        this.addRestaurante(REST_NOME, REST_MORADA, portal.getGestor(GESTOR_USER));
        this.addPrato(PRATO_NOME, criaAlimento(ALIMENTO_NOME, ALIMENTO_TIPO), 10, 3, REST_NOME);
    }

    @Atomic
    @After
    public void tearDown() {
        cleanPortal();
    }


    protected void cleanPortal() {
        if (portal == null) {
            portal = FenixFramework.getRoot();
        }
        Set<Restaurante> allRests = portal.getRestauranteSet();
        Set<Utilizador> allUsers = portal.getUtilizadorSet();
        allRests.clear();
        allUsers.clear();
        portal.setPrecoMaximo(-1);

    }


    protected ArrayList<Alimento> criaAlimento(String nome, String tipo) {
        Alimento alimento = new Alimento(nome, tipo);

        ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
        alimentos.add(alimento);

        return alimentos;
    }

    protected PortalDeRestaurantes getPortal() {
        return portal;
    }

    protected Prato getPrato(String nome, String restaurante) {
        return portal.getPrato(nome, restaurante);
    }


    protected Restaurante getRestaurante(String nome) {
        return portal.getRestaurante(nome);
    }


    protected Cliente getCliente(String username) {
        return portal.getCliente(username);
    }


    protected void addCliente(String user, String pass, String nome, String morada, String email) {
        portal.registaCliente(user, pass, nome, morada, email);
    }

    protected void addGestor(String user, String pass, String nome) {
        portal.registaGestor(user, pass, nome);
    }

    protected void addRestaurante(String nome, String morada, Gestor g) {
        portal.registaRestaurante(nome, morada, g);
    }

    protected void addPrato(String nome,
                            ArrayList<Alimento> alimentos,
                            int preco,
                            int calorias,
                            String restaurante) {
        portal.getRestaurante(restaurante).adicionaPrato(nome, preco, calorias, alimentos);
    }

    protected int numCompras(String nomeCliente) {
        return portal.getCliente(nomeCliente).getCompraCount();
    }
}
