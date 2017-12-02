package com.pha.qapital.network;

import com.pha.qapital.network.models.wrappers.QapSavingsGoalsWrapper;

import retrofit2.Call;
import timber.log.Timber;

/**
 * Created by pha on 2017-12-02.
 */

public class QapAPIClient {

    private QapAPIInterface api;

    public QapAPIClient(QapAPIInterface api) {
        this.api = api;
    }

    public Call getSavingsGoals(final QapAPICallback<QapSavingsGoalsWrapper> callback) {
        Timber.d("getSavingsGoals");
        Call<QapSavingsGoalsWrapper> call = api.getSavingsGoals();
        call.enqueue(callback);
        return call;
    }

//    private ECAPIInterface api;
//
//    public ECAPIClient(ECAPIInterface api) {
//        this.api = api;
//    }
//
//    public Call getAllSites(double latitude, double longitude, long radius, final ECAPICallback<ECSitesWrapper.Response> callback) {
//        Call<ECSitesWrapper.Response> call = api.getAllSites(latitude, longitude, radius);
//        call.enqueue(callback);
//        return call;
//    }

}
