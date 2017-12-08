package ru.android.monstrici.monstrici.presentation.model;

import android.content.res.TypedArray;

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
}
