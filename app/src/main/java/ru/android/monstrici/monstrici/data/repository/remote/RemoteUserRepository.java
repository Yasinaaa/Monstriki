package ru.android.monstrici.monstrici.data.repository.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.Star;
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
                        User user = ResponseParser.parseUser((HashMap) dataSnapshot.getValue());
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
    public void getMonster(String id, @NonNull IDataCallback<Monster> callback) {
        mDatabase.child("monster").child(id).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Monster monster = ResponseParser.parseMonster((HashMap) dataSnapshot.getValue());
                        if (monster == null) {
                            callback.onReceiveDataFailure(new Message("Monster " + id + " is unexpectedly null"));
                        } else {
                            callback.onReceiveDataSuccess(new Response<Monster>().setBody(monster));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        callback.onReceiveDataFailure(new Message(databaseError.toException().getMessage()));
                    }
                });
    }

    @Override
    public void getMonsters(@NonNull IDataCallback<Monster> callback) {
        mDatabase.child("monster").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<Monster> monsters = new ArrayList<>();
                        for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                            monsters.add(ResponseParser.parseMonster((HashMap) dataSnapshotChild.getValue()));
                        }
                        if (monsters.size() == 0) {
                            callback.onReceiveDataFailure(new Message("Monsters is unexpectedly null"));
                        } else {
                            callback.onReceiveDataSuccess(new Response<Monster>().setBody(monsters));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        callback.onReceiveDataFailure(new Message(databaseError.toException().getMessage()));
                    }
                });
    }

    @Override
    public void getStars(String id, @NonNull IDataCallback<Star> callback) {
        mDatabase.child("stars").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Star> stars = ResponseParser.parseStar(id, (HashMap) dataSnapshot.getValue());

                if (stars.size() == 0) {
                    callback.onReceiveDataFailure(new Message("Stars " + " are unexpectedly null"));
                } else {
                    callback.onReceiveDataSuccess(new Response<Star>().setBody(stars));
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
                            users.add(ResponseParser.parseUser((HashMap) dataSnapshotChild.getValue()));
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

    @Override
    public void saveMonster(Monster monster) {
        mDatabase.child("monster").child(monster.getId()).setValue(monster).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i("MONSTER", "SAVED");
            }
        });
    }

    @Override
    public void saveUser(User user) {
        mDatabase.child("users").child(user.getId()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i("USER", "SAVED");
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
