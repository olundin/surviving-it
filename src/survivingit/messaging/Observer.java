package survivingit.messaging;

/**
 * Observer class similar to the Java Observer.
 * Follows the observer pattern.
 *
 * @param <T> Observed type
 */
public interface Observer<T> {

    /**
     * The observable will call this method to notify the observer
     * @param object The observed object
     */
    public void onNotify(T object);

}
