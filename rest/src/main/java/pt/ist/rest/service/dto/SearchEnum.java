package pt.ist.rest.service.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum SearchEnum implements IsSerializable {

    NOME("NOME"), TIPO("TIPO");

    private final String value;

    SearchEnum() {
        this.value = null;
    }

    SearchEnum(String s) {
        this.value = s;
    }

    public static SearchEnum fromValue(String s) {
        if (s != null) {
            for (SearchEnum sE : values()) {
                if (sE.value.equals(s))
                    return sE;
            }
        }

        return null;
    }

    public String toValue() {
        return value;
    }

}
