package ru.android.monstrici.monstrici.presentation.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android.monstrici.monstrici.R;

/**
 * Created by yasina on 29.10.17.
 */

public class PupilAdapter extends RecyclerView.Adapter
        <PupilAdapter.PupilHolder> {

    private final String TAG = "PupilAdapter";
    private String[] mList;
    private Context mContext;
    private PupilAdapter.OnItemClicked mOnClick;

    public interface OnItemClicked {
        void onItemClick(String value);
    }

    public PupilAdapter(String[] list, PupilAdapter.OnItemClicked onClick) {
        this.mList = list;
        this.mOnClick = onClick;
    }

    @Override
    public PupilAdapter.PupilHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        mContext = parent.getContext();
        View itemView =
                LayoutInflater.from(mContext).inflate(R.layout.item_form_parameter,
                        parent, false);
        return new PupilAdapter.PupilHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PupilAdapter.PupilHolder holder,
                                 final int position) {

        holder.mTvItem.setText(mList[position]);
        holder.itemView.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClick.onItemClick(mList[position]);
            }
        });
    }

    public void setOnClick(PupilAdapter.OnItemClicked mOnClick) {
        this.mOnClick = mOnClick;
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }


    public class PupilHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pupil)
        TextView mTvItem;

        public PupilHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}