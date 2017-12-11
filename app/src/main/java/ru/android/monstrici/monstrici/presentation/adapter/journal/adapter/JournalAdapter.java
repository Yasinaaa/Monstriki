package ru.android.monstrici.monstrici.presentation.adapter.journal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IGoalItemListener;
import ru.android.monstrici.monstrici.presentation.adapter.journal.factory.PupilViewHolderFactory;
import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.JournalViewHolder;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class JournalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> mList;
    private Context mContext;
    private Date mStartDate, mFinishDate;

    public JournalAdapter(List<User> list, Date startDate, Date finishDate) {
        mList = list;
        mStartDate = startDate;
        mFinishDate = finishDate;
    }

    public JournalAdapter(List<User> list) {
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mContext = parent.getContext();
        return new PupilViewHolderFactory().createViewHolder(parent, inflater);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        JournalViewHolder viewHolder = (JournalViewHolder) holder;
        if (mStartDate == null && mFinishDate == null)
            viewHolder.bindView(mList.get(position), mContext);
        else
            viewHolder.bindView(mList.get(position), mContext, mStartDate, mFinishDate);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
