package com.maple.everyday.wechat.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description 日期工具类
 * @Date 2019/7/15 17:39
 * @Created by 王弘博
 */
public class DateUtils {

    public static final String YYYYMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static Date parse(String f, String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(f);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 在指定时间的基础上增加 days 后的日期
     *
     * @param days
     * @return
     */
    public static Date dayAdd(int days, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + days);
        return calendar.getTime();
    }

    public static Date dayAdd(int days) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + days);
        return calendar.getTime();
    }

    public static String add(int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE, days);//把日期往后增加一天.整数往后推,负数往前移动
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(calendar.getTime());
    }
}
