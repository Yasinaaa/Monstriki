package ru.android.monstrici.monstrici.presentation.adapter.journal.listener;

import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.GoalViewHolder;
import ru.android.monstrici.monstrici.presentation.adapter.journal.holder.JournalViewHolder;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public interface IGoalItemListener {
    void onItemClick(int adapterPosition, Star star);
}
