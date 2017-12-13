package ru.android.monstrici.monstrici.presentation.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private Resources mResource;

    public WeekDesitionsAdapter(DayDesition[] list) {
        this.mList = list;
    }

    @Override
    public WeekDesitionsAdapter.WeekDesitionsHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        mContext = parent.getContext();
        mResource = mContext.getResources();
        View itemView =
                LayoutInflater.from(mContext).inflate(R.layout.item_week_desitions,
                        parent, false);
        return new WeekDesitionsAdapter.WeekDesitionsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WeekDesitionsAdapter.WeekDesitionsHolder holder,
                                 final int position) {
        DayDesition dayDesition;
        if (position == 0){
            holder.mTvDayOfWeek.setText("");
            holder.mTvDate.setText("");
            holder.mTvGoals.setText(mResource.getString(R.string.points));
            holder.mTag.setText(mResource.getString(R.string.points));

            setHeight(setWrapContentHeight(),holder.mTvDayOfWeek, holder.mTvDate,
                    holder.mTvGoals, holder.mTag);

        }else {

            setHeight(setMatchParentHeight(),holder.mTvDayOfWeek, holder.mTvDate,
                    holder.mTvGoals, holder.mTag);
            dayDesition = mList[position-1];
            holder.mTvDayOfWeek.setText(mContext.getResources().
                    getString(dayDesition.getDayOfWeek()));
            holder.mTvDate.setText(dayDesition.getDate());
            holder.mTvGoals.setText(String.valueOf(dayDesition.getGoals()));
            holder.mTag.setText(String.valueOf(dayDesition.getTag()));
        }

    }

    private void setHeight(int height, View v, View v1, View v2, View v3){
        setHeight(height,v);
        setHeight(height,v1);
        setHeight(height,v2);
        setHeight(height,v3);
    }

    private void setHeight(int height, View v){
        v.getLayoutParams().height = height;
    }

    private int setWrapContentHeight(){
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    private int setMatchParentHeight(){
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }

    public class WeekDesitionsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pupil_name)
        TextView mTvDayOfWeek;
        @BindView(R.id.tv_donuts_count)
        TextView mTvDate;
        @BindView(R.id.tv_goals)
        TextView mTvGoals;
        @BindView(R.id.tv_tag)
        TextView mTag;

        public WeekDesitionsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
