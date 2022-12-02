package co.id.gundala.infrastructure.advice;

import co.id.gundala.infrastructure.model.BaseResponse;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseErrorAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse> handleMethodArgumentNotValid(Exception ex,
                                                                     HttpServletRequest request) {

        BaseResponse response = new BaseResponse(HttpStatus.BAD_REQUEST.value(), 1,
                ex.getLocalizedMessage(), request.getRequestURL());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<BaseResponse> handleConversionFailedException(Exception ex,
                                                                        HttpServletRequest request) {

        BaseResponse response = new BaseResponse(HttpStatus.BAD_REQUEST.value(), 1,
                ex.getLocalizedMessage(), request.getRequestURL());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
