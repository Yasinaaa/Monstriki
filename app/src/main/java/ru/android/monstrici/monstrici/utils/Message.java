package ru.android.monstrici.monstrici.utils;

/**
 * Created by elisiumGusev
 *
 * @Date 20/10/2017
 * @Author Andrei Gusev
 */

public class Message {
    private static final String INTERNET_CONNECTION_FAILED = "";
    private final String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
