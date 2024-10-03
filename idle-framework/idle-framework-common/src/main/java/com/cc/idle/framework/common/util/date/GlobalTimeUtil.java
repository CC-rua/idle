package com.cc.idle.framework.common.util.date;

import lombok.Setter;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 全局时间工具
 */
public class GlobalTimeUtil {
    @Setter
    private static long deviationTimeMs = 0;//偏移时长

    public static long getNow() {
        return System.currentTimeMillis() + deviationTimeMs;
    }

    public static int getNowSec() {
        return (int) ((System.currentTimeMillis() + deviationTimeMs) / 1000);
    }

    public static Calendar getCalendarInstance() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getNow());
        return calendar;
    }

    /**
     * 偏移一天
     */
    public static void addDay() {
        deviationTimeMs += 86400000;
    }

    /**
     * 前往下一天跨天前
     */
    public static void toNextDay0() {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(getNow());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        deviationTimeMs = calendar.getTimeInMillis() - currentTimeMillis;
    }

    /**
     * 前往下个月跨天前
     */
    public static void toNextMonth0() {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getNow());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        deviationTimeMs = calendar.getTimeInMillis() - currentTimeMillis;
    }

    /**
     * 两个日期是否跨周
     *
     * @param timestamp1
     * @param timestamp2
     * @return
     */
    public static boolean crossesMonday(long timestamp1, long timestamp2) {
        // 创建 Calendar 对象，并设置为第一个时间戳的日期
        Calendar calendar1 = GlobalTimeUtil.getCalendarInstance();
        calendar1.setTimeInMillis(timestamp1);
        calendar1.setFirstDayOfWeek(Calendar.MONDAY);

        // 创建 Calendar 对象，并设置为第二个时间戳的日期
        Calendar calendar2 = GlobalTimeUtil.getCalendarInstance();
        calendar2.setTimeInMillis(timestamp2);
        calendar2.setFirstDayOfWeek(Calendar.MONDAY);

        //检查两个日期是否在同一年
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);

        // 检查两个时间戳是否位于同一个星期的不同日期
        int week1 = calendar1.get(Calendar.WEEK_OF_YEAR);
        int week2 = calendar2.get(Calendar.WEEK_OF_YEAR);

        //跨了不止一年
        if (week1 == week2 && year1 != year2) {
            return true;
        }

        // 如果两个时间戳不在同一周，或者它们的年份不同
        return (week1 != week2);
    }

    /**
     * 两个日期是否跨月
     *
     * @param timestamp1
     * @param timestamp2
     * @return
     */
    public static boolean crossesMonth(long timestamp1, long timestamp2) {
        // 创建 Calendar 对象，并设置为第一个时间戳的日期
        Calendar calendar1 = GlobalTimeUtil.getCalendarInstance();
        calendar1.setTimeInMillis(timestamp1);
        calendar1.setFirstDayOfWeek(Calendar.MONDAY);

        // 创建 Calendar 对象，并设置为第二个时间戳的日期
        Calendar calendar2 = GlobalTimeUtil.getCalendarInstance();
        calendar2.setTimeInMillis(timestamp2);
        calendar2.setFirstDayOfWeek(Calendar.MONDAY);

        //检查两个日期是否在同一年
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);

        // 检查两个时间戳是否位于同一个星期的不同日期
        int month1 = calendar1.get(Calendar.MONTH);
        int month2 = calendar2.get(Calendar.MONTH);

        //跨了不止一年
        if (month1 == month2 && year1 != year2) {
            return true;
        }

        // 如果两个时间戳不在同一月，或者它们的年份不同
        return (month1 != month2);
    }

    /**
     * 获取当月天数
     *
     * @return
     */
    public static int getDayOfMonth() {
        Calendar calendar = GlobalTimeUtil.getCalendarInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
