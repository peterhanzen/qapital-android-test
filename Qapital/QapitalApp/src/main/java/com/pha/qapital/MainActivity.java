package com.pha.qapital;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pha.qapital.network.models.QapSavingsGoal;
import com.pha.qapital.ui.ItemFragment;

/**
 * Created by pha on 2017-12-02.
 */

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Timber.d("onStart");
//        QapNetworkManager.getInstance(this).getApiClient().getSavingsGoals(new QapAPICallback<QapSavingsGoalsWrapper>() {
//            @Override
//            public void onSuccess(QapSavingsGoalsWrapper response) {
//                Timber.d("onSuccess");
//                ItemFragment.newInstance(response.savingsGoals);
//            }
//
//            @Override
//            public void onFailure(QapAPIError stapiError) {
//                Timber.d("onFailure");
//            }
//        });
//    }

    @Override
    public void onListFragmentInteraction(QapSavingsGoal item) {

    }
}
