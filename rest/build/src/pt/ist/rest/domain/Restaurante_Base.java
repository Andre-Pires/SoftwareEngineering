package pt.ist.rest.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Restaurante_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    public final static dml.runtime.RoleMany<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.Gestor> role$$gestor = new dml.runtime.RoleMany<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.Gestor>() {
        public dml.runtime.RelationBaseSet<pt.ist.rest.domain.Gestor> getSet(pt.ist.rest.domain.Restaurante o1) {
            return ((Restaurante_Base)o1).get$rl$gestor();
        }
        public dml.runtime.Role<pt.ist.rest.domain.Gestor,pt.ist.rest.domain.Restaurante> getInverseRole() {
            return pt.ist.rest.domain.Gestor.role$$restaurante;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.PortalDeRestaurantes> role$$portal = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.PortalDeRestaurantes>() {
        public pt.ist.rest.domain.PortalDeRestaurantes getValue(pt.ist.rest.domain.Restaurante o1) {
            return ((Restaurante_Base.DO_State)o1.get$obj$state(false)).portal;
        }
        public void setValue(pt.ist.rest.domain.Restaurante o1, pt.ist.rest.domain.PortalDeRestaurantes o2) {
            ((Restaurante_Base.DO_State)o1.get$obj$state(true)).portal = o2;
        }
        public dml.runtime.Role<pt.ist.rest.domain.PortalDeRestaurantes,pt.ist.rest.domain.Restaurante> getInverseRole() {
            return pt.ist.rest.domain.PortalDeRestaurantes.role$$restaurante;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.Prato> role$$prato = new dml.runtime.RoleMany<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.Prato>() {
        public dml.runtime.RelationBaseSet<pt.ist.rest.domain.Prato> getSet(pt.ist.rest.domain.Restaurante o1) {
            return ((Restaurante_Base)o1).get$rl$prato();
        }
        public dml.runtime.Role<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Restaurante> getInverseRole() {
            return pt.ist.rest.domain.Prato.role$$restaurante;
        }
        
    };
    public static dml.runtime.Relation<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.Gestor> GestorContainsRestaurante;
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.PortalDeRestaurantes> PortalContainsRestaurants = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.PortalDeRestaurantes>(role$$portal);
    static {
        pt.ist.rest.domain.PortalDeRestaurantes.PortalContainsRestaurants = PortalContainsRestaurants.getInverseRelation();
    }
    
    static {
        PortalContainsRestaurants.setRelationName("pt.ist.rest.domain.Restaurante.PortalContainsRestaurants");
    }
    public static dml.runtime.Relation<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.Prato> RestauranteContainsPrato;
    
    
    private RelationList<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.Gestor> get$rl$gestor() {
        return get$$relationList("gestor", GestorContainsRestaurante);
        
    }
    private RelationList<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.Prato> get$rl$prato() {
        return get$$relationList("prato", RestauranteContainsPrato);
        
    }
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Restaurante_Base() {
        super();
    }
    
    public java.lang.String getNome() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "nome");
        return ((DO_State)this.get$obj$state(false)).nome;
    }
    
    public void setNome(java.lang.String nome) {
        ((DO_State)this.get$obj$state(true)).nome = nome;
    }
    
    private java.lang.String get$nome() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).nome;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$nome(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).nome = (java.lang.String)((arg0 == null) ? null : arg0);
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
    
    public int getGestorCount() {
        return get$rl$gestor().size();
    }
    
    public boolean hasAnyGestor() {
        return (! get$rl$gestor().isEmpty());
    }
    
    public boolean hasGestor(pt.ist.rest.domain.Gestor gestor) {
        return get$rl$gestor().contains(gestor);
    }
    
    public java.util.Set<pt.ist.rest.domain.Gestor> getGestorSet() {
        return get$rl$gestor();
    }
    
    public void addGestor(pt.ist.rest.domain.Gestor gestor) {
        GestorContainsRestaurante.add((pt.ist.rest.domain.Restaurante)this, gestor);
    }
    
    public void removeGestor(pt.ist.rest.domain.Gestor gestor) {
        GestorContainsRestaurante.remove((pt.ist.rest.domain.Restaurante)this, gestor);
    }
    
    public java.util.List<pt.ist.rest.domain.Gestor> getGestor() {
        return get$rl$gestor();
    }
    
    public void set$gestor(OJBFunctionalSetWrapper gestor) {
        get$rl$gestor().setFromOJB(this, "gestor", gestor);
    }
    
    public java.util.Iterator<pt.ist.rest.domain.Gestor> getGestorIterator() {
        return get$rl$gestor().iterator();
    }
    
    public pt.ist.rest.domain.PortalDeRestaurantes getPortal() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "portal");
        return ((DO_State)this.get$obj$state(false)).portal;
    }
    
    public void setPortal(pt.ist.rest.domain.PortalDeRestaurantes portal) {
        PortalContainsRestaurants.add((pt.ist.rest.domain.Restaurante)this, portal);
    }
    
    public boolean hasPortal() {
        return (getPortal() != null);
    }
    
    public void removePortal() {
        setPortal(null);
    }
    
    private java.lang.Long get$oidPortal() {
        pt.ist.fenixframework.pstm.AbstractDomainObject value = ((DO_State)this.get$obj$state(false)).portal;
        return (value == null) ? null : value.getOid();
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
        RestauranteContainsPrato.add((pt.ist.rest.domain.Restaurante)this, prato);
    }
    
    public void removePrato(pt.ist.rest.domain.Prato prato) {
        RestauranteContainsPrato.remove((pt.ist.rest.domain.Restaurante)this, prato);
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
        if (hasAnyGestor()) handleAttemptToDeleteConnectedObject();
        if (hasPortal()) handleAttemptToDeleteConnectedObject();
        if (hasAnyPrato()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$nome(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "NOME"), state);
        set$morada(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "MORADA"), state);
        castedState.portal = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_PORTAL");
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        if (attrName.equals("gestor")) return GestorContainsRestaurante;
        if (attrName.equals("prato")) return RestauranteContainsPrato;
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        get$$relationList("gestor", GestorContainsRestaurante);
        get$$relationList("prato", RestauranteContainsPrato);
        
    }
    protected static class DO_State extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State {
        private java.lang.String nome;
        private java.lang.String morada;
        private pt.ist.rest.domain.PortalDeRestaurantes portal;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.nome = this.nome;
            newCasted.morada = this.morada;
            newCasted.portal = this.portal;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private java.lang.String nome;
            private java.lang.String morada;
            private pt.ist.rest.domain.PortalDeRestaurantes portal;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.nome = obj.nome;
                this.morada = obj.morada;
                this.portal = obj.portal;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.nome = this.nome;
                state.morada = this.morada;
                state.portal = this.portal;
                
            }
            
        }
        
    }
    
}
