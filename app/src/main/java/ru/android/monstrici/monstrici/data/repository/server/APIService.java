package ru.android.monstrici.monstrici.data.repository.server;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by elisiumGusev
 *
 * @Date 14/10/2017
 * @Author Andrei Gusev
 */

public class APIService implements IAPIClient {

    private static APIService instance;
    private final String BASE_URL = "http://httpbin.org";

    private IRetrofitClient client;

    public static APIService getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new APIService();
            return instance;
        }
    }

    private IRetrofitClient getClient() {
        return client;
    }

    private APIService() {
        client = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(IRetrofitClient.class);
    }

    private void executeCallback(Call<ResponseBody> result, final IAPICallback callback) {
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    callback.onSuccess(getJson(response));
                } catch (JSONException | IOException e) {
                    callback.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    private JSONObject getJson(Response<ResponseBody> response) throws JSONException, IOException {
        JSONObject jsonObject;
        String body;
        if (response.isSuccessful()) {
            body = response.body().string();
        } else {
            body = response.errorBody().string();
        }
        jsonObject = new JSONObject(body);
        return jsonObject;
    }


    @Override
    public void get(String id, IAPICallback callback) {
        Call<ResponseBody> result = client.get(id);
        executeCallback(result, callback);
    }

    @Override
    public void sendMessage(String message, IAPICallback callback) {
        Call<ResponseBody> result = client.sendMessage(message);
        executeCallback(result, callback);
    }
}
