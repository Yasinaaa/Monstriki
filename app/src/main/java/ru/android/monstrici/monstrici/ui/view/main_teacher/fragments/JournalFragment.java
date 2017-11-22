package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.utils.DialogUtils;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 29.10.17.
 */

public class JournalFragment extends BaseFragment {

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

    private String mDate, mForm;
    private TableItems mTableItems;

    public JournalFragment() {
    }

    public static JournalFragment newInstance(){
        Bundle args = new Bundle();
        JournalFragment newFragment = new JournalFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    public static JournalFragment newInstance(String form, String date){
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
        if(getArguments() != null){
            mDate = getArguments().getString(JOURNAL_DATE);
            mForm = getArguments().getString(JOURNAL_FORM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_journal);
        return mView;
    }

    @Override
    public void init() {

        if (mDate != null){
            mTvData.setText(mDate);
        }

        if (mForm != null){
            mTvFormText.setText(mForm);
        }

        generateTableLayout();
    }

    //TODO: set real data from db
    public void generateTableLayout(){
        for (int i=0; i<10; i++) {
            LayoutInflater inflater = (LayoutInflater)
                    getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_table_form, null);

            mTableItems = new TableItems(view, getActivity());
            if (i == 0) {
                mTableItems.setHeader();
            } else {
                mTableItems.setItem(Resources.mTempPupils[i], String.valueOf(0),
                        String.valueOf(1));
            }

            mTableLayout.addView(view, i);
        }
    }

    public class TableItems{

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

        public void setHeader(){
            setTableItems(getResources().getString(R.string.pupil),
                    getResources().getString(R.string.points),
                    getResources().getString(R.string.comment));
            mTvAddDonuts.setVisibility(View.GONE);
            mTvRemoveDonuts.setVisibility(View.GONE);
        }

        public void setItem(String pupilName,
                            String pupilPoints, String pointsTag){

            setTableItems(pupilName, pupilPoints, pointsTag);
            mTvTag.setBackgroundResource(R.drawable.cell_shape);
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

            LayoutInflater inflater = mActivity.getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_tags, null);
            TagContainerLayout mTagContainerLayout =
                    (TagContainerLayout) view.findViewById(R.id.tags);
            mTagContainerLayout.setTags(new ArrayList<String>(
                    Arrays.asList(
                            mActivity.getResources().getStringArray(R.array.tags))));
            mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
                @Override
                public void onTagClick(int position, String text) {
                    // ...
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

                    AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity)
                            .setView(view)
                            .setTitle(getResources().getString(R.string.choose_tag))
                            .setPositiveButton(mActivity.getString(R.string.choose),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setNegativeButton(mActivity.getString(R.string.cancel),
                                    new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
            dialog.show();
        }

        private void setDonutsCount(boolean isAddition){
            int currentValue = Integer.parseInt(mTvDonutsCount.getText().toString());
            if (isAddition) {
                currentValue++;
            }else {
                currentValue--;
            }
            mTvDonutsCount.setText(String.valueOf(currentValue));
        }

        private void setTableItems(String one, String two, String three){
            mTableItems.mTvPupilName.setText(one);
            mTableItems.mTvDonutsCount.setText(two);
            mTableItems.mTvTag.setText(three);
        }
    }

    @Override
    public void setTag() {
        TAG = "JournalFragment";
    }

    @OnClick(R.id.tv_form_bracket)
    public void onFormBracketClick(){
        openFragment(FormParametersFragment.newInstance(false));
    }

    @OnClick(R.id.tv_form)
    public void onFormClick(){
        openFragment(FormParametersFragment.newInstance(false));
    }

}
