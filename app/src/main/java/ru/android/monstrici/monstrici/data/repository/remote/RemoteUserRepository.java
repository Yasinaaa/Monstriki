package ru.android.monstrici.monstrici.data.repository.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
    public void getUsers(@NonNull IDataCallback<User> callback) {
        mDatabase.child("users").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<User> users = new ArrayList<>();
                        Iterator<DataSnapshot> dataSnapshotsChat = dataSnapshot.getChildren().iterator();
                        while (dataSnapshotsChat.hasNext()) {
                            DataSnapshot dataSnapshotChild = dataSnapshotsChat.next();
                            users.add(parseUser((HashMap) dataSnapshotChild.getValue()));
                        }
                        if (users.size() == 0) {
                            callback.onReceiveDataFailure(new Message("Users " + " are unexpectedly null"));
                        } else {
                            callback.onReceiveDataSuccess(new Response<User>().setBody(users));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        callback.onReceiveDataFailure(new Message(databaseError.toException().getMessage()));
                    }
                });
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
