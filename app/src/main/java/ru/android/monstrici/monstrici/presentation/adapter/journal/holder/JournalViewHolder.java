package ru.android.monstrici.monstrici.presentation.adapter.journal.holder;

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
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class JournalViewHolder extends RecyclerView.ViewHolder
        implements IGoalItemListener{

    private TextView mTvPupilName;
    private RecyclerView mRvStars;
    private GoalAdapter mGoalAdapter;

    public JournalViewHolder(View itemView) {
        super(itemView);
        mTvPupilName = itemView.findViewById(R.id.tv_pupil_name);
        mRvStars = itemView.findViewById(R.id.rv_goals);
    }

    public void bindView(User user, Context context) {
        ArrayList<Star> stars = getTodaysGoals(new ArrayList(
                user.getStarStorage().getStars().values()));
        init(user, context, stars);
    }

    public void bindView(User user, Context context, Date beginDate, Date finishDate) {
        ArrayList<Star> stars = getWeekssGoals(new ArrayList(
                user.getStarStorage().getStars().values()),
                beginDate, finishDate);
        init(user, context, stars);
    }

    public void init(User user, Context context, ArrayList<Star> stars){
        mTvPupilName.setText(user.getName());
        mGoalAdapter = new GoalAdapter(stars, this);
        mRvStars.setLayoutManager(new LinearLayoutManager(context));
        mRvStars.setAdapter(mGoalAdapter);
    }

    private ArrayList<Star> getTodaysGoals(ArrayList<Star> stars){
        Date date = Calendar.getInstance().getTime();
        ArrayList<Star> result = new ArrayList<Star>();

        for (int i=0; i<stars.size(); i++){
            Date starsDate = new Date(Long.parseLong(stars.get(i).getDate()));
            if (Resources.DATE_FORMAT.format(date).equals(
                    Resources.DATE_FORMAT.format(starsDate))){
                result.add(stars.get(i));
            }
        }
        return result;
    }

    private ArrayList<Star> getWeekssGoals(ArrayList<Star> stars, Date beginDate,
                                           Date finishDate){
        ArrayList<Star> result = new ArrayList<Star>();

        for (int i=0; i<stars.size(); i++){
            Date starsDate = new Date(Long.parseLong(stars.get(i).getDate()));

            if (starsDate.getTime() >= beginDate.getTime() &&
                    starsDate.getTime() <= finishDate.getTime()){
                result.add(stars.get(i));
            }
        }
        return result;
    }

    @Override
    public void onItemClick(GoalViewHolder sender, int adapterPosition,
                            int id, int goals) {

    }
}
