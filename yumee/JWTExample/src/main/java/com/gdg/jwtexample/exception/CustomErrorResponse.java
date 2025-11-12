package com.gdg.jwtexample.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomErrorResponse{
    private CustomErrorCode status;
    private String statusMessage;
}