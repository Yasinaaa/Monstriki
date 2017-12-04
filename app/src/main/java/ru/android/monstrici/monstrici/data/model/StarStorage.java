package ru.android.monstrici.monstrici.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elisium
 *
 * @Date 02/12/2017
 * @Author Andrei Gusev
 */

public class StarStorage {
    private String mId;
    private List<Star> mStars;

    public StarStorage() {
        mStars = new ArrayList<>();
    }

    public List<Star> getStars() {
        return mStars;
    }

    public void setStars(List<Star> stars) {
        mStars = stars;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
