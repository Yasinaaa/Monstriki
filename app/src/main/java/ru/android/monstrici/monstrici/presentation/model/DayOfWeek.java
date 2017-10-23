package ru.android.monstrici.monstrici.presentation.model;

/**
 * Created by yasina on 23.10.17.
 */

public class DayOfWeek {

    private String mDayTitle;
    private String mDate;
    private int mDonutsCount;

    public DayOfWeek(String mDayTitle) {
        this.mDayTitle = mDayTitle;
    }

    public DayOfWeek(String mDayTitle, String mDate, int mDonutsCount) {
        this.mDayTitle = mDayTitle;
        this.mDate = mDate;
        this.mDonutsCount = mDonutsCount;
    }

    public String getDayTitle() {
        return mDayTitle;
    }

    public void setDayTitle(String mDayTitle) {
        this.mDayTitle = mDayTitle;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public int getDonutsCount() {
        return mDonutsCount;
    }

    public void setDonutsCount(int mDonutsCount) {
        this.mDonutsCount = mDonutsCount;
    }
}
