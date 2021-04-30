package iut.cours.lifehealth.model.utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A class with some utils methods for Date
 */
public class DateUtil {
    /**
     * A method to remove the time (Hours, minutes, seconds, milliseconds) from a Date
     * @param date The Date from which we want to remove the time
     * @return The Date without the time
     */
    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * A method to calculate the age between the date and today's date
     * @param date The date from which we want the date
     * @return The age between the date and today's date
     */
    public static int getAge(Date date) {
        Calendar todaysCalendar = Calendar.getInstance();
        Calendar birthdayCalendar = Calendar.getInstance();
        birthdayCalendar.setTime(date);

        int age = todaysCalendar.get(Calendar.YEAR) - birthdayCalendar.get(Calendar.YEAR);
        if(todaysCalendar.get(Calendar.DAY_OF_YEAR) < birthdayCalendar.get(Calendar.DAY_OF_YEAR)) age -= 1;

        return age;
    }

    /**
     * A method to get a Date from a string, with the format we want
     * @param stringDate The string of the date that we want
     * @param format The format of the string. This is the same that for the class SimpleDateFormat
     * @return The Date get by the string, or null if something went wrong
     */
    public static Date getDateFromString(String stringDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.FRANCE);
        try {
            return sdf.parse(stringDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * A method to get a Date from a string, with the format "dd/MM/yyyy"
     * @param stringDate The string of the date that we want
     * @return The Date get by the string, or null if something went wrong
     */
    public static Date getDateFromString(String stringDate) {
        return getDateFromString(stringDate, "dd/MM/yyyy");
    }

    /**
     * A method to get a string from a Date, with the format we want
     * @param date The date from which we want to the string
     * @param format The string format that we want
     * @return The string get by the date
     */
    public static String getStringFromDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.FRANCE);

        return sdf.format(date);
    }

    /**
     * A method to get a string from a Date, with the format "dd/MM/yyyy"
     * @param date The date from which we want to the string
     * @return The string get by the date
     */
    public static String getStringFromDate(Date date) {
        return getStringFromDate(date, "dd/MM/yyyy");
    }

    /**
     * A method to get a string from milliseconds date (Date.getTime()), with the format we want
     * @param millis The milliseconds date (Date.getTime()) from which we want to the string
     * @param format The string format that we want
     * @return The string get by the milliseconds date
     */
    public static String getStringFromDateMillis(float millis, String format) {
        return getStringFromDate(new Date((long) millis), format);
    }

    /**
     * A method to get a string from milliseconds date (Date.getTime()), with the format "dd/MM/yyyy"
     * @param millis The milliseconds date (Date.getTime()) from which we want to the string
     * @return The string get by the milliseconds date
     */
    public static String getStringFromDateMillis(float millis) {
        return getStringFromDateMillis(millis,"dd/MM/yyyy");
    }

    /**
     * A method to get a Date with years, months, days, hours, minutes, seconds and milliseconds set to 0.
     * @return A Date with years, months, days, hours, minutes, seconds and milliseconds set to 0.
     */
    public static Date getDefaultDate() {
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.set(Calendar.YEAR, 0);
        currentCalendar.set(Calendar.MONTH, 0);
        currentCalendar.set(Calendar.DAY_OF_MONTH, 0);
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);
        return currentCalendar.getTime();
    }

    /**
     * A method to get some values from a Date
     * @param date The Date from which we want the value
     * @param value The value we want, set in Calender, such as years, months, days, hours, minutes, seconds and milliseconds.
     * @return The value wanted
     */
    public static int getValueFromDate(Date date, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(value);
    }

    /**
     * A method to add some values to a Date
     * @param date The Date we want to modify
     * @param value The value we want to modify, set in Calender, such as years, months, days, hours, minutes, seconds and milliseconds.
     * @param number The number that we want to add
     * @return The new Date modified
     */
    public static Date addValueToDate(Date date, int value, int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(value, number);
        return calendar.getTime();
    }
}
