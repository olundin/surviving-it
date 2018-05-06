package survivingit.messaging;

/**
 * An observable object, observed by an observer.
 * Follows the observer pattern.
 * @param <T> The object that is observed
 *
 * @see Observer
 */
public interface Observable<T> {

    /**
     * Attaches an observer to the observable
     * @param observer The observer to attach
     */
    // IGNORED INSPECTION WARNING
    // Unused (as the pointer to the object using it is never Observable<T>, but still kept since removing it would
    // defeat the purpose of the interface.
    public void attach(Observer<T> observer);

    /**
     * Function called by the observable when notifying its observers
     */
    // IGNORED INSPECTION WARNING
    // Unused (as the pointer to the object using it is never Observable<T>, but still kept since removing it would
    // defeat the purpose of the interface.
    public void notifyObservers();

}
