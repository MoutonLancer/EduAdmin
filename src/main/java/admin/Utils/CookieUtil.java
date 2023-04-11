package admin.Utils;

import admin.Utils.Exception.UtilsCreateException;

import javax.servlet.http.Cookie;

public class CookieUtil {
    private CookieUtil() throws UtilsCreateException {
        throw new UtilsCreateException("No EduAdmin.Utils.CookieUtil instances for you!");
    }

    public static Cookie createCookie(String key, String value, Integer lifetime, String Path){
        Cookie cookie= new Cookie(key, value);
        if (!"".equals(Path))    cookie.setPath(Path);
        if (lifetime != null)   cookie.setMaxAge(lifetime);
        return cookie;
    }

    public static String getCookieValue(Cookie[] cookies,String key){
        if (cookies.length==0 || "".equals(key))    return "ParameterError";
        for (Cookie cookie : cookies)
            if(key.equals(cookie.getName()))        return cookie.getValue();
        return "No Cookie";
    }
}
