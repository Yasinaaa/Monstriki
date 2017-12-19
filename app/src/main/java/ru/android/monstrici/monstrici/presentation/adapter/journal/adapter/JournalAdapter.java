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

    public ArrayList<Star> mResultListStar;
    public ArrayList<String> mResultListUser;
    private List<User> mList;
    private Context mContext;
    private Date mStartDate, mFinishDate;
    private ArrayList<Star> mStarsList;
    private IJournalItemListener mListener;
    private Activity mActivity;

    public JournalAdapter(Date startDate, Date finishDate,
                          IJournalItemListener listener, Activity activity) {
        mActivity = activity;
        mStartDate = startDate;
        mFinishDate = finishDate;
        mListener = listener;
        mList = new ArrayList<User>();
        mResultListStar = new ArrayList<Star>();
        mResultListUser = new ArrayList<String>();
    }

    public JournalAdapter(IJournalItemListener listener, Activity activity) {
        mActivity = activity;
        mListener = listener;
        mList = new ArrayList<User>();
        mResultListStar = new ArrayList<Star>();
        mResultListUser = new ArrayList<String>();
    }

    public void updateAdapter(Date startDate, Date finishDate){
        mStartDate = startDate;
        mFinishDate = finishDate;
        updateAdapter();
    }

    public void updateAdapter(){
        mList = new ArrayList<User>();
        mResultListStar = new ArrayList<Star>();
        mResultListUser = new ArrayList<String>();
        notifyDataSetChanged();
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
        JournalViewHolder journalViewHolder = (JournalViewHolder) holder;
        if (mStartDate == null && mFinishDate == null) {
            mStarsList = getTodaysGoals(new ArrayList(
                    mList.get(position).getStarStorage().getStars().values()));
            journalViewHolder.bindView(mList.get(position), mStarsList, mContext, this);
        }
        else{
            mStarsList = getWeekssGoals(new ArrayList(
                            mList.get(position).getStarStorage().getStars().values()),
                    mStartDate, mFinishDate);
            journalViewHolder.bindView(mList.get(position), mStarsList, mContext, this);
        }
    }

    public void add(User user) {
        this.mList.add(user);
        notifyItemInserted(mList.size() - 1);
        notifyDataSetChanged();
    }

    public void removeItem(Star star){
        mList.remove(star);
        notifyItemRemoved(mList.indexOf(star));
    }

    public void removeFromResultList(Star star){

        //mResultList.remove(star);
        int d = mResultListStar.indexOf(star);
        mResultListUser.remove(d);
        mResultListStar.remove(star);
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

        /*if (star.getGoals().equals("0")){
            removeItem(adapterPosition, star);
        }*/

        mList.get(adapterPosition).getStarStorage().updateStar(star);

        mResultListStar.add(star);
        mResultListUser.add(mList.get(adapterPosition).getId());
        //mResultList.put(mList.get(adapterPosition).getId(), star);
    }

    @Override
    public void onItemClick(JournalViewHolder journalViewHolder) {
        journalViewHolder.addEmptyStar();
    }

   /* public Map<String, Star> getResultList(){
        return mResultList;
    }*/

}
