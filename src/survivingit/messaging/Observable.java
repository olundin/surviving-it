package survivingit.messaging;

public interface Observable<T> {

    public void attach(Observer<T> observer);

    public void notifyObservers(T data);

}
