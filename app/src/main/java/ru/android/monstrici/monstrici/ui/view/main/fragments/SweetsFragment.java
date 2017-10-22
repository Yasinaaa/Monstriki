package ru.android.monstrici.monstrici.ui.view.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.android.monstrici.monstrici.R;

/**
 * Created by yasina on 17.10.17.
 */

public class SweetsFragment extends Fragment {

    private LayoutInflater inflater;
    private View mView;

    public SweetsFragment() {
    }
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPageNo;

    public static SweetsFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        SweetsFragment fragment = new SweetsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            /*mListOn = getArguments().getIntArray(ON_LIST);
            mListOff = getArguments().getIntArray(OFF_LIST);
            mCurrentMonth = getArguments().getInt(MONTH);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sweets, container, false);

        return mView;

    }

}

