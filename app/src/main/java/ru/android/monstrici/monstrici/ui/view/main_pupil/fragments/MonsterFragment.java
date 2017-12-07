package ru.android.monstrici.monstrici.ui.view.main_pupil.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.CoreComponent;
import ru.android.monstrici.monstrici.presentation.presenter.monster.MonsterPresenter;
import ru.android.monstrici.monstrici.presentation.view.monster.MonsterView;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.utils.Message;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 17.10.17.
 */

public class MonsterFragment extends BaseFragment implements MonsterView {

    ImageView mIvBodyPart;
    ImageView mIvEyePart;
    ImageView mIvMouthPart;
    private IActivityCallback mCallback;
    @InjectPresenter
    MonsterPresenter mPresenter;
//    @BindView(R.id.tv_name)
//    TextView mTvMonsterName;
//    @BindView(R.id.tv_donut_num)
//    TextView mTvDonutNum;

    @ProvidePresenter
    public MonsterPresenter providePresenter() {
        MonsterPresenter presenter = new MonsterPresenter();
        getComponent(AppComponent.class).inject(presenter);
        return presenter;
    }

    public interface IActivityCallback {
        void monsterNameUpdate(String name);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IActivityCallback)
            mCallback = (IActivityCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    public MonsterFragment() {
    }

    public static MonsterFragment newInstance(int monsterImageId) {
        Bundle args = new Bundle();
        args.putInt(Resources.MONSTER_IMAGE, monsterImageId);
        MonsterFragment newFragment = new MonsterFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(CoreComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_monster, container, false);
        ButterKnife.bind(this, mView);
        ConstraintLayout monsterLayout = mView.findViewById(R.id.la_monster);
        mIvBodyPart = monsterLayout.findViewById(R.id.iv_body_part);
        mIvEyePart = monsterLayout.findViewById(R.id.iv_eye_part);
        mIvMouthPart = monsterLayout.findViewById(R.id.iv_mouth_part);
        mPresenter.getMonster();
        return mView;
    }

    @Override
    public void init() {
    }

    @Override
    public void setTag() {
        TAG = "MonsterFragment";
    }

    @Override
    public void updateMonster(Drawable res, int bodyPart) {
        switch (bodyPart) {
            case 1: {
                mIvBodyPart.setImageDrawable(res);
                break;
            }
            case 2: {
                mIvMouthPart.setImageDrawable(res);
                break;
            }
            case 3: {
                mIvEyePart.setImageDrawable(res);
                break;
            }
        }
    }

    @Override
    public void onMonsterGet(Monster monster) {
        mCallback.monsterNameUpdate(monster.getName());
    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }
}
