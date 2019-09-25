package co.id.gooddoctor.gundala.infrastructure.advice;

import co.id.gooddoctor.gundala.domain.settlement.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ResponseErrorAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse> handleMethodArgumentNotValid(Exception ex,
                                                                     HttpServletRequest request) {

        BaseResponse response = new BaseResponse(HttpStatus.BAD_REQUEST.value(), 1,
                ex.getLocalizedMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
