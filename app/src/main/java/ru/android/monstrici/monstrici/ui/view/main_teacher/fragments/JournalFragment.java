package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.CoreComponent;
import ru.android.monstrici.monstrici.presentation.presenter.teacher.JournalPresenter;
import ru.android.monstrici.monstrici.presentation.view.journal.IJournalView;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by yasina on 29.10.17.
 */

public class JournalFragment extends BaseFragment implements IJournalView {

    protected static final String JOURNAL_DATE = "journal_date";
    protected static final String JOURNAL_FORM = "journal_form";
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
    @BindView(R.id.tableLayout)
    TableLayout mTableLayout;
    @BindView(R.id.fab_save)
    FloatingActionButton mFabSave;

    private String mDate, mForm;
    private long mDateLong;
    private TableItems mTableItems;
    private int i;
    private View mTableView;
    private List<TableItems> mTableItemsList;
    private List<User> mUsersList;
    private List<Star> mStarsList;

    private SimpleDateFormat mDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @InjectPresenter
    JournalPresenter mPresenter;

    public JournalFragment() {
    }

    @ProvidePresenter
    public JournalPresenter providePresenter() {
        JournalPresenter presenter = new JournalPresenter();
        getComponent(AppComponent.class).inject(presenter);
        return presenter;
    }

    public static JournalFragment newInstance() {
        Bundle args = new Bundle();
        JournalFragment newFragment = new JournalFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    public static JournalFragment newInstance(String form, String date) {
        Bundle args = new Bundle();
        args.putString(JOURNAL_FORM, form);
        args.putString(JOURNAL_DATE, date);
        JournalFragment newFragment = new JournalFragment();
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
        mPresenter.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_journal);

        return mView;
    }

    @Override
    public void init() {

        mStarsList = new ArrayList<Star>();
        if (mDate == null) {
            mDateLong = Calendar.getInstance().getTime().getTime();
            mDate = mDateFormat.
                    format(mDateLong);
        }
        mTvData.setText(mDate);

        if (mForm != null) {
            mTvFormText.setText(mForm);
        }
        mPresenter.getUsers();
        mTableItemsList = new ArrayList<TableItems>();

    }

    @OnClick(R.id.fab_save)
    protected void onFabClick() {
        for (int j = 0; j < mTableItemsList.size(); j++) {
            TableItems tableItem = mTableItemsList.get(j);
            Star star = mStarsList.get(j);
            User user = mUsersList.get(j);
            star.setGoals(tableItem.mTvDonutsCount.getText().
                    toString());
            star.setTag(tableItem.mTvTag.getText().toString());
            star.setId(user.getStarId());
            mPresenter.saveStars(star, user.getId());
        }
    }

    public void generateTableLayout(List<User> users) {

        int position = 0;
        for (i = 0; i < users.size()+1; i++) {

            LayoutInflater inflater = (LayoutInflater)
                    getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mTableView = inflater.inflate(R.layout.item_table_form, null);

            mTableItems = new TableItems(mTableView, getActivity());
            if (i == 0) {
                mTableItems.setHeader();
                mTableLayout.addView(mTableView, position);
                position++;
            } else {
                User mUser = users.get(i - 1);

                if (Integer.parseInt(mUser.getStarId()) != -1) {
                    mTableItems.setItem(mUser.getName(), null, null);
                    mTableLayout.addView(mTableView, position);
                    position++;
                    mUsersList.add(mUser);
                    mTableItemsList.add(mTableItems);
                    mPresenter.getStars(mUser);
                }

            }

        }
    }

    @Override
    public void onTeacherPrepare(User user) {
        mTvFormText.setText(user.getSchoolClass().getName());
    }

    @Override
    public void onUsersPrepare(List<User> users) {
        mUsersList = new ArrayList<User>();
        generateTableLayout(users);
    }

    @Override
    public void onStarsGet(User mUser, List<Star> stars) {
        Star usersStar = null;

        for (Star star : stars) {
            if (Long.parseLong(star.getDate()) == mDateLong) {
                usersStar = star;

            }
        }
        if (usersStar != null) {
            mTableItems.setTableItems(mUser.getName(), usersStar.getGoals(),
                    usersStar.getTag());
            mStarsList.add(usersStar);
        } else {
            mTableItems.setItem(mUser.getName(), null, null);
            mTableItems.mTvDonutsCount.setText("");
            mTableItems.mTvTag.setText("");
            Star star = new Star();
            star.setDate(String.valueOf(mDateLong));
            mStarsList.add(star);
        }
        //

    }

    @Override
    public void showLoading(boolean flag) {
        Log.d(TAG, "loading");
    }

    @Override
    public void showError(Message message) {
        Log.d(TAG, message.getMessage());
    }

    public class TableItems {

        @BindView(R.id.tv_pupil_name)
        TextView mTvPupilName;
        @BindView(R.id.tv_add_donuts)
        TextView mTvAddDonuts;
        @BindView(R.id.tv_donuts_count)
        TextView mTvDonutsCount;
        @BindView(R.id.tv_remove_donuts)
        TextView mTvRemoveDonuts;
        @BindView(R.id.tv_tag)
        TextView mTvTag;

        private Activity mActivity;

        public TableItems(View view, Activity activity) {
            ButterKnife.bind(this, view);
            mActivity = activity;
        }

        public void setHeader() {
            setTableItems(getResources().getString(R.string.pupil),
                    getResources().getString(R.string.points),
                    getResources().getString(R.string.comment));
            mTvAddDonuts.setVisibility(View.GONE);
            mTvRemoveDonuts.setVisibility(View.GONE);
        }

        /*
        public void setItem(String pupilName,
                            int pupilPoints, String pointsTag) {

         */

        public void setItem(String pupilName,
                            String pupilPoints, String pointsTag) {

            setTableItems(pupilName, pupilPoints, pointsTag);
            mTvTag.setBackgroundResource(R.drawable.textview_border);
            setOnPupilNameClick(pupilName);
        }

        public void setOnPupilNameClick(String pupilName) {
            mTvPupilName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFragment(PupilFragment.newInstance(pupilName));
                }
            });
        }

        @OnClick(R.id.tv_add_donuts)
        public void setOnAddPointsClick() {
            setDonutsCount(true);
        }

        @OnClick(R.id.tv_remove_donuts)
        public void setOnRemovePointsClick() {
            setDonutsCount(false);
        }

        @OnClick(R.id.tv_tag)
        public void setOnTagClick() {

            final String[] tagText = {""};
            LayoutInflater inflater = mActivity.getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_tags, null);

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity)
                    .setView(view)
                    .setTitle(getResources().getString(R.string.choose_tag));
            dialogBuilder
                    .setPositiveButton(mActivity.getString(R.string.choose),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mTvTag.setText(tagText[0]);
                                }
                            })
                    .setNegativeButton(mActivity.getString(R.string.cancel),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();

            TagContainerLayout mTagContainerLayout =
                    (TagContainerLayout) view.findViewById(R.id.tags);
            mTagContainerLayout.setTags(new ArrayList<String>(
                    Arrays.asList(
                            mActivity.getResources().getStringArray(R.array.tags_array))));
            mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
                @Override
                public void onTagClick(int position, String text) {
                    tagText[0] = text;
                }

                @Override
                public void onTagLongClick(final int position, String text) {
                    // ...
                }

                @Override
                public void onTagCrossClick(int position) {
                    // ...
                }
            });
        }

        private void setDonutsCount(boolean isAddition) {
            String text = mTvDonutsCount.getText().toString();
            int currentValue = 0;

            if (!text.equals("")) {
                currentValue = Integer.parseInt(text);
            }
            if (isAddition) {
                currentValue++;
            } else {
                currentValue--;
            }
            mTvDonutsCount.setText(String.valueOf(currentValue));
        }

        private void setTableItems(String one, String two, String three) {
            mTvPupilName.setText(one);
            mTvDonutsCount.setText(two);
            mTvTag.setText(three);
        }

    }

    @Override
    public void setTag() {
        TAG = "JournalFragment";
    }

    @OnClick(R.id.tv_form_bracket)
    public void onFormBracketClick() {
        openFragment(FormParametersFragment.newInstance(false));
    }

    @OnClick(R.id.tv_form)
    public void onFormClick() {
        openFragment(FormParametersFragment.newInstance(false));
    }

}
