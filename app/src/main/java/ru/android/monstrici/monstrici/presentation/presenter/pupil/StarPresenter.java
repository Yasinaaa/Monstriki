package ru.android.monstrici.monstrici.presentation.presenter.pupil;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.IUserRepository;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.pupil.IStarDesc;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 05/12/2017
 * @Author Andrei Gusev
 */
@InjectViewState
public class StarPresenter extends BasePresenter<IStarDesc> {

    @Inject
    UserRepositoryImpl mRepository;

    public void getUsersMonsters() {
        mRepository.getUsersByClass(new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                ArrayList<Monster> monsters = new ArrayList<>();
                for (User u : response.getBodyList()) {
                    monsters.add(u.getMonster());
                }
                getViewState().showMonsters(monsters);
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        });
    }

}
