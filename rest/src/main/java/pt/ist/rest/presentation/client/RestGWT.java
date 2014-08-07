package pt.ist.rest.presentation.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RestGWT
        implements EntryPoint {
    /**
     * The message displayed to the user when the server cannot be reached or returns an
     * error.
     */
    public static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final RestServletAsync rpcService = GWT.create(RestServlet.class);

    /**
     * @return the rpcService
     */
    public RestServletAsync getRpcService() {
        return this.rpcService;
    }

    private LoginPage loginPage;

    private RestManagementPage managementPage;

    /**
     * Contains the error message displayed to the user.
     */
    private final Label errorMessage = new Label("");

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {

        RootPanel.get().add(errorMessage);

        final boolean isLocal = RootPanel.get("isOnline") == null;
        rpcService.initServer(isLocal, new AsyncCallback<Void>() {

            @Override
            public void onSuccess(Void result) {
                System.out.println("Inicializou bem.");
            }

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Erro na inicialização");
                showErrorMessage("Not able to init aplication server bridge: "
                        + caught.getMessage());
            }
        });




        loginPage = new LoginPage(this, rpcService);
        managementPage = new RestManagementPage(this, rpcService);

        showLoginPage();
    }

    // show the contact page of the logged person
    void showLoginPage() {
        RootPanel.get("managementContainer").clear();
        loginPage.cleanPage();
        RootPanel.get("loginContainer").add(loginPage);
        errorMessage.setText("");
    }

    // show the contact page of the logged person
    void showManagementPage(String cliente) {
        RootPanel.get("loginContainer").clear();
        managementPage.cleanPage();
        managementPage.showPage(cliente);
        errorMessage.setText("");
    }

    public void showErrorMessage(String message) {
        errorMessage.setText(message);
    }

    public void cleanError() {
        errorMessage.setText("");
    }
}
