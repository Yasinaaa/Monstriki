package ru.android.monstrici.monstrici.presentation.adapter.journal.holder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.adapter.journal.adapter.GoalAdapter;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IGoalItemListener;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IJournalItemListener;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class JournalViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private IJournalItemListener mListener;
    private TextView mTvPupilName, mTvPlus;
    private RecyclerView mRvStars;
    private GoalAdapter mGoalAdapter;
    private User mUser;
    private Activity mActivity;

    public JournalViewHolder(View itemView, IJournalItemListener listener,
                             Activity activity) {
        super(itemView);
        mListener = listener;
        mActivity = activity;
        mTvPlus =  itemView.findViewById(R.id.tv_plus);
        mTvPupilName = itemView.findViewById(R.id.tv_week_title);
        mRvStars = itemView.findViewById(R.id.rv_goals);
    }

    public void bindView(User user, ArrayList<Star> stars, Context context,
                         IGoalItemListener listener) {
        mUser = user;
        mTvPupilName.setText(user.getName());
        mTvPupilName.setOnClickListener(this);
        //stars.add(addEmptyItem(user.getStarId()));
        //stars.add(null);
        mGoalAdapter = new GoalAdapter(stars, listener, mActivity, this);
        mRvStars.setLayoutManager(new LinearLayoutManager(context));
        mRvStars.setAdapter(mGoalAdapter);

        mTvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoalAdapter.add(mUser.getStarId());
            }
        });
    }


    public void addEmptyStar(){
        mGoalAdapter.add(mUser.getStarId());
    }


    @Override
    public void onClick(View v) {
        mListener.onItemClick(mUser.getId());
    }


}
