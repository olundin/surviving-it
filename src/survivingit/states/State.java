package survivingit.states;

import survivingit.gameobjects.GameObject;

/**
 * Interface class for a State used in a StateMachine.
 * @param <O> Class for which State is bound to.
 */
public interface State<O> {

    /**
     * Function called when the State is entered by the entered object.
     * @param object object that the State is bound to.
     */
    public void enter(O object);

    /**
     * Update function that is called each gametick and updates the State
     * @param dt
     * @param object
     * @return
     */
    public State<O> update(double dt, O object); // Returns new state on transition
    public void exit(O object);

}
