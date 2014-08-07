package pt.ist.rest.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Cliente_Base extends pt.ist.rest.domain.Utilizador {
    public final static dml.runtime.RoleMany<pt.ist.rest.domain.Cliente,pt.ist.rest.domain.Prato> role$$prato = new dml.runtime.RoleMany<pt.ist.rest.domain.Cliente,pt.ist.rest.domain.Prato>() {
        public dml.runtime.RelationBaseSet<pt.ist.rest.domain.Prato> getSet(pt.ist.rest.domain.Cliente o1) {
            return ((Cliente_Base)o1).get$rl$prato();
        }
        public dml.runtime.Role<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Cliente> getInverseRole() {
            return pt.ist.rest.domain.Prato.role$$cliente;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.rest.domain.Cliente,pt.ist.rest.domain.Compra> role$$compra = new dml.runtime.RoleMany<pt.ist.rest.domain.Cliente,pt.ist.rest.domain.Compra>() {
        public dml.runtime.RelationBaseSet<pt.ist.rest.domain.Compra> getSet(pt.ist.rest.domain.Cliente o1) {
            return ((Cliente_Base)o1).get$rl$compra();
        }
        public dml.runtime.Role<pt.ist.rest.domain.Compra,pt.ist.rest.domain.Cliente> getInverseRole() {
            return pt.ist.rest.domain.Compra.role$$cliente;
        }
        
    };
    public static dml.runtime.Relation<pt.ist.rest.domain.Cliente,pt.ist.rest.domain.Prato> Gostos;
    public static dml.runtime.Relation<pt.ist.rest.domain.Cliente,pt.ist.rest.domain.Compra> ClienteContainsCompraFinalizada;
    
    
    private RelationList<pt.ist.rest.domain.Cliente,pt.ist.rest.domain.Prato> get$rl$prato() {
        return get$$relationList("prato", Gostos);
        
    }
    private RelationList<pt.ist.rest.domain.Cliente,pt.ist.rest.domain.Compra> get$rl$compra() {
        return get$$relationList("compra", ClienteContainsCompraFinalizada);
        
    }
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Cliente_Base() {
        super();
    }
    
    public java.lang.String getMorada() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "morada");
        return ((DO_State)this.get$obj$state(false)).morada;
    }
    
    public void setMorada(java.lang.String morada) {
        ((DO_State)this.get$obj$state(true)).morada = morada;
    }
    
    private java.lang.String get$morada() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).morada;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$morada(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).morada = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.String getEmail() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "email");
        return ((DO_State)this.get$obj$state(false)).email;
    }
    
    public void setEmail(java.lang.String email) {
        ((DO_State)this.get$obj$state(true)).email = email;
    }
    
    private java.lang.String get$email() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).email;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$email(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).email = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public int getNif() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "nif");
        return ((DO_State)this.get$obj$state(false)).nif;
    }
    
    public void setNif(int nif) {
        ((DO_State)this.get$obj$state(true)).nif = nif;
    }
    
    private int get$nif() {
        int value = ((DO_State)this.get$obj$state(false)).nif;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$nif(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).nif = (int)(arg0);
    }
    
    public int getSaldo() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "saldo");
        return ((DO_State)this.get$obj$state(false)).saldo;
    }
    
    public void setSaldo(int saldo) {
        ((DO_State)this.get$obj$state(true)).saldo = saldo;
    }
    
    private int get$saldo() {
        int value = ((DO_State)this.get$obj$state(false)).saldo;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$saldo(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).saldo = (int)(arg0);
    }
    
    public int getIdCompra() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "idCompra");
        return ((DO_State)this.get$obj$state(false)).idCompra;
    }
    
    public void setIdCompra(int idCompra) {
        ((DO_State)this.get$obj$state(true)).idCompra = idCompra;
    }
    
    private int get$idCompra() {
        int value = ((DO_State)this.get$obj$state(false)).idCompra;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$idCompra(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).idCompra = (int)(arg0);
    }
    
    public int getPratoCount() {
        return get$rl$prato().size();
    }
    
    public boolean hasAnyPrato() {
        return (! get$rl$prato().isEmpty());
    }
    
    public boolean hasPrato(pt.ist.rest.domain.Prato prato) {
        return get$rl$prato().contains(prato);
    }
    
    public java.util.Set<pt.ist.rest.domain.Prato> getPratoSet() {
        return get$rl$prato();
    }
    
    public void addPrato(pt.ist.rest.domain.Prato prato) {
        Gostos.add((pt.ist.rest.domain.Cliente)this, prato);
    }
    
    public void removePrato(pt.ist.rest.domain.Prato prato) {
        Gostos.remove((pt.ist.rest.domain.Cliente)this, prato);
    }
    
    public java.util.List<pt.ist.rest.domain.Prato> getPrato() {
        return get$rl$prato();
    }
    
    public void set$prato(OJBFunctionalSetWrapper prato) {
        get$rl$prato().setFromOJB(this, "prato", prato);
    }
    
    public java.util.Iterator<pt.ist.rest.domain.Prato> getPratoIterator() {
        return get$rl$prato().iterator();
    }
    
    public int getCompraCount() {
        return get$rl$compra().size();
    }
    
    public boolean hasAnyCompra() {
        return (! get$rl$compra().isEmpty());
    }
    
    public boolean hasCompra(pt.ist.rest.domain.Compra compra) {
        return get$rl$compra().contains(compra);
    }
    
    public java.util.Set<pt.ist.rest.domain.Compra> getCompraSet() {
        return get$rl$compra();
    }
    
    public void addCompra(pt.ist.rest.domain.Compra compra) {
        ClienteContainsCompraFinalizada.add((pt.ist.rest.domain.Cliente)this, compra);
    }
    
    public void removeCompra(pt.ist.rest.domain.Compra compra) {
        ClienteContainsCompraFinalizada.remove((pt.ist.rest.domain.Cliente)this, compra);
    }
    
    public java.util.List<pt.ist.rest.domain.Compra> getCompra() {
        return get$rl$compra();
    }
    
    public void set$compra(OJBFunctionalSetWrapper compra) {
        get$rl$compra().setFromOJB(this, "compra", compra);
    }
    
    public java.util.Iterator<pt.ist.rest.domain.Compra> getCompraIterator() {
        return get$rl$compra().iterator();
    }
    
    protected void checkDisconnected() {
        super.checkDisconnected();
        if (hasAnyPrato()) handleAttemptToDeleteConnectedObject();
        if (hasAnyCompra()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        super.readStateFromResultSet(rs, state);
        DO_State castedState = (DO_State)state;
        set$morada(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "MORADA"), state);
        set$email(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "EMAIL"), state);
        set$nif(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "NIF"), state);
        set$saldo(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "SALDO"), state);
        set$idCompra(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "ID_COMPRA"), state);
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        if (attrName.equals("prato")) return Gostos;
        if (attrName.equals("compra")) return ClienteContainsCompraFinalizada;
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        get$$relationList("prato", Gostos);
        get$$relationList("compra", ClienteContainsCompraFinalizada);
        
    }
    protected static class DO_State extends pt.ist.rest.domain.Utilizador.DO_State {
        private java.lang.String morada;
        private java.lang.String email;
        private int nif;
        private int saldo;
        private int idCompra;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.morada = this.morada;
            newCasted.email = this.email;
            newCasted.nif = this.nif;
            newCasted.saldo = this.saldo;
            newCasted.idCompra = this.idCompra;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.rest.domain.Utilizador.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private java.lang.String morada;
            private java.lang.String email;
            private int nif;
            private int saldo;
            private int idCompra;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.morada = obj.morada;
                this.email = obj.email;
                this.nif = obj.nif;
                this.saldo = obj.saldo;
                this.idCompra = obj.idCompra;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.morada = this.morada;
                state.email = this.email;
                state.nif = this.nif;
                state.saldo = this.saldo;
                state.idCompra = this.idCompra;
                
            }
            
        }
        
    }
    
}
