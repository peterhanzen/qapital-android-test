package com.pha.qapital.network;

import com.pha.qapital.network.models.wrappers.QapSavingsGoalsWrapper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pha on 2017-12-02.
 */

public class QapAPIClient {

    private static final String ENDPOINT = "http://qapital-ios-testtask.herokuapp.com/";

    private static QapAPIInterface apiInterface = null;
    private static QapAPIClient instance;

    public interface QapAPICallback<T> {
        void onSuccess(T response);
    }

    public static QapAPIClient getInstance() {
        if (instance == null) instance = new QapAPIClient();
        return instance;
    }

    private QapAPIClient() {
        apiInterface = ServiceFactory.createRetrofitService(QapAPIInterface.class, ENDPOINT);
    }

    public void getSavingsGoals(final QapAPICallback<QapSavingsGoalsWrapper> callback) {

        apiInterface
                .getSavingsGoals()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<QapSavingsGoalsWrapper>() {
                    @Override
                    public void accept(QapSavingsGoalsWrapper qapSavingsGoalsWrapper) throws Exception {
                        callback.onSuccess(qapSavingsGoalsWrapper);
                    }
                });
    }

}
