package ru.android.monstrici.monstrici.data.model;

/**
 * Created by elisium
 *
 * @Date 28/11/2017
 * @Author Andrei Gusev
 */

public class Star {
    private String mId;
    private String mClean;
    private String mAnswer;
    private String mDate;



    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getClean() {
        return mClean;
    }

    public void setClean(String clean) {
        mClean = clean;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        mAnswer = answer;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
