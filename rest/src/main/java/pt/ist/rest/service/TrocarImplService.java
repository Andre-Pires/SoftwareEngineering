package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.PortalDeRestaurantes;


public class TrocarImplService extends
        RestService {

    private final boolean local;

    public TrocarImplService(boolean local) {
        this.local = local;
    }

    /* (non-Javadoc)
     * @see pt.ist.rest.service.RestService#dispatch()
     */
    @Override
    protected void dispatch() {
        System.out.println("antes do getRoot.");
        PortalDeRestaurantes portalDeRestaurantes = FenixFramework.getRoot();
        System.out.println("terminou o getRoot.");

        if (local) {
            portalDeRestaurantes.setLocal();
        } else {
            portalDeRestaurantes.setRemote();
        }
    }
}
