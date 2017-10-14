package ru.android.monstrici.monstrici.data.repository.server;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by elisiumGusev
 *
 * @Date 14/10/2017
 * @Author Andrei Gusev
 */

interface IRetrofitClient {
    /**
     * GET
     */
    @GET("get/getId")
    Call<ResponseBody> get(@Query("id") String id);

    /**
     * POST
     */
    @POST("post/send")
    Call<ResponseBody> sendMessage(@Query("message") String message);
}
