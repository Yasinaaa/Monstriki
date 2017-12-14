package ru.android.monstrici.monstrici.utils;

import java.text.SimpleDateFormat;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.model.DayDesition;

/**
 * Created by yasina on 19.10.17.
 */

public class Resources {

    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public final static String TAG_FRAGMENT = "tag_fragment";
    public final static String MONSTER_NAME = "monster_name";
    public final static String LAST_DONUT = "monster_image";

    public static int[] mMainPupilDrawables = new int[]{
            R.drawable.main_icon, R.drawable.cup_icon,
            R.drawable.candy_icon, R.drawable.settings_icon,
            R.drawable.star_icon
    };

    public static int[] mEyesDrawables = new int[]{
        R.drawable.e1, R.drawable.e2, R.drawable.e3, R.drawable.e4, R.drawable.e5, R.drawable.e6
    };

    public static int[] mMonstersWithEyesDrawables = new int[]{
            R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4, R.drawable.m5, R.drawable.m6
    };

    public static int mStudyDaysOfWeek = 6;

    public static DayDesition[] mDesitionsOfWeek = new DayDesition[]{
            new DayDesition(R.string.monday),
            new DayDesition(R.string.tuesday),
            new DayDesition(R.string.wednesday),
            new DayDesition(R.string.thursday),
            new DayDesition(R.string.friday),
            new DayDesition(R.string.saturday)
    };

    public static int[] mRewardDrawables = new int[]{
            R.drawable.cleaning, R.drawable.study,
            R.drawable.sport, R.drawable.helper,
            R.drawable.active, R.drawable.duty,
            R.drawable.preparedness,R.drawable.discipline,
            R.drawable.watch,R.drawable.punctuality
    };


}
