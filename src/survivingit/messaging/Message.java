package survivingit.messaging;

/**
 * Messages are used for all kinds of communication.
 * Each message has a type and a data.
 *
 * @see MessageType
 */
public class Message {

    private MessageType type;
    private int data;

    /**
     * Creates a new message
     * @param type The message type
     * @param data The message data
     */
    public Message(MessageType type, int data) {
        this.type = type;
        this.data = data;
    }

    /**
     * Returns the message type
     * @return The message type
     */
    public MessageType getType() {
        return type;
    }

    /**
     * Returns the message data
     * @return The message data
     */
    public int getData() {
        return data;
    }

}
