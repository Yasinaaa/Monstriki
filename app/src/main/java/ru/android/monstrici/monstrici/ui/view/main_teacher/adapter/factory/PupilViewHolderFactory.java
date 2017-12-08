package ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.factory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.IRecyclerViewItemListener;
import ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.holder.PupilViewHolder;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class PupilViewHolderFactory implements IViewHolderFactory {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater, IRecyclerViewItemListener listener) {
        View view = inflater.inflate(R.layout.item_table_form, parent, false);
        return new PupilViewHolder(view, listener);
    }
}
