package ru.android.monstrici.monstrici.presentation.presenter.pupil;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.pupil.IPupilView;

/**
 * Created by yasina on 07.12.17.
 */

@InjectViewState
public class PupilPresenter extends BasePresenter<IPupilView> {

    @Inject
    UserRepositoryImpl mRepository;

    public void getUser(String id) {
    }
}
