package org.jeecg.common.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @ClassName LocalDateTimeUtils
 * @Description 时间日期操作类（基于jdk1.8以上，线程安全）
 * @Author 王振广
 * @Date 2019/04/24 8:38
 * Version 1.0
 **/
public class LocalDateTimeUtils {
    // 各种时间格式
    public static final DateTimeFormatter date_sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // 各种时间格式
    public static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
    // 各种时间格式
    public static final DateTimeFormatter date_sdf_wz = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    public static final DateTimeFormatter time_sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter yyyymmddhhmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter short_time_sdf = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter datetimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";
    public static final String datetimeFormatPattern = "yyyy-MM-dd HH:mm:ss";
    // 以毫秒表示的时间
    private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
    private static final long HOUR_IN_MILLIS = 3600 * 1000;
    private static final long MINUTE_IN_MILLIS = 60 * 1000;
    private static final long SECOND_IN_MILLIS = 1000;

    /**
     * @Author
     * @Description 指定模式的时间格式
     * @Date 11:43 2019/04/24
     * @Param
     * @return
     **/
    private static DateTimeFormatter getSDFormat(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }


    /**
     * @Author
     * @Description Date转换为LocalDateTime
     * @Date 11:43 2019/04/24
     * @Param [date]
     * @return java.time.LocalDateTime
     **/
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * @Author
     * @Description LocalDateTime转换为Date
     * @Date 11:43 2019/04/24
     * @Param [time]
     * @return java.util.Date
     **/
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * @Author
     * @Description 获取指定日期的毫秒
     * @Date 11:44 2019/04/24
     * @Param [time]
     * @return java.lang.Long
     **/
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * @Author
     * @Description 获取指定日期的秒
     * @Date 11:44 2019/04/24
     * @Param [time]
     * @return java.lang.Long
     **/
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

     /**
     * @Author
     * @Description 获取指定时间的指定格式
     * @Date 11:42 2019/04/24
     * @Param [LocalDateTime, DateTimeFormatter]
     * @return java.lang.String
     **/
    public static String formatTime(LocalDateTime time,DateTimeFormatter dateTimeFormatter) {
        return time.format(dateTimeFormatter);
    }

    /**
     * @Author
     * @Description 获取指定时间的指定格式
     * @Date 11:51 2019/04/24
     * @Param [date, dateTimeFormatter]
     * @return java.lang.String
     **/
    public static String formatTime(Date date,DateTimeFormatter dateTimeFormatter) {
        LocalDateTime time = convertDateToLDT(date);
        return time.format(dateTimeFormatter);
    }

    /**
     * @Author
     * @Description //获取指定时间的指定格式
     * @Date 11:44 2019/04/24
     * @Param [time, pattern]
     * @return java.lang.String
     **/
    public static String formatTime(LocalDateTime time,String pattern) {
        return time.format(DateTimeFormatter. ofPattern(pattern));
    }

    /**
     * @Author
     * @Description 获取指定时间的指定格式
     * @Date 11:57 2019/04/24
     * @Param [date, pattern]
     * @return java.lang.String
     **/
    public static String formatTime(Date date,String pattern) {
        LocalDateTime time = convertDateToLDT(date);
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @Author
     * @Description 获取当前时间的指定格式
     * @Date 11:44 2019/04/24
     * @Param [pattern]
     * @return java.lang.String
     **/
    public static String formatNow(String pattern) {
        return  formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * @Author
     * @Description 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
     * @Date 11:44 2019/04/24
     * @Param [time, number, field]
     * @return java.time.LocalDateTime
     **/
    public static LocalDateTime plus(LocalDateTime time, long number, ChronoUnit field) {
        return time.plus(number, field);
    }

    /**
     * @Author
     * @Description 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     * @Date 11:44 2019/04/24
     * @Param [time, number, field]
     * @return java.time.LocalDateTime
     **/
    public static LocalDateTime minu(LocalDateTime time, long number, ChronoUnit field){
        return time.minus(number,field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     * @param startTime
     * @param endTime
     * @param field  单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) return period.getYears();
        if (field == ChronoUnit.MONTHS) return period.getYears() * 12 + period.getMonths();
        return field.between(startTime, endTime);
    }

    /**
     * @Author
     * @Description 获取一天的开始时间，2017,7,22 00:00
     * @Date 11:45 2019/04/24
     * @Param [time]
     * @return java.time.LocalDateTime
     **/
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * @Author
     * @Description 获取一天的结束时间，2017,7,22 23:59:59.999999999
     * @Date 11:45 2019/04/24
     * @Param [time]
     * @return java.time.LocalDateTime
     **/
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }

    /**
     * @Author 
     * @Description 字符串转化为时间 LocalDateTime
     * @Date 13:33 2019/04/24
     * @Param [str, dateTimeFormatter]
     * @return java.time.LocalDateTime
     **/
    public static LocalDateTime parseLocalDateTime(String strDate,DateTimeFormatter dateTimeFormatter){
        return LocalDateTime.parse(strDate,dateTimeFormatter);
    }

    /**
     * @Author 
     * @Description 字符串转化为LocalDate
     * @Date 13:33 2019/04/24
     * @Param [str, dateTimeFormatter]
     * @return java.time.LocalDate
     **/
    public static LocalDate parseLocalDate(String strDate,DateTimeFormatter dateTimeFormatter){
        return LocalDate.parse(strDate,dateTimeFormatter);
    }

    /**
     * @Author
     * @Description  字符串转化为日期Date
     * @Date 13:35 2019/04/24
     * @Param [str, dateTimeFormatter]
     * @return java.util.Date
     **/
    public static Date parseDate(String strDate,DateTimeFormatter dateTimeFormatter){
        return convertLDTToDate(LocalDateTime.parse(strDate,dateTimeFormatter));
    }

    /**
     * 系统时间的毫秒数
     *
     * @return 系统时间的毫秒数
     */
    public static long getMillis() {
        return System.currentTimeMillis();
    }

    /**
     * @Author
     * @Description 指定毫秒数表示日期的默认显示，具体格式：yyyy-MM-dd HH:mm:ss
     * @Date 13:51 2019/04/24
     * @Param [millis]
     * @return java.lang.String指定毫秒数表示日期按“yyyy-MM-dd HH:mm:ss格式显示
     **/
    public static String formatDate(long millis) {
        return formatTime(new Date(millis),datetimeFormat);
    }
    /**
     * @Author
     * @Description 指定时间戳
     * @Date 14:52 2019/07/12
     * @Param []
     * @return java.sql.Timestamp
     **/
    public static Timestamp getTimestamp() {
        return new Timestamp(new Date().getTime());
    }
    /**
     * @Author
     * @Description 指定毫秒数的时间戳
     * @Date 14:53 2019/07/12
     * @Param [millis]
     * @return java.sql.Timestamp
     **/
    public static Timestamp getTimestamp(long millis) {
        return new Timestamp(new Date().getTime());
    }
}
