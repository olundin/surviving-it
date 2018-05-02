package survivingit.messaging;

/**
 * An observable object, observed by an observer.
 * @param <T> The object that is observed
 *
 * @see Observer
 * @see java.util.Observable
 */
public interface Observable<T> {

    /**
     * Attaches an observer to the observable
     * @param observer The observer to attach
     */
    public void attach(Observer<T> observer);

    /**
     * Function called by the observable when notifying its observers
     * @param data The data to notify with (itself)
     */
    public void notifyObservers(T data);

}
