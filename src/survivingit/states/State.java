package survivingit.states;

import survivingit.gameobjects.GameObject;

public interface State<O> {

    public void enter(O object);
    public State<O> update(double dt, O object); // Returns new state on transition
    public void exit(O object);

}
