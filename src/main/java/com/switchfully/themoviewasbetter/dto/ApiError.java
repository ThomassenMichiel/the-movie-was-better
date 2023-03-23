package com.switchfully.themoviewasbetter.dto;

public class ApiError {
    private int httpStatus;
    private String message;

    public ApiError(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
