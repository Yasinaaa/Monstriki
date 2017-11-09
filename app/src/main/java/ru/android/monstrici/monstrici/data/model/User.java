package ru.android.monstrici.monstrici.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by elisiumGusev
 *
 * @Date 14/10/2017
 * @Author Andrei Gusev
 */
@IgnoreExtraProperties
@Entity
public class User {
    @Id
    String id;

    @Transient
    Map<String, String> stars;
    String login;
    String name;
    String surname;
    String password;
    String position;


    @Index(unique = true)
    String key;


    public User() {

    }

    public User(String id) {
        this.id = id;
    }

    @Generated(hash = 686597411)
    public User(String id, String login, String name, String surname, String password,
                String position, String key) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.position = position;
        this.key = key;
    }

    public User(String id, HashMap<String, String> stars, String login, String name, String surname,
                String password, String position, String key) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.position = position;
        this.key = key;
        this.stars = stars;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", key='" + key + '\'' +
                '}';
    }

    public Map<String, String> getStars() {
        return stars;
    }

    public void setStars(Map<String, String> stars) {
        this.stars = stars;
    }
}

