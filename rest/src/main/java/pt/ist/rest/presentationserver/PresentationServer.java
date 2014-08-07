package pt.ist.rest.presentationserver;

import java.util.ArrayList;
// import java.util.Collection;
import java.util.List;

import jvstm.Atomic;
import pt.ist.chequerefeicao.ChequeRefeicao;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.rest.domain.Alimento;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Gestor;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.domain.QuantidadePrato;
import pt.ist.rest.domain.Utilizador;
import pt.ist.rest.exception.CoreException;
import pt.ist.rest.service.ActualizarSaldoService;
import pt.ist.rest.service.ComprarPratoService;
import pt.ist.rest.service.GostarPratoService;
import pt.ist.rest.service.MenuRestauranteService;
import pt.ist.rest.service.ObterRestaurantesService;
import pt.ist.rest.service.PagamentoTabuleiroService;
// import pt.ist.rest.service.ProcurarPratosTipoService;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;
import pt.ist.rest.service.dto.PortalDeRestaurantesDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;

/**
 * Esta classe actua exclusivamente como Servidor de Apresentação imprimindo os dados a
 * partir de informação persistente.
 */

public class PresentationServer {


    /**
     * Permite a um gestor adicionar o prato bitoque a um restaurante.
     * 
     * @param portal o portal da aplicação
     * @param gestor o gestor que vai adicionar o prato
     * @param nomeRest o restaurante a que vai ser adicionado o prato
     */
    @Atomic
    private static void adicionaBitoqueRestaurante(PortalDeRestaurantes portal,
                                                   Gestor gestor,
                                                   String nomeRest) {
        try {
            Alimento alimento = new Alimento("Bife", "Carne");
            ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
            alimentos.add(alimento);
            gestor.adicionaPrato(portal.getRestaurante(nomeRest), "Bitoque", 8, 4, alimentos);
        } catch (CoreException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adiciona o prato Canja de Galinha do restaurante Barriga Feliz com a quantidade 3
     * ao tabuleiro de compras do Zeze.
     */
    private static void adicionaTabuleiroZeze(String restaurante) {
        try {
            RestauranteDto rest = new RestauranteDto(restaurante);
            PratoDto prato1 = new PratoDto("Canja de Galinha");
            PratoDto prato2 = new PratoDto("Bacalhau com batatas");
            ItemDto item1 = new ItemDto(prato1, 3, rest);
            ItemDto item2 = new ItemDto(prato2, 2, rest);
            ClienteDto cliente = new ClienteDto("zeze");

            ComprarPratoService servico1 = new ComprarPratoService(item1, cliente);
            ComprarPratoService servico2 = new ComprarPratoService(item2, cliente);

            servico1.execute();
            servico2.execute();
        } catch (CoreException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Imprime lista de restaurantes.
     * 
     * @param lista a lista de restaurantes a imprimir.
     */
    protected static void imprimeListaRestaurante(PortalDeRestaurantesDto portal) {
        try {
            for (RestauranteDto r : portal.getListaRestaurantes()) {
                System.out.println("Restaurante " + r.getNome() + " " + r.getMorada());

                MenuRestauranteService ementa = new MenuRestauranteService(r);
                ementa.execute();

                for (PratoDto p : ementa.getPratos()) {
                    System.out.println("    " + p.getNome() + " " + p.getPreco() + " "
                            + p.getCalorias());
                }
            }
        } catch (CoreException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Imprime lista de utilizadores.
     * 
     * @param lista a lista de utilizadores a imprimir.
     */
    protected static void imprimeListaUtilizador(List<Utilizador> lista) {
        try {
            for (Utilizador u : lista) {
                System.out.println(u.toString());
            }
        } catch (CoreException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Imprime ratings de dois restaurantes.
     * 
     * @param portal o portal da aplicação
     * @param restaurante1 o nome do primeiro restaurante
     * @param restaurante2 o nome do segundo restaurante
     */
    private static void imprimeRatings(PortalDeRestaurantes portal) {
        try {
            ObterRestaurantesService restaurantes = new ObterRestaurantesService();
            restaurantes.execute();

            for (RestauranteDto r : restaurantes.getResultado().getListaRestaurantes()) {
                System.out.println("Rating de " + r.getNome() + " - " + r.getRating());
            }

        } catch (CoreException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Imprime restaurantes listados no portal da aplicação.
     * 
     * @param portal o portal da aplicação
     */
    private static void imprimeRestaurantes(PortalDeRestaurantes portal) {
        try {
            ObterRestaurantesService restaurantes = new ObterRestaurantesService();
            restaurantes.execute();

            imprimeListaRestaurante(restaurantes.getResultado());
        } catch (CoreException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Imprime tabuleiro de compras do cliente.
     * 
     * @param portal o portal da aplicação
     * @param cliente o cliente do qual se imprime o tabuleiro.
     */
    @Atomic
    private static void imprimeTabuleiro(PortalDeRestaurantes portal, String cliente) {
        try {
            Cliente c = portal.getCliente(cliente);

            System.out.println("Tabuleiro de Compras de " + cliente);

            for (QuantidadePrato q : c.getTabuleiro().getQuantidadePrato()) {
                System.out.println("    " + q.getPrato().getNome() + " - "
                        + q.getPrato().getRestaurante().getNome() + " - " + q.getQuantidade());
            }
        } catch (CoreException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Imprime utilizadores listados no portal da aplicação.
     * 
     * @param portal o portal da aplicação
     */
    @Atomic
    private static void imprimeUtilizadores(PortalDeRestaurantes portal) {
        try {
            imprimeListaUtilizador(portal.getUtilizador());
        } catch (CoreException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Permite à cliente Maria gostar de um prato.
     * 
     * @param portal o portal da aplicação.
     * @param restaurante o restaurante que contém o prato.
     */
    private static void mariaGostaPrato(String restaurante) {
        try {
            RestauranteDto rest = new RestauranteDto(restaurante);
            PratoDto prato1 = new PratoDto("Canja de Galinha");
            ClienteDto cliente = new ClienteDto("mariazinha");

            GostarPratoService servico1 = new GostarPratoService(cliente, prato1, rest);

            servico1.execute();
        } catch (CoreException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Regista um restaurante.
     * 
     * @param portal o portal da aplicação.
     * @param gestor o gestor que vai gerir o restaurante
     * @param nome o nome do restaurante a registar
     * @param morada a morada do restaurante
     */
    @Atomic
    private static void registaRestaurante(PortalDeRestaurantes portal,
                                           Gestor gestor,
                                           String nome,
                                           String morada) {
        try {
            portal.registaRestaurante(nome, morada, gestor);
        } catch (CoreException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Permite ao cliente Zeze gostar de vários pratos.
     * 
     * @param portal o portal da aplicação.
     * @param restaurante o restaurante que contém o prato.
     */
    private static void zezeGostaPratos(String restaurante) {

        try {
            RestauranteDto rest = new RestauranteDto(restaurante);
            PratoDto prato1 = new PratoDto("Bitoque");
            PratoDto prato2 = new PratoDto("Canja de Galinha");
            PratoDto prato3 = new PratoDto("Bacalhau com batatas");
            ClienteDto cliente = new ClienteDto("zeze");

            GostarPratoService servico1 = new GostarPratoService(cliente, prato1, rest);
            GostarPratoService servico2 = new GostarPratoService(cliente, prato2, rest);
            GostarPratoService servico3 = new GostarPratoService(cliente, prato3, rest);

            servico1.execute();
            servico2.execute();
            servico3.execute();
        } catch (CoreException e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * Contém as funções que procuram e imprimem a informação a apresentar.
     * 
     * @throws CheckAlreadyUsedException
     * @throws InvalidCheckException
     */
    protected static void apresentacao() {

        PortalDeRestaurantes portal = null;
        Gestor gestorPP = null;

        try {
            Transaction.begin();
            portal = FenixFramework.getRoot();
            gestorPP = portal.getGestor("pp");
            assert gestorPP != null : "PresentationServer: gestor e' null";
            Transaction.commit();
        } catch (RuntimeException e) {
            System.out.println("Erro a inicializar as variaveis.");
            Transaction.abort();
        }


        registaRestaurante(portal, gestorPP, "Barriga Cheia", "Porto, Portugal");

        imprimeUtilizadores(portal);

        imprimeRestaurantes(portal);

        adicionaBitoqueRestaurante(portal, gestorPP, "Barriga Cheia");

        imprimeRatings(portal);

        zezeGostaPratos("Barriga Feliz");

        mariaGostaPrato("Barriga Feliz");

        imprimeRatings(portal);

        adicionaTabuleiroZeze("Barriga Feliz");

        imprimeTabuleiro(portal, "zeze");

        imprimeTabuleiro(portal, "mariazinha");

        List<String> cheques = new ArrayList<String>();
        cheques.add("19");

        ClienteDto zezeDto = new ClienteDto("zeze");

        efectuarPagamentoCompra(cheques, zezeDto);

        imprimeTabuleiro(portal, "zeze");
    }


    /**
     * Função que acrescenta o valor dos cheques recebidos ao saldo de um cliente e
     * finaliza a compra actualmente no seu tabuleiro.
     * 
     * @param cheques Lista de cheques
     * @param cliente Cliente que vai receber o valor dos cheques
     * @throws InvalidCheckException Cheque inválido
     * @throws CheckAlreadyUsedException Cheque já está a ser usado
     */
    public static void efectuarPagamentoCompra(List<String> cheques, ClienteDto cliente) {
        /*
        ChequeRefeicao servicoCheques = new ChequeRefeicao();
        int valorCheques = servicoCheques.cashChecks(cliente.getUser(), cheques);

        ActualizarSaldoService servicoSaldo = new ActualizarSaldoService(valorCheques, cliente);
        servicoSaldo.execute();

        PagamentoTabuleiroService servicoPagamento = new PagamentoTabuleiroService(cliente);
        servicoPagamento.execute();

        */
        //TODO na 4a parte do projecto, acrescentar a parte da factura 
        //Implementação provável (repetir):
        //RegistoFacturaService servicoFactura = new RegistoFacturaService(cliente, ?);
        //servicoFactura.execute();
    }


    /**
     * O método main, encarregue da ligação à base de dados "restdb".
     * 
     * @param args os argumentos do método main
     * @throws CheckAlreadyUsedException
     * @throws InvalidCheckException
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

        apresentacao();

    }
}
