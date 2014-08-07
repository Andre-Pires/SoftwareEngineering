package pt.ist.rest.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Gestor_Base extends pt.ist.rest.domain.Utilizador {
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.rest.domain.Gestor,pt.ist.rest.domain.Restaurante> role$$restaurante = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.rest.domain.Gestor,pt.ist.rest.domain.Restaurante>() {
        public pt.ist.rest.domain.Restaurante getValue(pt.ist.rest.domain.Gestor o1) {
            return ((Gestor_Base.DO_State)o1.get$obj$state(false)).restaurante;
        }
        public void setValue(pt.ist.rest.domain.Gestor o1, pt.ist.rest.domain.Restaurante o2) {
            ((Gestor_Base.DO_State)o1.get$obj$state(true)).restaurante = o2;
        }
        public dml.runtime.Role<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.Gestor> getInverseRole() {
            return pt.ist.rest.domain.Restaurante.role$$gestor;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Gestor,pt.ist.rest.domain.Restaurante> GestorContainsRestaurante = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Gestor,pt.ist.rest.domain.Restaurante>(role$$restaurante);
    static {
        pt.ist.rest.domain.Restaurante.GestorContainsRestaurante = GestorContainsRestaurante.getInverseRelation();
    }
    
    static {
        GestorContainsRestaurante.setRelationName("pt.ist.rest.domain.Gestor.GestorContainsRestaurante");
    }
    
    
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Gestor_Base() {
        super();
    }
    
    public pt.ist.rest.domain.Restaurante getRestaurante() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "restaurante");
        return ((DO_State)this.get$obj$state(false)).restaurante;
    }
    
    public void setRestaurante(pt.ist.rest.domain.Restaurante restaurante) {
        GestorContainsRestaurante.add((pt.ist.rest.domain.Gestor)this, restaurante);
    }
    
    public boolean hasRestaurante() {
        return (getRestaurante() != null);
    }
    
    public void removeRestaurante() {
        setRestaurante(null);
    }
    
    private java.lang.Long get$oidRestaurante() {
        pt.ist.fenixframework.pstm.AbstractDomainObject value = ((DO_State)this.get$obj$state(false)).restaurante;
        return (value == null) ? null : value.getOid();
    }
    
    protected void checkDisconnected() {
        super.checkDisconnected();
        if (hasRestaurante()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        super.readStateFromResultSet(rs, state);
        DO_State castedState = (DO_State)state;
        castedState.restaurante = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_RESTAURANTE");
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        
    }
    protected static class DO_State extends pt.ist.rest.domain.Utilizador.DO_State {
        private pt.ist.rest.domain.Restaurante restaurante;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.restaurante = this.restaurante;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.rest.domain.Utilizador.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private pt.ist.rest.domain.Restaurante restaurante;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.restaurante = obj.restaurante;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.restaurante = this.restaurante;
                
            }
            
        }
        
    }
    
}
