package org.pneditor.petrinet.models.binome04;

/**
 * Interface representing the basic structure of a Petri Net.
 * Defines methods for managing places, arcs, and transitions.
 *
 * @autor: JIAO Yongshun, ZHU Xinlei
 * @date: 08/11/2024
 * @version: v1.0
 * 
 */

interface PetriNetInterface {
    void addPlace(Place place);
    void deletePlace(Place place);
    void addArc(Arc arc);
//    void deleteArc(Arc arc);

    /*
     * Removes an Arc from the Petri Net.
     */
    void deleteArc(Arc arc, Transition transition);

    void addTransition(Transition transition);
    void deleteTransition(Transition transition);
    void fireAll();
    void fire(Transition transition);
}