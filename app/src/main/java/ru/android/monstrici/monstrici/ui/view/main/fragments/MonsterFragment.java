package ru.android.monstrici.monstrici.ui.view.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.ui.view.parameters.ParametersActivity;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 17.10.17.
 */

public class MonsterFragment extends BaseFragment {

    @BindView(R.id.iv_monster)
    ImageView mIvMonster;

    private int mMonsterImageId;

    public MonsterFragment() {
    }

    public static MonsterFragment newInstance(int monsterImageId){
        Bundle args = new Bundle();
        args.putInt(Resources.MONSTER_IMAGE, monsterImageId);
        MonsterFragment newFragment = new MonsterFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mMonsterImageId = getArguments().getInt(Resources.MONSTER_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_monster);
        return mView;
    }

    @Override
    public void init() {
        mIvMonster.setBackgroundResource(mMonsterImageId);
    }

    @Override
    public void setTag() {
        TAG = "MonsterFragment";
    }

}
