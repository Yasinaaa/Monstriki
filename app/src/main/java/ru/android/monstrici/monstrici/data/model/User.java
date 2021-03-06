package ru.android.monstrici.monstrici.data.model;

/**
 * Created by elisiumGusev
 *
 * @Date 14/10/2017
 * @Author Andrei Gusev
 */
public class User {
    String id;

    StarStorage stars;
    String starId;
    String login;
    String tag;
    String name;
    String surname;
    String password;
    String position;
    Monster monster;
    SchoolClass mSchoolClass;


    String key;


    public User() {
        stars = new StarStorage();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStarId() {
        return starId;
    }

    public void setStarId(String starId) {
        this.starId = starId;
    }

    public User(String id) {
        this.id = id;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public SchoolClass getSchoolClass() {
        return mSchoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        mSchoolClass = schoolClass;
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

    public StarStorage getStarStorage() {
        return stars;
    }


    public void setStars(StarStorage stars) {
        this.stars = stars;
    }
}

