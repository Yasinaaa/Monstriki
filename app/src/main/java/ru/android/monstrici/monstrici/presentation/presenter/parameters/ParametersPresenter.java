package ru.android.monstrici.monstrici.presentation.presenter.parameters;

import android.app.Activity;
import android.content.Intent;

import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.ui.view.main.MainMenu;
import ru.android.monstrici.monstrici.utils.Resources;
/**
 * Created by yasina on 16.10.17.
 */

public class ParametersPresenter extends BasePresenter {

    public ParametersPresenter(Activity activity) {
        super(activity);
    }

    public void goNext(String monsterName, int monsterImage){

        Intent intent = new Intent(mActivity, MainMenu.class);
        //TODO: change with Obserable to check this
        if (monsterName.equals("")){
            monsterName = "Брозябр";
        }
        intent.putExtra(Resources.MONSTER_NAME, monsterName);
        intent.putExtra(Resources.MONSTER_IMAGE, monsterImage);
        mActivity.startActivity(intent);
        mActivity.finish();
    }

    /*public static Observable<String> getTextWatcherObservable(@NonNull final EditText editText) {

        final PublishSubject<String> subject = PublishSubject.create();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                subject.onNext(s.toString());
            }
        });

        return subject;
    }

    private Observable<String> getObserable(EditText editText){
        Observable<String> observable = getTextWatcherObservable(editText);
        observable.debounce(800, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public Subscription setSubscriptionToName(final EditText editText, Button button){
        return subscribeToButton(button, getObserable(editText)
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        if(!editText.getText().equals("")){
                            editText.setError(mActivity.getResources().getString(R.string.empty_name));
                            return false;
                        }else {

                        }
                        return true;
                    }
                }));
    }


    private Subscription subscribeToButton(final Button button, Observable<Boolean> observable){
        return observable.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean enabled) {
                if(button != null)
                    button.setEnabled(enabled);
            }
        });
    }*/

}
