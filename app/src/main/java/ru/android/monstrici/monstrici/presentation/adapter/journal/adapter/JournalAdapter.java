package ru.android.monstrici.monstrici.presentation.adapter.journal.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.GoalViewHolder;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IGoalItemListener;
import ru.android.monstrici.monstrici.presentation.adapter.journal.factory.PupilViewHolderFactory;
import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.JournalViewHolder;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IJournalItemListener;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class JournalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements IGoalItemListener{

    private Map<String, Star> mResultList;
    private List<User> mList;
    private Context mContext;
    private Date mStartDate, mFinishDate;
    private ArrayList<Star> mStarsList;
    private IJournalItemListener mListener;
    private Activity mActivity;

    public JournalAdapter(List<User> list, Date startDate, Date finishDate,
                          IJournalItemListener listener, Activity activity) {
        mList = list;
        mActivity = activity;
        mResultList = new HashMap<String, Star>();
        mStartDate = startDate;
        mFinishDate = finishDate;
        mListener = listener;
    }

    public JournalAdapter(List<User> list,
                          IJournalItemListener listener, Activity activity) {
        mList = list;
        mActivity = activity;
        mResultList = new HashMap<String, Star>();
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mContext = parent.getContext();
        return new PupilViewHolderFactory().createViewHolder(parent, inflater,
                mListener, mActivity);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        JournalViewHolder viewHolder = (JournalViewHolder) holder;
        if (mStartDate == null && mFinishDate == null) {
            mStarsList = getTodaysGoals(new ArrayList(
                    mList.get(position).getStarStorage().getStars().values()));
            viewHolder.bindView(mList.get(position), mStarsList, mContext, this);
        }
        else{
            mStarsList = getWeekssGoals(new ArrayList(
                            mList.get(position).getStarStorage().getStars().values()),
                    mStartDate, mFinishDate);
            viewHolder.bindView(mList.get(position), mStarsList, mContext, this);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
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
    public void onItemClick(int adapterPosition, Star star) {
        mList.get(adapterPosition).getStarStorage().updateStar(star);
        mResultList.put(mList.get(adapterPosition).getId(), star);
    }

    public Map<String, Star> getResultList(){
        return mResultList;
    }

}
