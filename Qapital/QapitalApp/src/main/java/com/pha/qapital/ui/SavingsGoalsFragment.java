package com.pha.qapital.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pha.qapital.R;
import com.pha.qapital.network.QapAPIClient;
import com.pha.qapital.network.models.QapSavingsGoal;
import com.pha.qapital.network.models.wrappers.QapSavingsGoalsWrapper;

/**
 * Created by pha on 2017-12-03.
 */

public class SavingsGoalsFragment extends Fragment {

    private static final String TAG = SavingsGoalsFragment.class.getName();

    private OnListFragmentInteractionListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_savings_goals_list, container, false);

        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            getSavingsGoals(recyclerView);
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnListFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(QapSavingsGoal savingsGoal);
    }

    private void getSavingsGoals(final RecyclerView recyclerView) {
        super.onStart();
        QapAPIClient.getInstance().getSavingsGoals(new QapAPIClient.QapAPICallback<QapSavingsGoalsWrapper>() {
            @Override
            public void onSuccess(QapSavingsGoalsWrapper response) {
                recyclerView.setAdapter(new SavingsGoalsViewAdapter(response.savingsGoals, listener));
            }
        });
    }

}
