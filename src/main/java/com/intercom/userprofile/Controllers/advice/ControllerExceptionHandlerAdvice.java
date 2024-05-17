package com.intercom.userprofile.Controllers.advice;

import com.intercom.userprofile.model.*;

import io.quarkus.security.ForbiddenException;
import io.quarkus.security.UnauthorizedException;

import io.undertow.util.BadRequestException;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@Log4j2
public class ControllerExceptionHandlerAdvice {
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestErrorResponse> badRequestErrorHandler(BadRequestErrorResponse ex)
    {
      BadRequestErrorResponse badRequestErrorResponse=new BadRequestErrorResponse();
      badRequestErrorResponse.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
      badRequestErrorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
      log.info(" badRequestErrorHandler :ex.getMessage() "+ex.getMessage());
      badRequestErrorResponse.setMoreInformation(ex.getMessage());

      return ResponseEntity .status(HttpStatus.BAD_REQUEST)
              .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
              .body(badRequestErrorResponse);


    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundErrorResponse> notFoundErrorHandler(NotFoundException ex)
    {
        log.info("notFoundErrorHandler");
        NotFoundErrorResponse notFoundErrorResponse=new NotFoundErrorResponse();
        notFoundErrorResponse.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        notFoundErrorResponse.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        notFoundErrorResponse.setMoreInformation(ex.getMessage());

        return ResponseEntity .status(HttpStatus.NOT_FOUND)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(notFoundErrorResponse);


    }

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<UnauthorizedErrorResponse> unauthorizedErrorHandler(UnauthorizedException ex)
    {
        log.info("unauthorizedErrorHandler");
        UnauthorizedErrorResponse unauthorizedErrorResponse=new UnauthorizedErrorResponse();
        unauthorizedErrorResponse.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        unauthorizedErrorResponse.setCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        unauthorizedErrorResponse.setMoreInformation(ex.getMessage());

        return ResponseEntity .status(HttpStatus.UNAUTHORIZED)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(unauthorizedErrorResponse);


    }
    @ResponseBody
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ForbiddenErrorResponse> ForbiddenErrorHandler (ForbiddenException ex)
    {
        log.info("ForbiddenErrorHandler");
        ForbiddenErrorResponse forbiddenErrorResponse=new ForbiddenErrorResponse();
        forbiddenErrorResponse.setMessage(HttpStatus.FORBIDDEN.getReasonPhrase());
        forbiddenErrorResponse.setCode(String.valueOf(HttpStatus.FORBIDDEN.value()));
        forbiddenErrorResponse.setMoreInformation(ex.getMessage());

        return ResponseEntity .status(HttpStatus.FORBIDDEN)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(forbiddenErrorResponse);


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException = ", e);

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorValidationResponse errorValidationResponse = ErrorValidationResponse.builder()
                .validationErrors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorValidationResponse);
    }
//    @ResponseBody
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<MethodArgumentErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
//    {
//        log.info("methodArgumentNotValidExceptionHandler");
//        MethodArgumentErrorResponse methodArgumentErrorResponse= new MethodArgumentErrorResponse();
//        methodArgumentErrorResponse.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
//        methodArgumentErrorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
//        methodArgumentErrorResponse.setMoreInformation(ex.getMessage());
//
//        return ResponseEntity .status(HttpStatus.BAD_REQUEST)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//               .body(methodArgumentErrorResponse);
//
//
//   }
//
//    @ResponseBody
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<MethodArgumentErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        log.error("Validation exception occurred: {}", ex.getMessage());
//
//        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
//      String errorMessage = "";
//        for (FieldError error : fieldErrors) {
////            errorMessage = String.format("Validation failed for: '%s' with value '%s' (Reason: %s); ",
////                    error.getField(),
////                    error.getRejectedValue(),
////                    error.getDefaultMessage());
//            errorMessage = String.format("%s",
//                    error.getDefaultMessage());
//            log.error("errorMessage :"+errorMessage);
//        }
//
//        MethodArgumentErrorResponse errorResponse = new MethodArgumentErrorResponse();
//        errorResponse.setMoreInformation(errorMessage);
//        errorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
//        errorResponse.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
//
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .body(errorResponse);
//    }


}
