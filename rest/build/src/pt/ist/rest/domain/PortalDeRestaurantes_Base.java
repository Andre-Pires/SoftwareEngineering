package pt.ist.rest.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class PortalDeRestaurantes_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    public final static dml.runtime.RoleMany<pt.ist.rest.domain.PortalDeRestaurantes,pt.ist.rest.domain.Utilizador> role$$utilizador = new dml.runtime.RoleMany<pt.ist.rest.domain.PortalDeRestaurantes,pt.ist.rest.domain.Utilizador>() {
        public dml.runtime.RelationBaseSet<pt.ist.rest.domain.Utilizador> getSet(pt.ist.rest.domain.PortalDeRestaurantes o1) {
            return ((PortalDeRestaurantes_Base)o1).get$rl$utilizador();
        }
        public dml.runtime.Role<pt.ist.rest.domain.Utilizador,pt.ist.rest.domain.PortalDeRestaurantes> getInverseRole() {
            return pt.ist.rest.domain.Utilizador.role$$portal;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.rest.domain.PortalDeRestaurantes,pt.ist.rest.domain.Restaurante> role$$restaurante = new dml.runtime.RoleMany<pt.ist.rest.domain.PortalDeRestaurantes,pt.ist.rest.domain.Restaurante>() {
        public dml.runtime.RelationBaseSet<pt.ist.rest.domain.Restaurante> getSet(pt.ist.rest.domain.PortalDeRestaurantes o1) {
            return ((PortalDeRestaurantes_Base)o1).get$rl$restaurante();
        }
        public dml.runtime.Role<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.PortalDeRestaurantes> getInverseRole() {
            return pt.ist.rest.domain.Restaurante.role$$portal;
        }
        
    };
    public static dml.runtime.Relation<pt.ist.rest.domain.PortalDeRestaurantes,pt.ist.rest.domain.Utilizador> PortalContainsUtilizador;
    public static dml.runtime.Relation<pt.ist.rest.domain.PortalDeRestaurantes,pt.ist.rest.domain.Restaurante> PortalContainsRestaurants;
    
    
    private RelationList<pt.ist.rest.domain.PortalDeRestaurantes,pt.ist.rest.domain.Utilizador> get$rl$utilizador() {
        return get$$relationList("utilizador", PortalContainsUtilizador);
        
    }
    private RelationList<pt.ist.rest.domain.PortalDeRestaurantes,pt.ist.rest.domain.Restaurante> get$rl$restaurante() {
        return get$$relationList("restaurante", PortalContainsRestaurants);
        
    }
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  PortalDeRestaurantes_Base() {
        super();
    }
    
    public int getIdPrato() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "idPrato");
        return ((DO_State)this.get$obj$state(false)).idPrato;
    }
    
    public void setIdPrato(int idPrato) {
        ((DO_State)this.get$obj$state(true)).idPrato = idPrato;
    }
    
    private int get$idPrato() {
        int value = ((DO_State)this.get$obj$state(false)).idPrato;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$idPrato(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).idPrato = (int)(arg0);
    }
    
    public int getPrecoMaximo() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "precoMaximo");
        return ((DO_State)this.get$obj$state(false)).precoMaximo;
    }
    
    public void setPrecoMaximo(int precoMaximo) {
        ((DO_State)this.get$obj$state(true)).precoMaximo = precoMaximo;
    }
    
    private int get$precoMaximo() {
        int value = ((DO_State)this.get$obj$state(false)).precoMaximo;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$precoMaximo(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).precoMaximo = (int)(arg0);
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
    
    public int getNifPortal() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "nifPortal");
        return ((DO_State)this.get$obj$state(false)).nifPortal;
    }
    
    public void setNifPortal(int nifPortal) {
        ((DO_State)this.get$obj$state(true)).nifPortal = nifPortal;
    }
    
    private int get$nifPortal() {
        int value = ((DO_State)this.get$obj$state(false)).nifPortal;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$nifPortal(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).nifPortal = (int)(arg0);
    }
    
    public java.lang.String getNomePortal() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "nomePortal");
        return ((DO_State)this.get$obj$state(false)).nomePortal;
    }
    
    public void setNomePortal(java.lang.String nomePortal) {
        ((DO_State)this.get$obj$state(true)).nomePortal = nomePortal;
    }
    
    private java.lang.String get$nomePortal() {
        java.lang.String value = ((DO_State)this.get$obj$state(false)).nomePortal;
        return (value == null) ? null : pt.ist.fenixframework.pstm.ToSqlConverter.getValueForString(value);
    }
    
    private final void set$nomePortal(java.lang.String arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).nomePortal = (java.lang.String)((arg0 == null) ? null : arg0);
    }
    
    public boolean getPediuSerie() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "pediuSerie");
        return ((DO_State)this.get$obj$state(false)).pediuSerie;
    }
    
    public void setPediuSerie(boolean pediuSerie) {
        ((DO_State)this.get$obj$state(true)).pediuSerie = pediuSerie;
    }
    
    private boolean get$pediuSerie() {
        boolean value = ((DO_State)this.get$obj$state(false)).pediuSerie;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForboolean(value);
    }
    
    private final void set$pediuSerie(boolean arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).pediuSerie = (boolean)(arg0);
    }
    
    public int getUtilizadorCount() {
        return get$rl$utilizador().size();
    }
    
    public boolean hasAnyUtilizador() {
        return (! get$rl$utilizador().isEmpty());
    }
    
    public boolean hasUtilizador(pt.ist.rest.domain.Utilizador utilizador) {
        return get$rl$utilizador().contains(utilizador);
    }
    
    public java.util.Set<pt.ist.rest.domain.Utilizador> getUtilizadorSet() {
        return get$rl$utilizador();
    }
    
    public void addUtilizador(pt.ist.rest.domain.Utilizador utilizador) {
        PortalContainsUtilizador.add((pt.ist.rest.domain.PortalDeRestaurantes)this, utilizador);
    }
    
    public void removeUtilizador(pt.ist.rest.domain.Utilizador utilizador) {
        PortalContainsUtilizador.remove((pt.ist.rest.domain.PortalDeRestaurantes)this, utilizador);
    }
    
    public java.util.List<pt.ist.rest.domain.Utilizador> getUtilizador() {
        return get$rl$utilizador();
    }
    
    public void set$utilizador(OJBFunctionalSetWrapper utilizador) {
        get$rl$utilizador().setFromOJB(this, "utilizador", utilizador);
    }
    
    public java.util.Iterator<pt.ist.rest.domain.Utilizador> getUtilizadorIterator() {
        return get$rl$utilizador().iterator();
    }
    
    public int getRestauranteCount() {
        return get$rl$restaurante().size();
    }
    
    public boolean hasAnyRestaurante() {
        return (! get$rl$restaurante().isEmpty());
    }
    
    public boolean hasRestaurante(pt.ist.rest.domain.Restaurante restaurante) {
        return get$rl$restaurante().contains(restaurante);
    }
    
    public java.util.Set<pt.ist.rest.domain.Restaurante> getRestauranteSet() {
        return get$rl$restaurante();
    }
    
    public void addRestaurante(pt.ist.rest.domain.Restaurante restaurante) {
        PortalContainsRestaurants.add((pt.ist.rest.domain.PortalDeRestaurantes)this, restaurante);
    }
    
    public void removeRestaurante(pt.ist.rest.domain.Restaurante restaurante) {
        PortalContainsRestaurants.remove((pt.ist.rest.domain.PortalDeRestaurantes)this, restaurante);
    }
    
    public java.util.List<pt.ist.rest.domain.Restaurante> getRestaurante() {
        return get$rl$restaurante();
    }
    
    public void set$restaurante(OJBFunctionalSetWrapper restaurante) {
        get$rl$restaurante().setFromOJB(this, "restaurante", restaurante);
    }
    
    public java.util.Iterator<pt.ist.rest.domain.Restaurante> getRestauranteIterator() {
        return get$rl$restaurante().iterator();
    }
    
    protected void checkDisconnected() {
        if (hasAnyUtilizador()) handleAttemptToDeleteConnectedObject();
        if (hasAnyRestaurante()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$idPrato(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "ID_PRATO"), state);
        set$precoMaximo(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "PRECO_MAXIMO"), state);
        set$numSeq(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "NUM_SEQ"), state);
        set$numSerie(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "NUM_SERIE"), state);
        set$nifPortal(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "NIF_PORTAL"), state);
        set$nomePortal(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "NOME_PORTAL"), state);
        set$pediuSerie(pt.ist.fenixframework.pstm.ResultSetReader.readboolean(rs, "PEDIU_SERIE"), state);
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        if (attrName.equals("utilizador")) return PortalContainsUtilizador;
        if (attrName.equals("restaurante")) return PortalContainsRestaurants;
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        get$$relationList("utilizador", PortalContainsUtilizador);
        get$$relationList("restaurante", PortalContainsRestaurants);
        
    }
    protected static class DO_State extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State {
        private int idPrato;
        private int precoMaximo;
        private int numSeq;
        private int numSerie;
        private int nifPortal;
        private java.lang.String nomePortal;
        private boolean pediuSerie;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.idPrato = this.idPrato;
            newCasted.precoMaximo = this.precoMaximo;
            newCasted.numSeq = this.numSeq;
            newCasted.numSerie = this.numSerie;
            newCasted.nifPortal = this.nifPortal;
            newCasted.nomePortal = this.nomePortal;
            newCasted.pediuSerie = this.pediuSerie;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private int idPrato;
            private int precoMaximo;
            private int numSeq;
            private int numSerie;
            private int nifPortal;
            private java.lang.String nomePortal;
            private boolean pediuSerie;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.idPrato = obj.idPrato;
                this.precoMaximo = obj.precoMaximo;
                this.numSeq = obj.numSeq;
                this.numSerie = obj.numSerie;
                this.nifPortal = obj.nifPortal;
                this.nomePortal = obj.nomePortal;
                this.pediuSerie = obj.pediuSerie;
                
            }
            
             Object readResolve() throws java.io.ObjectStreamException {
                DO_State newState = new DO_State();
                fillInState(newState);
                return newState;
            }
            
            protected void fillInState(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State obj) {
                super.fillInState(obj);
                DO_State state = (DO_State)obj;
                state.idPrato = this.idPrato;
                state.precoMaximo = this.precoMaximo;
                state.numSeq = this.numSeq;
                state.numSerie = this.numSerie;
                state.nifPortal = this.nifPortal;
                state.nomePortal = this.nomePortal;
                state.pediuSerie = this.pediuSerie;
                
            }
            
        }
        
    }
    
}
