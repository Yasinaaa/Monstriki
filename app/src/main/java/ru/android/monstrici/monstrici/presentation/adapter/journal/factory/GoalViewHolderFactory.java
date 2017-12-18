package ru.android.monstrici.monstrici.presentation.adapter.journal.factory;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.adapter.journal.factory.model.IViewGoalHolderFactory;
import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.GoalViewHolder;
import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.JournalViewHolder;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IGoalItemListener;

/**
 * Created by yasina on 11.12.17.
 */

public class GoalViewHolderFactory implements IViewGoalHolderFactory {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent,
                                                    LayoutInflater inflater,
                                                    IGoalItemListener listener,
                                                    Activity activity,
                                                    JournalViewHolder journalViewHolder) {
        View view = inflater.inflate(R.layout.item_goal, parent,
                false);
        return new GoalViewHolder(view, listener, activity, journalViewHolder);
    }
}