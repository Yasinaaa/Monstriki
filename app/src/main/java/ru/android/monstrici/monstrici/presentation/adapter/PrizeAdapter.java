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
import ru.android.monstrici.monstrici.presentation.model.Prize;

/**
 * Created by yasina on 23.10.17.
 */

public class PrizeAdapter extends RecyclerView.Adapter<PrizeAdapter.PrizeHolder>{

    private final String TAG = "PrizeAdapter";
    private ArrayList<Prize> mList;
    private Context mContext;
    private OnItemClicked mOnClick;

    public interface OnItemClicked {
        void onItemClick(int image);
    }

    public PrizeAdapter(ArrayList<Prize> list, OnItemClicked onClick) {
        this.mList = list;
        this.mOnClick = onClick;
    }

    @Override
    public PrizeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView =
                LayoutInflater.from(mContext).inflate(R.layout.item_prize, parent, false);
        return new PrizeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PrizeHolder holder, final int position) {

        Prize prize = mList.get(position);
        holder.mIvPrize.setImageResource(prize.getPrizePicture());

        holder.mTvPrizeTitle.setText(prize.getPrizeTitle());
        holder.mTvPrizeDate.setText(prize.getPrizeDate());

        holder.itemView.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClick.onItemClick(position);
            }
        });
    }

    public void setOnClick(OnItemClicked mOnClick)
    {
        this.mOnClick = mOnClick;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class PrizeHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_prize)
        ImageView mIvPrize;
        @BindView(R.id.tv_prize_title)
        TextView mTvPrizeTitle;
        @BindView(R.id.tv_prize_date)
        TextView mTvPrizeDate;

        public PrizeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

