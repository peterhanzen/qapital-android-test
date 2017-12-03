package com.pha.qapital.network.models;

import java.util.List;

/**
 * Created by pha on 2017-12-02.
 */

public class QapSavingsGoal {

    private String goalImageURL;
    private int userId;
    private double targetAmount;
    private double currentBalance;
    private List<Integer> created;
    private QapStatus status;
    private String name;
    // TODO: Whats this?
    private int id;
    // TODO: @SerializedName
//    private List<Integer> connectedUserIds;
    private List<Integer> connectedUsers;

}