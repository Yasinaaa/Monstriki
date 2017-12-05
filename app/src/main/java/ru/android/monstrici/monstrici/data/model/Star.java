package ru.android.monstrici.monstrici.data.model;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by elisium
 *
 * @Date 28/11/2017
 * @Author Andrei Gusev
 */

public class Star {
    private String id;
    private String tag;
    private String goals;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @SuppressLint("SimpleDateFormat")
    public String getFormatedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date newDate = new Date(Long.parseLong(date));
        return dateFormat.format(newDate);
    }
}
