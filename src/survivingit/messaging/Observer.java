package survivingit.messaging;

/**
 *
 * @param <T> Observed type
 */
public interface Observer<T> {

    public void onNotify(Observable<T> object, T data);

}
