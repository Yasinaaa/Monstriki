package ru.android.monstrici.monstrici.data.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by elisium
 *
 * @Date 02/12/2017
 * @Author Andrei Gusev
 */

public class StarStorage {
    private String mId;
    private Map<String, Star> mStars;

    public StarStorage() {
        mStars = new HashMap<>();
    }

    public Star getStar(String id) {
        return mStars.get(id);
    }

    public Map<String, Star> getStars() {
        return mStars;
    }

    public void setStars(Map<String, Star> stars) {
        mStars = stars;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public void updateStar(Star star) {
        mStars.put(star.getId(), star);
    }

    public int getStarsCount(){
        int count = 0;
        for (Map.Entry<String, Star> entry : mStars.entrySet())
        {
            String goals = entry.getValue().getGoals();
            if (!goals.equals(""))
                count += Integer.parseInt(goals);
        }
        return count;
    }
}
