package pt.ist.rest.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Compra_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    public final static dml.runtime.RoleMany<pt.ist.rest.domain.Compra,pt.ist.rest.domain.QuantidadePrato> role$$quantidadePrato = new dml.runtime.RoleMany<pt.ist.rest.domain.Compra,pt.ist.rest.domain.QuantidadePrato>() {
        public dml.runtime.RelationBaseSet<pt.ist.rest.domain.QuantidadePrato> getSet(pt.ist.rest.domain.Compra o1) {
            return ((Compra_Base)o1).get$rl$quantidadePrato();
        }
        public dml.runtime.Role<pt.ist.rest.domain.QuantidadePrato,pt.ist.rest.domain.Compra> getInverseRole() {
            return pt.ist.rest.domain.QuantidadePrato.role$$compra;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.rest.domain.Compra,pt.ist.rest.domain.Cliente> role$$cliente = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.rest.domain.Compra,pt.ist.rest.domain.Cliente>() {
        public pt.ist.rest.domain.Cliente getValue(pt.ist.rest.domain.Compra o1) {
            return ((Compra_Base.DO_State)o1.get$obj$state(false)).cliente;
        }
        public void setValue(pt.ist.rest.domain.Compra o1, pt.ist.rest.domain.Cliente o2) {
            ((Compra_Base.DO_State)o1.get$obj$state(true)).cliente = o2;
        }
        public dml.runtime.Role<pt.ist.rest.domain.Cliente,pt.ist.rest.domain.Compra> getInverseRole() {
            return pt.ist.rest.domain.Cliente.role$$compra;
        }
        
    };
    public static dml.runtime.Relation<pt.ist.rest.domain.Compra,pt.ist.rest.domain.QuantidadePrato> CompraContainsQuantidadePrato;
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Compra,pt.ist.rest.domain.Cliente> ClienteContainsCompraFinalizada = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Compra,pt.ist.rest.domain.Cliente>(role$$cliente);
    static {
        pt.ist.rest.domain.Cliente.ClienteContainsCompraFinalizada = ClienteContainsCompraFinalizada.getInverseRelation();
    }
    
    static {
        ClienteContainsCompraFinalizada.setRelationName("pt.ist.rest.domain.Compra.ClienteContainsCompraFinalizada");
    }
    
    
    private RelationList<pt.ist.rest.domain.Compra,pt.ist.rest.domain.QuantidadePrato> get$rl$quantidadePrato() {
        return get$$relationList("quantidadePrato", CompraContainsQuantidadePrato);
        
    }
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Compra_Base() {
        super();
    }
    
    public int getTotalCompra() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "totalCompra");
        return ((DO_State)this.get$obj$state(false)).totalCompra;
    }
    
    public void setTotalCompra(int totalCompra) {
        ((DO_State)this.get$obj$state(true)).totalCompra = totalCompra;
    }
    
    private int get$totalCompra() {
        int value = ((DO_State)this.get$obj$state(false)).totalCompra;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$totalCompra(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).totalCompra = (int)(arg0);
    }
    
    public int getId() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "id");
        return ((DO_State)this.get$obj$state(false)).id;
    }
    
    public void setId(int id) {
        ((DO_State)this.get$obj$state(true)).id = id;
    }
    
    private int get$id() {
        int value = ((DO_State)this.get$obj$state(false)).id;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$id(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).id = (int)(arg0);
    }
    
    public boolean getIsFinalizada() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "isFinalizada");
        return ((DO_State)this.get$obj$state(false)).isFinalizada;
    }
    
    public void setIsFinalizada(boolean isFinalizada) {
        ((DO_State)this.get$obj$state(true)).isFinalizada = isFinalizada;
    }
    
    private boolean get$isFinalizada() {
        boolean value = ((DO_State)this.get$obj$state(false)).isFinalizada;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForboolean(value);
    }
    
    private final void set$isFinalizada(boolean arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).isFinalizada = (boolean)(arg0);
    }
    
    public int getNifCliente() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "nifCliente");
        return ((DO_State)this.get$obj$state(false)).nifCliente;
    }
    
    public void setNifCliente(int nifCliente) {
        ((DO_State)this.get$obj$state(true)).nifCliente = nifCliente;
    }
    
    private int get$nifCliente() {
        int value = ((DO_State)this.get$obj$state(false)).nifCliente;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$nifCliente(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).nifCliente = (int)(arg0);
    }
    
    public int getIva() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "iva");
        return ((DO_State)this.get$obj$state(false)).iva;
    }
    
    public void setIva(int iva) {
        ((DO_State)this.get$obj$state(true)).iva = iva;
    }
    
    private int get$iva() {
        int value = ((DO_State)this.get$obj$state(false)).iva;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$iva(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).iva = (int)(arg0);
    }
    
    public org.joda.time.DateTime getDataEmissao() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "dataEmissao");
        return ((DO_State)this.get$obj$state(false)).dataEmissao;
    }
    
    public void setDataEmissao(org.joda.time.DateTime dataEmissao) {
        ((DO_State)this.get$obj$state(true)).dataEmissao = dataEmissao;
    }
    
    private java.sql.Timestamp get$dataEmissao() {
        org.joda.time.DateTime value = ((DO_State)this.get$obj$state(false)).dataEmissao;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForDateTime(value);
    }
    
    private final void set$dataEmissao(org.joda.time.DateTime arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).dataEmissao = (org.joda.time.DateTime)((arg0 == null) ? null : arg0);
    }
    
    public int getNumSerie() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "numSerie");
        return ((DO_State)this.get$obj$state(false)).numSerie;
    }
    
    public void setNumSerie(int numSerie) {
        ((DO_State)this.get$obj$state(true)).numSerie = numSerie;
    }
    
    private int get$numSerie() {
        int value = ((DO_State)this.get$obj$state(false)).numSerie;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$numSerie(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).numSerie = (int)(arg0);
    }
    
    public int getNumSeq() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "numSeq");
        return ((DO_State)this.get$obj$state(false)).numSeq;
    }
    
    public void setNumSeq(int numSeq) {
        ((DO_State)this.get$obj$state(true)).numSeq = numSeq;
    }
    
    private int get$numSeq() {
        int value = ((DO_State)this.get$obj$state(false)).numSeq;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$numSeq(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).numSeq = (int)(arg0);
    }
    
    public java.lang.String getEmissor() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "emissor");
        return ((DO_State)this.get$obj$state(false)).emissor;
    }
    
    public void setEmissor(java.lang.String emissor) {
        ((DO_State)this.get$obj$state(true)).emissor = emissor;
    }
    
    private java.lang.String get$emissor() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).emissor;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$emissor(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).emissor = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public int getNifEmissor() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "nifEmissor");
        return ((DO_State)this.get$obj$state(false)).nifEmissor;
    }
    
    public void setNifEmissor(int nifEmissor) {
        ((DO_State)this.get$obj$state(true)).nifEmissor = nifEmissor;
    }
    
    private int get$nifEmissor() {
        int value = ((DO_State)this.get$obj$state(false)).nifEmissor;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$nifEmissor(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).nifEmissor = (int)(arg0);
    }
    
    public int getQuantidadePratoCount() {
        return get$rl$quantidadePrato().size();
    }
    
    public boolean hasAnyQuantidadePrato() {
        return (! get$rl$quantidadePrato().isEmpty());
    }
    
    public boolean hasQuantidadePrato(pt.ist.rest.domain.QuantidadePrato quantidadePrato) {
        return get$rl$quantidadePrato().contains(quantidadePrato);
    }
    
    public java.util.Set<pt.ist.rest.domain.QuantidadePrato> getQuantidadePratoSet() {
        return get$rl$quantidadePrato();
    }
    
    public void addQuantidadePrato(pt.ist.rest.domain.QuantidadePrato quantidadePrato) {
        CompraContainsQuantidadePrato.add((pt.ist.rest.domain.Compra)this, quantidadePrato);
    }
    
    public void removeQuantidadePrato(pt.ist.rest.domain.QuantidadePrato quantidadePrato) {
        CompraContainsQuantidadePrato.remove((pt.ist.rest.domain.Compra)this, quantidadePrato);
    }
    
    public java.util.List<pt.ist.rest.domain.QuantidadePrato> getQuantidadePrato() {
        return get$rl$quantidadePrato();
    }
    
    public void set$quantidadePrato(OJBFunctionalSetWrapper quantidadePrato) {
        get$rl$quantidadePrato().setFromOJB(this, "quantidadePrato", quantidadePrato);
    }
    
    public java.util.Iterator<pt.ist.rest.domain.QuantidadePrato> getQuantidadePratoIterator() {
        return get$rl$quantidadePrato().iterator();
    }
    
    public pt.ist.rest.domain.Cliente getCliente() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "cliente");
        return ((DO_State)this.get$obj$state(false)).cliente;
    }
    
    public void setCliente(pt.ist.rest.domain.Cliente cliente) {
        ClienteContainsCompraFinalizada.add((pt.ist.rest.domain.Compra)this, cliente);
    }
    
    public boolean hasCliente() {
        return (getCliente() != null);
    }
    
    public void removeCliente() {
        setCliente(null);
    }
    
    private java.lang.Long get$oidCliente() {
        pt.ist.fenixframework.pstm.AbstractDomainObject value = ((DO_State)this.get$obj$state(false)).cliente;
        return (value == null) ? null : value.getOid();
    }
    
    protected void checkDisconnected() {
        if (hasAnyQuantidadePrato()) handleAttemptToDeleteConnectedObject();
        if (hasCliente()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$totalCompra(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "TOTAL_COMPRA"), state);
        set$id(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "ID"), state);
        set$isFinalizada(pt.ist.fenixframework.pstm.ResultSetReader.readboolean(rs, "IS_FINALIZADA"), state);
        set$nifCliente(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "NIF_CLIENTE"), state);
        set$iva(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "IVA"), state);
        set$dataEmissao(pt.ist.fenixframework.pstm.ResultSetReader.readDateTime(rs, "DATA_EMISSAO"), state);
        set$numSerie(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "NUM_SERIE"), state);
        set$numSeq(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "NUM_SEQ"), state);
        set$emissor(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "EMISSOR"), state);
        set$nifEmissor(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "NIF_EMISSOR"), state);
        castedState.cliente = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_CLIENTE");
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        if (attrName.equals("quantidadePrato")) return CompraContainsQuantidadePrato;
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        get$$relationList("quantidadePrato", CompraContainsQuantidadePrato);
        
    }
    protected static class DO_State extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State {
        private int totalCompra;
        private int id;
        private boolean isFinalizada;
        private int nifCliente;
        private int iva;
        private org.joda.time.DateTime dataEmissao;
        private int numSerie;
        private int numSeq;
        private java.lang.String emissor;
        private int nifEmissor;
        private pt.ist.rest.domain.Cliente cliente;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.totalCompra = this.totalCompra;
            newCasted.id = this.id;
            newCasted.isFinalizada = this.isFinalizada;
            newCasted.nifCliente = this.nifCliente;
            newCasted.iva = this.iva;
            newCasted.dataEmissao = this.dataEmissao;
            newCasted.numSerie = this.numSerie;
            newCasted.numSeq = this.numSeq;
            newCasted.emissor = this.emissor;
            newCasted.nifEmissor = this.nifEmissor;
            newCasted.cliente = this.cliente;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private int totalCompra;
            private int id;
            private boolean isFinalizada;
            private int nifCliente;
            private int iva;
            private org.joda.time.DateTime dataEmissao;
            private int numSerie;
            private int numSeq;
            private java.lang.String emissor;
            private int nifEmissor;
            private pt.ist.rest.domain.Cliente cliente;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.totalCompra = obj.totalCompra;
                this.id = obj.id;
                this.isFinalizada = obj.isFinalizada;
                this.nifCliente = obj.nifCliente;
                this.iva = obj.iva;
                this.dataEmissao = obj.dataEmissao;
                this.numSerie = obj.numSerie;
                this.numSeq = obj.numSeq;
                this.emissor = obj.emissor;
                this.nifEmissor = obj.nifEmissor;
                this.cliente = obj.cliente;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.totalCompra = this.totalCompra;
                state.id = this.id;
                state.isFinalizada = this.isFinalizada;
                state.nifCliente = this.nifCliente;
                state.iva = this.iva;
                state.dataEmissao = this.dataEmissao;
                state.numSerie = this.numSerie;
                state.numSeq = this.numSeq;
                state.emissor = this.emissor;
                state.nifEmissor = this.nifEmissor;
                state.cliente = this.cliente;
                
            }
            
        }
        
    }
    
}
