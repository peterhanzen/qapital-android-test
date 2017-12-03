package com.pha.qapital.network.models;

import java.util.List;

/**
 * Created by pha on 2017-12-02.
 */

public class QapSavingsGoal {

    private String goalImageURL;
    private int userId;
    // TODO: optional
    private double targetAmount;
    private double currentBalance;
    // TODO: ?
    private List<Integer> created;
    private QapStatus status;
    private String name;
    private int id;
    // TODO: optional
    // TODO: @SerializedName
//    private List<Integer> connectedUserIds;
    private List<Integer> connectedUsers;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}