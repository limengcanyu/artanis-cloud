package com.spring.cloud.common.advice;

import com.spring.cloud.common.dto.MyErrorBody;
import com.spring.cloud.common.dto.Result;
import com.spring.cloud.common.exception.MyException;
import com.spring.cloud.common.exception.PermissionException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Error Handling
 */
@RestControllerAdvice
public class ResponseControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(MyErrorBody.builder().value(status.value()).detailMessage(ex.getMessage()).build(), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = HttpStatus.resolve(code);
        return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @ExceptionHandler(PermissionException.class)
    public Result handleRolePermissionException(PermissionException exception) {
        return Result.builder().code("1").message(exception.getMessage()).build();
    }

}
