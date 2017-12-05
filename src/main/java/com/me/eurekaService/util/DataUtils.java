package com.me.eurekaService.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

public class DataUtils {
    private static SimpleDateFormat defaultDatePattern = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat defaultDateTimePattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static DecimalFormat df = new DecimalFormat("0.00");

    /**
     * 获得指定日期偏移天数后的日期
     * @param date 指定日志
     * @param days 偏移天数,正数为增加天数,负数为减少天数
     * @return
     */
    public static Date addDayToDate(Date date, int days) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_MONTH, days);
        return ca.getTime();
    }

    /**
     * 获得指定时间偏移分钟数后的时间
     * @param date 时间
     * @param minutes 偏移分钟数,正数为增加数,负数为减少数
     * @return
     */
    public static Date addMinuteToDate(Date date, int minutes) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MINUTE, minutes);
        return ca.getTime();
    }

    /**
     * 格式化金额，保留两位小数
     * @param amount 金额
     * @return
     */
    public static String amountFormat(BigDecimal amount) {
        return df.format(amount);
    }

    /**
     * 格式化日期yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        return defaultDatePattern.format(date);
    }

    /**
     * 格式化日期yyyy-MM-dd HH:mm
     * @param date
     * @return
     */
    public static String formatDateMinute(Date date) {
        if (date == null) {
            return null;
        }
        return sdf.format(date);
    }

    /**
     * 格式化时间yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return defaultDateTimePattern.format(date);
    }

    /**
     * 方法会取两个日期当天的最小时间，然后进行计算
     * 方法返回lastDay和fastDay相差的天数
     *  今天大于指定日期返回结果为正，否则为负
     *  返回一个字符串类型
     * @return
     *
     */
    public static Long getBetweenToday(Date fastDay, Date lastDay) {
        fastDay = getMinTime(fastDay);
        lastDay = getMinTime(lastDay);

        Long ltday = fastDay.getTime();
        Long lcday = lastDay.getTime();
        Long between = (lcday - ltday) / 1000;

        Long day = between / (3600 * 24);
        return Long.sum(day, 1);
    }

    /**
      * 获得指定日期的指定整点时间,hour需为整数， 负数计算结果无意义
      * @param date 指定日期
      * @param hour 整点数
      * @return
      */
    public static Date getHourOfDay(Date date, Integer hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY) + hour);
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));

        return calendar.getTime();
    }

    /**
     * 获得上个月的今天
     */
    public static Date getLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获得dt当天最大时间
     * @param dt
     * @return
     */
    public static Date getMaxTime(Date dt) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(dt);
        ca.set(Calendar.HOUR_OF_DAY, ca.getActualMaximum(Calendar.HOUR_OF_DAY));
        ca.set(Calendar.MINUTE, ca.getActualMaximum(Calendar.MINUTE));
        ca.set(Calendar.SECOND, ca.getActualMaximum(Calendar.SECOND));

        return ca.getTime();
    }

    /**
     * 获得dt当天最小时间
     * @param dt
     * @return
     */
    public static Date getMinTime(Date dt) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(dt);
        ca.set(Calendar.HOUR_OF_DAY, ca.getActualMinimum(Calendar.HOUR_OF_DAY));
        ca.set(Calendar.MINUTE, ca.getActualMinimum(Calendar.MINUTE));
        ca.set(Calendar.SECOND, ca.getActualMinimum(Calendar.SECOND));

        return ca.getTime();
    }

    /**
     * 获得本月里的第day天,
     * @param date指定日期
     * @param day第几天，必须要大于1，小于1计算值无意义
     * @return 返回日期的最小时间
     */
    public static Date getMonthDay(Date date, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));

        calendar.add(Calendar.DAY_OF_MONTH, day - 1);
        return calendar.getTime();
    }

    /**
     * 得到本月的第一天
     *
     * @return
     */
    public static Date getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));

        return calendar.getTime();
    }

    /**
     * 得到本月的最后一天
     *
     * @return
     */
    public static String getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(calendar.getTime());
    }

    /**
     * 获得某个日期一个月后的日期
     * @return
     */
    public static Date getNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 生成一个随机数字；
     */
    public final static int getRandomNumber(int max) {
        return getRandomNumber(0, max);
    }

    public final static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return (random.nextInt(max - min) + min);
    }

    /**
     * 从指定的字符列表中生成随机数字串
     */
    public final static String getRandomNumberStr(int length) {
        final String[] s = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };

        if (length < 1) {
            return "";
        }

        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            int position = getRandomNumber(s.length - 1);
            Collections.shuffle(Arrays.asList(s));
            sb.append(s[position]);
        }

        return sb.toString();
    }

    /**
     * 从指定的字符列表中生成随机字符串
     */
    public final static String getRandomString(int length) {
        final String[] s = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "D", "E", "F", "G",
                "H", "J", "L", "M", "N", "Q", "R", "T", "Y" };

        if (length < 1) {
            return "";
        }

        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            int position = getRandomNumber(s.length - 1);
            Collections.shuffle(Arrays.asList(s));
            sb.append(s[position]);
        }

        return sb.toString();
    }

    /**
    * UUID
    * @return
    */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 金额验证，35.3=true|35=true|-23=false
     * @return
     */
    public static boolean isAmount(String match) {
        String regex = "\\d+.?\\d{0,}";
        return Pattern.matches(regex, match);
    }

    /**
     * 数字验证，35.3=false|35=true|-23=false|2 3=false
     * @return
     */
    public static boolean isNumber(String match) {
        String regex = "\\d+";
        return Pattern.matches(regex, match);
    }
}

