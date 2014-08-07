import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Collection;

import jvstm.Atomic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ist.rest.domain.Prato;
import pt.ist.rest.exception.CoreException;
import pt.ist.rest.service.ObterTabuleiroDeComprasService;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;
import pt.ist.rest.service.dto.PratoDto;


public class ObterTabuleiroDeComprasServiceTest extends
        RestTest {


    private static final String SUBSTRING = "Bi";
    private final PratoDto pratoCorrecto = new PratoDto(PRATO_NOME);
    private final PratoDto pratoSubString = new PratoDto(SUBSTRING);
    private final PratoDto pratoErrado = new PratoDto("Bife com batatas fritas");
    private Collection<ItemDto> resultado;



    /*
     * Duplicado o setUp por não poder ter @Atomic do setUp super.
     * Para testar as Substrings foi adicionado um segundo prato.
     * @see RestTest#setUp()
     */
    @Override
    @Before
    @Atomic
    public void setUp() {
        cleanPortal();
        this.addCliente(CLIENTE_USER, CLIENTE_PASS, CLIENTE_NOME, CLIENTE_MORADA, CLIENTE_EMAIL);
        this.addGestor(GESTOR_USER, GESTOR_PASS, GESTOR_NOME);
        this.addRestaurante(REST_NOME, REST_MORADA, portal.getGestor(GESTOR_USER));
        this.addPrato(PRATO_NOME, criaAlimento(ALIMENTO_NOME, ALIMENTO_TIPO), 4, 3, REST_NOME);
        this.addPrato("Bife", criaAlimento(ALIMENTO_NOME, ALIMENTO_TIPO), 4, 7, REST_NOME);
        this.getCliente(CLIENTE_USER).comprarPrato(this.getPrato(PRATO_NOME, REST_NOME), 2);

    }

    @Override
    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
    @Atomic
    public void testDispatchCorrecto() {

        try {
            ObterTabuleiroDeComprasService servicoOk = new ObterTabuleiroDeComprasService(
                    new ClienteDto(CLIENTE_USER));
            servicoOk.dispatch();
            resultado = servicoOk.getResultado();

            for (ItemDto itemDto : resultado) {
                PratoDto p = itemDto.getPratoDto();
                assertThat(p.getNome(), containsString(PRATO_NOME));
                int pid = p.getId();
                int preco = p.getPreco();
                int quant = itemDto.getQuantidade();
                Prato pTest = this.getPrato(PRATO_NOME, REST_NOME);
                assert pid == pTest.getId();
                assert preco == pTest.getPreco();
            }

        } catch (CoreException e) {
            fail("O prato existe, o serviço devia retornar uma lista de Dtos com o prato."
                    + " Não devia ter sido lançada uma excepção.");
        }
    }
}
