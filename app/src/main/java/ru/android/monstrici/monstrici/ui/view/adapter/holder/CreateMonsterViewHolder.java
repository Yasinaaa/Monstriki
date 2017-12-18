package ru.android.monstrici.monstrici.ui.view.adapter.holder;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.adapter.IRecyclerViewItemListener;

/**
 * Created by elisium
 *
 * @Date 11/12/2017
 * @Author Andrei Gusev
 */

public class CreateMonsterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private IRecyclerViewItemListener mListener;
    private Drawable mResId;
    private int mType;
    ImageView mIvMonsterItem;

    public CreateMonsterViewHolder(View itemView, IRecyclerViewItemListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        mIvMonsterItem = itemView.findViewById(R.id.iv_rv_item);
        mListener = listener;
    }

    public void bindView(Drawable drawable, int type) {
        mResId = drawable;
        mType = type;
        mIvMonsterItem.setImageDrawable(drawable);
    }

    @Override
    public void onClick(View v) {
        mListener.onItemClick(this, getAdapterPosition(), mResId,mType);
    }
}
