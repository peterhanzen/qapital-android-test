package com.pha.qapital.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pha.qapital.R;
import com.pha.qapital.network.models.QapSavingsGoal;

import timber.log.Timber;

/**
 * Created by pha on 2017-12-02.
 */

public class MainActivity extends AppCompatActivity implements SavingsGoalsFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(QapSavingsGoal savingsGoal) {
        Timber.d("Item clicked: " + savingsGoal.getName());
    }

}
