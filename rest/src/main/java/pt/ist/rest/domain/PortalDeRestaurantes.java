package pt.ist.rest.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import pt.ist.chequerefeicao.ChequeRefeicao;
import pt.ist.registofatura.RegistoFatura;
import pt.ist.rest.exception.RegistoFaturaException;
import pt.ist.rest.exception.core.duplicatekey.NameAlreadyTakenException;
import pt.ist.rest.exception.core.duplicatekey.UsernameAlreadyTakenException;
import pt.ist.rest.exception.core.unknowkey.NoSuchManagerException;
import pt.ist.rest.exception.core.unknowkey.NoSuchRestauranteException;
import build.classes.pt.registofatura.ws.ClienteInexistente_Exception;
import build.classes.pt.registofatura.ws.EmissorInexistente_Exception;
import build.classes.pt.registofatura.ws.Fatura;
import build.classes.pt.registofatura.ws.FaturaInvalida_Exception;
import build.classes.pt.registofatura.ws.ItemFatura;
import build.classes.pt.registofatura.ws.Serie;
import build.classes.pt.registofatura.ws.TotaisIncoerentes_Exception;



/**
 * The Class PortalDeRestaurantes. Mantém os diferentes restaurantes e utilizadores
 * associados ao sistema (tanto os clientes como os gestores dos Restaurantes). Não
 * permite que hajam restaurantes com nomes iguais, bem como clientes com nomes de
 * utilizador iguais.
 */
public class PortalDeRestaurantes extends
        PortalDeRestaurantes_Base {

    private static RegistoFatura registoFatura = new RegistoFatura();
    private static ChequeRefeicao chequeRefeicao = new ChequeRefeicao();

    /**
     * Instancia um novo portal de restaurantes.
     */
    public PortalDeRestaurantes() {
        super();
        System.out.println("a instanciar um novo portal...");
        this.setPediuSerie(false);
        this.setPrecoMaximo(-1);
        this.setNifPortal(1212);
        this.setNomePortal("RestPortal");
        super.setNumSeq(1);

    }

    /**
     * Novo id prato.
     * 
     * @return retorna um identificador unico no Portal de Restaurantes que ira ser
     *         associado a um prato
     */
    public int novoIdPrato() {
        this.setIdPrato(this.getIdPrato() + 1);

        return this.getIdPrato();
    }

    /**
     * Regista gestor.
     * 
     * @param user username que identifica o novo gestor que vai ser adicionado
     * @param pass password que o gestor vai usar para fazer login no portal
     * @param nome nome real do gestor
     * @throws IllegalArgumentException exepção lançada casa o nome do gestor já exista
     */
    public void registaGestor(String user, String pass, String nome) {
        if (this.isUsernameValido(user)) {
            this.addUtilizador(new Gestor(user, pass, nome));
        } else {
            throw new UsernameAlreadyTakenException(user);
        }
    }

    /**
     * Regista cliente.
     * 
     * @param user username que identifica o novo cliente que vai ser adicionado
     * @param pass password que o cliente vai usar para fazer login no portal
     * @param nome nome real do cliente que vai ser adicionado
     * @param morada morada do cliente que vai ser adicionado
     * @param email email do cliente que vai ser adicionado
     * @param nif NIF do cliente que vai ser adicionado
     * @throws IllegalArgumentException exepção lançada casa o nome do utilizador já
     *             exista
     */
    public void registaCliente(String user,
                               String pass,
                               String nome,
                               String morada,
                               String email,
                               String nif) {
        if (this.isUsernameValido(user)) {
            this.addUtilizador(new Cliente(user, pass, nome, morada, email, nif));
        } else {
            throw new UsernameAlreadyTakenException(user);
        }
    }

    /**
     * Regista cliente.
     * 
     * @param user username que identifica o novo cliente que vai ser adicionado
     * @param pass password que o cliente vai usar para fazer login no portal
     * @param nome nome real do cliente que vai ser adicionado
     * @param morada morada do cliente que vai ser adicionado
     * @param email email do cliente que vai ser adicionado
     * @throws IllegalArgumentException exepção lançada casa o nome do cliente já exista
     */
    public void registaCliente(String user, String pass, String nome, String morada, String email) {
        if (this.isUsernameValido(user)) {
            this.addUtilizador(new Cliente(user, pass, nome, morada, email));
        } else {
            throw new UsernameAlreadyTakenException(user);
        }
    }

    /**
     * Regista restaurante.
     * 
     * @param nome nome do Restaurante
     * @param morada morada do Restaurante
     * @param gestor Gestor responsavel pelo Restaurante
     */
    public void registaRestaurante(String nome, String morada, final Gestor gestor) {
        if (this.isRestauranteValido(nome)) {
            this.addRestaurante(new Restaurante(nome, morada, gestor));
        } else {
            throw new NameAlreadyTakenException(nome);
        }
    }

    /**
     * Retorna um gestor com determinado username.
     * 
     * @param username username do gestor que se procura
     * @return retorna o gestor procurado caso tenha sido encontrado ou, null caso não
     *         haja nenhum gestor com esse username no portal.
     */
    public Gestor getGestor(String username) {
        for (Utilizador u : this.getUtilizador()) {
            if (u.getUsername().equals(username) && u instanceof Gestor) {
                return (Gestor) u;
            }
        }
        return null;
    }

    /**
     * Retorna um cliente com determinado username.
     * 
     * @param username username do Cliente que se procura
     * @return retorna o Cliente procurado caso tenha sido encontrado ou, null caso não
     *         haja nenhum Cliente com esse username no portal.
     */
    public Cliente getCliente(String username) {
        for (Utilizador u : this.getUtilizador()) {
            if (u.getUsername().equals(username) && u instanceof Cliente) {
                return (Cliente) u;
            }
        }
        return null;
    }

    /**
     * Retorna o restaurante com determinado nome.
     * 
     * @param nome username do Restaurante que se procura
     * @return retorna o Restaurante procurado caso tenha sido encontrado ou, null caso
     *         não haja nenhum Restaurante com esse nome no portal.
     */
    public Restaurante getRestaurante(String nome) {
        for (Restaurante r : this.getRestaurante()) {

            if (r.getNome().equals(nome)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Verifica se o nome pretendido e valido.
     * 
     * @param nome Nome do restaurante a verificar.
     * @return Se ja existe um Restaurante com o nome passado, retorna falso; caso
     *         contrario, retorna true.
     */
    private boolean isRestauranteValido(String nome) {

        for (Restaurante r : this.getRestaurante()) {

            if (r.getNome().equals(nome)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se o username pretendido e valido.
     * 
     * @param username O do utilizador a verificar.
     * @return Retorna true se nao existe nenhum utilizador com o nome passado, caso
     *         contrario retorna false.
     */
    private boolean isUsernameValido(String username) {
        for (Utilizador u : this.getUtilizador()) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adiciona gestor ao restaurante.
     * 
     * @param nomeRest nome do restaurante
     * @param usernameGestor username gestor
     */
    public void adicionaGestorRestaurante(String nomeRest, String usernameGestor) {
        final Restaurante restaurante = this.getRestaurante(nomeRest);
        final Gestor gestor = this.getGestor(usernameGestor);

        if (gestor != null && restaurante != null) {
            gestor.setRestaurante(restaurante);
        } else if (gestor == null) {
            throw new NoSuchManagerException(usernameGestor);
        } else if (restaurante == null) {
            throw new NoSuchRestauranteException(nomeRest);
        }
    }

    /**
     * Recebe um cliente e emite uma fatura para o serviço externo do ACTUAL tabuleiro do
     * cliente. NAO FINALIZA O TABULEIRO DE COMPRAS. NOTA: Deve ser utilizado apenas pelo
     * serviço pagamentoTabuleiroService.
     * 
     * @return id da fatura emitida.
     */
    public List<Integer> emitirFatura(Cliente cliente) throws RegistoFaturaException {
        assert this.getNumSeq() <= 4 && this.getNumSerie() >= 0 : "numseq: " + this.getNumSeq()
                + " - numSerie: " + this.getNumSerie();
        final Compra tab = cliente.getTabuleiro();

        System.out.println("actual num serie:" + getNumSerie() + " - num seq:" + getNumSeq());
        super.setNumSerie(this.getNumSerie());

        if (!this.getPediuSerie()) {
            try {
                Serie serie = registoFatura.pedirSerie(this.getNifPortal());
                System.err.println("'Pedir Serie'. Received numSerie is " + serie.getNumSerie());
                super.setNumSerie(serie.getNumSerie());
                this.setPediuSerie(true);
            } catch (EmissorInexistente_Exception e) {
                System.out.println("Problema a pedir serie. Excep: " + e.getMessage());
                e.printStackTrace();
            }
        }

        List<ItemFatura> listItemsFaturas = new ArrayList<ItemFatura>();
        List<QuantidadePrato> listQuantPratos = tab.getQuantidadePrato();
        final BigDecimal taxaIva = new BigDecimal("0.23");
        BigDecimal ivaTotalItems = BigDecimal.valueOf(0);
        int precoTotal = 0;

        for (QuantidadePrato qPrato : listQuantPratos) {
            final int preco = qPrato.getPrecoTotal();
            precoTotal += preco;
            // iva = P * taxaIva / (1 + taxaIva)
            // Define a precisão da divisão nas 2 casas decimais e arredonda para cima no 5
            BigDecimal ivaItem = BigDecimal.valueOf(preco).multiply(taxaIva)
                    .divide(taxaIva.add(BigDecimal.valueOf(1)), 2, RoundingMode.HALF_UP);
            ivaTotalItems = ivaTotalItems.add(ivaItem);

            ItemFatura item = new ItemFatura();
            item.setDescricao(qPrato.getPrato().getNome());
            item.setPreco(preco);
            item.setQuantidade(qPrato.getQuantidade());
            listItemsFaturas.add(item);
        }
        ivaTotalItems = ivaTotalItems.setScale(0, RoundingMode.HALF_UP);

        Fatura fatura = new Fatura();
        fatura.setData(XMLCalendarToDate.toXMLGregorianCalendar(new DateTime().toDate()));
        fatura.setNumSeqFatura(this.getNumSeq());
        fatura.setNumSerie(this.getNumSerie());
        fatura.setNifEmissor(this.getNifPortal());
        fatura.setNomeEmissor(this.getNomePortal());
        fatura.setNifCliente(new Integer(cliente.getNif()));
        fatura.getItens().addAll(listItemsFaturas);
        fatura.setIva(ivaTotalItems.toBigIntegerExact().intValue());
        fatura.setTotal(precoTotal);

        try {
            System.out.println("Dentro do comunicar ... mesmo antes de chamar... " + "numserie = "
                    + this.getNumSerie() + "numseq = " + this.getNumSeq());
            registoFatura.comunicarFatura(fatura);
        } catch (ClienteInexistente_Exception e) {
            System.out.println("Excepção do comunicar fatura: " + e.getMessage());
            throw new RegistoFaturaException(e.getMessage());
        } catch (EmissorInexistente_Exception e) {
            System.out.println("Excepção do comunicar fatura: " + e.getMessage());
            throw new RegistoFaturaException(e.getMessage());
        } catch (FaturaInvalida_Exception e) {
            System.out.println("Excepção do comunicar fatura: " + e.getMessage());
            throw new RegistoFaturaException(e.getMessage());
        } catch (TotaisIncoerentes_Exception e) {
            System.out.println("Excepção do comunicar fatura: " + e.getMessage());
            throw new RegistoFaturaException(e.getMessage());
        }

        if (getNumSeq() < 4) {
            System.out.println("Ja Chamou. a fazer acts do numseq... : " + super.getNumSeq());
            super.setNumSeq(super.getNumSeq() + 1);
            System.out.println("fez a actualizacao: " + super.getNumSeq());

        } else {
            System.out.println("Ja Chamou. a fazer acts do numseq E DA serie... : "
                    + super.getNumSeq());
            super.setNumSeq(1);
            try {
                System.out.println("comunicar: A pedir uma nova serie ...");
                Serie novaSerie = registoFatura.pedirSerie(this.getNifPortal());
                System.out.println("actualizou o numSerie.");
                super.setNumSerie(novaSerie.getNumSerie());
            } catch (EmissorInexistente_Exception e) {
                System.out.println("Excepção do pedir nova serie: " + e.getMessage());
                throw new RegistoFaturaException(e.getMessage());
            }

        }


        assert this.getNumSeq() <= 4 && this.getNumSerie() >= 0;
        List<Integer> id = new ArrayList<Integer>();
        id.add(fatura.getNumSerie());
        id.add(fatura.getNumSeqFatura());
        return id;
    }


    /* @Throws UnsupportedOperationException 
     * @see pt.ist.rest.domain.PortalDeRestaurantes_Base#setNumSeq(int)
     */
    @Override
    public void setNumSeq(int numSeq) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /* @Throws UnsupportedOperationException
     * @see pt.ist.rest.domain.PortalDeRestaurantes_Base#setNumSerie(int)
     */
    @Override
    public void setNumSerie(int numSerie) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Retorna o prato com determinado nome, de determinado restaurante
     * 
     * @param nomePrato nome do prato procurado
     * @param nomeRest nome do restaurante que o prato pertence
     * @return O prato, caso exista
     */
    public Prato getPrato(String nomePrato, String nomeRest) {
        Restaurante r = this.getRestaurante(nomeRest);
        if (r != null) {
            return r.getPrato(nomePrato);
        }

        return null;
    }

    /**
     * Retorna o prato com determinado ID.
     * 
     * @param id ID do prato procurado
     * @return O prato, caso exista
     */
    public Prato getPrato(int id) {
        for (Restaurante r : this.getRestaurante()) {
            for (Prato p : r.getPrato()) {
                if (p.getId() == id) {
                    return p;
                }
            }
        }

        return null;
    }

    public ChequeRefeicao getChequeRefeicao() {
        return chequeRefeicao;
    }

    public void setRemote() {
        PortalDeRestaurantes.registoFatura.setRemote();
        PortalDeRestaurantes.chequeRefeicao.setRemote();
    }

    public void setLocal() {
        System.out.println("Portal: setting as local.");
        PortalDeRestaurantes.registoFatura.setLocal();
        PortalDeRestaurantes.chequeRefeicao.setLocal();
    }

}
