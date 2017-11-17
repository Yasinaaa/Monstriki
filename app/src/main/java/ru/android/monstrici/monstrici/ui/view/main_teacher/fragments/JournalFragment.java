package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.utils.Resources;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

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

        for (int i=0; i<10; i++) {
            LayoutInflater inflater = (LayoutInflater)
                    getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_table_form, null);
            final int ii = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFragment(PupilFragment.newInstance(Resources.mTempPupils[ii]));
                }
            });

            final TextView day = (TextView) view.findViewById(R.id.tv_day_of_week);
            final TextView on = (TextView) view.findViewById(R.id.tv_date);
            final TextView off = (TextView) view.findViewById(R.id.tv_off_time);

            if (i == 0) {
                day.setText(getResources().getString(R.string.pupil));
                on.setText(getResources().getString(R.string.for_answer));
                off.setText(getResources().getString(R.string.for_cleaning));
            } else {
                day.setText(Resources.mTempPupils[i]);
                on.setText(String.valueOf(0));
                off.setText(String.valueOf(1));
            }

            mTableLayout.addView(view, i);
        }
    }

    @Override
    public void setTag() {
        TAG = "JournalFragment";
    }

    /*@OnClick(R.id.tv_data_bracket)
    public void onDataClick(){
        openFragment(DataFragment.newInstance(true));
    }*/

    @OnClick(R.id.tv_form_bracket)
    public void onFormBracketClick(){
        openFragment(FormParametersFragment.newInstance(false));
    }

    @OnClick(R.id.tv_form)
    public void onFormClick(){
        openFragment(FormParametersFragment.newInstance(false));
    }

}
