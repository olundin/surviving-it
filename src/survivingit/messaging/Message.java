package survivingit.messaging;

import survivingit.gameobjects.GameObject;

public class Message {

    private MessageType type;
    private int data;

    public Message(MessageType type, int data) {
        this.type = type;
        this.data = data;
    }

    public MessageType getType() {
        return type;
    }

    public int getData() {
        return data;
    }

}
