package pt.ist.rest.service;

import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Compra;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.QuantidadePrato;
import pt.ist.rest.exception.core.unknowkey.NoSuchClienteException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;
import pt.ist.rest.service.dto.PratoDto;

/**
 * The Class ObterTabuleiroDeComprasService.
 */
public class ObterTabuleiroDeComprasService extends
        RestService {

    /**
     * Conteudo do tabuleiro.
     */
    private ArrayList<ItemDto> inTab;

    /**
     * The username do cliente.
     */
    private static String username;

    /**
     * Instantiates a new obter tabuleiro de compras service.
     * 
     * @param cliente the cliente que possui o tabuleiro
     */
    public ObterTabuleiroDeComprasService(ClienteDto cliente) {
        username = cliente.getUser();
    }

    /**
     * Obtem tabulerio de compras de um cliente.
     * 
     * @throws NoSuchClienteException quando o cliente não existe;
     * @see pt.ist.rest.service.RestService#dispatch()
     */
    @Override
    public final void dispatch() throws NoSuchClienteException {
        PortalDeRestaurantes pdr = FenixFramework.getRoot();
        inTab = new ArrayList<ItemDto>();
        Cliente c = pdr.getCliente(username);
        if (c == null) {
            throw new NoSuchClienteException(username);
        }
        Compra tabuleiro = c.getTabuleiro();
        for (QuantidadePrato qP : tabuleiro.getQuantidadePrato()) {
            Prato prat = qP.getPrato();
            int quant = qP.getQuantidade();
            inTab.add(new ItemDto(new PratoDto(prat.getNome(), prat.getId(), prat.getPreco(), prat
                    .getRestaurante().getNome()), quant));
        }
    }

    /**
     * Gets the resultado.
     * 
     * @return the resultado
     */
    public ArrayList<ItemDto> getResultado() {
        return inTab;
    }

}
