package survivingit.messaging;

/**
 * A messagable object can receive and handle messages
 *
 * @see Message
 */
public interface Messagable {

    /**
     * Receives and handles a message
     * @param msg The message to receive
     */
    public void receiveMessage(Message msg);

}
