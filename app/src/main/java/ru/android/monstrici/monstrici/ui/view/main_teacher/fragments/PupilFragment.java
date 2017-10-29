package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 29.10.17.
 */

public class PupilFragment extends BaseFragment {

    public PupilFragment() {
    }

    public static PupilFragment newInstance(){
        Bundle args = new Bundle();
        //args.putInt(Resources.MONSTER_IMAGE, monsterImageId);
        PupilFragment newFragment = new PupilFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            //mMonsterImageId = getArguments().getInt(Resources.MONSTER_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_pupil);
        return mView;
    }

    @Override
    public void init() {
    }

    @Override
    public void setTag() {
        TAG = "PupilFragment";
    }

}
