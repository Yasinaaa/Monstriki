package ru.android.monstrici.monstrici.presentation.adapter.journal.holder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.adapter.journal.listener.IGoalItemListener;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 11.12.17.
 */

public class GoalViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private Star mCurrentStar;
    private Activity mActivity;
    private IGoalItemListener mListener;
    private TextView mTvDonutsCount;
    private TextView mTvPlus;
    private TextView mTvMinus;
    private TextView mTvTag;

    public GoalViewHolder(View itemView, IGoalItemListener listener, Activity activity) {
        super(itemView);
        itemView.setOnClickListener(this);
        mListener = listener;
        mActivity = activity;
        //LinearLayout center = itemView.findViewById(R.id.center__donut_layout);
        mTvDonutsCount = itemView.findViewById(R.id.tv_donuts_count);
        mTvPlus = itemView.findViewById(R.id.tv_add_donuts);
        mTvMinus = itemView.findViewById(R.id.tv_remove_donuts);
        mTvTag = itemView.findViewById(R.id.tv_tag);
        mTvPlus.setOnClickListener(this);
        mTvMinus.setOnClickListener(this);
        mTvTag.setOnClickListener(this);
    }

    public void bindView(Star star) {
        mCurrentStar = star;
        if (mCurrentStar == null){
            mCurrentStar = new Star();
            mCurrentStar.setDate(String.valueOf(Calendar.getInstance().getTime().
                    getTime()));
            mTvDonutsCount.setText("0");
        }else if (star.getGoals().equals("")){
            mTvDonutsCount.setText("0");
        }
        else
        {
            mTvDonutsCount.setText(star.getGoals());
            mTvTag.setText(star.getTag());
        }
        mTvTag.setBackgroundResource(R.drawable.textview_border);
    }

    public void updateTag(String tag) {
        mTvTag.setText(tag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_donuts: {
                String text = mTvDonutsCount.getText().toString();
                int answer = Integer.parseInt(text) + 1;
                setText(answer);
                break;
            }
            case R.id.tv_remove_donuts: {
                String text = mTvDonutsCount.getText().toString();
                int answer = Integer.parseInt(text) - 1;
                if (answer < 0)
                    answer = 0;
                setText(answer);
                break;
            }
            case R.id.tv_tag:{
                final String[] tagText = {""};
                LayoutInflater inflater = mActivity.getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_tags, null);

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity)
                        .setView(view)
                        .setTitle(mActivity.getResources().getString(R.string.choose_tag));
                dialogBuilder
                        .setPositiveButton(mActivity.getString(R.string.choose),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mTvTag.setText(tagText[0]);
                                    }
                                })
                        .setNegativeButton(mActivity.getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();

                TagContainerLayout mTagContainerLayout =
                        (TagContainerLayout) view.findViewById(R.id.tags);
                mTagContainerLayout.setTags(new ArrayList<String>(
                        Arrays.asList(
                                mActivity.getResources().getStringArray(R.array.tags_array))));
                mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
                    @Override
                    public void onTagClick(int position, String text) {
                        tagText[0] = text;
                    }

                    @Override
                    public void onTagLongClick(final int position, String text) {
                        // ...
                    }

                    @Override
                    public void onTagCrossClick(int position) {
                        // ...
                    }
                });
            }
        }
    }

    private void setText(int answer){
        mTvDonutsCount.setText(String.valueOf(answer));
        int position = getAdapterPosition();
        mCurrentStar.setTag(mTvTag.getText().toString());
        mCurrentStar.setGoals(mTvDonutsCount.getText().toString());
        mListener.onItemClick(position, mCurrentStar);
    }
}

