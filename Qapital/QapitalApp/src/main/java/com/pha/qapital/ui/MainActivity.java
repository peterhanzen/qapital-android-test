package com.pha.qapital.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.pha.qapital.R;
import com.pha.qapital.network.models.QapSavingsGoal;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import timber.log.Timber;

/**
 * Created by pha on 2017-12-02.
 */

public class MainActivity extends AppCompatActivity implements SavingsGoalsFragment.OnListFragmentInteractionListener {

    // TODO: 2017-12-05 Hmm
    public static Activity activity;
    MyCallback myCallback = new MyCallback();

    private static class MyCallback implements Callback {
        @Override
        public void onSuccess() {
            System.out.println("onSuccess");
        }

        @Override
        public void onError() {
            System.out.println("onError");

        }
    }

    // TODO: 2017-12-05 Remove
    public MainActivity() {
        Timber.d("MainActivity called, activity set");
        activity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.d("onCreate, Picasso");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Picasso
                .with(this)
                .setLoggingEnabled(true);
        Picasso
                .with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .fit()
                .into(imageView, myCallback);
    }

    @Override
    public void onListFragmentInteraction(QapSavingsGoal item) {

    }
}
