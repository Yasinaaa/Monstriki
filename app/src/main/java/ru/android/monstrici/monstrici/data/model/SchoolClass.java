package ru.android.monstrici.monstrici.data.model;

/**
 * Created by elisium
 *
 * @Date 28/11/2017
 * @Author Andrei Gusev
 */

public class SchoolClass {
    private String mId;
    private String mNumber;
    private String mLetter;

    public String getName() {
        return mNumber + " " + mLetter;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getLetter() {
        return mLetter;
    }

    public void setLetter(String letter) {
        mLetter = letter;
    }
}
