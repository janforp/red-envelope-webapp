package com.iask.red_envelope.model.dto;

import java.io.Serializable;

/**
 * Created by craig on 16/2/14.
 */
public class StateDto implements Serializable {
    private String state;
    private String scope;
    private String uri;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}