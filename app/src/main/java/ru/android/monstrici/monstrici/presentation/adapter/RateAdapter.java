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
import ru.android.monstrici.monstrici.presentation.model.Rate;

/**
 * Created by yasina on 19.11.17.
 */

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.RateHolder>{

    private final String TAG = "RateAdapter";
    private ArrayList<Rate> mList;
    private Context mContext;

    public RateAdapter(ArrayList<Rate> list) {
        this.mList = list;
    }

    @Override
    public RateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView =
                LayoutInflater.from(mContext).inflate(R.layout.item_rate, parent, false);
        return new RateHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RateHolder holder, final int position) {

        Rate rate = mList.get(position);
        holder.mIvMonster.setImageResource(rate.getMonsterImage());
        holder.mTvMonsterName.setText(rate.getMonsterName());
        if(rate.getMonsterDonutNum() <= 0){
            holder.mIvMonsterDonuts.setVisibility(View.GONE);
        }else {
            holder.mIvMonsterDonuts.setImageResource(R.drawable.donut);
            holder.mTvMonsterDonuts.setText(String.valueOf(rate.getMonsterDonutNum()));
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class RateHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.iv_monster)
    ImageView mIvMonster;
    @BindView(R.id.tv_monster_name)
    TextView mTvMonsterName;
    @BindView(R.id.tv_monster_donuts)
    TextView mTvMonsterDonuts;
    @BindView(R.id.iv_monster_donuts)
    ImageView mIvMonsterDonuts;

    public RateHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
}


