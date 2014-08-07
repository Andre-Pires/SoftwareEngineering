package pt.ist.rest;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.PortalDeRestaurantes;

public class DatabaseBootstrap {
    private static boolean isInitialized = false;

    public synchronized static void init() {
        if (!isInitialized) {
            FenixFramework.initialize(new Config() {
                {
                    dbAlias = "//localhost:3306/restdb";
                    dbUsername = "rest";
                    dbPassword = "r3st";
                    domainModelPath = "src/main/dml/domain.dml";
                    rootClass = PortalDeRestaurantes.class;
                }
            });
        }

        isInitialized = true;
    }

    public static void setup() {
        pt.ist.rest.RestSetup.main(null);
    }
}
