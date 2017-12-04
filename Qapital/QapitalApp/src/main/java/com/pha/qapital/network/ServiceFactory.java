package com.pha.qapital.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.pha.qapital.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pha on 2017-12-04.
 */

public class ServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {

        T service = new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(HttpClient.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(clazz);

        return service;
    }

    private static class HttpClient {

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

        private static OkHttpClient getOkHttpClient() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(HttpClient.Timeouts.CONNECTION.timeout, HttpClient.Timeouts.timeUnit)
                    .readTimeout(HttpClient.Timeouts.READ.timeout, HttpClient.Timeouts.timeUnit)
                    .writeTimeout(HttpClient.Timeouts.WRITE.timeout, HttpClient.Timeouts.timeUnit)
                    .retryOnConnectionFailure(true)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(logging);
                builder.addNetworkInterceptor(new StethoInterceptor());
            }

            return builder.build();
        }

    }

}
