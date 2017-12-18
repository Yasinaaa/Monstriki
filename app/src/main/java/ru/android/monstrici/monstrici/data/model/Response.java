package ru.android.monstrici.monstrici.data.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String requestId;
    private T body;
    private List<T> bodyList;
    private StarStorage mStarStorage;
    private Map<String, T> bodyMap;

    public Response() {
        bodyList = new ArrayList<>();
        bodyMap = new HashMap<>();
    }

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

    public List<T> getBodyList() {
        return bodyList;
    }

    public Map<String, T> getBodyMap() {
        return bodyMap;
    }

    public Response<T> setBody(T body) {
        this.body = body;
        return this;
    }

    public Response<T> setBody(StarStorage body) {
        mStarStorage = body;
        return this;
    }

    public Response<T> setBody(List<T> bodyList) {
        this.bodyList = bodyList;
        return this;
    }

    public Response<T> setBody(Map<String, T> bodyMap) {
        this.bodyMap = bodyMap;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public Response<T> setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }
    public StarStorage getStarStorage(){
        return mStarStorage;
    }
}
