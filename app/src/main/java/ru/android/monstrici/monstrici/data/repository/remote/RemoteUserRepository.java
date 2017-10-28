package ru.android.monstrici.monstrici.data.repository.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.android.monstrici.monstrici.data.model.FirebaseUserConvertor;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.IUserRepository;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.utils.Message;

import static android.content.ContentValues.TAG;

/**
 * Created by elisiumGusev
 *
 * @Date 20/10/2017
 * @Author Andrei Gusev
 */
@Singleton
public class RemoteUserRepository implements IUserRepository {
    private FirebaseAuth mAuth;

    @Inject
    public RemoteUserRepository() {

    }


    @Override
    public void getUser(String id, @NonNull IDataCallback<User> callback) {

    }

    @Override
    public void getUsers(@NonNull String userId, @NonNull IDataCallback<User> callback) {

    }

    @Override
    public void checkLogin(String login, String password, @NonNull IDataCallback<User> callback) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(login, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        callback.onReceiveDataSuccess(new Response<User>()
                                .setBody(FirebaseUserConvertor.convert(user)));
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        callback.onReceiveDataFailure(new Message(task.getException().getMessage()));
                    }
                });

    }


    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();

                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    }

                });
    }

    public void sendEmailVerification(@NonNull IDataCallback<Message> callback) {
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onReceiveDataSuccess(new Response<Message>()
                                .setBody(new Message("Verification email sent to "
                                        + user.getEmail())));
                    } else {
                        Log.e(TAG, "sendEmailVerification", task.getException());
                        callback.onReceiveDataFailure(new Message("Failed to send verification email."
                                + user.getEmail()));
                    }
                });
    }
}
