package ru.android.monstrici.monstrici.ui.view.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentUsualToolbar;
import ru.android.monstrici.monstrici.ui.view.parameters.ParametersActivity;

/**
 * Created by yasina on 17.10.17.
 */

public class SettingsFragment extends BaseFragmentUsualToolbar {

    public static int TOOLBAR_IMAGE = R.drawable.settings_icon_transparent;
    public static int TOOLBAR_TITLE = R.string.settings;

    public SettingsFragment() {
        super(TOOLBAR_IMAGE, TOOLBAR_TITLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            /*mListOn = getArguments().getIntArray(ON_LIST);
            mListOff = getArguments().getIntArray(OFF_LIST);
            mCurrentMonth = getArguments().getInt(MONTH);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_settings);
        return mView;
    }

    @Override
    public void setTag() {

    }

    @Override
    public void init() {

    }

}
