package ru.android.monstrici.monstrici.presentation.adapter.journal.holder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.adapter.journal.adapter.GoalAdapter;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IGoalItemListener;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IJournalItemListener;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class JournalViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private IJournalItemListener mListener;
    private TextView mTvPupilName;
    private RecyclerView mRvStars;
    private GoalAdapter mGoalAdapter;
    private User mUser;
    private Activity mActivity;

    public JournalViewHolder(View itemView, IJournalItemListener listener,
                             Activity activity) {
        super(itemView);
        mListener = listener;
        mActivity = activity;
        mTvPupilName = itemView.findViewById(R.id.tv_pupil_name);
        mRvStars = itemView.findViewById(R.id.rv_goals);
    }

    public void bindView(User user, ArrayList<Star> stars, Context context,
                         IGoalItemListener listener) {
        mUser = user;
        mTvPupilName.setText(user.getName());
        mTvPupilName.setOnClickListener(this);
        if (stars.size() == 0){
            stars.add(null);
        }
        mGoalAdapter = new GoalAdapter(stars, listener, mActivity);
        mRvStars.setLayoutManager(new LinearLayoutManager(context));
        mRvStars.setAdapter(mGoalAdapter);
    }

    @Override
    public void onClick(View v) {
        mListener.onItemClick(mUser.getId());
    }
}
