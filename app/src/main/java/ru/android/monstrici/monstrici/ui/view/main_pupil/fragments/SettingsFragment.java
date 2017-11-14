package ru.android.monstrici.monstrici.ui.view.main_pupil.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.presenter.main_pupil.MonsterPictureFunction;
import ru.android.monstrici.monstrici.ui.view.authorisation.AuthorisationActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentUsualToolbar;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 17.10.17.
 */

public class SettingsFragment extends BaseFragmentUsualToolbar implements View.OnClickListener {

    public static int TOOLBAR_IMAGE = R.drawable.settings_icon_transparent;
    public static int TOOLBAR_TITLE = R.string.settings;

    @BindView(R.id.iv_eyes)
    ImageView mIvEyes;
    @BindView(R.id.iv_mouth)
    ImageView mIvMouth;
    @BindView(R.id.iv_hands)
    ImageView mIvHands;
    @BindView(R.id.iv_monster)
    ImageView mIvMonster;
    @BindView(R.id.iv_logout)
    ImageView mIvLogout;
    @BindView(R.id.tv_eyes)
    TextView mTvEyes;
    @BindView(R.id.tv_mouth)
    TextView mTvMouth;
    @BindView(R.id.tv_hands)
    TextView mTvHands;
    @BindView(R.id.tv_logout)
    TextView mTvLogout;
    @BindView(R.id.et_monster_name)
    EditText mEtMonsterName;

    private int mMonsterImageId = 0;

    public static SettingsFragment newInstance(int monsterImageId) {
        Bundle args = new Bundle();
        args.putInt(Resources.MONSTER_IMAGE, monsterImageId);
        SettingsFragment newFragment = new SettingsFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    public SettingsFragment() {
        super(TOOLBAR_IMAGE, TOOLBAR_TITLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMonsterImageId = getArguments().getInt(Resources.MONSTER_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_settings);
        mIvLogout.setOnClickListener(this);
        mTvLogout.setOnClickListener(this);
        return mView;
    }

    @Override
    public void setTag() {

    }

    @Override
    public void init() {
        mEtMonsterName.setText("Брозябр");
        MonsterPictureFunction.setMonsterPicture(this, mMonsterImageId, mIvMonster);
        Glide.with(this).load(R.drawable.eye).into(mIvEyes);
        Glide.with(this).load(R.drawable.mouth).into(mIvMouth);
        Glide.with(this).load(R.drawable.hand).into(mIvHands);
        Glide.with(this).load(R.drawable.m1).into(mIvMonster);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_logout:
            case R.id.tv_logout: {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), AuthorisationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
        }
    }
}
