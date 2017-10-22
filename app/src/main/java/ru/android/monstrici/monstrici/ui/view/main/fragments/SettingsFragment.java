package ru.android.monstrici.monstrici.ui.view.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android.monstrici.monstrici.R;

/**
 * Created by yasina on 17.10.17.
 */

public class SettingsFragment extends Fragment {

    private LayoutInflater inflater;
    private View mView;
    @BindView(R.id.iv_fragment_logo)
    ImageView mIvFragmentLogo;
    @BindView(R.id.tv_fragment_title)
    TextView mTvFragmentTitle;

    public SettingsFragment() {
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
        mView = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(mView);
        return mView;

    }

}
