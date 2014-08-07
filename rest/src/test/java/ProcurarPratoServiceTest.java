import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import jvstm.Atomic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ist.rest.domain.Alimento;
import pt.ist.rest.exception.CoreException;
import pt.ist.rest.exception.core.unknowkey.NoSuchTypeException;
import pt.ist.rest.service.ProcuraService;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.SearchDto;
import pt.ist.rest.service.dto.SearchEnum;
import pt.ist.rest.service.procura.ProcurarPrato;

public class ProcurarPratoServiceTest extends
        RestTest {

    private static final String SUBSTRING = "Bi";
    private final PratoDto pratoCorrecto = new PratoDto(PRATO_NOME);
    private final PratoDto pratoSubString = new PratoDto(SUBSTRING);
    private final PratoDto pratoErrado = new PratoDto("Bife com batatas fritas");
    private List<PratoDto> resultado;
    private final PratoDto peixe = new PratoDto("Peixe");
    private final PratoDto carne = new PratoDto("Carne");
    private final PratoDto vegetal = new PratoDto("Vegetariano");
    private final PratoDto tipoErrado = new PratoDto("tipo errado");



    /*
     * Duplicado o setUp por não poder ter @Atomic do setUp super.
     * Para testar as Substrings foi adicionado um segundo prato.
     * @see RestTest#setUp()
     */
    @Override
    @Before
    @Atomic
    public void setUp() {
        Alimento alimento1 = new Alimento("Bacalhau", "Peixe");
        Alimento alimento2 = new Alimento("Batatas", "Vegetariano");
        Alimento alimento3 = new Alimento("Bife", "Carne");
        Alimento alimento4 = new Alimento("Galinha", "Carne");
        ArrayList<Alimento> alimentos1 = new ArrayList<Alimento>();
        ArrayList<Alimento> alimentos2 = new ArrayList<Alimento>();
        ArrayList<Alimento> alimentos3 = new ArrayList<Alimento>();
        ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
        alimentos1.add(alimento1);
        alimentos1.add(alimento2);
        alimentos2.add(alimento3);
        alimentos3.add(alimento4);
        alimentos.add(alimento2);


        cleanPortal();

        this.addCliente(CLIENTE_USER, CLIENTE_PASS, CLIENTE_NOME, CLIENTE_MORADA, CLIENTE_EMAIL);
        this.addGestor(GESTOR_USER, GESTOR_PASS, GESTOR_NOME);
        this.addRestaurante(REST_NOME, REST_MORADA, portal.getGestor(GESTOR_USER));
        this.addRestaurante("Barriga Feliz", REST_MORADA, portal.getGestor(GESTOR_USER));
        this.addRestaurante("Barriga Cheia", REST_MORADA, portal.getGestor(GESTOR_USER));
        this.addPrato(PRATO_NOME, criaAlimento(ALIMENTO_NOME, ALIMENTO_TIPO), 5, 6, REST_NOME);
        this.addPrato("Bife", criaAlimento(ALIMENTO_NOME, ALIMENTO_TIPO), 7, 8, REST_NOME);
        this.addPrato("Bacalhau com batatas", alimentos1, 8, 9, "Barriga Cheia");
        this.addPrato("Bacalhau com batatas", alimentos1, 9, 10, "Barriga Feliz");
        this.addPrato("Batata", alimentos, 23, 12, "Barriga Feliz");
        this.addPrato("Bitoque", alimentos2, 3, 9, "Barriga Feliz");

    }

    @Override
    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
    @Atomic
    public void testDispatchNomeCorrecto() {

        try {
            ArrayList s = new ArrayList<ProcurarPrato>();
            s.add(new SearchDto(SearchEnum.NOME.toString(), pratoCorrecto.getNome()));
            ProcuraService servicoOk = new ProcuraService(s);
            servicoOk.dispatch();
            resultado = servicoOk.getResultado();

            for (PratoDto pratoDto : resultado) {
                assertThat(pratoDto.getNome(), containsString(PRATO_NOME));
                System.out.println(pratoDto.getNome());
            }
        } catch (CoreException e) {
            fail("O prato existe, o serviço devia retornar uma lista de Dtos com o prato."
                    + " Não devia ter sido lançada uma excepção.");
        }
    }

    @Test
    @Atomic
    public void testDispatchProcuraPorNome() {

        try {
            ArrayList s = new ArrayList<ProcurarPrato>();
            s.add(new SearchDto(SearchEnum.NOME.toString(), pratoErrado.getNome()));
            ProcuraService servicoErrado = new ProcuraService(s);
            servicoErrado.dispatch();
            resultado = servicoErrado.getResultado();


            assertTrue(
                    "O prato "
                            + pratoErrado.getNome()
                            + "não existe na base de dados como tal o serviço devia ter retornado uma lista vazia.",
                    resultado.isEmpty());

        } catch (CoreException e) {
            fail("O prato não existe, o serviço devia retornar uma lista vazia."
                    + " Não devia ter sido lançada uma excepção.");
        }

    }

    /**
     * Testa a procura por pratos dada uma substring. No teste usa-se "Bi" que deve
     * retornar Bitoque e Bife, daí a verificação do size() igual a 2.
     */
    @Test
    @Atomic
    public void testDispatchNomeSubString() {

        try {
            ArrayList s = new ArrayList<ProcurarPrato>();
            System.out.println(SearchEnum.NOME.toString().toString());
            s.add(new SearchDto("NOME", SUBSTRING));
            ProcuraService servicoSubString = new ProcuraService(s);
            servicoSubString.dispatch();
            resultado = servicoSubString.getResultado();

            for (PratoDto pratoDto : resultado) {
                assertThat(pratoDto.getNome(), containsString(SUBSTRING));
                System.out.println(pratoDto.getNome());
            }

            assertTrue("Existe dois pratos na lista com a substring " + SUBSTRING + "",
                    resultado.size() == 3);

        } catch (CoreException e) {
            fail("A lista devia conter dois pratos com a substring " + SUBSTRING
                    + " ,o serviço devia retornar a lista."
                    + " Não devia ter sido lançada uma excepção.");
        }

    }




    @Test
    @Atomic
    public void testDispatchCarne() {

        try {
            ArrayList s = new ArrayList<ProcurarPrato>();

            s.add(new SearchDto(SearchEnum.TIPO.toString(), carne.getNome()));
            ProcuraService servicoOk = new ProcuraService(s);
            servicoOk.dispatch();
            resultado = servicoOk.getResultado();
            if (resultado.isEmpty()) {
                fail("deviram ter sido encontrados pratos");
            }
            boolean bitoque = false;
            for (PratoDto pratoDto : resultado) {
                if (pratoDto.getNome().equals("Bitoque")) {
                    bitoque = true;
                    break;
                }
            }
            if (!bitoque) {
                fail("não foi encontrado prato Bitoque");
            }
        } catch (CoreException e) {
            fail("O prato existe, o serviço devia retornar uma lista de Dtos com o prato."
                    + " Não devia ter sido lançada uma excepção." + e.getMessage());
        }
    }

    @Test
    @Atomic
    public void testDispatchVegetariano() {

        try {
            ArrayList s = new ArrayList<ProcurarPrato>();
            s.add(new SearchDto(SearchEnum.TIPO.toString(), vegetal.getNome()));
            ProcuraService servicoOk = new ProcuraService(s);
            servicoOk.dispatch();
            resultado = servicoOk.getResultado();
            if (resultado.isEmpty()) {
                fail("deviram ter sido encontrados pratos");
            }
            boolean batatas = false;
            for (PratoDto pratoDto : resultado) {
                if (pratoDto.getNome().equals("Batata")) {
                    batatas = true;
                    break;
                }
            }
            if (!batatas) {
                fail("não foi encontrado prato Batata");
            }
        } catch (CoreException e) {
            fail("O prato existe, o serviço devia retornar uma lista de Dtos com o prato."
                    + " Não devia ter sido lançada uma excepção." + e.getMessage());
        }
    }

    @Test
    @Atomic
    public void testDispatchPeixe() {

        try {
            ArrayList s = new ArrayList<ProcurarPrato>();
            s.add(new SearchDto(SearchEnum.TIPO.toString(), peixe.getNome()));
            ProcuraService servicoOk = new ProcuraService(s);
            servicoOk.dispatch();
            resultado = servicoOk.getResultado();
            if (resultado.isEmpty()) {
                fail("deviram ter sido encontrados pratos");
            }
            boolean bacalhau = false;
            for (PratoDto pratoDto : resultado) {
                if (pratoDto.getNome().equals("Bacalhau com batatas")) {
                    bacalhau = true;
                    break;
                }
                System.out.println(pratoDto.getNome());
            }
            if (!bacalhau) {
                fail("não foi encontrado prato bacalhau com batatas");
            }

        } catch (CoreException e) {
            fail("O prato existe, o serviço devia retornar uma lista de Dtos com o prato."
                    + " Não devia ter sido lançada uma excepção." + e.getMessage());
        }
    }

    @Test
    @Atomic
    public void testDispatchProcuraPorTipoErrado() {

        try {
            ArrayList s = new ArrayList<ProcurarPrato>();
            s.add(new SearchDto(SearchEnum.TIPO.toString(), tipoErrado.getNome()));
            ProcuraService servicoErrado = new ProcuraService(s);
            servicoErrado.dispatch();
            resultado = servicoErrado.getResultado();


            /*assertTrue(
                    "O prato "
                            + pratoErrado.getNome()
                            + "não existe na base de dados como tal o serviço devia ter retornado uma lista vazia.",
                    resultado.isEmpty());
            */
            fail("pratos não deviam ter sido encontrados");
        } catch (NoSuchTypeException e) {
            //Funcionou
        }

        catch (CoreException e) {
            fail("O prato não existe, o serviço devia retornar uma lista vazia."
                    + " Não devia ter sido lançada uma excepção.");

        }
    }

    @Test
    @Atomic
    public void testMultipleSearch() {

        try {
            ArrayList s = new ArrayList<SearchDto>();
            s.add(new SearchDto(SearchEnum.NOME.toString(), pratoCorrecto.getNome()));
            s.add(new SearchDto(SearchEnum.NOME.toString(), pratoCorrecto.getNome()));
            s.add(new SearchDto(SearchEnum.NOME.toString(), "Bi"));
            s.add(new SearchDto(SearchEnum.TIPO.toString(), carne.getNome()));
            ProcuraService servicoOk = new ProcuraService(s);
            servicoOk.dispatch();
            resultado = servicoOk.getResultado();

            for (PratoDto pratoDto : resultado) {
                assertThat(pratoDto.getNome(), containsString(PRATO_NOME));
                System.out.println(pratoDto.getNome());
            }

        } catch (CoreException e) {
            fail("O prato existe, o serviço devia retornar uma lista de Dtos com o prato."
                    + " Não devia ter sido lançada uma excepção.");
        }
    }
}
