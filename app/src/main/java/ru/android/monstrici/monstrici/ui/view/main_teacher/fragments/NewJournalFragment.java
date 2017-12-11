package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.CoreComponent;
import ru.android.monstrici.monstrici.presentation.presenter.journal.JournalPresenter;
import ru.android.monstrici.monstrici.presentation.view.journal.IJournalView;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IGoalItemListener;
import ru.android.monstrici.monstrici.presentation.adapter.journal.adapter.JournalAdapter;
import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.JournalViewHolder;
import ru.android.monstrici.monstrici.utils.Message;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class NewJournalFragment extends BaseFragment
        implements IJournalView {

    protected static final String JOURNAL_DATE = "journal_date";
    protected static final String JOURNAL_FORM = "journal_form";

    private String mDate, mForm;
    private long mDateLong;

    @BindView(R.id.tv_form)
    TextView mTvForm;
    @BindView(R.id.tv_form_text)
    TextView mTvFormText;
    @BindView(R.id.tv_journal)
    TextView mTvJournal;
    @BindView(R.id.tv_form_bracket)
    TextView mTvFormBracket;
    @BindView(R.id.tv_data_day)
    TextView mTvData;
    @BindView(R.id.rv_journal)
    RecyclerView mRvJournal;
    @BindView(R.id.fab_save)
    FloatingActionButton mFabSave;

    @InjectPresenter
    JournalPresenter mPresenter;

    private JournalAdapter mJournalAdapter;

    @ProvidePresenter
    public JournalPresenter providePresenter() {
        JournalPresenter presenter = new JournalPresenter();
        getComponent(AppComponent.class).inject(presenter);
        return presenter;
    }

    public static NewJournalFragment newInstance() {
        Bundle args = new Bundle();
        NewJournalFragment newFragment = new NewJournalFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    public static NewJournalFragment newInstance(String form, String date) {
        Bundle args = new Bundle();
        args.putString(JOURNAL_FORM, form);
        args.putString(JOURNAL_DATE, date);
        NewJournalFragment newFragment = new NewJournalFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDate = getArguments().getString(JOURNAL_DATE);
            mForm = getArguments().getString(JOURNAL_FORM);
        }
        getComponent(CoreComponent.class).inject(this);
        //mPresenter.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_journal);
        return mView;
    }

    @Override
    public void init() {
        if (mDate == null) {
            mDateLong = Calendar.getInstance().getTime().getTime();
            mDate = Resources.DATE_FORMAT.format(mDateLong);
        }
        mTvData.setText(mDate);

        if (mForm != null) {
            mTvFormText.setText(mForm);
        }
        mPresenter.getUsers();
    }

    @Override
    public void onTeacherPrepare(User user) {

    }

    @Override
    public void onUsersPrepare(List<User> userList) {
        if (!mDate.contains("-")) {
            mJournalAdapter = new JournalAdapter(userList);
        }else {
            String[] dates = mDate.split("-");
            Date startDate = makeDate(dates[0]);
            Date finishDate = makeDate(dates[1]);
            mJournalAdapter = new JournalAdapter(userList, startDate, finishDate);
        }

        mRvJournal.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvJournal.setAdapter(mJournalAdapter);
    }

    private Date makeDate(String d){
        Date startDate = null;
        try {
            startDate = Resources.DATE_FORMAT.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    @Override
    public void onStarsGet(User user, List<Star> stars) {

    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }

    @OnClick(R.id.fab_save)
    protected void onFabClick() {
        /*for (int j = 0; j < mTableItemsList.size(); j++) {
            JournalFragment.TableItems tableItem = mTableItemsList.get(j);
            Star star = mStarsList.get(j);
            User user = mUsersList.get(j);
            star.setGoals(tableItem.mTvDonutsCount.getText().
                    toString());
            star.setTag(tableItem.mTvTag.getText().toString());
            star.setId(user.getStarId());
            mPresenter.saveStars(star, user.getId());
        }*/
    }
}
