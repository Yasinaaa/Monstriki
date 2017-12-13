package ru.android.monstrici.monstrici.presentation.adapter.journal.factory.model;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.JournalViewHolder;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IGoalItemListener;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public interface IViewGoalHolderFactory {
    RecyclerView.ViewHolder createViewHolder(ViewGroup parent,
                                             LayoutInflater inflater,
                                             IGoalItemListener listener,
                                             Activity activity,
                                             JournalViewHolder journalViewHolder);
}
