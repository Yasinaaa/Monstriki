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

public class PrizesFragment extends Fragment {

    private LayoutInflater inflater;
    private View mView;

    public PrizesFragment() {
    }
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPageNo;

    public static PrizesFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        PrizesFragment fragment = new PrizesFragment();
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
        mView = inflater.inflate(R.layout.fragment_prizes, container, false);

        return mView;

    }

}
