package com.pha.qapital.network;

import com.google.gson.Gson;

/**
 * Created by pha on 2017-12-02.
 */

public class QapErrorBody {

    private static final Gson gson = new Gson();

    public static final QapErrorBody UNKNOWN_ERROR = new QapErrorBody(QapAPIError.FaultCode.UNKNOWN_ERROR, "An unknown error occured");
    public static final QapErrorBody UNATHORIZED = new QapErrorBody(QapAPIError.FaultCode.UNATHORIZED, "Unathorized");
    public static final QapErrorBody SERVER_TIME_OUT_RESPONSE = new QapErrorBody(QapAPIError.FaultCode.SERVER_TIME_OUT_RESPONSE, "The request channel timed out");

    public String status;
    public QapError error;

    public QapErrorBody() {} //For gson

    public QapErrorBody(QapAPIError.FaultCode faultCode, String faultMessage) {
        this.status = faultCode.errorCode;
        this.error = new QapError(faultMessage);
    }

    public static QapErrorBody fromJsonString(String jsonString) {
        return gson.fromJson(jsonString, QapErrorBody.class);
    }

    public String toJsonString() {
        return gson.toJson(this);
    }

    public static class QapError {
        public String id;
        public String title;
        public String detail;
        public String code;

        public QapError() {} //For gson

        public QapError(String message) {
            this.id = "";
            this.title = message;
            this.detail = message;
            this.code = "";
        }

//        public String localizedDetail(Context context) {
//            return QapErrorCode.lookupByCode(this.code).message(context, this.detail);
//        }
    }

}
