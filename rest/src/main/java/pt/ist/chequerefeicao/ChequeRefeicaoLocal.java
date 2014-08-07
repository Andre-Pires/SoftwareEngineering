package pt.ist.chequerefeicao;

/**
 * This class represents a simplified local version of the external service
 * ChequeRefeicao. Each check is represented by a string. The simplifications made are the
 * following ones: - The data concerning the service ChequeRefeicao is not persistent - It
 * just support the operation of cashing checks. - In order to be able to simulate valid
 * and invalid checks, a valid check has an identifier that ends with a digit. - The
 * amount of a check is equal to 10 times the last digit of the identifier of the check.
 * 
 * @author ES
 **/

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import pt.chequerefeicao.ws.*;

public class ChequeRefeicaoLocal
        implements ChequeRefeicaoPortType {
    
    private Set<String> cashed;
    
    public ChequeRefeicaoLocal() {
        this.cashed = new HashSet<String>();
    }

    public String emitir(String titular, int valor, boolean endossavel) throws UtilizadorInexistente_Exception,
            ValorInvalido_Exception {
        return titular;
    }

    public Integer sacar(String beneficiario, List<String> checks) throws ChequeInexistente_Exception,
            ChequeJaUsado_Exception,
            UtilizadorInexistente_Exception {
        int amount = 0;
        char lastChar;

        for (String check : checks) {
            if (cashed.contains(check))
            throw new ChequeJaUsado_Exception(check, new ChequeJaUsado());

            lastChar = check.charAt(check.length() - 1); // last character

            if (lastChar < '0')
            throw new ChequeInexistente_Exception(check, new ChequeInexistente());

            amount += (lastChar - '0') * 10;
        }

        cashed.addAll(checks);
        return amount;
    }

    public String endossar(String titular, String terceiro, String numero) throws ChequeInexistente_Exception,
            ChequeJaUsado_Exception,
            ChequeNaoEndossavel_Exception,
            UtilizadorInexistente_Exception {
        return "true";
    }

    public List<String> listar(String titular, boolean sacados) throws UtilizadorInexistente_Exception {
        return new ArrayList<String>();
    }
    
    /**
     * A simple set of tests for this class.
     **/
    public static void main(String argv[]) {
        //TODO
    }
}
