package org.pneditor.petrinet.adapters.binome04;

import org.pneditor.petrinet.*;
import org.pneditor.petrinet.models.binome04.*;


public class PetriNetAdapter extends PetriNetInterface {
    private PetriNet petriNet;

    public PetriNetAdapter() {
        this.petriNet = new PetriNet();
    }

    @Override
    public AbstractPlace addPlace() {
        Place newPlace = new Place();
        this.petriNet.addPlace(newPlace);
        return new PlaceAdapter(newPlace); // 适配为 AbstractPlace
    }

    @Override
    public AbstractTransition addTransition() {
        Transition newTransition = new Transition(); // 假设 Transition 类有一个无参构造函数
        this.petriNet.addTransition(newTransition);
        // TODO: 写 transitionAdapter构造器TransitionAdapter(Transition transition)
        return new TransitionAdapter(newTransition); // 适配为 AbstractTransition
    }

    @Override
    public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
        if (source.isPlace() && !destination.isPlace()) { // 说明是 ArcIn
            // 1. 和petrinet 关联起来
            PlaceAdapter placeAdapter = (PlaceAdapter) source;
            Place place = placeAdapter.getActualNode();

            ArcIn arcIn = new ArcIn(1, place); // 3. 在创建的时候和 place 进行关联
            this.petriNet.addArc(arcIn);
            // 2. 和 transition 进行关联
            TransitionAdapter transitionAdapter = (TransitionAdapter) destination;

            Transition transition = transitionAdapter.getActualNode();

            transition.addArcIn(arcIn);
            // TODO: 写 arcAdapter构造器ArcAdapter(Arc arc)
            return new ArcAdapter(arcIn, placeAdapter, transitionAdapter, "IN"); // 适配为 AbstractArc
        } else if (!source.isPlace() && destination.isPlace()) { // 说明是 ArcOut
            //  1. 和petrinet 关联起来
            PlaceAdapter placeAdapter = (PlaceAdapter) destination;
            Place place = placeAdapter.getActualNode();

            ArcOut arcOut = new ArcOut(1, place); // 假设默认权重为 1
            this.petriNet.addArc(arcOut);
            // 2. 和 transition 进行关联
            TransitionAdapter transitionAdapter = (TransitionAdapter) source;
            Transition actualTransition = transitionAdapter.getActualNode();
            actualTransition.addArcOut(arcOut);

            // TODO: 写 arcAdapter构造器ArcAdapter(Arc arc)
            return new ArcAdapter(arcOut, transitionAdapter, placeAdapter, "OUT"); // 适配为 AbstractArc
        } else {
            throw new UnimplementedCaseException("ArcRegularArc should be added between a place and a transition.");
        }
    }

    @Override
    public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
            throws UnimplementedCaseException {
        // 1. 和 PetriNet 进行连接
        PlaceAdapter placeAdapter = (PlaceAdapter) place; // 向下转型
        Place actualPlace = placeAdapter.getActualNode(); // 调用 getActualNode()

        ZeroArc zeroArc = new ZeroArc(actualPlace);
        this.petriNet.addArc(zeroArc);

        // 2. 和 Transition 进行连接:
        TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        Transition actualTransition = transitionAdapter.getActualNode();
        actualTransition.addArcIn(zeroArc);

        // TODO: 写 arcAdapter构造器ArcAdapter(Arc arc)
        return new ArcAdapter(zeroArc, placeAdapter, transitionAdapter, "ZERO");
    }

    @Override
    public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition) throws UnimplementedCaseException {

        // 1. 和 PetriNet 进行连接
        PlaceAdapter placeAdapter = (PlaceAdapter) place; // 向下转型
        Place actualPlace = placeAdapter.getActualNode(); // 调用 getActualNode()
        VideursArc videursArc = new VideursArc(actualPlace);
        this.petriNet.addArc(videursArc);
        // 2. 和 Transition 进行连接:
        TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        Transition actualTransition = transitionAdapter.getActualNode();
        actualTransition.addArcIn(videursArc);

        // TODO: 写 arcAdapter构造器ArcAdapter(Arc arc)
        return new ArcAdapter(videursArc, placeAdapter, transitionAdapter, "VIDEURS");

    }

    @Override
    public void removePlace(AbstractPlace place) {
        PlaceAdapter adapter = (PlaceAdapter) place; // 向下转型
        Place actualPlace = adapter.getActualNode();

        this.petriNet.deletePlace(actualPlace);
    }

    @Override
    public void removeTransition(AbstractTransition transition) {
        TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        Transition actualtransition = transitionAdapter.getActualNode();

        this.petriNet.deleteTransition(actualtransition);
    }

    @Override
    public void removeArc(AbstractArc arc) {
        AbstractNode node;
        TransitionAdapter transition;
        ArcAdapter arcAdapter = (ArcAdapter) arc;
        Arc actualArc = arcAdapter.getActualNode();
        if (arcAdapter.getType().equals("OUT")) {
            node = arcAdapter.getSource();
            transition = (TransitionAdapter) node;
        } else {
            node = arcAdapter.getDestination();
            transition = (TransitionAdapter) node;
        }

        this.petriNet.deleteArc(actualArc, transition.getActualNode());
    }

    @Override
    public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
        TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        return transitionAdapter.isFireAble();
    }

    @Override
    public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
        TransitionAdapter transitionAdapter = (TransitionAdapter) transition;
        transitionAdapter.doFire();
    }
}