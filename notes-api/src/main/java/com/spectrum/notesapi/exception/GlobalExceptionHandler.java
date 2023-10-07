package com.spectrum.notesapi.exception;

import com.spectrum.notesapi.model.api.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handle(
            ConstraintViolationException e,
            WebRequest request
    ) {
        ResponseEntity<?> responseEntity = new ResponseEntity<>(ErrorResponse.builder()
                .timestamp(new Date().toString())
                .status(BAD_REQUEST.toString())
                .errors(e.getConstraintViolations()
                        .stream()
                        .map(x -> x.getMessage())
                        .collect(Collectors.toList()))
                .build(), BAD_REQUEST);

        log.error("Exception occurred while processing the request. URI={} , tracingID = {}, Client-App = {}",
                ((ServletWebRequest) request).getRequest().getServletPath(),
                request.getHeader("Tracing-Id"),
                request.getHeader("Client-App"),
                e);

        log.info("Returning response  URI={}, tracingID = {} responseEntity={}",
                ((ServletWebRequest) request).getRequest().getServletPath(),
                request.getHeader("Tracing-Id"),
                responseEntity);

        return responseEntity;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgument(
            IllegalArgumentException e,
            WebRequest request
    ) {
        ResponseEntity<?> responseEntity = new ResponseEntity<>(ErrorResponse.builder()
                .timestamp(new Date().toString())
                .status(BAD_REQUEST.toString())
                .errors(new ArrayList<String>() {{
                    add(e.getMessage());
                }})
                .build(), BAD_REQUEST);

        log.error("Exception occurred while processing the request. URI={} , tracingID = {}, Client-App = {}",
                ((ServletWebRequest) request).getRequest().getServletPath(),
                request.getHeader("Tracing-Id"),
                request.getHeader("Client-App"),
                e);

        log.info("Returning response  URI={}, tracingID = {} responseEntity={}",
                ((ServletWebRequest) request).getRequest().getServletPath(),
                request.getHeader("Tracing-Id"),
                responseEntity);


        return responseEntity;

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAllException(
            Exception e,
            HttpServletRequest servletRequest,
            WebRequest request
    ) {

        ResponseEntity<?> responseEntity = new ResponseEntity<>(ErrorResponse.builder()
                .timestamp(new Date().toString())
                .status(INTERNAL_SERVER_ERROR.toString())
                .errors(new ArrayList<String>() {{
                    add(findActualCause(e).getMessage());// add(e.getCause().getMessage());
                }})
                .build(), getHttpHeaders(servletRequest), INTERNAL_SERVER_ERROR);

        log.error("Exception occurred while processing the request. URI={} , tracingID = {}, Client-App = {}",
                ((ServletWebRequest) request).getRequest().getServletPath(),
                servletRequest.getHeader("Tracing-Id"),
                servletRequest.getHeader("Client-App"),
                e);

        log.info("Returning response  URI={}, tracingID = {} responseEntity={}",
                ((ServletWebRequest) request).getRequest().getServletPath(),
                servletRequest.getHeader("Tracing-Id"),
                responseEntity);

        return responseEntity;
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity handleMissingRequestHeaderException(
            Exception e,
            WebRequest request
    ) {
        ResponseEntity<?> responseEntity = new ResponseEntity<>(ErrorResponse.builder()
                .timestamp(new Date().toString())
                .status(BAD_REQUEST.toString())
                .errors(new ArrayList<String>() {{
                    add(e.getMessage());
                }})
                .build(), BAD_REQUEST);

        log.error("Exception occurred while processing the request. URI={} , tracingID = {}, Client-App = {}",
                ((ServletWebRequest) request).getRequest().getServletPath(),
                request.getHeader("Tracing-Id"),
                request.getHeader("Client-App"),
                e);

        log.info("Returning response  URI={}, tracingID = {} responseEntity={}",
                ((ServletWebRequest) request).getRequest().getServletPath(),
                request.getHeader("Tracing-Id"),
                responseEntity);

        return responseEntity;
    }

    public static Throwable findActualCause(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    private HttpHeaders getHttpHeaders(HttpServletRequest servletRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Tracing-Id", servletRequest.getHeader("Tracing-Id"));
        return responseHeaders;
    }
}
