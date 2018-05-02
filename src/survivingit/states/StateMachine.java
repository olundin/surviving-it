package survivingit.states;

/**
 * State Machine class. Handles state transitions.
 * @param <O> The object that the state machine is controlling states on.
 */
public class StateMachine<O> {

    private O owner;

    private State<O> previousState;
    private State<O> currentState;

    /**
     * Creates a new state machine.
     * @param owner The owner of the state machine.
     * @param initialState The first state to enter.
     */
    public StateMachine(O owner, State<O> initialState) {
        this.owner = owner;
        this.currentState = null;
        this.previousState = null;
        setState(initialState);
    }

    /**
     * Updates the state machine.
     * @param dt Time between last update and this one.
     */
    public void update(double dt) {
        if(currentState != null) {
            setState(currentState.update(dt, owner));
        }
    }

    /**
     * Sets the state of the state machine.
     * @param newState The new state to go to.
     */
    public void setState(State<O> newState) {
        if(newState == currentState) return;

        previousState = currentState;
        if(currentState != null) {
            currentState.exit(owner);
        }

        currentState = newState;
        if(currentState != null) {
            currentState.enter(owner);
        }
    }
}
