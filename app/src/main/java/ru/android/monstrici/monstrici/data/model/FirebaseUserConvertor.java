package ru.android.monstrici.monstrici.data.model;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by elisiumGusev
 *
 * @Date 25/10/2017
 * @Author Andrei Gusev
 */

public class FirebaseUserConvertor {

    public static User convert(FirebaseUser user) {
        return new User();
    }
}
