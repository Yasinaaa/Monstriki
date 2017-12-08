package ru.android.monstrici.monstrici.data.model;

import java.util.List;

/**
 * Created by yasina on 07.12.17.
 */

public class UserList {

    public static int[] getGoalsList(List<User> mUserList){
        int[] mGoalsArray = new int[mUserList.size()];
        for (int i=0; i<mUserList.size(); i++){
            mGoalsArray[i] = mUserList.get(i).getStarStorage().getStarsCount();
        }
        return mGoalsArray;
    }

}
