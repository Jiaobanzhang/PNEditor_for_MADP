package org.pneditor.petrinet.adapters.binome04;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.binome04.Transition;

public class TransitionAdapter extends AbstractTransition {
    private final Transition transition;

    // Constructor to initialize the adapter with the Transition object
    public TransitionAdapter(Transition transition) {
        super(""); // Initialize AbstractTransition with an empty label or a default name
        this.transition = transition;
    }

    /**
     * Executes the transition using the encapsulated Transition object.
     */
    public void doFire() {
        this.transition.doFire();
    }

    /**
     * Checks if the transition can fire by using the encapsulated Transition object.
     *
     * @return true if the transition is fireable, false otherwise.
     */
    public boolean isFireAble() {
        return this.transition.isFireAble();
    }

    /**
     * Exposes the underlying Transition object for direct access if needed.
     *
     * @return the encapsulated Transition object.
     */
    public Transition getActualNode() {
        return this.transition;
    }
}