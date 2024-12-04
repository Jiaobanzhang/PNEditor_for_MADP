package org.pneditor.petrinet.models.binome04;

/**
 * Represents a special type of input arc that empties the Place of all tokens.
 * This arc type only allows the transition if the Place has tokens available.
 * When the transition occurs, all tokens are removed from the Place.
 * 
 * @autor: JIAO Yongshun, ZHU Xinlei
 * @date: 08/11/2024
 * @version: v1.0
 * 
 */
public class VideursArc extends ArcIn{

    public VideursArc(Place place) {
        super(0, place);
    }

    @Override
    public boolean fireAble() {
        return this.getPlace().getToken() > 0;
    }

    @Override
    public void executeTransition(){
        int allToken = this.getPlace().getToken();
        this.getPlace().removeToken(allToken);
    }
}
