package com.spimex.spxfeedmds.general.exception;

import com.spimex.spxfeedmds.general.constant.MdsMessageConstant;
import com.spimex.spxfeedmds.general.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class MdsFeedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAuthenticationException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto(
            HttpStatus.UNAUTHORIZED.toString(),
            ex.getLocalizedMessage()));
    }

    @ExceptionHandler(value = {MdsPermissionCheckException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handlePermissionException(MdsPermissionCheckException checkException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDto.of(
                checkException.getError(),
                checkException.getMessage()
        ));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDto message = ErrorDto.of(HttpStatus.BAD_REQUEST.name(), MdsMessageConstant.MSG_WRONG_REQUEST_BODY_MESSAGE);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
