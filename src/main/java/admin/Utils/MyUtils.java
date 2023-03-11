package admin.Utils;

import javax.servlet.http.Cookie;

public class MyUtils {


    /** 检查传入的参数，是否全部非空 */
    public static boolean AllParamsIsNull(Object ...params){
        if(params.length==0)    return true;
        for (Object param : params) {
            if(param==null)
                return false;
        }
        return true;
    }
    /** 检查传入的参数，是否均为有效参数*/
    public static boolean AllParamIsMeaningful(Boolean flag, Object ...str){
        if(str==null || str.length==0)    return !flag;
        for (Object o : str) {
            //非String包装类视为有效数据
            if((!(o instanceof Number) && !(o instanceof Boolean) && !(o instanceof Character) && !(o instanceof String)) == flag)
                if(!(o instanceof String))
                    return false;
            //String 非 ”“ ”null“ 视为有效数据
            if((o instanceof String && !MyUtils.StringEquals((String) o, "", "null"))!= flag )
                if (o instanceof String)
                    return false;
        }
        return true;
        /*
        有效 flag=true    过
        无效 flag=false   过
        无效 flag=true    false
        有效 flag=false   false
         */
    }


    /**  判断字符串 key 是否存在于 str 中*/
    public static boolean StringEquals(String key, String... str){
        for (String i: str){
            if (i.equals(key))
                return true;
        }
        return false;
    }

    public static Cookie createCookie(String key, String value,Integer lifetime,String Path){
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
