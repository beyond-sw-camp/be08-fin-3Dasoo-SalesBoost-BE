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

    @Value("${backend.domain}")
    private String domain;

    @Value("${backend.secure}")
    private boolean secure;

    public Cookie getCookie(HttpServletRequest req, String cookieName){
        //쿠키가 없는 경우
        if(req.getCookies() == null){
            return null;
        }

        final List<Cookie> cookies = Arrays.stream(req.getCookies()).toList();
        Cookie cookieRes = cookies.stream().filter(cookie -> cookie.getName().equals(cookieName)).findFirst().get();
        return cookieRes;
    }

    public Cookie createCookie(String key, String value, int maxAge){
            Cookie cookie = new Cookie(key,value);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(maxAge);
            cookie.setPath("/");
            return cookie;
    }
}