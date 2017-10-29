package ru.android.monstrici.monstrici.ui.view.main_pupil.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.adapter.PrizeAdapter;
import ru.android.monstrici.monstrici.presentation.model.Prize;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentUsualToolbar;

/**
 * Created by yasina on 17.10.17.
 */

public class PrizesFragment extends BaseFragmentUsualToolbar implements PrizeAdapter.OnItemClicked{

    public static int TOOLBAR_IMAGE = R.drawable.cup_icon_transparent;
    public static int TOOLBAR_TITLE = R.string.prize;

    @BindView(R.id.rv_prizes)
    RecyclerView mRvPrizes;

    private PrizeAdapter mPrizeAdapter;
    private ArrayList<Prize> mPrizesList;

    public PrizesFragment() {
        super(TOOLBAR_IMAGE, TOOLBAR_TITLE);
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
        createLayout(inflater, container, R.layout.fragment_prizes);
        return mView;
    }

    @Override
    public void setTag() {
        TAG = "PrizesFragment";
    }

    @Override
    public void init() {
        //TODO: this is temp prizes list, change it
        mPrizesList = new ArrayList<Prize>();
        mPrizesList.add(new Prize(R.drawable.brain, getString(R.string.the_smartest), "30.10.2017"));

        mPrizeAdapter = new PrizeAdapter(mPrizesList, this);
        mRvPrizes.setHasFixedSize(true);
        mRvPrizes.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvPrizes.setAdapter(mPrizeAdapter);
    }

    @Override
    public void onItemClick(int image) {

    }
}