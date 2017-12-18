package ru.android.monstrici.monstrici.data.model;

import java.util.ArrayList;

/**
 * Created by elisium
 *
 * @Date 28/11/2017
 * @Author Andrei Gusev
 */

public class SchoolClass {
    private String id;
    private String number;
    private String letter;
    private ArrayList<String> pupils;

    public SchoolClass() {
        pupils = new ArrayList<>();
    }

    public String getName() {
        return number + " " + letter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public ArrayList<String> getPupils() {
        return pupils;
    }

    public void setPupils(ArrayList<String> pupils) {
        this.pupils = pupils;
    }
}
