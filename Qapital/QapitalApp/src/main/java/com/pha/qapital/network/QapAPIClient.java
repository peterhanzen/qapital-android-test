package com.pha.qapital.network;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.pha.qapital.network.models.wrappers.QapSavingsGoalsWrapper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pha on 2017-12-02.
 */

public class QapAPIClient {

    private QapAPIInterface api;

    public QapAPIClient(QapAPIInterface api) {
        this.api = api;
    }

//    public Call getSavingsGoals(final QapAPICallback<QapSavingsGoalsWrapper> callback) {
//        Timber.d("getSavingsGoals");
//        Call<QapSavingsGoalsWrapper> call = api.getSavingsGoals();
//        call.enqueue(callback);
//        return call;
//    }

    // TODO: EndPoint...
    public void getSavingsGoals(final QapAPICallback<QapSavingsGoalsWrapper> callback) {

        QapAPIInterface service = ServiceFactory.createRetrofitService(QapAPIInterface.class, "http://10.0.2.2:8080");

        service
                .getSavingsGoals()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<QapSavingsGoalsWrapper>() {
                .subscribe(new Consumer<QapSavingsGoalsWrapper>() {
                    @Override
                    public void accept(QapSavingsGoalsWrapper qapSavingsGoalsWrapper) throws Exception {
                        callback.onSuccess(qapSavingsGoalsWrapper);
                    }
                });
    }


    private static final class ServiceFactory {

        public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {

//            final Retrofit restAdapter = new Retrofit.Builder()
//                    .baseUrl(endPoint)
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build();
//            T service = restAdapter.create(clazz);

            T service = new Retrofit.Builder()
                    .baseUrl(endPoint)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(clazz);

            return service;
        }

    }

}
