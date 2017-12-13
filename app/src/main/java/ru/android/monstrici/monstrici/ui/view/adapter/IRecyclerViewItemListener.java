package ru.android.monstrici.monstrici.ui.view.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public interface IRecyclerViewItemListener {
    void onItemClick(RecyclerView.ViewHolder sender, int adapterPosition, Drawable drawable, int type);
}
