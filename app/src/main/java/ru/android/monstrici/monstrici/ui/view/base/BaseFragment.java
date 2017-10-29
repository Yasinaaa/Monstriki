package ru.android.monstrici.monstrici.ui.view.base;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 23.10.17.
 */

public abstract class BaseFragment extends Fragment{

    public String TAG = BaseFragment.class.getSimpleName();

    public View mView;

    public BaseFragment(){

    }

    public void createLayout(LayoutInflater inflater, ViewGroup container, int id){
        mView = inflater.inflate(id, container, false);
        ButterKnife.bind(this, mView);
        init();
    }

    public void openFragment(BaseFragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_main, fragment, Resources.TAG_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    public abstract void setTag();
    public abstract void init();


}
