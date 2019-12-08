package webservice.dto;

import java.time.LocalDateTime;

public class ExceptionMessage {
    private int errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;

    public ExceptionMessage(int errorCode, String message, LocalDateTime timestamp) {
        this.errorCode = errorCode;
        this.errorMessage = message;
        this.timestamp = timestamp;
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
