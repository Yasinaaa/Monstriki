package ru.android.monstrici.monstrici.presentation.presenter.settings;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.model.MonsterContainer;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.settings.SettingsView;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 28/11/2017
 * @Author Andrei Gusev
 */
@InjectViewState
public class SettingsPresenter extends BasePresenter<SettingsView> {

    private int mNumBody, mNumMouth, mNumEye;
    @Inject
    UserRepositoryImpl mRepository;
    @Inject
    MonsterContainer mMonsterContainer;

    public void nextEye() {
        if (mNumEye == mMonsterContainer.getEyesList().length()) {
            mNumEye = 0;
        }
        getViewState().updateMonster(mMonsterContainer.getEyesList().getDrawable(mNumEye), 3);
        mMonsterContainer.getMonster().setEyes(String.valueOf(mNumEye));
        mNumEye++;
    }

    public void nextMouth() {
        if (mNumMouth == mMonsterContainer.getMouthList().length()) {
            mNumMouth = 0;
        }
        getViewState().updateMonster(mMonsterContainer.getMouthList().getDrawable(mNumMouth), 2);
        mMonsterContainer.getMonster().setMouth(String.valueOf(mNumMouth));
        mNumMouth++;
    }

    public void nextHands() {
        if (mNumBody == mMonsterContainer.getBodyList().length()) {
            mNumBody = 0;
        }
        getViewState().updateMonster(mMonsterContainer.getBodyList().getDrawable(mNumBody), 1);
        mMonsterContainer.getMonster().setBody(String.valueOf(mNumBody));
        mNumBody++;
    }

    public void getMonster(Monster monster) {
        mRepository.getMonster(monster.getId(), mRepository.getCurrentUser().getId(), new IDataCallback<Monster>() {
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
                getViewState().setMonsterName(response.getBody().getName());
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        });
    }

    public void getUser() {
        mRepository.getUser(mRepository.getCurrentUser().getId(), new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                getMonster(response.getBody().getMonster());
                getViewState().updateUser(response.getBody());
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                getViewState().showError(message);
            }
        });
    }

    public void saveMonster() {
        mMonsterContainer.setBodyIndex(mNumBody);
        mMonsterContainer.setEyeIndex(mNumEye);
        mMonsterContainer.setMouthIndex(mNumMouth);
        mRepository.saveMonster(mMonsterContainer.getMonster());
    }

    public void updateMonsterName(String name) {
        if (!mMonsterContainer.getMonster().getName().equals(name))
            mMonsterContainer.getMonster().setName(name);
    }
}
