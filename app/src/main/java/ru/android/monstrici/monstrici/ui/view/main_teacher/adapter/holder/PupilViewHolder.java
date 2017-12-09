package ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.holder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.IRecyclerViewItemListener;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class PupilViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private IRecyclerViewItemListener mListener;
    private TextView mTvPupilName;
    private TextView mTvDonutsCount;
    private TextView mTvPlus;
    private TextView mTvMinus;
    private TextView mTvTag;

    public PupilViewHolder(View itemView, IRecyclerViewItemListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        mListener = listener;
        mTvPupilName = itemView.findViewById(R.id.tv_pupil_name);
        LinearLayout center = itemView.findViewById(R.id.center__donut_layout);
        mTvDonutsCount = center.findViewById(R.id.tv_donuts_count);
        mTvPlus = center.findViewById(R.id.tv_add_donuts);
        mTvMinus = center.findViewById(R.id.tv_remove_donuts);
        mTvTag = itemView.findViewById(R.id.ll3).findViewWithTag(R.id.tv_tag);
        mTvPlus.setOnClickListener(this);
        mTvMinus.setOnClickListener(this);
        mTvTag.setOnClickListener(this);
    }

    public void bindView(User user) {
        mTvPupilName.setText(user.getName());
        mTvDonutsCount.setText(user.getStarStorage().getStarsCount());
    }

    public void updateTag(String tag) {
        mTvTag.setText(tag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_donuts: {
                String text = mTvDonutsCount.getText().toString();
                mTvDonutsCount.setText(Integer.parseInt(text) + 1);
                break;
            }
            case R.id.tv_remove_donuts: {
                String text = mTvDonutsCount.getText().toString();
                mTvDonutsCount.setText(Integer.parseInt(text) - 1);
                break;
            }
            default: {
                int position = getAdapterPosition();
                if (itemView == v && position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(this, position, v.getId(),
                            Integer.parseInt(mTvDonutsCount.getText().toString()));
                }
            }
        }

    }
}
