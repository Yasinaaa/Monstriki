package ru.android.monstrici.monstrici.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ru.android.monstrici.monstrici.R;

/**
 * Created by yasina on 19.10.17.
 */

public class EyesAdapter extends RecyclerView.Adapter<EyesAdapter.EyeHolder>{

    private final String TAG = "EyesAdapter";
    private int[] mList;
    private Context mContext;
    private OnItemClicked mOnClick;

    public interface OnItemClicked {
        void onItemClick(int image);
    }

    public EyesAdapter(int[] eyesList, OnItemClicked onClick) {
        this.mList = eyesList;
        this.mOnClick = onClick;
    }

    @Override
    public EyeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView =
                LayoutInflater.from(mContext).inflate(R.layout.item_eyes, parent, false);
        return new EyeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EyeHolder holder, final int position) {
        Glide.with(mContext).load(mList[position]).into(holder.imageView);
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
        return mList.length;
    }


    public class EyeHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;

        public EyeHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_eye);
        }
    }
}

