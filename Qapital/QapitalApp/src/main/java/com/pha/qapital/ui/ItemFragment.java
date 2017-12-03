package com.pha.qapital.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private List<QapSavingsGoal> savingsGoals;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
//    public static ItemFragment newInstance(int columnCount) {
    public static ItemFragment newInstance(List<QapSavingsGoal> savingsGoals) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
        // TODO: TAG?
        args.putString(savingsGoals.getClass().getCanonicalName(), JsonUtil.toJsonString(savingsGoals));
        fragment.setArguments(args);

        fragment.savingsGoals = savingsGoals;
//        fragment.setAdapter();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            savingsGoals = JsonUtil.fromJsonString(getArguments().getString(savingsGoals.getClass().getCanonicalName()),
                    savingsGoals.getClass());
        }
    }

    // TODO: Better solution for setting savingsGoals, this amounts in race condition
    // TODO: Also, callback from http in gui-thread?
//    private View view;
//    private void setAdapter() {
//        Timber.d("setAdapter");
//        if (getView() != null && savingsGoals != null) {
//            Timber.d("setting Adapter");
//            ((RecyclerView) getView()).setAdapter(new MyItemRecyclerViewAdapter(savingsGoals, mListener));
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(QapSavingsGoal item);
    }

    private void getSavingsGoals(final RecyclerView recyclerView) {
        super.onStart();
        Timber.d("onStart");
        QapNetworkManager.getInstance(getContext()).getApiClient().getSavingsGoals(new QapAPICallback<QapSavingsGoalsWrapper>() {
            @Override
            public void onSuccess(QapSavingsGoalsWrapper response) {
                Timber.d("onSuccess");
                recyclerView.setAdapter(new MyItemRecyclerViewAdapter(response.savingsGoals, mListener));
            }

            @Override
            public void onFailure(QapAPIError stapiError) {
                Timber.d("onFailure");
            }
        });
    }

}
