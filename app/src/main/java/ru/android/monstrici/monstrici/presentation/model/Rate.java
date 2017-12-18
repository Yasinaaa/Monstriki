package ru.android.monstrici.monstrici.presentation.model;

/**
 * Created by yasina on 19.11.17.
 */

public class Rate {

    private String mMonsterName;
    private int mMonsterImage, mMonsterDonutNum;

    public Rate(String mMonsterName, int mMonsterImage, int mMonsterDonutNum) {
        this.mMonsterName = mMonsterName;
        this.mMonsterImage = mMonsterImage;
        this.mMonsterDonutNum = mMonsterDonutNum;
    }

    public String getMonsterName() {
        return mMonsterName;
    }

    public void setMonsterName(String mMonsterName) {
        this.mMonsterName = mMonsterName;
    }

    public int getMonsterImage() {
        return mMonsterImage;
    }

    public void setMonsterImage(int mMonsterImage) {
        this.mMonsterImage = mMonsterImage;
    }

    public int getMonsterDonutNum() {
        return mMonsterDonutNum;
    }

    public void setMonsterDonutNum(int mMonsterDonutNum) {
        this.mMonsterDonutNum = mMonsterDonutNum;
    }
}
