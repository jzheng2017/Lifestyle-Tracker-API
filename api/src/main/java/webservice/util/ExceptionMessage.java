package webservice.util;

import java.time.LocalDateTime;

public class ExceptionMessage {
    private int errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;

    public ExceptionMessage(int errorCode, String errorMessage, LocalDateTime errorTime) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = errorTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
