package ru.android.monstrici.monstrici.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by yasina on 16.10.17.
 */

public class ActivityUtils {

    public static void startActivity(Activity from, Class to){
        Intent intent = new Intent(from, to);
        from.startActivity(intent);
    }

    public static void startActivityAndFinishCurrent(Activity from, Class to){
        Intent intent = new Intent(from, to);
        from.startActivity(intent);
        from.finish();
    }

}
