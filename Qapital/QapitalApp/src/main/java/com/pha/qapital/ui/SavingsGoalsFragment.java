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
import com.pha.qapital.network.QapAPICallback;
import com.pha.qapital.network.QapAPIError;
import com.pha.qapital.network.QapNetworkManager;
import com.pha.qapital.network.models.QapSavingsGoal;
import com.pha.qapital.network.models.wrappers.QapSavingsGoalsWrapper;
import com.pha.qapital.util.JsonUtil;

import java.util.List;

import timber.log.Timber;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SavingsGoalsFragment extends Fragment {

    private static final String TAG = SavingsGoalsFragment.class.getName();

    private OnListFragmentInteractionListener mListener;
    private List<QapSavingsGoal> savingsGoals;

    public SavingsGoalsFragment() {}

    // TODO: ...
    public static SavingsGoalsFragment newInstance(List<QapSavingsGoal> savingsGoals) {
        SavingsGoalsFragment fragment = new SavingsGoalsFragment();
        Bundle args = new Bundle();
        args.putString(TAG, JsonUtil.toJsonString(savingsGoals));
        fragment.setArguments(args);

        fragment.savingsGoals = savingsGoals;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            savingsGoals = JsonUtil.fromJsonString(getArguments().getString(TAG), savingsGoals.getClass());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_savings_goals_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            getSavingsGoals(recyclerView);
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnListFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(QapSavingsGoal item);
    }

    private void getSavingsGoals(final RecyclerView recyclerView) {
        super.onStart();
        QapNetworkManager.getInstance(getContext()).getApiClient().getSavingsGoals(new QapAPICallback<QapSavingsGoalsWrapper>() {
            @Override
            public void onSuccess(QapSavingsGoalsWrapper response) {
                recyclerView.setAdapter(new SavingsGoalsViewAdapter(response.savingsGoals, mListener));
            }

            @Override
            public void onFailure(QapAPIError stapiError) {
                Timber.d("onFailure");
            }
        });
    }

}
