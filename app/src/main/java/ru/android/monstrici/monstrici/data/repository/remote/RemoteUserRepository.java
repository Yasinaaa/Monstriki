package ru.android.monstrici.monstrici.data.repository.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
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

public class RemoteUserRepository implements IUserRepository {
    private final FirebaseAuth mAuth;
    private final DatabaseReference mDatabase;


    public RemoteUserRepository(FirebaseAuth auth, DatabaseReference database) {
        mAuth = auth;
        mDatabase = database;
    }

    @Override
    public void getUser(String userId, @NonNull IDataCallback<User> callback) {
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = parseUser((HashMap) dataSnapshot.getValue());
                        if (user == null) {
                            callback.onReceiveDataFailure(new Message("User " + userId + " is unexpectedly null"));
                        } else {
                            callback.onReceiveDataSuccess(new Response<User>().setBody(user));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        callback.onReceiveDataFailure(new Message(databaseError.toException().getMessage()));
                    }
                });
    }

    @Override
    public Flowable<User> getUsers(@NonNull IDataCallback<User> callback) {
        return Flowable.create(new FlowableOnSubscribe<User>() {
            @Override
            public void subscribe(FlowableEmitter<User> e) throws Exception {

                mDatabase.child("users").addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //ArrayList<User> users = new ArrayList<>();
                                Iterator<DataSnapshot> dataSnapshotsChat = dataSnapshot.getChildren().iterator();
                                while (dataSnapshotsChat.hasNext()) {
                                    DataSnapshot dataSnapshotChild = dataSnapshotsChat.next();
                                    e.onNext(parseUser((HashMap) dataSnapshotChild.getValue()));
                                    e.onComplete();
                                    //users.add(parseUser((HashMap) dataSnapshotChild.getValue()));
                                }
//                                if (users.size() == 0) {
//                                    callback.onReceiveDataFailure(new Message("Users " + " are unexpectedly null"));
//                                } else {
//                                    callback.onReceiveDataSuccess(new Response<User>().setBody(users));
//                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                e.onError(databaseError.toException());
                                //callback.onReceiveDataFailure(new Message(databaseError.toException().getMessage()));
                            }
                        });
            }
        }, BackpressureStrategy.BUFFER);



    }

    @Override
    public void checkLogin(String login, String password, @NonNull IDataCallback<User> callback) {
        mAuth.signInWithEmailAndPassword(login, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        getUser(user.getUid(), callback);

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

    private User parseUser(HashMap hashMap) {
        User user = new User();
        user.setId((String) hashMap.get("id"));
        user.setPassword((String) hashMap.get("password"));
        user.setName((String) hashMap.get("name"));
        user.setLogin((String) hashMap.get("login"));
        user.setSurname((String) hashMap.get("surname"));
        user.setPosition((String) hashMap.get("position"));
        ArrayList<HashMap> starsList = (ArrayList<HashMap>) hashMap.get("stars");
        if (starsList != null)
            user.setStars(starsList.get(0));
        return user;
    }
}
