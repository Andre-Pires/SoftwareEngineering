package pt.ist.rest.service.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SearchDto
        implements IsSerializable {

    private String sE;

    private String arg;


    public SearchDto() {
    }

    public SearchDto(String tipo, String input) {
        this.sE = tipo;
        this.arg = input;
    }


    public String getEnum() {
        return sE;
    }

    public String getArg() {
        return arg;
    }


}
