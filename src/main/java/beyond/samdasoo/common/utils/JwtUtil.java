package beyond.samdasoo.common.utils;

public class JwtUtil {

    public static final String ACCESS_TOKEN_COOKIE_NAME = "accessToken";
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";

    // --- 테스트용 ---
    public static final int accessTokenExpireDuration = 300000; // 액세스 토큰 만료 기간 : 5분
 //   public static final int refreshTokenExpireDuration = 600000; // 리프레시 토큰 만료 기간 : 10분
    public static final int refreshTokenExpireDuration = 86400000; // 리프레시 토큰 만료 기간 : 1day
   // public static final int refreshTokenExpireDuration = 60000; // 리프레시 토큰 만료 기간 : 1분

    // ----- 배포용 ----
//    public static final int accessTokenExpireDuration = 600000; // 액세스 토큰 만료 기간 : 100분
//    public static final int refreshTokenExpireDuration = 604800000;  // 리프레시 토큰 만료 기간 : 7일
}
