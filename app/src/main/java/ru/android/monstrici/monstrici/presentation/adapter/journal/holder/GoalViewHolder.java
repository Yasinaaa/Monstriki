package ru.android.monstrici.monstrici.presentation.adapter.journal.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IGoalItemListener;

/**
 * Created by yasina on 11.12.17.
 */

public class GoalViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private IGoalItemListener mListener;
    private TextView mTvDonutsCount;
    private TextView mTvPlus;
    private TextView mTvMinus;
    private TextView mTvTag;

    public GoalViewHolder(View itemView, IGoalItemListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        mListener = listener;
        LinearLayout center = itemView.findViewById(R.id.center__donut_layout);
        mTvDonutsCount = center.findViewById(R.id.tv_donuts_count);
        mTvPlus = center.findViewById(R.id.tv_add_donuts);
        mTvMinus = center.findViewById(R.id.tv_remove_donuts);
        mTvTag = itemView.findViewById(R.id.ll3).findViewById(R.id.tv_tag);
        mTvPlus.setOnClickListener(this);
        mTvMinus.setOnClickListener(this);
        mTvTag.setOnClickListener(this);
    }

    public void bindView(Star star) {
        mTvDonutsCount.setText(star.getGoals());
        mTvTag.setText(star.getTag());
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

