package com.pha.qapital.network;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pha.qapital.R;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by pha on 2017-12-02.
 */

public class QapNetworkManager {

    private static final String MOCK_BUILD_TYPE = "mock";
    private static final int GLOBAL_TIMEOUT = 30;
    private enum Timeouts {
        CONNECTION(GLOBAL_TIMEOUT),
        READ(GLOBAL_TIMEOUT),
        WRITE(GLOBAL_TIMEOUT);

        public static final TimeUnit timeUnit = TimeUnit.SECONDS;
        public final long timeout;

        Timeouts(long seconds) {
            this.timeout = seconds;
        }
    }

    private QapAPIClient apiClient;
    private static volatile QapNetworkManager instance = null;

    // TODO: Skip context? Only used for R.string.api_base_url
    public static QapNetworkManager getInstance(Context context) {
        Timber.d("getInstance");
        if (instance == null) {
            synchronized (QapNetworkManager.class) {
                if (instance == null) {
                    instance = new QapNetworkManager(context);
                }
            }
        }
        return instance;
    }

    public QapAPIClient getApiClient() {
        return apiClient;
    }

    private QapNetworkManager(Context context) {
//        apiClient = buildQapAPIClient(context, interceptor);
        apiClient = buildQapAPIClient(context);
    }

    private OkHttpClient getOkHttpClient(Interceptor... interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(Timeouts.CONNECTION.timeout, Timeouts.timeUnit)
                .readTimeout(Timeouts.READ.timeout, Timeouts.timeUnit)
                .writeTimeout(Timeouts.WRITE.timeout, Timeouts.timeUnit)
                .retryOnConnectionFailure(true)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });

//        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
            builder.addNetworkInterceptor(new StethoInterceptor());
//        }
//        for (Interceptor interceptor : interceptors) {
//            builder.addInterceptor(interceptor);
//        }

        return builder.build();
    }

    private QapAPIClient buildQapAPIClient(Context context) {
//    private QapAPIClient buildQapAPIClient(Context context, Interceptor mainInterceptor) {
        Gson gson = new GsonBuilder().create();
//        Gson gson = new GsonBuilder().setDateFormat(ECDateUtil.API_DATE_FORMAT).create();
        return new QapAPIClient(new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(getOkHttpClient(mainInterceptor))
                .client(getOkHttpClient())
                .build()
                .create(QapAPIInterface.class));
    }

}

