package ru.android.monstrici.monstrici.ui.view.parameters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;

/**
 * Created by yasina on 16.10.17.
 */

public class ParametersActivity extends BaseActivity {

    @BindView(R.id.tv_large_text)
    TextView mTvLargeText;
    @BindView(R.id.rv_eyes)
    RecyclerView mRvEyes;
    @BindView(R.id.til_monster_name)
    TextInputLayout mTilMonsterName;
    @BindView(R.id.et_monster_name)
    EditText mEtMonsterName;
    @BindView(R.id.iv_monster)
    ImageView mIvMonster;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.rl)
    RelativeLayout mRelativeLayout;

    private ParametersPresenter mParametersPresenter;
    private boolean isQuestionMode = true; // can be 2 modes: QUESTION and CREATE_NAME
    //private FrameLayout.LayoutParams mLayoutParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);
        start();
    }

    @Override
    public void init() {
        mParametersPresenter = new ParametersPresenter(this);
        //mLayoutParams = (FrameLayout.LayoutParams) mRelativeLayout.getLayoutParams();

        //set question mode
        //mTilMonsterName.setVisibility(View.GONE);

    }

    @OnClick(R.id.btn_next)
    public void setOnRegistrationBtnClickListener(){
        if (isQuestionMode){
            setCreateNameMode();

        }else {

        }
    }

    //TODO: use it this method onBackButton <-
    private void setQuestionMode(){
        mTilMonsterName.setVisibility(View.GONE);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params1.addRule(RelativeLayout.BELOW, R.id.rv_eyes);
        mIvMonster.setLayoutParams(params1);
        isQuestionMode = true;
    }

    private void setCreateNameMode(){
        mRvEyes.setVisibility(View.GONE);

        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.BELOW, R.id.til_monster_name);
        mIvMonster.setLayoutParams(params1);
        isQuestionMode = false;
    }

    @Override
    public void setTag() {
        TAG = ParametersActivity.class.getCanonicalName();
    }
}
