package beyond.samdasoo.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseStatus {

    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, HttpStatus.OK.value(), "요청에 성공하였습니다"),

    /**
     * 400 : Request, Response 오류
     */

    /**
     * user 관련
     */

    EMAIL_ALREADY_EXIST(false,HttpStatus.BAD_REQUEST.value(), "이미 사용중인 이메일입니다"),
    EMAIL_OR_PWD_NOT_FOUND(false,HttpStatus.BAD_REQUEST.value(), "이메일/비밀번호를 확인해주세요"),
    USER_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 회원입니다"),


    /**
     * 500 :  Database, Server 오류
     */

    UNEXPECTED_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "예상치 못한 에러가 발생했습니다");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
