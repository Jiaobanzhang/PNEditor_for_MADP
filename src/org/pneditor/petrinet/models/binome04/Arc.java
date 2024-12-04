package org.pneditor.petrinet.models.binome04;

/**
 * Represents a generic arc in a Petri Net, connecting a Place with a Transition.
 * Arcs can have a weight, representing the number of tokens transferred during transitions.
 * This is a base class for different types of arcs.
 *
 * @author: JIAO Yongshun, ZHU Xinlei
 * @date: 08/11/2024
 * @version: v1.0
 * 
 */

public class Arc {
    private Place place;
    private int weight;

    public Arc(Place place) {
        this.place = place;
    }

    public Arc(int weight, Place place) {
        this.place = place;
        this.weight = weight;
    }

    public Place getPlace() {
        return place;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
