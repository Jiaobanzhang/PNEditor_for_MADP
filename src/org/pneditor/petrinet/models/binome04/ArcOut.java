package org.pneditor.petrinet.models.binome04;

/**
 * Represents an output arc in a Petri Net, connecting a Transition to a Place.
 * Adds tokens to the Place based on the arc's weight when the transition occurs.
 * 
 * @autor: JIAO Yongshun, ZHU Xinlei
 * @date: 08/11/2024
 * @version: v1.0
 */

public class ArcOut extends Arc {

    public ArcOut(int weight, Place place) {
        super(weight, place);
    }

    /**
     * Executes the transition by adding tokens to the Place.
     */
    public void executeTransition() {
        this.getPlace().addToken(this.getWeight());
    }
}
