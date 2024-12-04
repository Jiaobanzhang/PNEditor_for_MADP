package org.pneditor.petrinet.adapters.binome04;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.binome04.Arc;
import org.pneditor.petrinet.models.binome04.PetriNet;

public class ArcAdapter extends AbstractArc {
    private final AbstractNode from;
    private final AbstractNode to;
    private final Arc arc;
    private final String type;

    public ArcAdapter(Arc arc, AbstractNode from, AbstractNode to, String type) {
        this.from = from;
        this.to = to;
        this.arc = arc;
        this.type = type;
    }

    public AbstractNode getSource() {
        return this.from;
    }

    public AbstractNode getDestination() {
        return this.to;
    }

    public boolean isReset() {
        return this.type.equals("VIDEURS");
    }

    public boolean isRegular() {
        return (this.type.equals("IN")||this.type.equals("OUT"));
    }

    public boolean isInhibitory() {
        return this.type.equals("ZERO");
    }

    public int getMultiplicity() throws ResetArcMultiplicityException {
        return this.arc.getWeight();
    }

    public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
        if (this.isReset()) {
            throw new ResetArcMultiplicityException();
        } else {
            this.arc.setWeight(multiplicity);
        }
    }
    
    public Arc getActualNode() {
        return this.arc;
    }

    public String getType() {
        return this.type;
    }
}