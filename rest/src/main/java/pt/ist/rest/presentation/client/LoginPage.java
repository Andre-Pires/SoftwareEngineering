package pt.ist.rest.presentation.client;

import pt.ist.rest.exception.CoreException;
import pt.ist.rest.service.dto.ClienteDto;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class LoginPage extends
        Composite {

    private final Button btnLogin;
    private final TextBox nameField;
    private final TextBox passwordField;
    /**
     * Contains the result message from the login action.
     */
    private final Label messageLabel;


    public LoginPage(final RestGWT rootPage, final RestServletAsync rpcService) {
        FlexTable flexTable = new FlexTable();

        Label lblPerson = new Label("Username:");
        Label lblPassword = new Label("Password:");
        lblPerson.setStyleName("label");
        lblPassword.setStyleName("label");
        flexTable.setWidget(0, 0, lblPerson);
        flexTable.setWidget(2, 0, lblPassword);
        lblPerson.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        lblPassword.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

        nameField = new TextBox();
        passwordField = new PasswordTextBox();
        messageLabel = new Label("");
        flexTable.setWidget(1, 0, nameField);
        flexTable.setWidget(3, 0, passwordField);
        flexTable.setWidget(4, 0, messageLabel);

        nameField.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    doLogin(rootPage, rpcService);
                }
            }
        });

        passwordField.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    doLogin(rootPage, rpcService);
                }
            }
        });

        btnLogin = new Button("login");
        flexTable.setWidget(3, 1, btnLogin);

        btnLogin.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doLogin(rootPage, rpcService);
            }
        });

        initWidget(flexTable);
    }

    private final void doLogin(final RestGWT rootPage, final RestServletAsync rpcService) {
        // if any of the fields is empty, show warning:
        if (this.nameField.getText().equals("")) {
            rootPage.showErrorMessage("Please fill the name of the person!");
            return;
        }

        String nameToServer = nameField.getText();
        String passToServer = passwordField.getText();
        final ClienteDto cliente = new ClienteDto(nameToServer, passToServer);
        rpcService.login(cliente, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                if (caught instanceof CoreException) {
                    rootPage.showErrorMessage(caught.getMessage());
                } else {
                    rootPage.showErrorMessage(RestGWT.SERVER_ERROR);
                }
            }

            @Override
            public void onSuccess(Void result) {
                final String msg = "Bem vindo! Login efectuado com sucesso.";
                messageLabel.setText(msg);
                rootPage.showManagementPage(cliente.getUser());
            }
        });
    }

    /**
     * Limpa o conteúdo das labels e as textboxes.
     */
    public void cleanPage() {
        nameField.setText("");
        passwordField.setText("");
        messageLabel.setText("");
    }
}
