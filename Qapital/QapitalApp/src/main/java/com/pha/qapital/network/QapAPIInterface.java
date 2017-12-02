package com.pha.qapital.network;

import com.pha.qapital.network.models.wrappers.QapSavingsGoalsWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pha on 2017-12-02.
 */

public interface QapAPIInterface {

    @GET("/savingsgoals")
    Call<QapSavingsGoalsWrapper> getSavingsGoals();
//    Observable<QapSavingsGoal> getSavingsGoals();
}
