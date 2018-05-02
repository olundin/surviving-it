package survivingit.messaging;

/**
 * Observer class similar to the Java Observer.
 * Follows the observer pattern.
 *
 * @param <T> Observed type
 * @see java.util.Observer
 */
public interface Observer<T> {

    /**
     * The observable will call this method to notify the observer
     * @param object The observable object
     * @param data It's data (the object itself)
     */
    public void onNotify(Observable<T> object, T data);

}
