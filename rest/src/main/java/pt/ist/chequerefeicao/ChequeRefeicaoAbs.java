package pt.ist.chequerefeicao;

import java.util.List;

import pt.chequerefeicao.ws.*;  

public abstract class ChequeRefeicaoAbs {

    private ChequeRefeicaoPortType implementacao;

    public void setImplementacao(ChequeRefeicaoPortType newImplementacao) {
        implementacao = newImplementacao;
    }

    public String emitir(String titular, int valor, boolean endossavel) throws UtilizadorInexistente_Exception,
            ValorInvalido_Exception {
        return implementacao.emitir(titular, valor, endossavel);
    }

    public Integer sacar(String beneficiario, List<String> numeros) throws ChequeInexistente_Exception,
            ChequeJaUsado_Exception,
            UtilizadorInexistente_Exception {
        return implementacao.sacar(beneficiario, numeros);
    }

    public String endossar(String titular, String terceiro, String numero) throws ChequeInexistente_Exception,
            ChequeJaUsado_Exception,
            ChequeNaoEndossavel_Exception,
            UtilizadorInexistente_Exception {
        return implementacao.endossar(titular, terceiro, numero);
    }

    
    public List<String> listar(String titular, boolean sacados) throws UtilizadorInexistente_Exception {
        return implementacao.listar(titular, sacados);
    }
}
