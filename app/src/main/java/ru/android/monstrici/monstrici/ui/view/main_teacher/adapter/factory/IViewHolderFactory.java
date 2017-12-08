package ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.factory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.IRecyclerViewItemListener;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public interface IViewHolderFactory {
    RecyclerView.ViewHolder createViewHolder(ViewGroup parent,
                                             LayoutInflater inflater,
                                             IRecyclerViewItemListener listener);
}
