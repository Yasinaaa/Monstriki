package ru.android.monstrici.monstrici.presentation;

/**
 * Created by yasina on 16.10.17.
 */
@Deprecated
public class BaseModule {

    public interface BaseView{
        void setTag();
        void start();
        void init();
    }

    public interface BasePresenter{

    }
}
