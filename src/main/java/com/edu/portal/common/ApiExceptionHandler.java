package com.edu.portal.common;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.AccessDeniedException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = { ApiException.class })
    public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final ApiException e) {
        logger.info(request.toString());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseEntity(false, e.getApiStatusEntity(), e.getApiStatusEntity()));
    }

    @ExceptionHandler(value = { PersistenceException.class })
    public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final PersistenceException e) {
        String errorMessage = "";

        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            errorMessage = sw.toString();
            sw.close();
            pw.close();
        } catch(IOException e1) {
            logger.error("ApiExceptionHandler exceptionHandler ERROR: ", e);
        }

        logger.info(request.toString());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseEntity(false, new ApiStatusEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, ""), null));
    }

    @ExceptionHandler(value = { TooManyResultsException.class })
    public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final TooManyResultsException e) {
        String errorMessage = "";

        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            errorMessage = sw.toString();
            sw.close();
            pw.close();
        } catch(IOException e1) {
            logger.error("ApiExceptionHandler exceptionHandler ERROR: ", e);
        }

        logger.info(request.toString());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseEntity(false, new ApiStatusEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, ""), null));
    }

    @ExceptionHandler(value = { HttpClientErrorException.class })
    public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final HttpClientErrorException e) {
        logger.info(request.toString());
        e.printStackTrace();

        return ResponseEntity
                .status(e.getStatusCode())
                .body(new ApiResponseEntity(false, new ApiStatusEntity(e.getStatusCode().value(), e.getStatusText(), ""), null));
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final AccessDeniedException e) {
        logger.info(request.toString());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponseEntity(false, new ApiStatusEntity(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), ""), null));
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final Exception e) {
        logger.info(request.toString());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseEntity(false, new ApiStatusEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), ""), null));
    }

}
