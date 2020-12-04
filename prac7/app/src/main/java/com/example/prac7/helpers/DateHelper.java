package com.example.prac7.helpers;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
    public int year;
    public int month;
    public int day;

    // Splits the Java Date object into the day/month/year components
    //
    public static DateHelper convertDateToYearMonthDay(Date date)
    {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        DateHelper result = new DateHelper();
        result.year = year;
        result.month = month;
        result.day = day;
        return result;
    }

    // Creates a Java Date object representing today's date (the time is set as 12am)
    //
    public static Date convertTodayToDate()
    {
        Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        Calendar today = new GregorianCalendar(year, month, day);
        return today.getTime();
    }

    // Creates a Java Date object representing current system date and time.
    //
    public static Date convertNowToDate()
    {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }


    // Takes the year/month/day components and converts it into a Java Date object.
    //
    public static Date convertYearMonthDayToDate(int year, int month, int day)
    {
        Calendar date = new GregorianCalendar(year, month, day);
        return date.getTime();
    }
}
