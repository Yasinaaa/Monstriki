package ru.android.monstrici.monstrici.presentation.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
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
import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.model.MonsterContainer;
import ru.android.monstrici.monstrici.presentation.model.Rate;
import ru.android.monstrici.monstrici.utils.Message;

import static android.widget.ImageView.ScaleType.CENTER_INSIDE;

/**
 * Created by yasina on 19.11.17.
 */

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.RateHolder> {

    private final String TAG = "RateAdapter";
    private ArrayList<Rate> mList;
    private Context mContext;
    private MonsterCallback mCallback;
    private MonsterContainer mMonsterContainer;

    public RateAdapter(ArrayList<Rate> list, MonsterCallback callback, MonsterContainer container) {
        this.mList = list;
        mCallback = callback;
        mMonsterContainer = container;
    }

    public void add(User user, Monster monster){
        mList.add(new Rate(monster.getName(),
                R.drawable.m1, user.getStarStorage().getStarsCount()));
        notifyItemChanged(mList.size()-1);
    }

    @Override
    public RateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView =
                LayoutInflater.from(mContext).inflate(R.layout.item_rate, parent, false);
        return new RateHolder(itemView, mMonsterContainer);
    }

    @Override
    public void onBindViewHolder(final RateHolder holder, final int position) {


        Rate rate = mList.get(position);
        //holder.mIvMonster.setImageResource(rate.getMonsterImage());
        holder.mTvMonsterName.setText(rate.getMonsterName());
        if (rate.getMonsterDonutNum() <= 0) {
            holder.mIvMonsterDonuts.setVisibility(View.GONE);
        } else {
            holder.mIvMonsterDonuts.setImageResource(R.drawable.donut);
            holder.mTvMonsterDonuts.setText(String.valueOf(rate.getMonsterDonutNum()));
        }
        mCallback.setCallback(holder);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class RateHolder extends RecyclerView.ViewHolder implements IDataCallback<Monster> {

        @BindView(R.id.tv_monster_name)
        TextView mTvMonsterName;
        @BindView(R.id.tv_monster_donuts)
        TextView mTvMonsterDonuts;
        @BindView(R.id.iv_monster_donuts)
        ImageView mIvMonsterDonuts;
        ImageView mIvBodyPart;
        ImageView mIvEyePart;
        ImageView mIvMouthPart;
        private MonsterContainer mMonsterContainer;

        public RateHolder(View itemView, MonsterContainer container) {
            super(itemView);
            mMonsterContainer = container;
            ButterKnife.bind(this, itemView);
            ConstraintLayout monsterLayout = itemView.findViewById(R.id.iv_monster);
            mIvBodyPart = monsterLayout.findViewById(R.id.iv_body_part);
            mIvEyePart = monsterLayout.findViewById(R.id.iv_eye_part);
            mIvMouthPart = monsterLayout.findViewById(R.id.iv_mouth_part);


            mIvMouthPart.getLayoutParams().height = 30;
            mIvMouthPart.getLayoutParams().width = 30;

            mIvEyePart.getLayoutParams().height = 50;
            mIvEyePart.getLayoutParams().width = 50;

        }

        @Override
        public void onReceiveDataFailure(Message message) {

        }

        @Override
        public void onReceiveDataSuccess(Response<Monster> response) {
            for (Monster monster : response.getBodyList()) {
                if (monster.getName().equals(mTvMonsterName.getText().toString())) {
                    mIvBodyPart.setImageDrawable(mMonsterContainer.getBodyList()
                            .getDrawable(Integer.parseInt(monster
                                    .getBody())));
                    mIvMouthPart.setImageDrawable(mMonsterContainer
                            .getMouthList()
                            .getDrawable(Integer.parseInt(monster
                                    .getMouth())));
                    mIvEyePart.setImageDrawable(mMonsterContainer
                            .getEyesList()
                            .getDrawable(Integer.parseInt(monster
                                    .getEyes())));
                    break;
                }
            }
        }
    }
}


