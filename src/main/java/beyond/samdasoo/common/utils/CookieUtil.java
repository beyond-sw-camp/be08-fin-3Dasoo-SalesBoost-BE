package beyond.samdasoo.common.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CookieUtil {

//    @Value("${backend.domain}")
//    private String domain;
//
//    @Value("${backend.secure}")
//    private boolean secure;

    public Cookie getCookie(HttpServletRequest req, String cookieName){

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    String refreshToken = cookie.getValue();
                    System.out.println(refreshToken);
                    return cookie;
                }
            }
        }
        return null;
    }

    public Cookie createCookie(String key, String value, int maxAge){
            Cookie cookie = new Cookie(key,value);
            cookie.setHttpOnly(true);
            cookie.setSecure(false); // https 적용시 변경
            cookie.setMaxAge(maxAge);
            cookie.setPath("/");
            return cookie;
    }

}