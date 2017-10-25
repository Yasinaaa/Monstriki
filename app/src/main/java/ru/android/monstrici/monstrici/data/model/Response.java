package ru.android.monstrici.monstrici.data.model;

import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisiumGusev
 *
 * @Date 23/10/2017
 * @Author Andrei Gusev
 */

public class Response<T> {
    private Message mMessage;
    private Status mStatus;
    private T body;

    public Message getMessage() {
        return mMessage;
    }

    public Response<T> setMessage(Message message) {
        mMessage = message;
        return this;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

    public T getBody() {
        return body;
    }

    public Response<T> setBody(T body) {
        this.body = body;
        return this;
    }
}
