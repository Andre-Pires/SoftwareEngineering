package pt.ist.rest.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Alimento_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    public final static dml.runtime.RoleMany<pt.ist.rest.domain.Alimento,pt.ist.rest.domain.Prato> role$$prato = new dml.runtime.RoleMany<pt.ist.rest.domain.Alimento,pt.ist.rest.domain.Prato>() {
        public dml.runtime.RelationBaseSet<pt.ist.rest.domain.Prato> getSet(pt.ist.rest.domain.Alimento o1) {
            return ((Alimento_Base)o1).get$rl$prato();
        }
        public dml.runtime.Role<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Alimento> getInverseRole() {
            return pt.ist.rest.domain.Prato.role$$alimento;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Alimento,pt.ist.rest.domain.Prato> PratoContainsAlimento = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Alimento,pt.ist.rest.domain.Prato>(role$$prato);
    static {
        pt.ist.rest.domain.Prato.PratoContainsAlimento = PratoContainsAlimento.getInverseRelation();
    }
    
    static {
        PratoContainsAlimento.setRelationName("pt.ist.rest.domain.Alimento.PratoContainsAlimento");
        PratoContainsAlimento.addListener(new dml.runtime.RelationAdapter<pt.ist.rest.domain.Alimento,pt.ist.rest.domain.Prato>() {
            @Override
            public void beforeAdd(pt.ist.rest.domain.Alimento arg0, pt.ist.rest.domain.Prato arg1) {
                pt.ist.fenixframework.pstm.Transaction.addRelationTuple("PratoContainsAlimento", arg1, "alimento", arg0, "prato");
            }
            @Override
            public void beforeRemove(pt.ist.rest.domain.Alimento arg0, pt.ist.rest.domain.Prato arg1) {
                pt.ist.fenixframework.pstm.Transaction.removeRelationTuple("PratoContainsAlimento", arg1, "alimento", arg0, "prato");
            }
            
        }
        );
    }
    
    
    private RelationList<pt.ist.rest.domain.Alimento,pt.ist.rest.domain.Prato> get$rl$prato() {
        return get$$relationList("prato", PratoContainsAlimento);
        
    }
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Alimento_Base() {
        super();
    }
    
    public java.lang.String getDescricao() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "descricao");
        return ((DO_State)this.get$obj$state(false)).descricao;
    }
    
    public void setDescricao(java.lang.String descricao) {
        ((DO_State)this.get$obj$state(true)).descricao = descricao;
    }
    
    private java.lang.String get$descricao() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).descricao;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$descricao(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).descricao = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public java.lang.String getTipo() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "tipo");
        return ((DO_State)this.get$obj$state(false)).tipo;
    }
    
    public void setTipo(java.lang.String tipo) {
        ((DO_State)this.get$obj$state(true)).tipo = tipo;
    }
    
    private java.lang.String get$tipo() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).tipo;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$tipo(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).tipo = (java.lang.String)((arg0 == null) ? null : arg0);
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
        PratoContainsAlimento.add((pt.ist.rest.domain.Alimento)this, prato);
    }
    
    public void removePrato(pt.ist.rest.domain.Prato prato) {
        PratoContainsAlimento.remove((pt.ist.rest.domain.Alimento)this, prato);
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
    
    protected void checkDisconnected() {
        if (hasAnyPrato()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$descricao(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "DESCRICAO"), state);
        set$tipo(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "TIPO"), state);
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        if (attrName.equals("prato")) return PratoContainsAlimento;
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        get$$relationList("prato", PratoContainsAlimento);
        
    }
    protected static class DO_State extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State {
        private java.lang.String descricao;
        private java.lang.String tipo;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.descricao = this.descricao;
            newCasted.tipo = this.tipo;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private java.lang.String descricao;
            private java.lang.String tipo;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.descricao = obj.descricao;
                this.tipo = obj.tipo;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.descricao = this.descricao;
                state.tipo = this.tipo;
                
            }
            
        }
        
    }
    
}
