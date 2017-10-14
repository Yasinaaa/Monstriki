package ru.android.monstrici.monstrici.data.repository.server;

/**
 * Created by elisiumGusev
 *
 * @Date 14/10/2017
 * @Author Andrei Gusev
 */

interface IAPIClient {
    /**
     * GET
     */
    void get(String id, IAPICallback callback);

    /**
     * POST
     */
    void sendMessage(String message, IAPICallback callback);
}
