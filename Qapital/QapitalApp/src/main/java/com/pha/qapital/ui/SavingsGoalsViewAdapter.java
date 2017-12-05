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
        final QapSavingsGoal savingsGoal = savingsGoals.get(position);

        Picasso
                .with(context)
                // TODO: 2017-12-05 Use proper url and remove
                .load("http://i.imgur.com/DvpvklR.png")
//                .load(holder.savingsGoal.getGoalImageURL())
                .fit()
                .into(holder.goalImage);


        holder.goalName.setText(savingsGoal.getName());
        holder.goalCompletion.setText(getGoalCompletionString(savingsGoal));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onListFragmentInteraction(savingsGoal);
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
        public final View view;
        public final ImageView goalImage;
        public final TextView goalName;
        public final TextView goalCompletion;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            goalImage = (ImageView) view.findViewById(R.id.savingsGoalImage);
            goalCompletion = (TextView) view.findViewById(R.id.savingsGoalCompletion);
            goalName = (TextView) view.findViewById(R.id.savingsGoalName);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + goalName.getText() + "'";
        }
    }
}
