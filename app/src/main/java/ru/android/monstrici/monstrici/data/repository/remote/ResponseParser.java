package ru.android.monstrici.monstrici.data.repository.remote;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.SchoolClass;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;

/**
 * Created by elisiumGusev
 *
 * @Date 14/10/2017
 * @Author Andrei Gusev
 */

public class ResponseParser {
    public static boolean isSuccessful(JSONObject response) {
        boolean successful = true;
        try {
            successful = response.getBoolean("success");
        } catch (JSONException e) {
            // no "success"-block means response is successful
        }
        return successful;
    }


    public static ResponseException getResponseException(JSONObject response) throws JSONException {
        JSONObject tmp = response.getJSONObject("exception");
        ResponseException exception = new ResponseException(tmp.getInt("code"), tmp.getString("message"));
        return exception;
    }

    static ArrayList<Star> parseStar(String id, HashMap<String, HashMap> hashMap) {
        ArrayList<Star> stars = new ArrayList();
        Star star = new Star();
        star.setId(id);
        HashMap<String,HashMap> days=hashMap.get("days");
        for (Map.Entry<String, HashMap> e : days.entrySet()) {
            Star st = new Star();
            st.setDate(e.getKey());
            HashMap<String, String> answer = e.getValue();

            st.setAnswer(answer.get("answer"));
            st.setClean(answer.get("cleaning"));
            stars.add(st);
        }
        return stars;
    }

    static User parseUser(HashMap hashMap) {
        User user = new User();
        user.setId((String) hashMap.get("id"));
        user.setPassword((String) hashMap.get("password"));
        user.setName((String) hashMap.get("name"));
        user.setLogin((String) hashMap.get("login"));
        user.setSurname((String) hashMap.get("surname"));
        user.setPosition((String) hashMap.get("position"));
        user.setMonster(new Monster((String) hashMap.get("monster")));
        user.setStarId((String) hashMap.get("stars"));
        ArrayList<HashMap<String, String>> classList = (ArrayList<HashMap<String, String>>) hashMap.get("class");
        if (classList != null) {
            SchoolClass schoolClass = new SchoolClass();
            schoolClass.setLetter(classList.get(0).get("letter"));
            schoolClass.setId(classList.get(0).get("id"));
            schoolClass.setNumber(classList.get(0).get("number"));
            user.setSchoolClass(schoolClass);
        }
        return user;
    }

    static Monster parseMonster(HashMap hashMap) {
        Monster monster = new Monster();
        monster.setId((String) hashMap.get("id"));
        monster.setBody((String) hashMap.get("body"));
        monster.setEye((String) hashMap.get("eyes"));
        monster.setMouth((String) hashMap.get("mouth"));
        monster.setName((String) hashMap.get("name"));
        return monster;
    }
}