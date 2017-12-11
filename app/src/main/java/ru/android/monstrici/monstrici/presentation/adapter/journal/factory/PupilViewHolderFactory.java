package ru.android.monstrici.monstrici.presentation.adapter.journal.factory;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.adapter.journal.factory.model.IViewGoalHolderFactory;
import ru.android.monstrici.monstrici.presentation.adapter.journal.factory.model.IViewPupilHolderFactory;
import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.JournalViewHolder;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IJournalItemListener;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class PupilViewHolderFactory implements IViewPupilHolderFactory {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent,
                                                    LayoutInflater inflater,
                                                    IJournalItemListener listener,
                                                    Activity activity) {
        View view = inflater.inflate(R.layout.item_journal_goals, parent,
                false);
        return new JournalViewHolder(view, listener, activity);
    }
}
