package ru.android.monstrici.monstrici.data.model;

import ru.android.monstrici.monstrici.utils.ErrorMessage;

/**
 * Created by elisiumGusev
 *
 * @Date 23/10/2017
 * @Author Andrei Gusev
 */

public class Response<T> {
    private ErrorMessage mMessage;
    private Status mStatus;
    private T body;

    public ErrorMessage getMessage() {
        return mMessage;
    }

    public void setMessage(ErrorMessage message) {
        mMessage = message;
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

    public void setBody(T body) {
        this.body = body;
    }
}
