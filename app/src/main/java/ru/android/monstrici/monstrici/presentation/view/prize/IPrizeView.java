package ru.android.monstrici.monstrici.presentation.view.prize;

import java.util.List;

import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by yasina on 07.12.17.
 */

public interface IPrizeView extends IBaseView {

    void setReward(String rewardTitle, int rewardPicture, String monstrikName);
    void onPrizesGetListFinish();
    void setNoPrizes();
}
