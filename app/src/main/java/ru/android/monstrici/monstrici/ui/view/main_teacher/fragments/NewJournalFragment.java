package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import java.util.List;

import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.view.journal.IJournalView;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.IRecyclerViewItemListener;
import ru.android.monstrici.monstrici.ui.view.main_teacher.adapter.holder.PupilViewHolder;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public class NewJournalFragment extends BaseFragment implements IJournalView, IRecyclerViewItemListener {

    @Override
    public void onItemClick(PupilViewHolder sender, int adapterPosition, int id, int goals) {

    }

    @Override
    public void onTeacherPrepare(User user) {

    }

    @Override
    public void onUsersPrepare(List<User> userList) {

    }

    @Override
    public void onStarsGet(User user, List<Star> stars) {

    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }
}
