# Binôme04 - TP16

## 1. Project structure

```
org/
├── pneditor/
│   ├── petrinet/
│   │   ├── adapters/
│   │   │   ├── imta/
│   │   │   ├── initial/
│   │   │   └── binome04/
│   │   │       ├── ArcAdapter.java
│   │   │       ├── PetriNetAdapter.java
│   │   │       ├── PlaceAdapter.java
│   │   │       └── TransitionAdapter.java
│   │		├── models/
│   │   │		├── imta/
│   │   │		├── initial/
│   │   │		└── binome04/
│   │       		├── Arc.java
│   │       		├── ArcIn.java
│   │       		├── ArcOut.java
│   │       		├── PetriNet.java
│   │       		├── PetriNetInterface.java
│   │       		├── Place.java
│   │       		├── Transition.java
│   │       		├── VideursArc.java
│   │       		└── ZeroArc.java

```

![截屏2024-12-04 14.20.17](https://p.ipic.vip/ywu1qv.png)

## 2. Project content (Adapters part)

### (1) PetriNetAdapter :

- #### Overview: 

**PetriNetAdapter** is an adapter class that bridges the functionality of the **`PetriNet`** model with the abstracted interface **PetriNetInterface**. It facilitates adding and removing places, transitions, and arcs, while also supporting various specialized arcs like inhibitory arcs, reset arcs, and regular arcs.

- #### Methods

  - ##### Place Management

    - **`AbstractPlace addPlace()`**
      - Adds a new `Place` to the Petri Net.
      - Returns a `PlaceAdapter` wrapping the newly added `Place`.

    - **`void removePlace(AbstractPlace place)`**
      - Removes a `Place` from the Petri Net.

  - ##### Transition Management

    - **`AbstractTransition addTransition()`**
      - Adds a new `Transition` to the Petri Net.
      - Returns a `TransitionAdapter` wrapping the newly added `Transition`.

    - **`void removeTransition(AbstractTransition transition)`**
      - Removes a `Transition` from the Petri Net.

  - ##### Arc Management

    - **`AbstractArc addRegularArc(AbstractNode source, AbstractNode destination)`**
      - Adds a regular arc between a `Place` and a `Transition` 
      - Supports `ArcIn` and `ArcOut` based on the direction of the arc.

    - **`AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)`**
      - Adds an inhibitory (zero) arc between a `Place` and a `Transition`.

    - **`AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)`**
      - Adds a reset arc (clears tokens in a `Place`) between a `Place` and a `Transition`.

    - **`void removeArc(AbstractArc arc)`**
      - Removes an arc (regular, inhibitory, or reset) from the Petri Net.

  - ##### Transition Firing

    - **`boolean isEnabled(AbstractTransition transition)`**
      - Checks if a `Transition` is enabled (fireable).

    - **`void fire(AbstractTransition transition)`**
      - Fires a `Transition` in the Petri Net, executing the associated arcs.

### (2) TransitionAdapter :

- #### Overview:

The **`TransitionAdapter`** class is an adapter that bridges the functionality of the **`Transition`** class with the abstract **`AbstractTransition`** interface.

- #### Constructor:

  - **`TransitionAdapter(Transition transition)`**

    - Initializes the adapter with a `Transition` object.

    - Calls the parent `AbstractTransition` constructor with a default or empty label.

- #### Methods:

  - **`void doFire()`**
    - Executes the transition by delegating the call to the `Transition` object.
    - Ensures that the transition's logic is encapsulated while being accessible via the adapter.

  - **`boolean isFireAble()`**
    - Checks if the transition is enabled (fireable).
    - Returns `true` if the transition can fire; otherwise, `false`.

  - **`Transition getActualNode()`**
    - Provides access to the underlying `Transition` object.

### (3) PlaceAdapter :

- #### Overview:

​	The **`PlaceAdapter`** class is an adapter that bridges the functionality of the **`Place`** class with the **`AbstractPlace`** interface. 

- #### Constructor:

  - **`PlaceAdapter(Place place)`**

    - Initializes the adapter with a `Place` object.

    - Calls the parent `AbstractPlace` constructor with a default or empty label.

- #### Methods:

  - **`void addToken()`**
    - Adds one token to the `Place`.
    - Delegates the call to `Place.addToken(int)`.
  - **`void removeToken()`**
    - Removes one token from the `Place`.
    - Delegates the call to `Place.removeToken(int)`.
  - **`int getTokens()`**
    - Returns the current number of tokens in the `Place`.
    - Delegates the call to `Place.getToken()`.
  - **`void setTokens(int tokens)`**
    - Adjusts the token count in the `Place` to a specified value.
    - Calculates the difference between the current and target token counts and applies the necessary additions or removals.
  - **`Place getActualNode()`**
    - Returns the encapsulated `Place` object.


### (4) ArcAdapter :

- #### Overview :

  The `ArcAdapter` class is an adapter that bridges the functionality of the `Arc` class with the `AbstractArc` interface. 

- ### Constructor:

  - **`ArcAdapter(Arc arc, AbstractNode from, AbstractNode to, String type)`**

    - Initializes the adapter with an `Arc` object, source node (`from`), destination node (`to`), and the type of the arc (`type`).

    - Parameters:
      - `arc`: The encapsulated `Arc` object.
      - `from`: The source node of the arc (must implement `AbstractNode`).
      - `to`: The destination node of the arc (must implement `AbstractNode`).
      - `type`: The type of the arc, such as `IN`,  `OUT`,`ZERO`, or `VIDEURS`.

- ### Methods :

  - **`AbstractNode getSource()`**
    - Returns the source node of the arc. 
    - Delegates to the encapsulated `from` node.

  - **`AbstractNode getDestination()`**
    - Returns the destination node of the arc.
    - Delegates to the encapsulated `to` node.

  - **`boolean isReset()`**
    - Returns `true` if the arc is a reset arc (`VIDEURS` type); otherwise, `false`

  - **`boolean isRegular()`**
    - Returns `true` if the arc is a regular arc (`in` or `out`  type); otherwise, `false`.

  - **`boolean isInhibitory()`**
    - Returns `true` if the arc is an inhibitory arc (`ZERO` type); otherwise, `false`.

  - **`int getMultiplicity()`**
    - Returns the weight (multiplicity) of the arc.
    - Throws a `ResetArcMultiplicityException` if the arc is a reset arc (`VIDEURS` type).

  - **`void setMultiplicity(int multiplicity)`**
    - Sets the weight (multiplicity) of the arc.
    - Throws a `ResetArcMultiplicityException` if attempting to set the multiplicity of a reset arc (`VIDEURS` type).

## 3. How to use it:

- #### Click on the main function to run. 

If you have problems running main, it may be a java version problem, please adjust your java version according to the logs.

![截屏2024-12-04 14.19.29](https://p.ipic.vip/aohvsc.png)

- #### Click on the "change model" and select binome4

![截屏2024-12-04 14.21.38](https://p.ipic.vip/52m0g5.png)

- #### OK, you can test it by the binome04 model.

<img src="https://p.ipic.vip/ze50ff.png" alt="截屏2024-12-04 14.38.54" style="zoom:67%;" />

## 4. Runing Environment 

- Eclipse 2024

- IntelliJ IDEA 2024 (Need to config the Eclipse project to IDEA project)





































