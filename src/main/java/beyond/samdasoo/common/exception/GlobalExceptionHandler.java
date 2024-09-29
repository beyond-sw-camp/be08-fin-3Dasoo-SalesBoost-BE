package beyond.samdasoo.common.exception;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.common.response.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
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
}
