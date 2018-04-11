package survivingit.states;

public class StateMachine<O> {

    private O owner;

    private State<O> previousState;
    private State<O> currentState;

    public StateMachine(O owner, State<O> initialState) {
        this.owner = owner;
        setState(initialState);
    }

    public void update(double dt) {
        if(currentState != null) {
            setState(currentState.update(dt, owner));
        }
    }

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
