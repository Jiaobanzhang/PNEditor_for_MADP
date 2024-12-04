package org.pneditor.petrinet.models.binome04;

/**
 * Represents a Place in a Petri Net, holding a certain number of tokens.
 * Tokens represent resources or conditions needed for transitions.
 * 
 * @autor: JIAO Yongshun, ZHU Xinlei
 * @date: 08/11/2024
 * @version: v1.0
 * 
 */

public class Place {
    private int token;

    public Place() {
    }

    public Place(int token) {
        this.token = token;
    }

    public int getToken() {
        return token;
    }

    public void removeToken(int count) {
        token -= count;
    }

    public void addToken(int count) {
        token += count;
    }
}