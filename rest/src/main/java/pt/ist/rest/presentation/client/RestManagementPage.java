package pt.ist.rest.presentation.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;
import pt.ist.rest.service.dto.PortalDeRestaurantesDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;
import pt.ist.rest.service.dto.SearchDto;
import pt.ist.rest.service.dto.SearchEnum;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Classe responsável por gerir as opções de um utilizador no portal. .... (completar) O
 * utilizador pode fazer logout do sistema, clicando no botão "Logout", que recarrega a
 * página de login.
 */
public class RestManagementPage extends
        Composite {

    private String loggedPerson;
    private final HTML htmlPerson = new HTML("");
    private final Button btnLogout;

    /** Invoca remotamente o serviço obter restaurantes existentes no portal. */
    private final ToggleButton btnListarRests;

    private final ToggleButton btnTabuleiro;
    private final ToggleButton btnAbrirPesquisa;

    private final HTML htmlListaRests = new HTML("");
    private final HTML htmlTab = new HTML("");

    private final FlexTable flexTable = new FlexTable();
    private final FlexTable flexTableRests = new FlexTable();
    private final FlexTable flexTableTab = new FlexTable();
    private final FlexTable flexTablePesquisa = new FlexTable();

    private final Button btnPesquisarPratos;
    private final TextBox txtBoxPesquisa = new TextBox();
    private final RadioButton tipoVeg = new RadioButton("Tipo", "Vegetariano");
    private final RadioButton tipoCar = new RadioButton("Tipo", "Carne");
    private final RadioButton tipoPei = new RadioButton("Tipo", "Peixe");

    private final VerticalPanel divisaoGlobal = new VerticalPanel();
    private final HorizontalPanel divisaoLogin = new HorizontalPanel();

    private final RestGWT rootPage;



    public RestManagementPage(final RestGWT rootPage, final RestServletAsync rpcService) {
        this.rootPage = rootPage;

        btnLogout = new Button("Logout", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                htmlPerson.setText("");
                rootPage.showLoginPage();
            }
        });

        btnListarRests = new ToggleButton("Obter Restaurantes", "Fechar lista", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (btnListarRests.isDown()) {
                    rootPage.getRpcService().obterRestaurantes(
                            new AsyncCallback<PortalDeRestaurantesDto>() {

                                @Override
                                public void onSuccess(PortalDeRestaurantesDto result) {
                                    cleanPage();

                                    btnListarRests.setDown(true);
                                    apresentarRestaurantes(result.getListaRestaurantes());
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    rootPage.showErrorMessage("Não é possível listar os restaurantes");
                                    caught.printStackTrace();
                                }
                            });
                } else {
                    cleanRestList();
                }
            }
        });


        btnTabuleiro = new ToggleButton("Tabuleiro de Compras", "Esconder Tabuleiro",
                new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        if (btnTabuleiro.isDown()) {
                            final ClienteDto cliente = new ClienteDto(loggedPerson);
                            rootPage.getRpcService().obterTabuleiro(cliente,
                                    new AsyncCallback<ArrayList<ItemDto>>() {

                                        @Override
                                        public void onSuccess(ArrayList<ItemDto> result) {
                                            cleanPage();
                                            btnTabuleiro.setDown(true);
                                            apresentarTabuleiro(result);
                                        }

                                        @Override
                                        public void onFailure(Throwable caught) {
                                            rootPage.showErrorMessage("Não é possível apresentar o tabuleiro");
                                            caught.printStackTrace();
                                        }
                                    });
                        } else {
                            cleanTabuleiro();
                        }
                    }
                });

        btnPesquisarPratos = new Button("Pesquisar", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ArrayList<SearchDto> arg = new ArrayList<SearchDto>();

                if (!txtBoxPesquisa.getValue().isEmpty()) {
                    arg.add(new SearchDto(SearchEnum.NOME.toString(), txtBoxPesquisa.getValue()
                            .toString()));
                }

                if (tipoVeg.getValue()) {
                    arg.add(new SearchDto(SearchEnum.TIPO.toString(), tipoVeg.getText()));
                } else if (tipoCar.getValue()) {
                    arg.add(new SearchDto(SearchEnum.TIPO.toString(), tipoCar.getText()));
                } else if (tipoPei.getValue()) {
                    arg.add(new SearchDto(SearchEnum.TIPO.toString(), tipoPei.getText()));
                }

                rootPage.getRpcService().obterPesquisa(arg,
                        new AsyncCallback<ArrayList<PratoDto>>() {

                            @Override
                            public void onSuccess(ArrayList<PratoDto> result) {
                                cleanPage();
                                resultadoPesquisa(result);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                rootPage.showErrorMessage("Não foi possiver efectuar pesquisa");
                                caught.printStackTrace();
                            }
                        });

            }
        });

        btnAbrirPesquisa = new ToggleButton("Pesquisa de Pratos", "Esconder Pesquisa",
                new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        if (btnAbrirPesquisa.isDown()) {
                            cleanPage();
                            btnAbrirPesquisa.setDown(true);
                            flexTablePesquisa.setWidget(0, 0, new HTML(""));
                            flexTablePesquisa.setWidget(1, 0, txtBoxPesquisa);
                            flexTablePesquisa.setWidget(1, 1, btnPesquisarPratos);
                            flexTablePesquisa.setWidget(2, 0, tipoVeg);
                            flexTablePesquisa.setWidget(2, 1, tipoCar);
                            flexTablePesquisa.setWidget(2, 2, tipoPei);
                        } else {
                            cleanPesquisa();
                        }
                    }
                });


        divisaoLogin.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        divisaoLogin.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        divisaoLogin.add(htmlPerson);
        divisaoLogin.add(btnLogout);
        divisaoGlobal.add(divisaoLogin);
        divisaoGlobal.add(flexTable);
        flexTable.setWidget(1, 0, btnListarRests);
        flexTable.setWidget(2, 0, flexTableRests);
        flexTable.setWidget(1, 1, btnTabuleiro);
        flexTable.setWidget(2, 1, flexTableTab);
        flexTable.setWidget(1, 2, btnAbrirPesquisa);
        flexTable.setWidget(2, 2, flexTablePesquisa);

        initWidget(divisaoGlobal);
    }

    void showPage(String loggedPerson) {
        this.loggedPerson = loggedPerson;
        RootPanel.get("managementContainer").add(this);
        htmlPerson.setHTML("<h2>Logged in como <b>" + loggedPerson + "</b></h2>");
    }

    /**
     * Cria, a partir do serviço, a lista de Restaurantes (formatada) e insere-a na zona
     * de HTML.
     * 
     * @param listaRestaurantes Lista de RestaurantesDto
     */
    void apresentarRestaurantes(List<RestauranteDto> listaRestaurantes) {

        int row = 1;
        flexTableRests.setWidget(0, 0, htmlListaRests);
        htmlListaRests.setHTML("<h2>Lista de Restaurantes existentes no Portal:</h2>");
        flexTableRests.setWidget(0, 0, new HTML("<b>Nome</b>"));
        flexTableRests.setWidget(0, 1, new HTML("<b>Morada</b>"));
        flexTableRests.setWidget(0, 2, new HTML("<b>Classificação</b>"));
        for (RestauranteDto r : listaRestaurantes) {
            flexTableRests.setWidget(row, 0, new HTML(r.getNome()));
            flexTableRests.setWidget(row, 1, new HTML(r.getMorada()));
            flexTableRests.setWidget(row, 2, new HTML("" + r.getRating()));
            flexTableRests.setWidget(row, 3, apresentarBotaoRest(r, listaRestaurantes));

            row++;
        }

    }

    private Button apresentarBotaoRest(final RestauranteDto r,
                                       final List<RestauranteDto> listaRestaurantes) {

        final Button btnVoltar = new Button("Menu anterior", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                flexTable.setWidget(1, 0, btnListarRests);
                flexTable.setWidget(2, 0, flexTableRests);
                flexTable.setWidget(1, 1, btnTabuleiro);
                flexTable.setWidget(2, 1, flexTableTab);
                flexTable.setWidget(1, 2, btnAbrirPesquisa);
                flexTable.setWidget(2, 2, flexTablePesquisa);
                rootPage.cleanError();
            }
        });

        final Button btnMenu = new Button("Ver menu", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                rootPage.getRpcService().obterMenu(r, new AsyncCallback<Collection<PratoDto>>() {

                    @Override
                    public void onSuccess(Collection<PratoDto> result) {
                        flexTable.setWidget(1, 0, btnVoltar);
                        flexTable.setWidget(2, 0, apresentarMenu(result, r.getNome()));
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        rootPage.showErrorMessage("Não é possível mostrar o menu");
                        caught.printStackTrace();
                    }
                });
            }
        });

        return btnMenu;
    }


    private FlexTable apresentarBotaoAdPrato(final PratoDto p, final String restaurante) {

        FlexTable flexTableLocal = new FlexTable();
        final HTML labelResposta = new HTML();
        final IntegerBox intBox = new IntegerBox();
        intBox.setValue(new Integer(1));

        final Button btnAdicionar = new Button("Adicionar Prato", new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                final int boxValue = intBox.getValue().intValue();
                labelResposta.setHTML(p.getRestaurante());
                if (boxValue > 0) {
                    rootPage.getRpcService().adicionaPratoTabuleiro(
                            new ItemDto(p, boxValue, new RestauranteDto(restaurante)),
                            new ClienteDto(loggedPerson), new AsyncCallback<Void>() {

                                @Override
                                public void onSuccess(Void v) {
                                    labelResposta.setHTML("<b>Prato adicionado com sucesso.</b>");
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    rootPage.showErrorMessage("Não foi possível adicionar o prato ao tabuleiro.");
                                    caught.printStackTrace();
                                }
                            });
                } else {
                    rootPage.showErrorMessage("Não foi possível adicionar o prato ao tabuleiro.");
                }
            }
        });

        flexTableLocal.setWidget(0, 0, btnAdicionar);
        flexTableLocal.setWidget(0, 1, new HTML("Qtd: "));
        flexTableLocal.setWidget(0, 2, intBox);
        flexTableLocal.setWidget(0, 3, labelResposta);
        return flexTableLocal;


    }

    private FlexTable apresentarMenu(Collection<PratoDto> pratos, String restaurante) {
        int row = 2;
        FlexTable flexTableLocal = new FlexTable();
        flexTableLocal.setWidget(0, 0, new HTML("<h3>" + restaurante + "</h3>"));
        flexTableLocal.setWidget(1, 0, new HTML("<b>Nome</b>"));
        flexTableLocal.setWidget(1, 1, new HTML("<b>Calorias</b>"));
        flexTableLocal.setWidget(1, 2, new HTML("<b>Preço</b>"));
        flexTableLocal.setWidget(1, 3, new HTML("<b>Classificação</b>"));
        flexTableLocal.setWidget(1, 4, new HTML("<b>Adicionar prato a tabuleiro</b>"));
        for (PratoDto p : pratos) {
            flexTableLocal.setWidget(row, 0, new HTML(p.getNome()));
            flexTableLocal.setWidget(row, 1, new HTML("" + p.getCalorias()));
            flexTableLocal.setWidget(row, 2, new HTML("" + p.getPreco()));
            flexTableLocal.setWidget(row, 3, new HTML("" + p.getClassificacao()));
            flexTableLocal.setWidget(row, 4, apresentarBotaoAdPrato(p, restaurante));
            row++;
        }

        return flexTableLocal;
    }

    private FlexTable resultadoPesquisa(final ArrayList<PratoDto> itens) {
        int row = 2;

        flexTableRests.setWidget(0, 0, htmlTab);
        if (itens.isEmpty()) {
            flexTablePesquisa.setWidget(row, 0, new HTML(
                    "Não foram encontrados resultados para a pesquisa"));
        } else {
            flexTablePesquisa.setWidget(0, 0, new HTML("<h3>Pratos Encontrados</h3>"));
            flexTablePesquisa.setWidget(1, 0, new HTML("<b>Nome</b>"));
            flexTablePesquisa.setWidget(1, 1, new HTML("<b>Restaurante</b>"));
            flexTablePesquisa.setWidget(1, 2, new HTML("<b>Preço</b>"));
            for (PratoDto i : itens) {

                //@formatter:off
                flexTablePesquisa.setWidget(row, 0, new HTML(i.getNome()));
                flexTablePesquisa.setWidget(row, 1, new HTML(i.getRestaurante()));
                flexTablePesquisa.setWidget(row, 2, new HTML("" + i.getPreco()));
                flexTablePesquisa.setWidget(row, 3, apresentarBotaoAdPrato(i, i.getRestaurante()));
                row++;
                //@formatter:on
            }
        }

        return flexTablePesquisa;
    }

    private FlexTable apresentarTabuleiro(final ArrayList<ItemDto> itens) {
        int row = 2;
        int precoPratos = 0;

        flexTableRests.setWidget(0, 0, htmlTab);
        if (itens.isEmpty()) {
            flexTableTab.setWidget(row, 0, new HTML("Não existem pratos no tabuleiro"));
        } else {
            flexTableTab.setWidget(0, 0, new HTML("<h3>Tabuleiro de Compras</h3>"));
            flexTableTab.setWidget(1, 0, new HTML("<b>Nome</b>"));
            flexTableTab.setWidget(1, 1, new HTML("<b>Restaurante</b>"));
            flexTableTab.setWidget(1, 2, new HTML("<b>Preço</b>"));
            flexTableTab.setWidget(1, 3, new HTML("<b>Quantidade</b>"));
            for (ItemDto i : itens) {
                //@formatter:off
                precoPratos += i.getPratoDto().getPreco() * i.getQuantidade();
                flexTableTab.setWidget(row, 0, new HTML(i.getPratoDto().getNome()));
                flexTableTab.setWidget(row, 1, new HTML(i.getRestauranteDto().getNome()));
                flexTableTab.setWidget(row, 2, new HTML("" + i.getPratoDto().getPreco()));
                flexTableTab.setWidget(row, 3, new HTML("" + i.getQuantidade()));
                flexTableTab.setWidget(row, 4, apresentarBotaoAltQ(i, new ClienteDto(loggedPerson)));
                row++;
                //@formatter:on
            }
        }

        final TextBox textBox = new TextBox();
        flexTableTab.setWidget(row + 1, 0, new HTML("Insira cheques"));
        flexTableTab.setWidget(row + 2, 0, textBox);
        //flexTableTab.setWidget(row+2, 0, new HTML("Saldo: "+ loggedPerson));
        flexTableTab.setWidget(row, 0, new HTML("Preço total: " + precoPratos));

        final SubmitButton btnPagar = new SubmitButton("Pagar", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                rootPage.getRpcService().pagarTabuleiro(
                        new ArrayList<String>(Arrays.asList(textBox.getText().split(","))),
                        new ClienteDto(loggedPerson), new AsyncCallback<List<Integer>>() {
                            @Override
                            public void onSuccess(List<Integer> id) {
                                rootPage.showErrorMessage("Pagamento do tabuleiro efectuado.\n Fatura:\n Número de série: "
                                        + id.get(0) + " Número de sequência: " + id.get(1));
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                rootPage.showErrorMessage("Não foi possível efectuar a compra. Creditado o valor do cheque na conta do cliente.");
                                caught.printStackTrace();
                            }

                        });
            }
        });
        flexTableTab.setWidget(row + 2, 1, btnPagar);
        return flexTableTab;
    }

    private Button apresentarBotaoAltQ(final ItemDto i, final ClienteDto c) {

        final Button btnVoltar = new Button("Menu anterior", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                flexTable.setWidget(1, 1, btnTabuleiro);
                flexTable.setWidget(2, 1, flexTableTab);
            }
        });

        final Button btnAlterar = new Button("Alterar Quantidade", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                flexTable.setWidget(1, 1, btnVoltar);
                flexTable.setWidget(2, 1, alterarQuantMenu(i, c));
            }
        });
        return btnAlterar;
    }

    private FlexTable alterarQuantMenu(final ItemDto i, final ClienteDto c) {

        FlexTable flexTableLocal = new FlexTable();
        final IntegerBox tb = new IntegerBox();
        flexTableLocal.setWidget(0, 0, new HTML("<h3>" + i.getPratoDto().getNome() + "</h3>"));
        flexTableLocal.setWidget(1, 0, new HTML("<b>Adicionar Quantidade</b>"));

        flexTableLocal.setWidget(2, 0, tb);

        final SubmitButton btnSubmit = new SubmitButton("Submit", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                rootPage.getRpcService().alterarQuantidade(
                        new ItemDto(i.getPratoDto(), tb.getValue().intValue(),
                                i.getRestauranteDto()), c, new AsyncCallback<Void>() {

                            @Override
                            public void onSuccess(Void v) {
                                flexTable.setWidget(1, 1, btnTabuleiro);
                                flexTable.setWidget(2, 1, flexTableTab);
                                cleanTabuleiro();
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                rootPage.showErrorMessage("Não foi possível alterar a quantidade ");
                                caught.printStackTrace();
                            }
                        });
            }
        });

        flexTableLocal.setWidget(2, 1, btnSubmit);

        return flexTableLocal;
    }

    /**
     * Limpa o texto da tabela de restaurantes.
     */
    void cleanRestList() {
        flexTable.setWidget(1, 0, btnListarRests);
        flexTable.setWidget(2, 0, flexTableRests);
        flexTableRests.clear();
        btnListarRests.setDown(false);
        btnAbrirPesquisa.setDown(false);
        rootPage.cleanError();
    }

    void cleanTabuleiro() {
        flexTable.setWidget(1, 1, btnTabuleiro);
        flexTable.setWidget(2, 1, flexTableTab);
        flexTableTab.clear();
        btnTabuleiro.setDown(false);
        btnAbrirPesquisa.setDown(false);
        rootPage.cleanError();
    }

    void cleanPesquisa() {
        flexTable.setWidget(1, 2, btnAbrirPesquisa);
        flexTable.setWidget(2, 2, flexTablePesquisa);
        flexTablePesquisa.clear();
        btnTabuleiro.setDown(false);
        btnListarRests.setDown(false);
        rootPage.cleanError();
    }

    void cleanPage() {
        cleanRestList();
        cleanTabuleiro();
        cleanPesquisa();
        rootPage.cleanError();
    }
}
