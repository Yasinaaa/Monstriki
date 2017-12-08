package ru.android.monstrici.monstrici.ui.view.main_teacher.adapter;

import ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.holder.PupilViewHolder;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public interface IRecyclerViewItemListener {
    void onItemClick(PupilViewHolder sender, int adapterPosition, int id, int goals);
}
