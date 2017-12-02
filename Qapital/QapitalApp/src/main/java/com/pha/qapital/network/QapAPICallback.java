package com.pha.qapital.network;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by pha on 2017-12-02.
 */

public abstract class QapAPICallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        //Success if code is in range [200..300]
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            onFailureDefault(extractErrorFromResponse(response));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        QapErrorBody errorBody = t instanceof SocketTimeoutException ? QapErrorBody.SERVER_TIME_OUT_RESPONSE : QapErrorBody.UNKNOWN_ERROR;
        onFailureDefault(new QapAPIError(errorBody, t));
    }

    private QapAPIError extractErrorFromResponse(Response<T> response) {
        QapErrorBody errorBody;
        try {
            errorBody = QapErrorBody.fromJsonString(response.errorBody().string());
        } catch (Exception e) {
            Timber.e("Failed to parse error from response: %s", e.getMessage());
            errorBody = QapErrorBody.UNKNOWN_ERROR;
            okhttp3.Response raw = response.raw();
            errorBody.status = String.valueOf(raw.code());
            errorBody.error = new QapErrorBody.QapError(raw.message());
        }
        return new QapAPIError(errorBody, new Exception(response.message()));
    }

    // Default logging for the lazy. Can of course be overridden if you hate it.
    public void onFailureDefault(QapAPIError stapiError) {

        Timber.e(stapiError.toString());
        Timber.e(stapiError.cause.toString());
        Timber.e(stapiError.errorBody.toJsonString());

        // Print stacktrace
        // ... via String to enable logging with Timber
        StringWriter stackTrace = new StringWriter();
        stapiError.cause.printStackTrace(new PrintWriter(stackTrace));
        Timber.e(stackTrace.toString());

        onFailure(stapiError);
    }

    ////
    //// Abstract method to be implemented by child classes
    ////

    public abstract void onSuccess(T response);
    public abstract void onFailure(QapAPIError stapiError);

}
