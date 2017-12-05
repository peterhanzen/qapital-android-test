package com.pha.qapital.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pha.qapital.R;
import com.pha.qapital.network.models.QapSavingsGoal;
import com.pha.qapital.ui.SavingsGoalsFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SavingsGoalsViewAdapter extends RecyclerView.Adapter<SavingsGoalsViewAdapter.ViewHolder> {

    private final List<QapSavingsGoal> savingsGoals;
    private final OnListFragmentInteractionListener listener;
    private Context context;

    public SavingsGoalsViewAdapter(List<QapSavingsGoal> savingsGoals, OnListFragmentInteractionListener listener) {
        this.savingsGoals = savingsGoals;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.savings_goals_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // TODO: 2017-12-05 Remove? Set elsewhere or remove entirely?
        holder.mItem = savingsGoals.get(position);

        Picasso
                .with(context)
                // TODO: 2017-12-05 Use proper url and remove
                .load("http://i.imgur.com/DvpvklR.png")
//                .load(holder.mItem.getGoalImageURL())
                // TODO: 2017-12-05 centerCrop() and centerInsideI() don't work, but might be better?
                .fit()
                .into(holder.mGoalImage);


        holder.mContentView.setText(savingsGoals.get(position).getName());
        holder.mgoalCompletion.setText(getGoalCompletionString(savingsGoals.get(position)));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    // TODO: 2017-12-05 Extract strings
    private String getGoalCompletionString(QapSavingsGoal savingsGoal) {
        final int currentBalance = savingsGoal.getCurrentBalance();
        final int targetAmount = savingsGoal.getTargetAmount();
        final String goalCompletion = "$" + currentBalance + " of " + targetAmount;
        String comment = "";
        if (targetAmount == 0) comment = "  You ought to aim higher";
        else if (currentBalance >= targetAmount) comment = "  Well done (:";
        return goalCompletion + comment;
    }

    @Override
    public int getItemCount() {
        return savingsGoals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mGoalImage;
        public final TextView mContentView;
        public final TextView mgoalCompletion;
        // TODO: 2017-12-05 Remove?
        public QapSavingsGoal mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mGoalImage = (ImageView) view.findViewById(R.id.goalImage);
            mgoalCompletion = (TextView) view.findViewById(R.id.goalCompletion);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
