package admin.Utils;

import admin.Utils.Exception.UtilsCreateException;
import sun.awt.datatransfer.DataTransferer;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatUtil {
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_TIME = "HH:mm:ss";

    private static final ZoneId defaultZoneId;
    static {
        defaultZoneId = ZoneId.systemDefault();
    }

    private DateFormatUtil() throws UtilsCreateException {
        throw new UtilsCreateException("No EduAdmin.Utils.DateFormatUtil instances for you!");
    }

    private static DateTimeFormatter getFormat(String format){
        return DateTimeFormatter.ofPattern(format);
    }

    /**  Date  与  LocalDateTime 互相转换 */
    public static Date convert(LocalDateTime localDateTime){
        ZonedDateTime zdt = localDateTime.atZone(defaultZoneId);
        return Date.from(zdt.toInstant());
    }
    public static LocalDateTime convert(Date date){
        Instant instant = date.toInstant();
        return  instant.atZone(defaultZoneId).toLocalDateTime();
    }

    /**   字符串 转 日期时间对象*/
    public static Date strToDate(String strDate){
        return strToDate(strDate,DateFormatUtil.FORMAT_DATE);
    }
    public static Date strToDate(String strDate,String format){
        LocalDateTime localDateTime = strToLocalDateTime(strDate, format);
        return convert(localDateTime);
    }
    public static LocalDateTime strToLocalDateTime(String strDate){
        DateTimeFormatter formatter = getFormat(DateFormatUtil.FORMAT_DATE);
        return LocalDateTime.parse(strDate,formatter);
    }
    public static LocalDateTime strToLocalDateTime(String strDate,String format){
        DateTimeFormatter formatter = getFormat(format);
        return LocalDateTime.parse(strDate,formatter);
    }

    /**   时间戳 转 日期时间对象*/
    public static Date instantToDate(Long instant){
        return new Date(instant);
    }
    public static LocalDateTime instantToLocalDateTime(Long instant){
        return convert(instantToDate(instant));
    }

    /**  日期时间对象  转 时间戳*/
    public static Long dateToInstant(Date date){
        return date.getTime();
    }
    public static Long localDateTimeToInstant(LocalDateTime localDateTime){
        return convert(localDateTime).getTime();
    }



}
