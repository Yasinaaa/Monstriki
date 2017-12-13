package ru.android.monstrici.monstrici.presentation.presenter.form_parameters;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.form_parameters.IFormParametersView;

/**
 * Created by yasina on 13.12.17.
 */
@InjectViewState
public class FormParametersPresenter extends BasePresenter<IFormParametersView> {

    @Inject
    UserRepositoryImpl mRepository;

    public void getTeachersClasses(){
        getViewState().getClasses(mRepository.getCurrentUser().getSchoolClass().getNumber() +
                mRepository.getCurrentUser().getSchoolClass().getLetter());
    }
}
