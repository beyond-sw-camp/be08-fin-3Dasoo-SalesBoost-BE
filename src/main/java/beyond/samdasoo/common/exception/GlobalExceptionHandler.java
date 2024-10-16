package beyond.samdasoo.common.exception;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.common.response.BaseResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponse<BaseResponseStatus> baseExceptionHandle(BaseException e) {
         log.warn("BaseException. error message: {}", e.getMessage());
         return new BaseResponse<>(e.getStatus());
     }

    @ExceptionHandler(Exception.class)
    public BaseResponse<BaseResponseStatus> exceptionHandle(Exception exception) {
        log.error("Exception has occured. ", exception);
        return new BaseResponse<>(BaseResponseStatus.UNEXPECTED_ERROR);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponse<BaseResponseStatus>> authenticationExceptionHandle(AuthenticationException e, HttpServletRequest request){
        BaseResponseStatus status;
        if (request.getAttribute("exception") instanceof BaseResponseStatus){
            status = (BaseResponseStatus)request.getAttribute("exception");

        }else{
            log.warn("Unknown Authentication Exception. error message: {}", e.getMessage());
            status = BaseResponseStatus.JWT_AUTH_EMPTY;
        }

        return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.UNAUTHORIZED);
    }
}
