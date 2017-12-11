package ru.android.monstrici.monstrici.presentation.adapter.journal.factory.model;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IGoalItemListener;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IJournalItemListener;

/**
 * Created by yasina on 11.12.17.
 */

public interface IViewPupilHolderFactory {
    RecyclerView.ViewHolder createViewHolder(ViewGroup parent,
                                             LayoutInflater inflater,
                                             IJournalItemListener listener,
                                             Activity activity);
}
