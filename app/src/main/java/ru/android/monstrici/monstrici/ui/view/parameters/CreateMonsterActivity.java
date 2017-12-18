package ru.android.monstrici.monstrici.ui.view.parameters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.model.MonsterContainer;
import ru.android.monstrici.monstrici.presentation.presenter.main.TeacherMenuPresenter;
import ru.android.monstrici.monstrici.presentation.presenter.parameters.CreateMonsterPresenter;
import ru.android.monstrici.monstrici.presentation.view.parameters.ICreateMonsterView;
import ru.android.monstrici.monstrici.ui.view.adapter.CreateMonsterAdapter;
import ru.android.monstrici.monstrici.ui.view.adapter.IRecyclerViewItemListener;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
import ru.android.monstrici.monstrici.ui.view.main_pupil.MainPupilActivity;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 07/12/2017
 * @Author Andrei Gusev
 */
public class CreateMonsterActivity extends BaseActivity implements ICreateMonsterView, IRecyclerViewItemListener {

    @BindView(R.id.rv_monster_items)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_monster_create)
    TextView mTvMonsterCreate;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.btn_back)
    Button mBtnBack;
    ImageView mIvBodyPart;
    ImageView mIvEyePart;
    ImageView mIvMouthPart;
    private int mIndex = 1;
    private String mMonsterName;
    @InjectPresenter
    CreateMonsterPresenter mPresenter;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, CreateMonsterActivity.class);
        return intent;
    }

    @ProvidePresenter
    public CreateMonsterPresenter providePresenter() {
        CreateMonsterPresenter presenter = new CreateMonsterPresenter();
        getApplicationComponent().inject(presenter);
        return presenter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_create);
        ButterKnife.bind(this);
        ConstraintLayout monsterLayout = findViewById(R.id.la_monster);
        mIvBodyPart = monsterLayout.findViewById(R.id.iv_body_part);
        mIvEyePart = monsterLayout.findViewById(R.id.iv_eye_part);
        mIvMouthPart = monsterLayout.findViewById(R.id.iv_mouth_part);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        nextItem();

        mBtnNext.setOnClickListener(v -> {
            mIndex++;
            if (mIndex == 3) {
                mBtnNext.setText(R.string.create_done);

            }
            if (mIndex > 3) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                final EditText input = new EditText(CreateMonsterActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialogBuilder.setView(input);
                alertDialogBuilder.setTitle(R.string.create_monster_name);
                alertDialogBuilder.setPositiveButton(R.string.save,
                        (dialog, which) -> {
                            mMonsterName = input.getText().toString();
                            Intent intent = MainPupilActivity.newIntent(getApplicationContext(), mPresenter.getUserId());
                            startActivity(intent);
                            finish();
                        });
                alertDialogBuilder.show();

            } else {
                nextItem();
            }
        });
        mBtnBack.setOnClickListener(v -> {
            if (mIndex == 1) {
            } else {
                mIndex--;
                nextItem();
            }
        });

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
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.saveMonster(mMonsterName);
    }

    private void nextItem() {
        switch (mIndex) {
            case 1: {
                mTvMonsterCreate.setText(R.string.question_body);
                break;
            }
            case 2: {
                mTvMonsterCreate.setText(R.string.question_mouth);
                break;
            }
            case 3: {
                mTvMonsterCreate.setText(R.string.question_eyes);
                break;
            }
        }
        MonsterContainer monsterContainer = mPresenter.getMonsterContainer();
        CreateMonsterAdapter createMonsterAdapter =
                new CreateMonsterAdapter(monsterContainer.getNextArray(mIndex), this, mIndex);
        mRecyclerView.setAdapter(createMonsterAdapter);
        createMonsterAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder sender, int position, Drawable drawable, int type) {
        mPresenter.updateMonsterContainer(drawable, type, position);
    }
}
