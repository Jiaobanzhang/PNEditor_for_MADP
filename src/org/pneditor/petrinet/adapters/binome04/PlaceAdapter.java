package org.pneditor.petrinet.adapters.binome04;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.binome04.PetriNet;
import org.pneditor.petrinet.models.binome04.Place;

public class PlaceAdapter extends AbstractPlace {
    private final Place place;

    // Constructor to initialize the adapter with a Place object and petrinet
    public PlaceAdapter(Place place) {
        super(""); // Initialize AbstractPlace with an empty label or a default name
        this.place = place;
    }

    @Override
    public void addToken() {
        this.place.addToken(1); // Increment token count by 1
    }

    @Override
    public void removeToken() {
        this.place.removeToken(1); // Decrement token count by 1
    }

    @Override
    public int getTokens() {
        return this.place.getToken(); // Get the current token count
    }

    @Override
    public void setTokens(int tokens) {
        // Adjust tokens to the target value
        int currentTokens = this.place.getToken();
        if (tokens > currentTokens) {
            this.place.addToken(tokens - currentTokens); // Add the difference
        } else if (tokens < currentTokens) {
            this.place.removeToken(currentTokens - tokens); // Remove the difference
        }
    }

    /**
     * Expose the underlying Place object for direct access if needed.
     */
    public Place getActualNode() {
        return this.place;
    }
}
