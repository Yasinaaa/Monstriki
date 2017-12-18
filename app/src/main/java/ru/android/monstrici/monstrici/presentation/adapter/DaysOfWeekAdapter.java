package ru.android.monstrici.monstrici.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.model.DayOfWeek;

/**
 * Created by yasina on 23.10.17.
 */

public class DaysOfWeekAdapter extends RecyclerView.Adapter<DaysOfWeekAdapter.DayOfWeekHolder>{

    private final String TAG = "DaysOfWeekAdapter";
    private ArrayList<DayOfWeek> mList;
    private Context mContext;
    //private OnItemClicked mOnClick;

    public interface OnItemClicked {
        void onItemClick(int image);
    }

    /*public DaysOfWeekAdapter(ArrayList<DayOfWeek> list, OnItemClicked onClick) {
        this.mList = list;
        this.mOnClick = onClick;
    }*/

    public DaysOfWeekAdapter(ArrayList<DayOfWeek> list) {
        this.mList = list;
    }

    @Override
    public DayOfWeekHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView =
                LayoutInflater.from(mContext).inflate(R.layout.item_day_of_week, parent, false);
        return new DayOfWeekHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DayOfWeekHolder holder, final int position) {

        DayOfWeek day = mList.get(position);
        holder.mTvDayTitle.setText(day.getDayTitle());
        holder.mTvDate.setText(day.getDate());

        /*if(day.getDonutsCount() <= 0){
            holder.mIvDonut.setVisibility(View.GONE);
        }else {
            holder.mIvDonut.setImageResource(R.drawable.donut);
        }*/
        holder.mIvDonut.setImageResource(R.drawable.donut);
        holder.mTvDonutNum.setText(String.valueOf(day.getDonutsCount()));
        /*holder.itemView.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClick.onItemClick(position);
            }
        });*/
    }

    /*public void setOnClick(OnItemClicked mOnClick)
    {
        this.mOnClick = mOnClick;
    }*/

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class DayOfWeekHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_day_title)
        TextView mTvDayTitle;
        @BindView(R.id.tv_day_date)
        TextView mTvDate;
        @BindView(R.id.iv_day_donut)
        ImageView mIvDonut;
        @BindView(R.id.tv_day_donut_num)
        TextView mTvDonutNum;

        public DayOfWeekHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
