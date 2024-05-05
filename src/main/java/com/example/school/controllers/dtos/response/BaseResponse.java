package com.example.school.controllers.dtos.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter @Builder
public class BaseResponse {

    private Object data;

    private String message;

    private Boolean success;

    private HttpStatus httpStatus;
}
