package ru.android.monstrici.monstrici.ui.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;

/**
 * Created by yasina on 16.10.17.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        start();
    }

    @Override
    public void init() {

    }

    @Override
    public void setTag() {
        TAG = MainActivity.class.getCanonicalName();
    }
}

