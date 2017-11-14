package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.adapter.PupilAdapter;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 29.10.17.
 */

public class ChoosedFormFragment extends BaseFragment {

    public static final String FORM_PARAMETERS = "form_parameters";
    @BindView(R.id.tv_form)
    TextView mTvForm;
    @BindView(R.id.rv_form)
    RecyclerView mRvForm;

    private PupilAdapter mPupilAdapter;
    private String mChoosedPupil;
    private String mForm;

    public ChoosedFormFragment() {
    }

    public static ChoosedFormFragment newInstance(String form){
        Bundle args = new Bundle();
        args.putString(FORM_PARAMETERS, form);
        ChoosedFormFragment newFragment = new ChoosedFormFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mForm = getArguments().getString(FORM_PARAMETERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_choosed_form);
        return mView;
    }

    @Override
    public void init() {
        mTvForm.setText(getActivity().getResources().getString(R.string.form)
        + " " + mForm);
        mPupilAdapter = new PupilAdapter(Resources.mTempPupils, new PupilAdapter.OnItemClicked() {
            @Override
            public void onItemClick(String value) {
                PupilFragment pupilFragment = PupilFragment.newInstance(value + " " + mForm);
                openFragment(pupilFragment);
            }
        });

        mRvForm.setHasFixedSize(true);
        mRvForm.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvForm.setAdapter(mPupilAdapter);
    }

    @Override
    public void setTag() {
        TAG = "ChoosedFormFragment";
    }

}
