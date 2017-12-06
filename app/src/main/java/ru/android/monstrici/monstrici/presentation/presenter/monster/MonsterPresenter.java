package ru.android.monstrici.monstrici.presentation.presenter.monster;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.model.MonsterContainer;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.monster.MonsterView;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 28/11/2017
 * @Author Andrei Gusev
 */
@InjectViewState
public class MonsterPresenter extends BasePresenter<MonsterView> {
    @Inject
    UserRepositoryImpl mRepository;
    @Inject
    MonsterContainer mMonsterContainer;

    public void getMonster() {
        mRepository.getMonster("", new IDataCallback<Monster>() {
            @Override
            public void onReceiveDataSuccess(Response<Monster> response) {
                mMonsterContainer.setMonster(response.getBody().clone());
                getViewState().updateMonster(
                        mMonsterContainer
                                .getEyesList()
                                .getDrawable(Integer.parseInt(response.getBody()
                                        .getEyes())), 3);
                getViewState().updateMonster(
                        mMonsterContainer
                                .getMouthList()
                                .getDrawable(Integer.parseInt(response.getBody()
                                        .getMouth())), 2);
                getViewState().updateMonster(
                        mMonsterContainer
                                .getBodyList()
                                .getDrawable(Integer.parseInt(response.getBody()
                                        .getBody())), 1);
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        });
    }
}
