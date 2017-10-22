package ru.android.monstrici.monstrici.ui.view.parameters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 16.10.17.
 */

public class ParametersActivity extends BaseActivity implements EyesAdapter.OnItemClicked {

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
    private EyesAdapter mEyesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);
        start();
    }

    @Override
    public void init() {
        mParametersPresenter = new ParametersPresenter(this);
        mEyesAdapter = new EyesAdapter(Resources.mEyesDrawables, this);
        mRvEyes.setItemAnimator(new DefaultItemAnimator());
        mRvEyes.setHasFixedSize(true);
        mRvEyes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRvEyes.setAdapter(mEyesAdapter);
    }

    @OnClick(R.id.btn_next)
    public void setOnRegistrationBtnClickListener(){
        if (isQuestionMode){
            setCreateNameMode();
        }else {
            mParametersPresenter.goNext();
        }
    }

    //TODO: use this on onBackButton <- method
    private void setQuestionMode(){
        mTilMonsterName.setVisibility(View.GONE);
        setLayoutBelow(mIvMonster, R.id.rv_eyes);
        isQuestionMode = true;
    }

    private void setCreateNameMode(){
        mRvEyes.setVisibility(View.GONE);
        mTilMonsterName.setVisibility(View.VISIBLE);

        setLayoutBelow(mIvMonster, R.id.til_monster_name);
        isQuestionMode = false;
    }

    private void setLayoutBelow(View view, int parentId){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.addRule(RelativeLayout.BELOW, parentId);
        view.setLayoutParams(params);
    }

    @Override
    public void setTag() {
        TAG = ParametersActivity.class.getCanonicalName();
    }

    @Override
    public void onItemClick(int image) {
        mIvMonster.setBackgroundResource(Resources.mMonstersWithEyesDrawables[image]);
    }
}
