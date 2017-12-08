package ru.android.monstrici.monstrici.ui.view.parameters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;

/**
 * Created by elisium
 *
 * @Date 07/12/2017
 * @Author Andrei Gusev
 */

public class CreateMonsterActivity extends BaseActivity {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, CreateMonsterActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_create);
    }
}
