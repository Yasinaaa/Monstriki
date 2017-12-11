package ru.android.monstrici.monstrici.presentation.model;

/**
 * Created by yasina on 29.10.17.
 */

public class DayDesition {

    private String mDate;
    private int mDayOfWeek, mGoals;
    private String mTag;

    public DayDesition(int dayOfWeek, String date, int goals, String tag) {
        this.mDayOfWeek = dayOfWeek;
        this.mDate = date;
        this.mGoals = goals;
        this.mTag = tag;
    }

    public DayDesition(int mDayOfWeek, String mDate) {
        this.mDayOfWeek = mDayOfWeek;
        this.mDate = mDate;
        this.mGoals = 0;
        this.mTag = "";
    }

    public DayDesition(int mDayOfWeek) {
        this.mDayOfWeek = mDayOfWeek;
        this.mDate = "";
        this.mGoals = 0;
        this.mTag = "";
    }

    public int getDayOfWeek() {
        return mDayOfWeek;
    }

    public void setDayOfWeek(int mDayOfWeek) {
        this.mDayOfWeek = mDayOfWeek;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public int getGoals() {
        return mGoals;
    }

    public void setGoals(int mGoals) {
        this.mGoals = mGoals;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String mTag) {
        this.mTag = mTag;
    }
}
