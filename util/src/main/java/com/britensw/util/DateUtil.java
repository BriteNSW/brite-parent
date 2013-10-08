package com.britensw.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * @param that {@link Date} to get date of
     * @return A date representing {@param that} with all time values zeroed
     */
    public static Date dateOnly(final Date that) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(that);
        return dateOnly(calendar).getTime();
    }

    /**
     * Mutates {@param calendar}
     *
     * @param calendar {@link Calendar} to mutate.
     * @return {@param calendar} with time values zeroed.
     */
    public static Calendar dateOnly(final Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * @param that {@link Date} to get time of
     * @return A date representing {@param that} with all date values zeroed
     */
    public static Date timeOnly(final Date that) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(that);
        return timeOnly(calendar).getTime();
    }

    /**
     * Mutates {@param calendar}
     *
     * @param calendar {@link Calendar} to mutate.
     * @return {@param calendar} with date values zeroed.
     */
    public static Calendar timeOnly(final Calendar calendar) {
        calendar.set(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 0);
        return calendar;
    }

    /**
     * @param d1
     * @param d2
     * @return true is {@param d1} is before or equal to {@param d2}
     */
    public static boolean beforeInclusive(final Date d1, final Date d2) {
        final Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        final Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return beforeInclusive(c1, c2);
    }

    /**
     * @param c1
     * @param c2
     * @return true is {@param c1} is before or equal to {@param c2}
     */
    public static boolean beforeInclusive(final Calendar c1, final Calendar c2) {
        return c1.before(c2)
                || c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DATE) == c2.get(Calendar.DATE);
    }

    /**
     * @param d1
     * @param d2
     * @return true is {@param d1} is after or equal to {@param d2}
     */
    public static boolean afterInclusive(final Date d1, final Date d2) {
        final Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        final Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return afterInclusive(c1, c2);
    }

    /**
     * @param c1
     * @param c2
     * @return true is {@param c1} is after or equal to {@param c2}
     */
    public static boolean afterInclusive(final Calendar c1, final Calendar c2) {
        return c1.after(c2)
                || c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DATE) == c2.get(Calendar.DATE);
    }

    public static boolean betweenInclusive(final Date test, final Date start, final Date end) {
        return afterInclusive(test, start) && beforeInclusive(test, end);
    }

    public static boolean betweenInclusive(final Calendar test, final Calendar start, final Calendar end) {
        return afterInclusive(test, start) && beforeInclusive(test, end);
    }

    /**
     * @param date   The date to evaluate against
     * @param target The target to test if {@param date} is on the same day of.
     * @return The most recent weekday before or including {@param date}
     */
    public static boolean onSameDay(final Date date, final Date target) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return onSameDay(calendar, target);
    }

    /**
     * Mutates {@param calendar}
     *
     * @param calendar The calendar to find the same day of.
     * @param target The target to test if {@param calendar} is on the same day of.
     * @return {@param calendar} set to the end of the day of {@param target}.
     */
    private static boolean onSameDay(final Calendar calendar, final Date target) {
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
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getMostRecentWeekDay(calendar).getTime();
    }

    /**
     * Mutates {@param calendar}
     *
     * @param calendar The calendar to find the most recent week day of.
     * @return {@param calendar} set to the most recent weekday before or including
     *         {@param calendar} when it is passed in.
     */
    private static Calendar getMostRecentWeekDay(final Calendar calendar) {
        int day;
        while ((day = calendar.get(Calendar.DAY_OF_WEEK)) < Calendar.MONDAY || day > Calendar.FRIDAY) {
            calendar.add(Calendar.HOUR, -24);
        }
        return calendar;
    }

    /**
     * @param date The date to evaluate against
     * @return The second most recent weekday before or including {@param date}
     */
    public static Date getSecondMostRecentWeekDay(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getSecondMostRecentWeekDay(calendar).getTime();
    }

    /**
     * Mutates {@param calendar}
     *
     * @param calendar The calendar to find the second most recent week day of.
     * @return {@param calendar} set to the second most recent weekday before or including
     *         {@param calendar} when it is passed in.
     */
    private static Calendar getSecondMostRecentWeekDay(final Calendar calendar) {
        int day;
        while ((day = calendar.get(Calendar.DAY_OF_WEEK)) < Calendar.MONDAY || day > Calendar.FRIDAY) {
            calendar.add(Calendar.HOUR, -24);
        }
        calendar.add(Calendar.HOUR, -24);
        while ((day = calendar.get(Calendar.DAY_OF_WEEK)) < Calendar.MONDAY || day > Calendar.FRIDAY) {
            calendar.add(Calendar.HOUR, -24);
        }
        return calendar;
    }
}
