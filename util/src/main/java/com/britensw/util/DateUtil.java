package com.britensw.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static Date dateOnly(final Date that) {
        that.setHours(0);
        that.setMinutes(0);
        that.setSeconds(0);
        return that;
    }
    public static Date timeOnly(final Date that) {
        that.setYear(0);
        that.setMonth(0);
        that.setDate(0);
        return that;
    }

    public static boolean beforeInclusive(Date d1, Date d2) {
        return d1.before(d2)
                || d1.getYear() == d2.getYear()
                && d1.getDate() == d2.getDate()
                && d1.getMonth() == d2.getMonth();
    }

    public static boolean afterInclusive(Date d1, Date d2) {
        return d1.after(d2)
                || d1.getYear() == d2.getYear()
                && d1.getDate() == d2.getDate()
                && d1.getMonth() == d2.getMonth();
    }

    public static boolean betweenInclusive(Date test, Date start, Date end) {
        return afterInclusive(test, start) && beforeInclusive(test, end);
    }

    /**
     * @param date The date to evaluate against
     * @return The most recent weekday before or including {@param date}
     */
    public static boolean onSameDay(final Date date, final Date target) {
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        final Date start = calendar.getTime();
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        final Date end = calendar.getTime();
        return target.equals(start) || target.after(start) && target.before(end);
    }

    /**
     * @param date The date to evaluate against
     * @return The most recent weekday before or including {@param date}
     */
    public static Date getMostRecentWeekDay(final Date date) {
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int day;
        while ((day = calendar.get(Calendar.DAY_OF_WEEK)) < Calendar.MONDAY || day > Calendar.FRIDAY) {
            calendar.add(Calendar.HOUR, -24);
        }
        return calendar.getTime();
    }

    /**
     * @param date The date to evaluate against
     * @return The second most recent weekday before or including {@param date}
     */
    public static Date getSecondMostRecentWeekDay(final Date date) {
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int day;
        while ((day = calendar.get(Calendar.DAY_OF_WEEK)) < Calendar.MONDAY || day > Calendar.FRIDAY) {
            calendar.add(Calendar.HOUR, -24);
        }
        calendar.add(Calendar.HOUR, -24);
        while ((day = calendar.get(Calendar.DAY_OF_WEEK)) < Calendar.MONDAY || day > Calendar.FRIDAY) {
            calendar.add(Calendar.HOUR, -24);
        }
        return calendar.getTime();
    }
}
