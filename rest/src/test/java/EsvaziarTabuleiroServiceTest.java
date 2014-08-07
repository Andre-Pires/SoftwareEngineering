import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jvstm.Atomic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pt.ist.rest.domain.Cliente;
import pt.ist.rest.exception.CoreException;
import pt.ist.rest.service.EsvaziaTabuleiroComprasService;
import pt.ist.rest.service.dto.ClienteDto;

@RunWith(JUnit4.class)
public class EsvaziarTabuleiroServiceTest extends
        RestTest {

    public EsvaziarTabuleiroServiceTest() {
        super();
    }

    @Test
    @Atomic
    public void esvaziarTabuleiroVazio() {
        //Arrange
        Cliente cliente = this.getCliente(CLIENTE_USER);
        // o setUp() pode ter corrido mal
        assert cliente != null : "esvaziarTabuleiroVazio: cliente é null";
        EsvaziaTabuleiroComprasService serv = new EsvaziaTabuleiroComprasService(new ClienteDto(
                CLIENTE_USER));

        //Act
        try {
            serv.dispatch();
        } catch (CoreException e) {
            fail("Não devia!");
            System.out.println("falhou: " + e.getMessage());
        }

        //Assert
        assertEquals("O numero de compras é 1 (tabuleiro apenas)", super.numCompras(CLIENTE_USER),
                1);
        assertTrue("tabuleiro tem de estar vazio", !cliente.getTabuleiro().hasAnyQuantidadePrato());
    }

    @Test
    @Atomic
    public void esvaziarTabuleiroComCompras() {
        //Arrange
        Cliente cliente = this.getCliente(CLIENTE_USER);
        assert cliente != null : "cliente é null";
        final int quant = 5;
        cliente.comprarPrato(this.getPrato(PRATO_NOME, REST_NOME), quant);
        EsvaziaTabuleiroComprasService serv = new EsvaziaTabuleiroComprasService(new ClienteDto(
                CLIENTE_USER));

        //Act
        try {
            serv.dispatch();
        } catch (CoreException e) {
            fail("O cliente existe; não devia lançar excepção");
        }

        //Assert
        assertEquals("O numero de compras é 1 (tabuleiro apenas)", super.numCompras(CLIENTE_USER),
                1);
        assertTrue("tabuleiro tem de estar vazio", !cliente.getTabuleiro().hasAnyQuantidadePrato());
    }

    @Test
    @Atomic
    public void esvaziarTabuleiroHavendoComprasFinalizadas() {
        //Arrange
        Cliente cliente = this.getCliente(CLIENTE_USER);
        assert cliente != null : "cliente é null";
        final int quant = 5;

        cliente.comprarPrato(this.getPrato(PRATO_NOME, REST_NOME), quant);
        cliente.setSaldo(60);
        cliente.finalizarCompra();
        int nComprasFinalizadas = super.numCompras(CLIENTE_USER);
        cliente.comprarPrato(this.getPrato(PRATO_NOME, REST_NOME), quant);
        EsvaziaTabuleiroComprasService serv = new EsvaziaTabuleiroComprasService(new ClienteDto(
                CLIENTE_USER));

        //Act
        try {
            serv.dispatch();
        } catch (CoreException e) {
            fail("O cliente existe; não devia lançar excepção");
        }

        //Assert
        assertEquals("O numero de compras não finalizadas é 1 (tabuleiro apenas)",
                super.numCompras(CLIENTE_USER) - nComprasFinalizadas, 1);
        assertTrue("tabuleiro tem de estar vazio", !cliente.getTabuleiro().hasAnyQuantidadePrato());
    }

}
