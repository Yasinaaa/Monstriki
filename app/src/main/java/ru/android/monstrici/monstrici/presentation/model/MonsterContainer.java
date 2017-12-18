package ru.android.monstrici.monstrici.presentation.model;

import android.content.res.TypedArray;
import android.support.annotation.IntRange;

import java.io.Serializable;
import java.util.List;

import ru.android.monstrici.monstrici.data.model.Monster;

/**
 * Created by elisium
 *
 * @author Andrei Gusev 22/11/2017
 */

public class MonsterContainer implements Serializable {
    private Monster mMonster;
    private final TypedArray mBodyList;
    private final TypedArray mEyesList;
    private final TypedArray mMouthList;
    private int mBodyIndex, mEyeIndex, mMouthIndex;

    public MonsterContainer(TypedArray bodyList, TypedArray eyesList, TypedArray mouthList) {
        mBodyList = bodyList;
        mEyesList = eyesList;
        mMouthList = mouthList;
        mMonster = new Monster();
    }

    public Monster getMonster() {
        return mMonster;
    }

    public void setMonster(Monster monster) {
        mMonster = monster;
    }

    public TypedArray getBodyList() {
        return mBodyList;
    }

    public TypedArray getEyesList() {
        return mEyesList;
    }

    public TypedArray getMouthList() {
        return mMouthList;
    }

    public int getBodyIndex() {
        return mBodyIndex;
    }

    public void setBodyIndex(int bodyIndex) {
        mBodyIndex = bodyIndex;
    }

    public int getEyeIndex() {
        return mEyeIndex;
    }

    public void setEyeIndex(int eyeIndex) {
        mEyeIndex = eyeIndex;
    }

    public int getMouthIndex() {
        return mMouthIndex;
    }

    public void setMouthIndex(int mouthIndex) {
        mMouthIndex = mouthIndex;
    }

    /**
     * @param mIndex 1 -body ,2 -mouth, 3- eye
     */
    public TypedArray getNextArray(@IntRange(from = 1, to = 3) int mIndex) {
        switch (mIndex) {
            case 1:
                return mBodyList;
            case 2:
                return mMouthList;
            case 3:
                return mEyesList;
        }
        return null;
    }

    public void createMonster(String name, String id) {
        mMonster = new Monster();
        mMonster.setMouth(String.valueOf(mMouthIndex));
        mMonster.setEyes(String.valueOf(mEyeIndex));
        mMonster.setBody(String.valueOf(mBodyIndex));
        mMonster.setName(name);
        mMonster.setId(id);
    }
}
