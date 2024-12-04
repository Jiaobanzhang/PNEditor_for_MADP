package org.pneditor.petrinet.models.binome04;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the PetriNet interface.
 * Manages places, arcs, and transitions, and executes the steps of the Petri Net.
 * 
 * @autor: JIAO Yongshun, ZHU Xinlei
 * @date: 08/11/2024
 * @version: v1.0
 * 
 */

public class PetriNet implements PetriNetInterface {
    
    private List<Place> places;
    private List<Arc> arcs;
    private List<Transition> transitions;

    public PetriNet() {
        this.places = new ArrayList<>();
        this.arcs = new ArrayList<>();
        this.transitions = new ArrayList<>();
    }

    public PetriNet(List<Place> places, List<Arc> arcs, List<Transition> transitions) {
        this.places = places;
        this.arcs = arcs;
        this.transitions = transitions;
    }

    @Override
    /*
     * Adds a Place to the Petri Net.
     */
    public void addPlace(Place place) {
        places.add(place);
    }

    @Override
    /*
     * Removes a Place from the Petri Net.
     */
    public void deletePlace(Place place) {
        places.remove(place);
    }

    @Override
    /*
     * Adds an Arc to the Petri Net.
     */
    public void addArc(Arc arc) {
        arcs.add(arc);
    }

    @Override
    public void deleteArc(Arc arc, Transition transition) {
        if(arc instanceof ArcIn){
            transition.removeArcIn((ArcIn) arc);
        }else if(arc instanceof ArcOut){
            transition.removeArcOut((ArcOut) arc);
        }
        arcs.remove(arc);
    }

    @Override
    /*
     * Adds a Transition to the Petri Net.
     */
    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    @Override
    /*
     * Removes a Transition from the Petri Net.
     */
    public void deleteTransition(Transition transition) {
        transitions.remove(transition);
    }

    @Override
    /*
     * Executes all transitions that are fireable.
     */
    public void fireAll() {
        for (Transition transition : transitions) {
            fire(transition);
        }
    }

    @Override
    /*
     * Executes a specific transition.
     */
    public void fire(Transition transition){
        if(transition.isFireAble()){
           transition.doFire();
        }
    }
}