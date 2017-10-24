package ru.android.monstrici.monstrici.data.repository.remote;

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

public interface IRetrofitClient {
    /**
     * GET
     */
    @GET("checkLogin/getId")
    Call<ResponseBody> checkLogin(@Query("id") String id);

    /**
     * POST
     */
    @POST("post/send")
    Call<ResponseBody> sendMessage(@Query("message") String message);
}
