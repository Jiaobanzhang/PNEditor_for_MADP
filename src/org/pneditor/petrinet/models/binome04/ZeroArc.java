package org.pneditor.petrinet.models.binome04;

/**
 * Represents an input arc that only allows the transition if the Place has zero tokens.
 * Used to impose conditions on transitions based on the absence of tokens.
 * 
 * @autor: JIAO Yongshun, ZHU Xinlei
 * @date: 08/11/2024
 * @version: v1.0
 */
public class ZeroArc extends ArcIn{

    public ZeroArc(Place place) {
        super(0, place);
    }

    @Override
    public boolean fireAble(){
        return this.getPlace().getToken() == 0;
    }

    @Override
    public void executeTransition(){
        if (fireAble());
    }
}
