package com.pha.qapital.network.models;

import java.util.List;

/**
 * Created by pha on 2017-12-02.
 */

public class QapSavingsGoal {

    private int id;
    private int userId;
    private String name;
    private String goalImageURL;
    private int targetAmount;
    private int currentBalance;
    private List<Integer> created;
    private QapStatus status;
    private List<Integer> connectedUsers;

    public String getName() {
        return name;
    }

    public String getGoalImageURL() {
        return goalImageURL;
    }

    public int getTargetAmount() {
        return targetAmount;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }
}