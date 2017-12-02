package com.pha.qapital;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pha.qapital.network.QapAPICallback;
import com.pha.qapital.network.QapAPIError;
import com.pha.qapital.network.QapNetworkManager;
import com.pha.qapital.network.models.wrappers.QapSavingsGoalsWrapper;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        QapNetworkManager.getInstance(this).getApiClient().getSavingsGoals(new QapAPICallback<QapSavingsGoalsWrapper>() {
            @Override
            public void onSuccess(QapSavingsGoalsWrapper response) {
                System.out.println("onSuccess");
                Timber.d(response.toString());
            }

            @Override
            public void onFailure(QapAPIError stapiError) {
                Timber.d("onFailure");
            }
        });
        Timber.d("WTF");
    }
}
