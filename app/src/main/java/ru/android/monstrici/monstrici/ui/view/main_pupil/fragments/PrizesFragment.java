package ru.android.monstrici.monstrici.ui.view.main_pupil.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.ArrayList;
import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.CoreComponent;
import ru.android.monstrici.monstrici.presentation.adapter.PrizeAdapter;
import ru.android.monstrici.monstrici.presentation.model.Prize;
import ru.android.monstrici.monstrici.presentation.presenter.prize.PrizePresenter;
import ru.android.monstrici.monstrici.presentation.view.prize.IPrizeView;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentUsualToolbar;
import ru.android.monstrici.monstrici.utils.Message;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 17.10.17.
 */

public class PrizesFragment extends BaseFragmentUsualToolbar
        implements PrizeAdapter.OnItemClicked, IPrizeView{

    private final static String VIEW_TYPE = "view_type";
    public static int TOOLBAR_IMAGE = R.drawable.cup_icon_transparent;
    public static int TOOLBAR_TITLE = R.string.prize;

    @BindView(R.id.rv_prizes)
    RecyclerView mRvPrizes;

    private PrizeAdapter mPrizeAdapter;
    private ArrayList<Prize> mPrizesList;
    private boolean mIsSinglePageView;

    @InjectPresenter
    PrizePresenter mPresenter;

    @ProvidePresenter
    public PrizePresenter providePresenter() {
        PrizePresenter presenter = new PrizePresenter();
        getComponent(AppComponent.class).inject(presenter);
        return presenter;
    }

    public static PrizesFragment newInstance(boolean isSinglePageView) {
        Bundle args = new Bundle();
        args.putBoolean(VIEW_TYPE, isSinglePageView);
        PrizesFragment newFragment = new PrizesFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    public PrizesFragment() {
        super(TOOLBAR_IMAGE, TOOLBAR_TITLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIsSinglePageView = getArguments().getBoolean(VIEW_TYPE);
        }
        getComponent(CoreComponent.class).inject(this);
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
        mPrizesList = new ArrayList<Prize>();
        mPresenter.getUsers(getActivity());

    }

    @Override
    public void onItemClick(int image) {

    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }

    @Override
    public void setReward(String rewardTitle, int rewardPicture, String monsterName) {
        mPrizesList.add(new Prize(rewardPicture,
                rewardTitle, monsterName));
    }

    @Override
    public void initRecyclerView(){
        mPrizeAdapter = new PrizeAdapter(mPrizesList, this);
        mRvPrizes.setHasFixedSize(true);
        mRvPrizes.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvPrizes.setAdapter(mPrizeAdapter);

    }

    @Override
    public void onPrizesGetListFinish() {
        if (mIsSinglePageView){
            mPresenter.getCurrentUserAchievements();
        }else {
            mPresenter.getAllUsersAchievements();
        }
    }
}
