package com.pha.qapital.network.models;

/**
 * Created by pha on 2017-12-02.
 */

public enum QapStatus {

    // TODO: Extract
    ACTIVE("active"),
    PASSIVE("passive");

    String status;

    QapStatus(String status) {
        this.status = status;
    }

}
