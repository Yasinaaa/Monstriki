package ru.android.monstrici.monstrici.presentation.presenter.parameters;

import android.graphics.drawable.Drawable;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.model.MonsterContainer;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.parameters.ICreateMonsterView;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by yasina on 16.10.17.
 */
@InjectViewState
public class CreateMonsterPresenter extends BasePresenter<ICreateMonsterView> {
    private int mNumBody, mNumMouth, mNumEye;

    @Inject
    UserRepositoryImpl mRepository;
    @Inject
    MonsterContainer mMonsterContainer;

    public void updateMonsterContainer(Drawable drawable, int type, int position) {
        switch (type) {
            case 1: {
                mMonsterContainer.setBodyIndex(type);
                mNumBody = position;
                break;
            }
            case 2: {
                mMonsterContainer.setMouthIndex(type);
                mNumMouth = position;
                break;
            }
            case 3: {
                mMonsterContainer.setEyeIndex(type);
                mNumEye = position;
                break;
            }
        }
        getViewState().updateMonster(drawable, type);
    }

    public String getUserId() {
        return mRepository.getCurrentUser().getId();
    }

    public void saveMonster(String name) {
        mMonsterContainer.setBodyIndex(mNumBody);
        mMonsterContainer.setEyeIndex(mNumEye);
        mMonsterContainer.setMouthIndex(mNumMouth);
        mMonsterContainer.createMonster(name, mRepository.getCurrentUser().getMonster().getId());
        mRepository.getCurrentUser().setMonster(mMonsterContainer.getMonster());
        mRepository.addMonster(mMonsterContainer.getMonster());
    }

    public MonsterContainer getMonsterContainer() {
        return mMonsterContainer;
    }
}
