package pt.ist.rest.presentation.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jvstm.Transaction;
import pt.chequerefeicao.ws.ChequeInexistente_Exception;
import pt.chequerefeicao.ws.ChequeJaUsado_Exception;
import pt.chequerefeicao.ws.UtilizadorInexistente_Exception;
import pt.ist.chequerefeicao.ChequeRefeicao;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.DatabaseBootstrap;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.exception.CoreException;
import pt.ist.rest.exception.core.invalidkey.InvalidDishException;
import pt.ist.rest.exception.core.invalidkey.InvalidPasswordException;
import pt.ist.rest.exception.core.unknowkey.NoSuchClienteException;
import pt.ist.rest.presentation.client.RestServlet;
import pt.ist.rest.presentation.shared.FieldVerifier;
import pt.ist.rest.service.ActualizarSaldoService;
import pt.ist.rest.service.ComprarPratoService;
import pt.ist.rest.service.MenuRestauranteService;
import pt.ist.rest.service.ObterRestaurantesService;
import pt.ist.rest.service.ObterTabuleiroDeComprasService;
import pt.ist.rest.service.PagamentoTabuleiroService;
import pt.ist.rest.service.ProcuraService;
import pt.ist.rest.service.TrocarImplService;
import pt.ist.rest.service.VerificarPasswordService;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;
import pt.ist.rest.service.dto.PortalDeRestaurantesDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;
import pt.ist.rest.service.dto.SearchDto;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RestServletImpl extends
        RemoteServiceServlet
        implements RestServlet {

    @Override
    public void initServer(boolean local) {
        DatabaseBootstrap.init();
        trocarImplementacao(local);
    }

    @Override
    public void login(ClienteDto cliente) throws InvalidPasswordException, NoSuchClienteException {

        if (!FieldVerifier.isValidLogin(cliente.getUser(), cliente.getPass())) {
            throw new InvalidPasswordException();
        }
        VerificarPasswordService passwordService = new VerificarPasswordService(cliente);
        passwordService.execute();
    }

    @Override
    public PortalDeRestaurantesDto obterRestaurantes() {
        ObterRestaurantesService obterRests = new ObterRestaurantesService();
        obterRests.execute();
        return obterRests.getResultado();
    }

    @Override
    public Collection<PratoDto> obterMenu(RestauranteDto rest) {
        MenuRestauranteService menuRest = new MenuRestauranteService(rest);
        menuRest.execute();
        return menuRest.getPratos();
    }

    @Override
    public ArrayList<ItemDto> obterTabuleiro(ClienteDto cliente) {
        ObterTabuleiroDeComprasService obterTabuleiro = new ObterTabuleiroDeComprasService(cliente);
        obterTabuleiro.execute();
        return obterTabuleiro.getResultado();
    }

    @Override
    public void alterarQuantidade(ItemDto item, ClienteDto cliente) {
        ComprarPratoService cPS = new ComprarPratoService(item, cliente);
        cPS.execute();

    }

    @Override
    public void adicionaPratoTabuleiro(ItemDto item, ClienteDto cliente) {
        ArrayList<ItemDto> itens = obterTabuleiro(cliente);
        String nomePrato = item.getPratoDto().getNome();
        String nomeRestArg = item.getRestauranteDto().getNome();

        for (ItemDto i : itens) {
            final String nomePratoI = i.getPratoDto().getNome();
            final String nomeRestI = i.getRestauranteDto().getNome();

            if (nomePratoI.equals(nomePrato) && nomeRestArg.equals(nomeRestI)) {
                throw new InvalidDishException();
            }
        }

        ComprarPratoService cPS = new ComprarPratoService(item, cliente);
        cPS.execute();
    }

    @Override
    public List<Integer> pagarTabuleiro(List<String> cheques, ClienteDto cliente) {
        try {
            Transaction.begin();
            PortalDeRestaurantes portal = FenixFramework.getRoot();
            ChequeRefeicao servicoCheques = portal.getChequeRefeicao();
            int valorCheques = servicoCheques.sacar(cliente.getUser(), cheques);

            System.err.println("valor dos cheques: " + valorCheques);

            ActualizarSaldoService servicoSaldo = new ActualizarSaldoService(valorCheques, cliente);
            servicoSaldo.dispatch();

            Transaction.commit();
        } catch (ChequeInexistente_Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ChequeJaUsado_Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UtilizadorInexistente_Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PagamentoTabuleiroService servicoPagamento = new PagamentoTabuleiroService(cliente);
        servicoPagamento.execute();

        return servicoPagamento.getId();
    }

    @Override
    public ArrayList<PratoDto> obterPesquisa(ArrayList<SearchDto> pesquisa) {

        ProcuraService ps = new ProcuraService(pesquisa);
        ArrayList<PratoDto> resultado = null;

        ps.execute();
        resultado = ps.getResultado();

        return resultado;
    }

    // troca a versão local para a remota
    @Override
    public void trocarImplementacao(boolean local) {
        TrocarImplService trocarImpl = new TrocarImplService(local);
        trocarImpl.execute();
    }
}
