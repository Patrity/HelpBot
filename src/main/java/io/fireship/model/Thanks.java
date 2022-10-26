package io.fireship.model;

public class Thanks {
    private final String Thanker, Receiver, Message;
    private final long Timestamp;

    //constructor
    public Thanks(String thanker, String receiver, String message, long timestamp) {
        Thanker = thanker;
        Receiver = receiver;
        Message = message;
        Timestamp = timestamp;
    }

    //Misc getters
    public String getThanker() {
        return Thanker;
    }
    public String getReceiver() {
        return Receiver;
    }
    public String getMessage() {
        return Message;
    }
    public long getTimestamp() {
        return Timestamp;
    }

}
