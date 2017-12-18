package ru.android.monstrici.monstrici.ui.view.main_pupil.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.Random;

import butterknife.BindView;
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

    @BindView(R.id.cv_monster_talk)
    CardView mCvMonsterTalk;
    TextView mTvMonsterTalk;
    ImageView mIvBodyPart;
    ImageView mIvEyePart;
    ImageView mIvMouthPart;

    @InjectPresenter
    MonsterPresenter mPresenter;

    private IActivityCallback mCallback;
    private long mLastDonutDate;
    private String[] mWishCard1, mWishCard2, mWishCard3, mSatisfiedMonster;
    private Random mRandom;

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

    public static MonsterFragment newInstance(long date) {
        Bundle args = new Bundle();
        args.putLong(Resources.LAST_DONUT, date);
        MonsterFragment newFragment = new MonsterFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mLastDonutDate = getArguments().getLong(Resources.LAST_DONUT);
        }
        getComponent(CoreComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_monster);
        return mView;
    }

    @Override
    public void init() {
        ConstraintLayout monsterLayout = mView.findViewById(R.id.la_monster);
        mIvBodyPart = monsterLayout.findViewById(R.id.iv_body_part);
        mIvEyePart = monsterLayout.findViewById(R.id.iv_eye_part);
        mIvMouthPart = monsterLayout.findViewById(R.id.iv_mouth_part);
        mTvMonsterTalk = mCvMonsterTalk.findViewById(R.id.tv_monster_talk);
        mPresenter.getMonster();
        mPresenter.getLastDonutDay(mLastDonutDate);

        mSatisfiedMonster = getResources().getStringArray(R.array.happy_card);
        mWishCard1 = getResources().getStringArray(R.array.wish_card1);
        mWishCard2 = getResources().getStringArray(R.array.wish_card2);
        mWishCard3 = getResources().getStringArray(R.array.wish_card3);

        mRandom = new Random();

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

    private void setTextToTvMonsterTalk(int bound, String[] array){
        int num = mRandom.nextInt(bound);
        mTvMonsterTalk.setText(array[num]);
    }

    @Override
    public void setMonsterSatisfiedCard() {
        setTextToTvMonsterTalk(2, mSatisfiedMonster);
    }

    @Override
    public void setWishCard1() {
        setTextToTvMonsterTalk(3, mWishCard1);
    }

    @Override
    public void setWishCard2() {
        setTextToTvMonsterTalk(2, mWishCard2);
    }

    @Override
    public void setWishCard3() {
        setTextToTvMonsterTalk(2, mWishCard3);
    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }
}
