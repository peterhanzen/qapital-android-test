package com.pha.qapital.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pha on 2017-12-02.
 */

public enum QapStatus {

    // TODO: Extract
    @SerializedName("active")
    ACTIVE("active"),
    @SerializedName("passive")
    PASSIVE("passive");

    String status;

    QapStatus(String status) {
        this.status = status;
    }

}
