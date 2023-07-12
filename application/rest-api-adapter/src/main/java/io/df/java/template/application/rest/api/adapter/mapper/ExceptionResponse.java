package io.df.java.template.application.rest.api.adapter.mapper;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionResponse {
    Integer statusCode;
    String message;
    LocalDateTime localDateTime;
}
