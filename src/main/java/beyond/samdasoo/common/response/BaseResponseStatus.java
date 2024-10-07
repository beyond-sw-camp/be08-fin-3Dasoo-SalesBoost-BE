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
     *  jwt 관련
     */
    JWT_AUTH_EMPTY(false, HttpStatus.UNAUTHORIZED.value(),"권한 정보가 없는 토큰입니다."),
    JWT_EXPIRED_ACCESS_TOKEN(false, HttpStatus.UNAUTHORIZED.value(), "만료된 액세스 토큰입니다"),
    JWT_INVALID_ACCESS_TOKEN(false, HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 액세스 토큰입니다"),
    JWT_INVALID_REFRESH_TOKEN(false, HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 리프레시 토큰입니다"),
    /**
     * user 관련
     */

    EMAIL_ALREADY_EXIST(false,HttpStatus.BAD_REQUEST.value(), "이미 사용중인 이메일입니다"),
    EMAIL_OR_PWD_NOT_FOUND(false,HttpStatus.BAD_REQUEST.value(), "이메일/비밀번호를 확인해주세요"),
    USER_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 회원입니다"),

    /**
     * product 관련
     */
    PRODUCT_ALREADY_EXIST(false, HttpStatus.BAD_REQUEST.value(), "이미 등록된 제품입니다."),
    Product_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 제품입니다."),

    /**
     * process 관련
     */
    PROCESS_ALREADY_EXIST(false, HttpStatus.BAD_REQUEST.value(),"이미 등록된 프로세스 입니다."),
    PROCESS_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "등록되지 않은 프로세스입니다."),
    /**
     * subprocess 관련
     */
    SUBPROCESS_ALREADY_EXIST(false, HttpStatus.BAD_REQUEST.value(), "이미 등록된 하위 프로세스 입니다."),

    SUBPROCESS_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "등록되지 않은 하위 프로세스 입니다."),

    /**
     * department 관련
     */
    DEPARTMENT_ALREADY_EXIST(false, HttpStatus.BAD_REQUEST.value(), "이미 등록된 부서입니다."),
    DEPARTMENT_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "등록되지 않은 부서입니다."),
    UPPER_DEPARTMENT_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "등록되지 않은 상위 부서입니다."),


    /**
     * act 관련
     */
    ACT_NOT_EXIST(false, HttpStatus.NOT_FOUND.value(), "존재하지 않는 영업활동입니다."),

    /**
     * plan 관련
     */
    PLAN_NOT_EXIST(false, HttpStatus.NOT_FOUND.value(), "존재하지 않는 일정입니다."),

    /**
     * todo 관련
     */
    TODO_NOT_EXIST(false, HttpStatus.NOT_FOUND.value(), "존재하지 않는 할 일입니다."),

    /**
     * calendar 관련
     */
    CALENDAR_NOT_EXIST(false, HttpStatus.NOT_FOUND.value(), "존재하지 않는 캘린더입니다."),



    /**
     * proposal 관련
     */
    PROPOSAL_ALREADY_EXIST(false, HttpStatus.BAD_REQUEST.value(), "이미 등록된 제안입니다."),
    PROPOSAL_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "등록되지 않은 제안입니다."),

    /**
     * estimate 관련
     */
    ESTIMATE_ALREADY_EXIST(false, HttpStatus.BAD_REQUEST.value(), "이미 등록된 견적입니다."),
    ESTIMATE_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "등록되지 않은 견적입니다."),

    /*
        estProduct 관련
     */
    EST_PRODUCT_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "등록되지 않은 견적 내 물품입니다."),
    
    /**
     *  Contract 관련
     */
    CONTRACT_ALREADY_EXIST(false, HttpStatus.BAD_REQUEST.value(), "이미 등록된 계약입니다."),
    CONTRACT_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "등록되지 않은 계약입니다."),

    /**
     *  Sales 관련
     */
    SALES_ALREADY_EXIST(false, HttpStatus.BAD_REQUEST.value(), "이미 등록된 매출입니다."),
    SALES_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "등록되지 않은 매출입니다."),

      /**
     * potentialCustomer(잠재고객) 관련
     */
    POTENTIAL_CUSTOMER_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "등록되지 않은 잠재고객입니다."),
    CONTACT_HISTORY_NOT_EXIST(false, HttpStatus.BAD_REQUEST.value(), "없는 접촉 이력 정보입니다."),

    /*
        report(PDF) 관련
     */
    IO_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "REPORT 입출력 에러입니다."),
    JRE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "REPORT 양식 에러입니다."),

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
