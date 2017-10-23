package ru.android.monstrici.monstrici.presentation.model;

/**
 * Created by yasina on 23.10.17.
 */

public class Prize {

    private int mPrizePicture;
    private String mPrizeTitle, mPrizeDate;

    public Prize(int prizePicture, String prizeTitle, String prizeDate) {
        this.mPrizePicture = prizePicture;
        this.mPrizeTitle = prizeTitle;
        this.mPrizeDate = prizeDate;
    }

    public int getPrizePicture() {
        return mPrizePicture;
    }

    public void setPrizePicture(int mPrizePicture) {
        this.mPrizePicture = mPrizePicture;
    }

    public String getPrizeTitle() {
        return mPrizeTitle;
    }

    public void setPrizeTitle(String prizeTitle) {
        this.mPrizeTitle = prizeTitle;
    }

    public String getPrizeDate() {
        return mPrizeDate;
    }

    public void setPrizeDate(String prizeDate) {
        this.mPrizeDate = prizeDate;
    }
}
