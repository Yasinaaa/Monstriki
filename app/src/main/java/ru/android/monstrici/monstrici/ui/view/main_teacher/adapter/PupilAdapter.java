package ru.android.monstrici.monstrici.ui.view.main_teacher.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.factory.PupilViewHolderFactory;
import ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.holder.PupilViewHolder;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class PupilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<User> mList;
    private IRecyclerViewItemListener mListener;

    public PupilAdapter(List<User> list, IRecyclerViewItemListener listener) {
        mList = list;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PupilViewHolderFactory().createViewHolder(parent, inflater, mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PupilViewHolder viewHolder = (PupilViewHolder) holder;
        viewHolder.bindView(mList.get(position));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

}
