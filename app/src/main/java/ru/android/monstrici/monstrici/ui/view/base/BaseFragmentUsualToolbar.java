package ru.android.monstrici.monstrici.ui.view.base;

import ru.android.monstrici.monstrici.R;

/**
 * Created by yasina on 23.10.17.
 */

public abstract class BaseFragmentUsualToolbar extends BaseFragment {

    public int TOOLBAR_IMAGE = R.drawable.settings_icon;
    public int TOOLBAR_TITLE = R.string.settings;

    public BaseFragmentUsualToolbar(int idImage, int idTitle){
        TOOLBAR_IMAGE = idImage;
        TOOLBAR_TITLE = idTitle;
    }

    public int getToolbarImage() {
        return TOOLBAR_IMAGE;
    }

    public int getToolbarTitle() {
        return TOOLBAR_TITLE;
    }
}
