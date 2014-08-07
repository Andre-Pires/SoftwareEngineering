import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import jvstm.Atomic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pt.ist.rest.domain.Alimento;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.QuantidadePrato;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.exception.CoreException;
import pt.ist.rest.exception.core.invalidkey.InvalidDishException;
import pt.ist.rest.exception.core.unknowkey.NoSuchDishException;
import pt.ist.rest.service.ComprarPratoService;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;

@RunWith(JUnit4.class)
public class AdicionaItemTabuleiroTest extends
        RestTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();

    }

    @Override
    @After
    public void tearDown() {
        super.tearDown();
    }


    public AdicionaItemTabuleiroTest() {
        super();
    }

    @Test
    @Atomic
    public void testVazioQuantPositiva() {
        try {
            PratoDto pratoDto = new PratoDto(PRATO_NOME);
            RestauranteDto restauranteDto = new RestauranteDto(REST_NOME);
            ItemDto itemDto = new ItemDto(pratoDto, 3, restauranteDto);
            ClienteDto clienteDto = new ClienteDto(CLIENTE_USER);

            ComprarPratoService servicoOk = new ComprarPratoService(itemDto, clienteDto);
            servicoOk.dispatch();

            Prato prato = super.getPrato(PRATO_NOME, REST_NOME);
            QuantidadePrato quantidadePrato = new QuantidadePrato(0, new Prato(0, "Dummy",
                    new ArrayList<Alimento>()));

            Cliente cliente = super.getCliente(CLIENTE_USER);
            List<QuantidadePrato> listaPratos = cliente.getTabuleiro().getQuantidadePrato();

            for (QuantidadePrato quantidadePrato2 : listaPratos) {
                if (quantidadePrato2.getPrato().equals(prato))
                    quantidadePrato = quantidadePrato2;
            }

            assertEquals(quantidadePrato.getPrato(), prato);
            assertEquals(3, quantidadePrato.getQuantidade());

        } catch (CoreException e) {
            fail(e.getMessage()
                    + " :: O prato existe, o serviço devia adicionar o prato ao tabuleiro."
                    + " Não devia ter sido lançada uma excepção.");
        }
    }

    @Test
    @Atomic
    public void testAdicionaMaisPrato() {
        Restaurante rest = super.getRestaurante(REST_NOME);
        rest.adicionaPrato("SuperFixe", 3, 4, new ArrayList<Alimento>());
        Cliente cliente = super.getCliente(CLIENTE_USER);
        cliente.comprarPrato(rest.getPrato("SuperFixe"), 1);

        try {
            PratoDto pratoDto = new PratoDto(PRATO_NOME);
            RestauranteDto restauranteDto = new RestauranteDto(REST_NOME);
            ItemDto itemDto = new ItemDto(pratoDto, 3, restauranteDto);
            ClienteDto clienteDto = new ClienteDto(CLIENTE_USER);

            ComprarPratoService servicoOk = new ComprarPratoService(itemDto, clienteDto);
            servicoOk.dispatch();

            Prato prato = super.getPrato(PRATO_NOME, REST_NOME);
            QuantidadePrato quantidadePrato = new QuantidadePrato(0, new Prato(0, "Dummy",
                    new ArrayList<Alimento>()));

            List<QuantidadePrato> listaPratos = cliente.getTabuleiro().getQuantidadePrato();

            for (QuantidadePrato quantidadePrato2 : listaPratos) {
                if (quantidadePrato2.getPrato().equals(prato))
                    quantidadePrato = quantidadePrato2;
            }

            assertEquals(quantidadePrato.getPrato(), prato);
            assertEquals(3, quantidadePrato.getQuantidade());

        } catch (CoreException e) {
            fail(e.getMessage()
                    + " :: Exite mais um prato no tabuleiro mas todas as informações estão correctas."
                    + " Não devia ter sido lançada uma excepção.");
        }
    }

    @Test
    @Atomic
    public void testAdicionaQuantidade() {
        Restaurante rest = super.getRestaurante(REST_NOME);
        Prato prato1 = super.getPrato(PRATO_NOME, REST_NOME);
        rest.addPrato(prato1);
        Cliente cliente = super.getCliente(CLIENTE_USER);
        cliente.comprarPrato(rest.getPrato(PRATO_NOME), 3);

        try {
            PratoDto pratoDto = new PratoDto(PRATO_NOME);
            RestauranteDto restauranteDto = new RestauranteDto(REST_NOME);
            /* Primeira adição ao tabuleiro */
            ItemDto itemDto = new ItemDto(pratoDto, 2, restauranteDto);
            ClienteDto clienteDto = new ClienteDto(CLIENTE_USER);

            ComprarPratoService servicoOk = new ComprarPratoService(itemDto, clienteDto);
            servicoOk.dispatch();

            Prato prato = super.getPrato(PRATO_NOME, REST_NOME);
            QuantidadePrato quantidadePrato = new QuantidadePrato(0, new Prato(0, "Dummy",
                    new ArrayList<Alimento>()));

            List<QuantidadePrato> listaPratos = cliente.getTabuleiro().getQuantidadePrato();

            for (QuantidadePrato quantidadePrato2 : listaPratos) {
                if (quantidadePrato2.getPrato().equals(prato))
                    quantidadePrato = quantidadePrato2;
            }

            assertEquals(quantidadePrato.getPrato(), prato);
            assertEquals(5, quantidadePrato.getQuantidade());

        } catch (CoreException e) {
            fail(e.getMessage() + " :: Adiciona quantidade a um prato já existente."
                    + " Não devia ter sido lançada uma excepção.");
        }
    }

    @Test
    @Atomic
    public void testRemoverQuantidade() {
        Restaurante rest = super.getRestaurante(REST_NOME);
        Prato prato1 = super.getPrato(PRATO_NOME, REST_NOME);
        rest.addPrato(prato1);
        Cliente cliente = super.getCliente(CLIENTE_USER);
        cliente.comprarPrato(rest.getPrato(PRATO_NOME), 3);

        try {
            PratoDto pratoDto = new PratoDto(PRATO_NOME);
            RestauranteDto restauranteDto = new RestauranteDto(REST_NOME);
            /* Primeira adição ao tabuleiro */
            ItemDto itemDto = new ItemDto(pratoDto, -2, restauranteDto);
            ClienteDto clienteDto = new ClienteDto(CLIENTE_USER);

            ComprarPratoService servicoOk = new ComprarPratoService(itemDto, clienteDto);
            servicoOk.dispatch();

            Prato prato = super.getPrato(PRATO_NOME, REST_NOME);
            QuantidadePrato quantidadePrato = new QuantidadePrato(0, new Prato(0, "Dummy",
                    new ArrayList<Alimento>()));

            List<QuantidadePrato> listaPratos = cliente.getTabuleiro().getQuantidadePrato();

            for (QuantidadePrato quantidadePrato2 : listaPratos) {
                if (quantidadePrato2.getPrato().equals(prato))
                    quantidadePrato = quantidadePrato2;
            }

            assertEquals(quantidadePrato.getPrato(), prato);
            assertEquals(1, quantidadePrato.getQuantidade());

        } catch (CoreException e) {
            fail(e.getMessage() + " :: Remove quantidade de um prato já existente."
                    + " Não devia ter sido lançada uma excepção.");
        }
    }



    /* Daqui para baixo tem de explodir */

    @Test
    @Atomic
    public void testVazioQuantNegativa() {
        try {
            PratoDto pratoDto = new PratoDto(PRATO_NOME);
            RestauranteDto restauranteDto = new RestauranteDto(REST_NOME);
            ItemDto itemDto = new ItemDto(pratoDto, -3, restauranteDto);
            ClienteDto clienteDto = new ClienteDto(CLIENTE_USER);

            ComprarPratoService servicoOk = new ComprarPratoService(itemDto, clienteDto);
            servicoOk.dispatch();

            fail("Deveria lançar InvalidDishException");

        } catch (InvalidDishException e) {
            /* Lançou excepção como devia */
        }
    }

    @Test
    @Atomic
    public void testClienteErrado() {

            PratoDto pratoDto = new PratoDto(PRATO_NOME);
            RestauranteDto restauranteDto = new RestauranteDto(REST_NOME);
            ItemDto itemDto = new ItemDto(pratoDto, 3, restauranteDto);
            ClienteDto clienteDto = new ClienteDto("Xpto");
            
        try {
            
            ComprarPratoService servicoOk = new ComprarPratoService(itemDto, clienteDto);
            servicoOk.dispatch();

            fail("Cliente errado foi aceite; não lançou excepção como devia.");

        } catch (CoreException e) {
            /*Teste bem sucedido*/
        }
            
    }


    @Test
    @Atomic
    public void testRestauranteErrado() {

        PratoDto pratoDto = new PratoDto(PRATO_NOME);
        RestauranteDto restauranteDto = new RestauranteDto("Xpto");
        ItemDto itemDto = new ItemDto(pratoDto, 3, restauranteDto);
        ClienteDto clienteDto = new ClienteDto(CLIENTE_USER);
        
    try {
        
        ComprarPratoService servicoOk = new ComprarPratoService(itemDto, clienteDto);
        servicoOk.dispatch();

        fail("Restaurante errado foi aceite; não lançou excepção como devia.");

    } catch (NoSuchDishException e) {
        /*Teste bem sucedido*/
    }
    }
    
    @Test
    @Atomic
    public void testPratoErrado() {

        PratoDto pratoDto = new PratoDto("Xpto");
        RestauranteDto restauranteDto = new RestauranteDto(REST_NOME);
        ItemDto itemDto = new ItemDto(pratoDto, 3, restauranteDto);
        ClienteDto clienteDto = new ClienteDto(CLIENTE_USER);
        
    try {
        
        ComprarPratoService servicoOk = new ComprarPratoService(itemDto, clienteDto);
        servicoOk.dispatch();

        fail("Prato errado foi aceite; não lançou excepção como devia.");

    } catch (NoSuchDishException e) {
        /*Teste bem sucedido*/
    }
    }
}
