package admin.Utils;

import admin.Utils.Exception.UtilsCreateException;
import admin.model.VO.BaseVO;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyUtils {
    private MyUtils() throws UtilsCreateException {
        throw new UtilsCreateException("No EduAdmin.Utils.MyUtils instances for you!");
    }
    /** DTO对象转VO对象*/
    public static < PO, VO extends BaseVO<PO,VO>>  List<VO> toVoList(List<PO> poList, Class<VO> voClass) {
        return poList.stream().map(po -> {
            try {return voClass.newInstance().toVO(po);}
            catch (Exception e) {e.printStackTrace();return null;}
        }).collect(Collectors.toList());
    }
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
    //flag为true检测是否均为有效参数，flag为false检测是否均为无效参数
    public static boolean AllParamIsMeaningful(Boolean flag, Object ...str){
        if(str==null || str.length==0)    return !flag;
        for (Object o : str) {

            //含有元素的集合视为有效数据
            if (o instanceof Map && ((Map<?, ?>)o).size()==0)               return false;
            if (o instanceof Collection && ((Collection<?>)o).size()==0)    return false;

            //非String包装类视为有效数据
            if((!(o instanceof Number) && !(o instanceof Boolean) && !(o instanceof Character) && !(o instanceof String)) == flag)
                if(!(o instanceof String))
                    return false;

            //String 非 ”“ ”null“ 视为有效数据
            if((o instanceof String && !MyUtils.StringEquals((String) o, "", "null", "NULL", "Null"))!= flag )
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
    /**  布尔值字符串转Boolean,其他字符串为空  */
    public static Boolean strToBool(String str){
        if (str==null)
            return null;
        if (str.equals("true") ||str.equals("TRUE") ||str.equals("True")||
            str.equals("false")||str.equals("FALSE")||str.equals("False"))
            return  Boolean.parseBoolean(str);
        return null;

    }
    /** 所有入参字符串是否均可转为Integer*/
    public static Boolean allStrIsInt(String... str){
        if (str == null)
            return false;
        try {
            for (String s : str)
                Integer.parseInt(s);
        }catch (Exception ex){
            return false;
        }
        return true;
    }
    /** 所有入参字符串是否均可转为Long*/
    public static Boolean allStrIsLong(String... str){
        if (str == null)
            return false;
        try {
            for (String s : str)
                Long.parseLong(s);
        }catch (Exception ex){
            return false;
        }
        return true;
    }
    /** 字符串转Integer，排除了数字转换异常*/
    public static Integer strToInt(String  str){
        if (allStrIsInt(str))
            return Integer.parseInt(str);
        return null;
    }
    /** 字符串转Long，排除了数字转换异常*/
    public static Long strToLong(String  str){
        if (allStrIsLong(str))
            return Long.parseLong(str);
        return null;
    }
    /** 入参字符串是否为null、NULL、Null*/
    public static Boolean strLikeNull(String str){
        if (str == null)
            return false;
        return str.equals("null") || str.equals("NULL") || str.equals("Null");
    }
    /** 生成学生学号与教师教编，及互相转换  */
    public static final Integer CODE_LENGTH = 7;
    public static String codeGenerate(Integer id){
        return codeGenerate(false, id);
    }
    public static String codeGenerate(Boolean isTeacher, Integer id){
        if (id == null || id.toString().length()>CODE_LENGTH)
            return "-";
        isTeacher = isTeacher != null && isTeacher;
        int year = LocalDateTime.now().getYear();
        StringBuilder code = new StringBuilder(id.toString());

        int l = code.length();
        for (int i=0; i < CODE_LENGTH - l; i++)
            code.insert(0, "0");

        return (isTeacher?"T":"S") + year + code;
    }
    public static String codeConverter(String code){
        if (code.length() != CODE_LENGTH + 5)
            return code;
        return ((code.charAt(0) == 'T')?"S":"T") + code.substring(1);
    }
}
