package ru.android.monstrici.monstrici.ui.view.main_pupil.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.CoreComponent;
import ru.android.monstrici.monstrici.presentation.model.MonsterContainer;
import ru.android.monstrici.monstrici.presentation.presenter.settings.SettingsPresenter;
import ru.android.monstrici.monstrici.presentation.view.settings.SettingsView;
import ru.android.monstrici.monstrici.ui.view.authorisation.AuthorisationActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentUsualToolbar;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by yasina on 17.10.17.
 */

public class SettingsFragment extends BaseFragmentUsualToolbar implements View.OnClickListener, SettingsView {

    public static int TOOLBAR_IMAGE = R.drawable.settings_icon_transparent;
    public static int TOOLBAR_TITLE = R.string.settings;

    @BindView(R.id.iv_eyes)
    ImageView mIvEyes;
    @BindView(R.id.iv_mouth)
    ImageView mIvMouth;
    @BindView(R.id.iv_hands)
    ImageView mIvHands;
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
    //    @BindView(R.id.btn_save)
//    Button mBtnSave;
    @BindView(R.id.tv_settings_name)
    TextView mTvName;
    @BindView(R.id.tv_settings_class)
    TextView mTvClass;


    ImageView mIvBodyPart;
    ImageView mIvEyePart;
    ImageView mIvMouthPart;

    @Inject
    MonsterContainer mMonsterContainer;
    @InjectPresenter
    SettingsPresenter mPresenter;

    @ProvidePresenter
    public SettingsPresenter providePresenter() {
        SettingsPresenter presenter = new SettingsPresenter();
        getComponent(AppComponent.class).inject(presenter);
        return presenter;
    }

    public static SettingsFragment newInstance(int monsterImageId) {
        return new SettingsFragment();
    }

    public SettingsFragment() {
        super(TOOLBAR_IMAGE, TOOLBAR_TITLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(CoreComponent.class).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, mView);
        ConstraintLayout monsterLayout = mView.findViewById(R.id.la_monster);
        mIvBodyPart = monsterLayout.findViewById(R.id.iv_body_part);
        mIvEyePart = monsterLayout.findViewById(R.id.iv_eye_part);
        mIvMouthPart = monsterLayout.findViewById(R.id.iv_mouth_part);
        mIvMouth.setOnClickListener(this);
        mTvMouth.setOnClickListener(this);
        mIvEyes.setOnClickListener(this);
        mTvEyes.setOnClickListener(this);
        mIvHands.setOnClickListener(this);
        mTvHands.setOnClickListener(this);
        mTvLogout.setOnClickListener(this);
        mIvLogout.setOnClickListener(this);

        mIvMouthPart.getLayoutParams().height = 50;
        mIvMouthPart.getLayoutParams().width = 50;

        mIvEyePart.getLayoutParams().height = 100;
        mIvEyePart.getLayoutParams().width = 100;

        // mBtnSave.setOnClickListener(this);
        mEtMonsterName.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // do something, e.g. set your TextView here via .setText()
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        return mView;
    }

    @Override
    public void setTag() {

    }

    @Override
    public void init() {
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
            case R.id.iv_eyes:
            case R.id.tv_eyes: {
                mPresenter.nextEye();
                break;
            }
            case R.id.tv_mouth:
            case R.id.iv_mouth: {
                mPresenter.nextMouth();
                break;
            }
            case R.id.tv_hands:
            case R.id.iv_hands: {
                mPresenter.nextHands();
                break;
            }
//            case R.id.btn_save: {
//                mPresenter.saveMonster();
//                break;
//            }
        }
    }

    @Override
    public void updateMonster(@NotNull Drawable res, int bodyPart) {
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
    public void updateUser(User user) {
        mTvName.setText(user.getName());
        mTvClass.setText(user.getSchoolClass().getName());
    }

    @Override
    public void setMonsterName(String name) {
        mEtMonsterName.setText(name);
    }


    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (!mEtMonsterName.getText().toString().equals("")) {
            mPresenter.updateMonsterName(mEtMonsterName.getText().toString());
        }
        mPresenter.saveMonster();
    }
}
