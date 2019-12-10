package webservice.dto;

import java.time.LocalDateTime;

public class ExceptionMessage {
    private int code;
    private String error;
    private LocalDateTime timestamp;

    public ExceptionMessage(int code, String error, LocalDateTime timestamp) {
        this.code = code;
        this.error = error;
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
