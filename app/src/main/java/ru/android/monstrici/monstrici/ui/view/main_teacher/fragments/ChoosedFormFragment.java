package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;

/**
 * Created by yasina on 29.10.17.
 */

public class ChoosedFormFragment extends BaseFragment {

    //@BindView(R.id.iv_monster)
    //ImageView mIvMonster;

    public ChoosedFormFragment() {
    }

    public static ChoosedFormFragment newInstance(){
        Bundle args = new Bundle();
        ChoosedFormFragment newFragment = new ChoosedFormFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_choosed_form);
        return mView;
    }

    @Override
    public void init() {
    }

    @Override
    public void setTag() {
        TAG = "ChoosedFormFragment";
    }

}
