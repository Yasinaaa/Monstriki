package ru.android.monstrici.monstrici.presentation.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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

public class FormParametersAdapter extends RecyclerView.Adapter
        <FormParametersAdapter.FormParametersHolder>{

    private final String TAG = "FormParametersAdapter";
    private String[] mList;
    private Context mContext;
    private FormParametersAdapter.OnItemClicked mOnClick;
    private int mSelectedItem = -1;
    private List<View> mAllViews;

    public interface OnItemClicked {
        void onItemClick(String value);
    }

    public FormParametersAdapter(String[] list, FormParametersAdapter.OnItemClicked onClick) {
        this.mList = list;
        this.mOnClick = onClick;
        mAllViews = new ArrayList<View>();
    }

    @Override
    public FormParametersAdapter.FormParametersHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        mContext = parent.getContext();
        View itemView =
                LayoutInflater.from(mContext).inflate(R.layout.item_form_parameter,
                        parent, false);
        return new FormParametersAdapter.FormParametersHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FormParametersAdapter.FormParametersHolder holder,
                                 final int position) {

        mAllViews.add(holder.itemView);
        holder.mTvItem.setText(mList[position]);
        holder.itemView.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClick.onItemClick(mList[position]);
                if (mSelectedItem != position) {

                    mAllViews.get(position).setBackgroundColor(mContext.getResources().
                            getColor(R.color.color_toolbar));

                    if (mSelectedItem != -1) {
                        mAllViews.get(mSelectedItem).
                                setBackgroundColor(Color.TRANSPARENT);
                    }
                    mSelectedItem = position;

                }else {
                    mAllViews.get(position).
                            setBackgroundColor(Color.TRANSPARENT);
                    mSelectedItem = -1;
                }
            }
        });
    }

    public void setOnClick(FormParametersAdapter.OnItemClicked mOnClick)
    {
        this.mOnClick = mOnClick;
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }


    public class FormParametersHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_item)
        TextView mTvItem;

        public FormParametersHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

