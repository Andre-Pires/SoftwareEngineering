package pt.ist.rest.service;

import jvstm.Atomic;

/**
 * Classe abstracta da qual herdam os serviços todos.
 * 
 * @throws RuntimeException em caso de não ser definida excepção a lançar.
 */
public abstract class RestService {

    @Atomic
    public void execute() {
        dispatch();
    }

    protected abstract void dispatch();
}
