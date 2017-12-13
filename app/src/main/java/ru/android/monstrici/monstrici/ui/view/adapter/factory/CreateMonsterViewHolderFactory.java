package ru.android.monstrici.monstrici.ui.view.adapter.factory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.adapter.IRecyclerViewItemListener;
import ru.android.monstrici.monstrici.ui.view.adapter.holder.CreateMonsterViewHolder;

/**
 * Created by elisium
 *
 * @Date 11/12/2017
 * @Author Andrei Gusev
 */

public class CreateMonsterViewHolderFactory implements IViewHolderFactory{
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater, IRecyclerViewItemListener listener) {
        View view = inflater.inflate(R.layout.item_create_monster, parent, false);
        return new CreateMonsterViewHolder(view, listener);
    }
}
