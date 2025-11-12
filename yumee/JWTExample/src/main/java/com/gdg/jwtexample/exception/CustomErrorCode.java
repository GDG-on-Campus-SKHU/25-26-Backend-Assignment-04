package com.gdg.jwtexample.exception;

import org.springframework.http.HttpStatus;

public enum CustomErrorCode {
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"존재하지 않는 사용자입니다."),
    BOOKING_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"예약건을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    CustomErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
