package ru.android.monstrici.monstrici.ui.view.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android.monstrici.monstrici.R;

/**
 * Created by yasina on 23.10.17.
 */

public abstract class BaseFragmentWithToolbar extends Fragment{

    public String TAG = BaseFragmentWithToolbar.class.getSimpleName();
    public int TOOLBAR_IMAGE = R.drawable.settings_icon;
    public int TOOLBAR_TITLE = R.string.settings;
    public View mView;

    public BaseFragmentWithToolbar(){

    }

    public BaseFragmentWithToolbar(int idImage, int idTitle){
        TOOLBAR_IMAGE = idImage;
        TOOLBAR_TITLE = idTitle;
    }

    public void createLayout(LayoutInflater inflater, ViewGroup container, int id){
        mView = inflater.inflate(id, container, false);
        ButterKnife.bind(this, mView);
    }

    public abstract void setTag();
    public abstract void init();

    public int getToolbarImage() {
        return TOOLBAR_IMAGE;
    }

    public int getToolbarTitle() {
        return TOOLBAR_TITLE;
    }
}
