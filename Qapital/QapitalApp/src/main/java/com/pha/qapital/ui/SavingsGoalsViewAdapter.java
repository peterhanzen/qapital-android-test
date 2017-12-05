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

import timber.log.Timber;

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
        holder.mItem = savingsGoals.get(position);
        Timber.d("Picasso");

        // TODO: 2017-12-05 Context
        // TODO: 2017-12-05 setLogging only once
        Picasso
                .with(MainActivity.activity)
                .setLoggingEnabled(true);
        Picasso
                .with(MainActivity.activity)
                .load("http://i.imgur.com/DvpvklR.png")
                .fit()
                .into(holder.mGoalImage);

//        Picasso.with(holder.mView.getContext()).load("http://i.imgur.com/DvpvklR.png").into(holder.mGoalImage);

        // TODO: 2017-12-05 Dont set logging here
//        Picasso
//                .with(context)
//                .setIndicatorsEnabled(true);
//        Picasso
//                .with(context)
//                .setLoggingEnabled(true);
//        Picasso
//                .with(context)
//                .load("https://en.wikipedia.org/wiki/Portable_Network_Graphics#/media/File:PNG_transparency_demonstration_1.png")
////                .load("https://thegoldwater.com/static/media_store/c74461ae2a9917a2482ac7b53f195b3c6e2fdd59e778c673256fb29d1b07f181.jpg")
//                .into(holder.mGoalImage, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        Timber.d("onSuccess");
////                        holder.mMediaEvidencePb.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onError() {
//                        Timber.d("onError");
//                    }
//                });

//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.listener(new Picasso.Listener()
//        {
//            @Override
//            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
//            {
//                exception.printStackTrace();
//            }
//        });
//        builder.build()
//                .load("https://thegoldwater.com/static/media_store/c74461ae2a9917a2482ac7b53f195b3c6e2fdd59e778c673256fb29d1b07f181.jpg")
////                .load(holder.mItem.getGoalImageURL())
//                .into(holder.mGoalImage, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        Timber.d("onSuccess");
////                        holder.mMediaEvidencePb.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onError() {
//                        Timber.d("onError");
//                    }
//                });

//        builder.build().load("file://1bbf030a-94c6-4ded-bfcc-150335b2df26.jpg").into(holder.mGoalImage);
//        Picasso.with(context).load("a1bbf030a-94c6-4ded-bfcc-150335b2df26.jpg").into(holder.mGoalImage);
//        Picasso.with(context).load(holder.mItem.getGoalImageURL()).into(holder.mGoalImage);

        holder.mIdView.setText("" + savingsGoals.get(position).getId());
        holder.mContentView.setText(savingsGoals.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return savingsGoals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mGoalImage;
        public final TextView mIdView;
        public final TextView mContentView;
        public QapSavingsGoal mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mGoalImage = (ImageView) view.findViewById(R.id.goalImage);
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
