package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.adapter.FormParametersAdapter;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;

/**
 * Created by yasina on 29.10.17.
 */

public class FormParametersFragment extends BaseFragment {

    @BindView(R.id.rv_forms)
    RecyclerView mRvForms;
    @BindView(R.id.rv_letters)
    RecyclerView mRvLetters;
    @BindView(R.id.btn_ready)
    Button mBtnReady;

    private FormParametersAdapter mLiteraAdapter, mNumAdapter;
    private String[] mLiterasArray, mFormsNumArray;
    private String mChoosedLitera;
    private int mChoosedFormNum;

    public FormParametersFragment() {
    }

    public static FormParametersFragment newInstance(){
        Bundle args = new Bundle();
        FormParametersFragment newFragment = new FormParametersFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_form_parameters);
        return mView;
    }

    @Override
    public void init() {
        mLiterasArray = getContext().getResources().getStringArray(R.array.literas);
        mFormsNumArray = getContext().getResources().getStringArray(R.array.forms_num);
        mLiteraAdapter = new FormParametersAdapter(mLiterasArray, new FormParametersAdapter.OnItemClicked(){
            @Override
            public void onItemClick(String value) {
                mChoosedLitera = value;
            }
        });
        mNumAdapter = new FormParametersAdapter(mFormsNumArray, new FormParametersAdapter.OnItemClicked(){
            @Override
            public void onItemClick(String value) {
                mChoosedFormNum = Integer.valueOf(value);
            }
        });

        mRvLetters.setHasFixedSize(true);
        mRvForms.setHasFixedSize(true);
        mRvLetters.setLayoutManager(new
                LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRvForms.setLayoutManager(new
                LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        mRvLetters.setAdapter(mLiteraAdapter);
        mRvForms.setAdapter(mNumAdapter);
    }

    @OnClick(R.id.btn_ready)
    public void onBtnReadyClick(){
        ChoosedFormFragment choosedFormFragment = ChoosedFormFragment.newInstance(
                mChoosedFormNum + mChoosedLitera);
        openFragment(choosedFormFragment);
    }

    @Override
    public void setTag() {
        TAG = "FormParametersFragment";
    }

}
