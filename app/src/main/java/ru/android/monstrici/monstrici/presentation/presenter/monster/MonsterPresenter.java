package ru.android.monstrici.monstrici.presentation.presenter.monster;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
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

        User currentUser = mRepository.getCurrentUser();
        mRepository.getMonster(currentUser.getMonster().getId(), currentUser.getId(), new IDataCallback<Monster>() {
            @Override
            public void onReceiveDataSuccess(Response<Monster> response) {
                getViewState().onMonsterGet(response.getBody());
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

    public void getLastDonutDay(long day){
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(day);
        Calendar today = Calendar.getInstance();
        int days = today.get(Calendar.DAY_OF_YEAR) - date.get(Calendar.DAY_OF_YEAR);

        if (days < 2){
            getViewState().setMonsterSatisfiedCard();
        }else if (days >= 2){
            getViewState().setWishCard1();
        }else if (days >= 4){
            getViewState().setWishCard2();
        }else if (days >= 7){
            getViewState().setWishCard3();
        }

    }
}
