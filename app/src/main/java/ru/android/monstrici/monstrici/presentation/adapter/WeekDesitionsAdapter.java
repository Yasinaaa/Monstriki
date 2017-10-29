package ru.android.monstrici.monstrici.presentation.adapter;

import android.content.Context;
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
import ru.android.monstrici.monstrici.presentation.model.DayDesition;

/**
 * Created by yasina on 29.10.17.
 */

public class WeekDesitionsAdapter extends RecyclerView.Adapter
        <WeekDesitionsAdapter.WeekDesitionsHolder> {

    private final String TAG = "WeekDesitionsAdapter";
    private DayDesition[] mList;
    private Context mContext;

    public WeekDesitionsAdapter(DayDesition[] list) {
        this.mList = list;
    }

    @Override
    public WeekDesitionsAdapter.WeekDesitionsHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        mContext = parent.getContext();
        View itemView =
                LayoutInflater.from(mContext).inflate(R.layout.item_week_desitions,
                        parent, false);
        return new WeekDesitionsAdapter.WeekDesitionsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WeekDesitionsAdapter.WeekDesitionsHolder holder,
                                 final int position) {
        DayDesition dayDesition = mList[position];
        holder.mTvDayOfWeek.setText(mContext.getResources().
                getString(dayDesition.getDayOfWeek()));
        holder.mTvDate.setText(dayDesition.getDate());
        holder.mForCleaning.setText(String.valueOf(dayDesition.getForCleaning()));
        holder.mForAnswer.setText(String.valueOf(dayDesition.getForAnswer()));
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }

    public class WeekDesitionsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_day_of_week)
        TextView mTvDayOfWeek;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.tv_for_answer)
        TextView mForAnswer;
        @BindView(R.id.tv_for_cleaning)
        TextView mForCleaning;

        public WeekDesitionsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
