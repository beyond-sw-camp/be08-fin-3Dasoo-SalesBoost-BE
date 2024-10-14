package com.example.music.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";
    //    public static final int accessTokenExpireDuration = 1800000; // 액세스 토큰 만료 기간 : 30분
//    public static final int accessTokenExpireDuration = 6000000; // 액세스 토큰 만료 기간 : 100분
    public static final int accessTokenExpireDuration = 6000000; // 액세스 토큰 만료 기간 : 100분
    public static final int refreshTokenExpireDuration = 1209600000;  // 리프레시 토큰 만료 기간 : 14일

}
