package ru.android.monstrici.monstrici.presentation.presenter.main_pupil;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import ru.android.monstrici.monstrici.R;

/**
 * Created by yasina on 02.11.17.
 */

public class MonsterPictureFunction {

    public static void setMonsterPicture(Fragment context, int mMonsterImageId,
                                         ImageView mIvMonster){
        RequestBuilder<Drawable> picture = null;
        if (mMonsterImageId == 0){
            picture = Glide.with(context).load(R.drawable.m1);
        }else {
            picture = Glide.with(context).load(mMonsterImageId);
        }
        picture.into(mIvMonster);
    }
}
