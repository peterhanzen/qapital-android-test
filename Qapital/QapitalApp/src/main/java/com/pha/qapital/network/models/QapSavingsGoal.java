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
    // TODO: optional
    private double targetAmount;
    private double currentBalance;
    // TODO: ?
    private List<Integer> created;
    private QapStatus status;
    // TODO: optional
    // TODO: @SerializedName
//    private List<Integer> connectedUserIds;
    private List<Integer> connectedUsers;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGoalImageURL() {
        return goalImageURL;
    }
}