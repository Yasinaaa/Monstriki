package ru.android.monstrici.monstrici.presentation.model;

/**
 * Created by yasina on 29.10.17.
 */

public class DayDesition {

    private String mDate;
    private int mDayOfWeek,mForAnswer, mForCleaning;

    public DayDesition(int mDayOfWeek, String mDate, int mForAnswer, int mForCleaning) {
        this.mDayOfWeek = mDayOfWeek;
        this.mDate = mDate;
        this.mForAnswer = mForAnswer;
        this.mForCleaning = mForCleaning;
    }

    public DayDesition(int mDayOfWeek, String mDate) {
        this.mDayOfWeek = mDayOfWeek;
        this.mDate = mDate;
        this.mForAnswer = 0;
        this.mForCleaning = 0;
    }

    public DayDesition(int mDayOfWeek) {
        this.mDayOfWeek = mDayOfWeek;
        this.mDate = "";
        this.mForAnswer = 0;
        this.mForCleaning = 0;
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

    public int getForAnswer() {
        return mForAnswer;
    }

    public void setForAnswer(int mForAnswer) {
        this.mForAnswer = mForAnswer;
    }

    public int getForCleaning() {
        return mForCleaning;
    }

    public void setForCleaning(int mForCleaning) {
        this.mForCleaning = mForCleaning;
    }
}
