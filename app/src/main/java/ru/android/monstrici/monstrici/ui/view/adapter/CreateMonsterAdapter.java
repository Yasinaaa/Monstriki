package ru.android.monstrici.monstrici.ui.view.adapter;

import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ru.android.monstrici.monstrici.ui.view.adapter.factory.CreateMonsterViewHolderFactory;
import ru.android.monstrici.monstrici.ui.view.adapter.holder.CreateMonsterViewHolder;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class CreateMonsterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private TypedArray mList;
    private IRecyclerViewItemListener mListener;
    private int mType;

    public CreateMonsterAdapter(TypedArray list, IRecyclerViewItemListener listener, int type) {
        mList = list;
        mListener = listener;
        mType = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CreateMonsterViewHolderFactory().createViewHolder(parent, inflater, mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CreateMonsterViewHolder viewHolder = (CreateMonsterViewHolder) holder;
        viewHolder.bindView(mList.getDrawable(position), mType);
    }


    @Override
    public int getItemCount() {
        return mList.length();
    }

}
