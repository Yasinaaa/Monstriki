package ru.android.monstrici.monstrici.presentation.adapter.journal.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.adapter.journal.factory.GoalViewHolderFactory;
import ru.android.monstrici.monstrici.presentation.adapter.journal.factory.PupilViewHolderFactory;
import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.GoalViewHolder;
import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.JournalViewHolder;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IGoalItemListener;

/**
 * Created by yasina on 11.12.17.
 */

public class GoalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Star> mList;
    private IGoalItemListener mListener;

    public GoalAdapter(ArrayList<Star> list, IGoalItemListener listener) {
        mList = list;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new GoalViewHolderFactory().createViewHolder(parent, inflater, mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoalViewHolder viewHolder = (GoalViewHolder) holder;
        viewHolder.bindView(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
