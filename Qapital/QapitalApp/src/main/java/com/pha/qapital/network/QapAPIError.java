package com.pha.qapital.network;

/**
 * Created by pha on 2017-12-02.
 */

public final class QapAPIError {

    public final QapErrorBody errorBody;
    public final FaultCode faultCode;
    public final Throwable cause;

    public QapAPIError(QapErrorBody errorBody, Throwable cause) {
        this.errorBody = errorBody;
        this.faultCode = FaultCode.forCode(errorBody.status);
        this.cause = cause;
    }

    public enum FaultCode {

        SERVER_TIME_OUT_RESPONSE("102"),
        UNATHORIZED("401"),
        FORBIDDEN("403"),
        NOT_FOUND("404"),
        GONE("410"),
        INTERNAL_SERVER_ERROR("500"),
        SERVICE_UNAVAILABLE("503"),
        UNKNOWN_ERROR("-1");

        String errorCode;

        FaultCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public static FaultCode forCode(String code) {
            for (FaultCode faultCode : FaultCode.values()) {
                if (faultCode.errorCode.equals(code)) {
                    return faultCode;
                }
            }
            return UNKNOWN_ERROR;
        }
    }
}
