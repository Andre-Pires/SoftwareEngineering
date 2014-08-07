import static org.junit.Assert.assertTrue;
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
import pt.ist.rest.domain.Gestor;
import pt.ist.rest.domain.PortalDeRestaurantes;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.QuantidadePrato;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.exception.CoreException;
import pt.ist.rest.exception.core.MaxPriceExceededException;
import pt.ist.rest.exception.core.invalidkey.InvalidDishException;
import pt.ist.rest.exception.core.unknowkey.NoSuchDishException;
import pt.ist.rest.service.ComprarPratoService;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;

@RunWith(JUnit4.class)
public class PrecoMaximoTest extends
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


    public PrecoMaximoTest() {
        super();
    }

    @Test
    @Atomic
    public void testPrecoMaximo() {
        portal.setPrecoMaximo(7);
        assertTrue(portal.getPrecoMaximo() == 7);
    }
    
    @Test
    @Atomic
    public void testPrecoMaximoAddPrato(){
        try {
            Gestor gestor = portal.getGestor(GESTOR_USER); 
            
            portal.setPrecoMaximo(7);
            gestor.adicionaPrato(portal.getRestaurante(REST_NOME), ALIMENTO_NOME, 6, 10, criaAlimento(ALIMENTO_NOME, ALIMENTO_TIPO));            
        } catch (MaxPriceExceededException e) {
            fail("Não devia ter lançado excepção, preço está dentro dos limites.");
        }
    }
    
    @Test
    @Atomic
    public void testPrecoMaximoAddPratoFail(){
        try {
            Gestor gestor = portal.getGestor(GESTOR_USER); 
            
            portal.setPrecoMaximo(7);
            gestor.adicionaPrato(portal.getRestaurante(REST_NOME), ALIMENTO_NOME, 8, 10, criaAlimento(ALIMENTO_NOME, ALIMENTO_TIPO)); 
            
            fail("Devia ter lançado excepção, preço está fora dos limites.");
        } catch (MaxPriceExceededException e) {
            //The test run as expected.
        }       
    }
    
    @Test
    @Atomic 
    public void testPrecoMaximoNaoAlteraPratoAnterior(){
        Gestor gestor = portal.getGestor(GESTOR_USER); 
        
        gestor.adicionaPrato(portal.getRestaurante(REST_NOME), "Alimento de Teste", 8, 10, criaAlimento(ALIMENTO_NOME, ALIMENTO_TIPO)); 
        
        portal.setPrecoMaximo(7);
        
        assertTrue(portal.getRestaurante(REST_NOME).getPrato("Alimento de Teste").getPreco() == 8);  
    }
    
}
