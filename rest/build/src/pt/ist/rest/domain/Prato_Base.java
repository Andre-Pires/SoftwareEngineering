package pt.ist.rest.domain;

import pt.ist.fenixframework.pstm.VBox;
import pt.ist.fenixframework.pstm.RelationList;
import pt.ist.fenixframework.pstm.OJBFunctionalSetWrapper;
import pt.ist.fenixframework.ValueTypeSerializationGenerator.*;
public abstract class Prato_Base extends pt.ist.fenixframework.pstm.OneBoxDomainObject {
    public final static dml.runtime.RoleMany<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Cliente> role$$cliente = new dml.runtime.RoleMany<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Cliente>() {
        public dml.runtime.RelationBaseSet<pt.ist.rest.domain.Cliente> getSet(pt.ist.rest.domain.Prato o1) {
            return ((Prato_Base)o1).get$rl$cliente();
        }
        public dml.runtime.Role<pt.ist.rest.domain.Cliente,pt.ist.rest.domain.Prato> getInverseRole() {
            return pt.ist.rest.domain.Cliente.role$$prato;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Restaurante> role$$restaurante = new pt.ist.fenixframework.pstm.dml.RoleOne<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Restaurante>() {
        public pt.ist.rest.domain.Restaurante getValue(pt.ist.rest.domain.Prato o1) {
            return ((Prato_Base.DO_State)o1.get$obj$state(false)).restaurante;
        }
        public void setValue(pt.ist.rest.domain.Prato o1, pt.ist.rest.domain.Restaurante o2) {
            ((Prato_Base.DO_State)o1.get$obj$state(true)).restaurante = o2;
        }
        public dml.runtime.Role<pt.ist.rest.domain.Restaurante,pt.ist.rest.domain.Prato> getInverseRole() {
            return pt.ist.rest.domain.Restaurante.role$$prato;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.rest.domain.Prato,pt.ist.rest.domain.QuantidadePrato> role$$quantidadePrato = new dml.runtime.RoleMany<pt.ist.rest.domain.Prato,pt.ist.rest.domain.QuantidadePrato>() {
        public dml.runtime.RelationBaseSet<pt.ist.rest.domain.QuantidadePrato> getSet(pt.ist.rest.domain.Prato o1) {
            return ((Prato_Base)o1).get$rl$quantidadePrato();
        }
        public dml.runtime.Role<pt.ist.rest.domain.QuantidadePrato,pt.ist.rest.domain.Prato> getInverseRole() {
            return pt.ist.rest.domain.QuantidadePrato.role$$prato;
        }
        
    };
    public final static dml.runtime.RoleMany<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Alimento> role$$alimento = new dml.runtime.RoleMany<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Alimento>() {
        public dml.runtime.RelationBaseSet<pt.ist.rest.domain.Alimento> getSet(pt.ist.rest.domain.Prato o1) {
            return ((Prato_Base)o1).get$rl$alimento();
        }
        public dml.runtime.Role<pt.ist.rest.domain.Alimento,pt.ist.rest.domain.Prato> getInverseRole() {
            return pt.ist.rest.domain.Alimento.role$$prato;
        }
        
    };
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Cliente> Gostos = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Cliente>(role$$cliente);
    static {
        pt.ist.rest.domain.Cliente.Gostos = Gostos.getInverseRelation();
    }
    
    static {
        Gostos.setRelationName("pt.ist.rest.domain.Prato.Gostos");
        Gostos.addListener(new dml.runtime.RelationAdapter<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Cliente>() {
            @Override
            public void beforeAdd(pt.ist.rest.domain.Prato arg0, pt.ist.rest.domain.Cliente arg1) {
                pt.ist.fenixframework.pstm.Transaction.addRelationTuple("Gostos", arg1, "prato", arg0, "cliente");
            }
            @Override
            public void beforeRemove(pt.ist.rest.domain.Prato arg0, pt.ist.rest.domain.Cliente arg1) {
                pt.ist.fenixframework.pstm.Transaction.removeRelationTuple("Gostos", arg1, "prato", arg0, "cliente");
            }
            
        }
        );
    }
    public final static pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Restaurante> RestauranteContainsPrato = new pt.ist.fenixframework.pstm.LoggingRelation<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Restaurante>(role$$restaurante);
    static {
        pt.ist.rest.domain.Restaurante.RestauranteContainsPrato = RestauranteContainsPrato.getInverseRelation();
    }
    
    static {
        RestauranteContainsPrato.setRelationName("pt.ist.rest.domain.Prato.RestauranteContainsPrato");
    }
    public static dml.runtime.Relation<pt.ist.rest.domain.Prato,pt.ist.rest.domain.QuantidadePrato> PratoContainsQuantidadePrato;
    public static dml.runtime.Relation<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Alimento> PratoContainsAlimento;
    
    
    private RelationList<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Cliente> get$rl$cliente() {
        return get$$relationList("cliente", Gostos);
        
    }
    private RelationList<pt.ist.rest.domain.Prato,pt.ist.rest.domain.QuantidadePrato> get$rl$quantidadePrato() {
        return get$$relationList("quantidadePrato", PratoContainsQuantidadePrato);
        
    }
    private RelationList<pt.ist.rest.domain.Prato,pt.ist.rest.domain.Alimento> get$rl$alimento() {
        return get$$relationList("alimento", PratoContainsAlimento);
        
    }
    
    
    private void initInstance() {
        initInstance(true);
    }
    
    private void initInstance(boolean allocateOnly) {
        
    }
    
    {
        initInstance(false);
    }
    
    protected  Prato_Base() {
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
    
    public int getPreco() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "preco");
        return ((DO_State)this.get$obj$state(false)).preco;
    }
    
    public void setPreco(int preco) {
        ((DO_State)this.get$obj$state(true)).preco = preco;
    }
    
    private int get$preco() {
        int value = ((DO_State)this.get$obj$state(false)).preco;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$preco(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).preco = (int)(arg0);
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
    
    public int getCalorias() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "calorias");
        return ((DO_State)this.get$obj$state(false)).calorias;
    }
    
    public void setCalorias(int calorias) {
        ((DO_State)this.get$obj$state(true)).calorias = calorias;
    }
    
    private int get$calorias() {
        int value = ((DO_State)this.get$obj$state(false)).calorias;
        return pt.ist.fenixframework.pstm.ToSqlConverter.getValueForint(value);
    }
    
    private final void set$calorias(int arg0, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  obj$state) {
        ((DO_State)obj$state).calorias = (int)(arg0);
    }
    
    public int getClienteCount() {
        return get$rl$cliente().size();
    }
    
    public boolean hasAnyCliente() {
        return (! get$rl$cliente().isEmpty());
    }
    
    public boolean hasCliente(pt.ist.rest.domain.Cliente cliente) {
        return get$rl$cliente().contains(cliente);
    }
    
    public java.util.Set<pt.ist.rest.domain.Cliente> getClienteSet() {
        return get$rl$cliente();
    }
    
    public void addCliente(pt.ist.rest.domain.Cliente cliente) {
        Gostos.add((pt.ist.rest.domain.Prato)this, cliente);
    }
    
    public void removeCliente(pt.ist.rest.domain.Cliente cliente) {
        Gostos.remove((pt.ist.rest.domain.Prato)this, cliente);
    }
    
    public java.util.List<pt.ist.rest.domain.Cliente> getCliente() {
        return get$rl$cliente();
    }
    
    public void set$cliente(OJBFunctionalSetWrapper cliente) {
        get$rl$cliente().setFromOJB(this, "cliente", cliente);
    }
    
    public java.util.Iterator<pt.ist.rest.domain.Cliente> getClienteIterator() {
        return get$rl$cliente().iterator();
    }
    
    public pt.ist.rest.domain.Restaurante getRestaurante() {
        pt.ist.fenixframework.pstm.DataAccessPatterns.noteGetAccess(this, "restaurante");
        return ((DO_State)this.get$obj$state(false)).restaurante;
    }
    
    public void setRestaurante(pt.ist.rest.domain.Restaurante restaurante) {
        RestauranteContainsPrato.add((pt.ist.rest.domain.Prato)this, restaurante);
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
        PratoContainsQuantidadePrato.add((pt.ist.rest.domain.Prato)this, quantidadePrato);
    }
    
    public void removeQuantidadePrato(pt.ist.rest.domain.QuantidadePrato quantidadePrato) {
        PratoContainsQuantidadePrato.remove((pt.ist.rest.domain.Prato)this, quantidadePrato);
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
    
    public int getAlimentoCount() {
        return get$rl$alimento().size();
    }
    
    public boolean hasAnyAlimento() {
        return (! get$rl$alimento().isEmpty());
    }
    
    public boolean hasAlimento(pt.ist.rest.domain.Alimento alimento) {
        return get$rl$alimento().contains(alimento);
    }
    
    public java.util.Set<pt.ist.rest.domain.Alimento> getAlimentoSet() {
        return get$rl$alimento();
    }
    
    public void addAlimento(pt.ist.rest.domain.Alimento alimento) {
        PratoContainsAlimento.add((pt.ist.rest.domain.Prato)this, alimento);
    }
    
    public void removeAlimento(pt.ist.rest.domain.Alimento alimento) {
        PratoContainsAlimento.remove((pt.ist.rest.domain.Prato)this, alimento);
    }
    
    public java.util.List<pt.ist.rest.domain.Alimento> getAlimento() {
        return get$rl$alimento();
    }
    
    public void set$alimento(OJBFunctionalSetWrapper alimento) {
        get$rl$alimento().setFromOJB(this, "alimento", alimento);
    }
    
    public java.util.Iterator<pt.ist.rest.domain.Alimento> getAlimentoIterator() {
        return get$rl$alimento().iterator();
    }
    
    protected void checkDisconnected() {
        if (hasAnyCliente()) handleAttemptToDeleteConnectedObject();
        if (hasRestaurante()) handleAttemptToDeleteConnectedObject();
        if (hasAnyQuantidadePrato()) handleAttemptToDeleteConnectedObject();
        if (hasAnyAlimento()) handleAttemptToDeleteConnectedObject();
        
    }
    
    protected void readStateFromResultSet(java.sql.ResultSet rs, pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  state) throws java.sql.SQLException {
        DO_State castedState = (DO_State)state;
        set$nome(pt.ist.fenixframework.pstm.ResultSetReader.readString(rs, "NOME"), state);
        set$preco(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "PRECO"), state);
        set$id(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "ID"), state);
        set$calorias(pt.ist.fenixframework.pstm.ResultSetReader.readint(rs, "CALORIAS"), state);
        castedState.restaurante = pt.ist.fenixframework.pstm.ResultSetReader.readDomainObject(rs, "OID_RESTAURANTE");
    }
    protected dml.runtime.Relation get$$relationFor(String attrName) {
        if (attrName.equals("cliente")) return Gostos;
        if (attrName.equals("quantidadePrato")) return PratoContainsQuantidadePrato;
        if (attrName.equals("alimento")) return PratoContainsAlimento;
        return super.get$$relationFor(attrName);
        
    }
    protected pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  make$newState() {
        return new DO_State();
        
    }
    protected void create$allLists() {
        super.create$allLists();
        get$$relationList("cliente", Gostos);
        get$$relationList("quantidadePrato", PratoContainsQuantidadePrato);
        get$$relationList("alimento", PratoContainsAlimento);
        
    }
    protected static class DO_State extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State {
        private java.lang.String nome;
        private int preco;
        private int id;
        private int calorias;
        private pt.ist.rest.domain.Restaurante restaurante;
        protected void copyTo(pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State  newState) {
            super.copyTo(newState);
            DO_State newCasted = (DO_State)newState;
            newCasted.nome = this.nome;
            newCasted.preco = this.preco;
            newCasted.id = this.id;
            newCasted.calorias = this.calorias;
            newCasted.restaurante = this.restaurante;
            
        }
        
        // serialization code
        protected Object writeReplace() throws java.io.ObjectStreamException {
            return new SerializedForm(this);
        }
        
        protected static class SerializedForm extends pt.ist.fenixframework.pstm.OneBoxDomainObject.DO_State.SerializedForm {
            private static final long serialVersionUID = 1L;
            
            private java.lang.String nome;
            private int preco;
            private int id;
            private int calorias;
            private pt.ist.rest.domain.Restaurante restaurante;
            
            protected  SerializedForm(DO_State obj) {
                super(obj);
                this.nome = obj.nome;
                this.preco = obj.preco;
                this.id = obj.id;
                this.calorias = obj.calorias;
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
                state.nome = this.nome;
                state.preco = this.preco;
                state.id = this.id;
                state.calorias = this.calorias;
                state.restaurante = this.restaurante;
                
            }
            
        }
        
    }
    
}
