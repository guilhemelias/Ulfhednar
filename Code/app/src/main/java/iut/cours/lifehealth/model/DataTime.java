package iut.cours.lifehealth.model;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import iut.cours.lifehealth.model.utils.DateUtil;

/**
 * A class for Date data, especially for hours and minutes
 */
public class DataTime implements Data {
    /**
     * The history of data
     */
    private Map<Date, Date> history;

    /**
     * The constructor of the class DataTime
     */
    public DataTime() {
        setHistory(new TreeMap<>());
    }

    /**
     * The getter of the history of data
     * @return The history of data
     */
    public Map<Date, Date> getHistory() {
        return history;
    }

    /**
     * The setter of the history of data
     * @param history The history of data
     */
    private void setHistory(Map<Date, Date> history) {
        this.history = history;
    }

    /**
     * A method to add a Date to the data history
     * It replace the current value to the current date key
     * @param date The date which we want the data
     * @param time The data we want to add
     */
    public void addTime(Date date, Date time) {
        if(date == null) return;

        date = DateUtil.removeTime(date);

        history.put(date, time);
    }



    /**
     * A method to add hours on the current date
     * @param number The number of hours we want to add
     */
    public void addHours(int number) {
        Date todaysDate = DateUtil.removeTime(new Date(System.currentTimeMillis()));
        Date currentTime = history.get(todaysDate);
        Log.d("ADDHOURS", "First");
        if(currentTime == null) {
            currentTime = DateUtil.getDefaultDate();
            Log.d("IFADDHOURS", "Second");
        }
        currentTime = DateUtil.addValueToDate(currentTime, Calendar.HOUR, number);
        addTime(todaysDate, currentTime);
    }




    /**
     * A method to add minutes on the current date
     * @param number The number of minutes we want to add
     */
    public void addMinutes(int number) {
        Date todaysDate = DateUtil.removeTime(new Date(System.currentTimeMillis()));
        Date currentTime = history.get(todaysDate);
        if(currentTime == null) {
            currentTime = DateUtil.getDefaultDate();
        }
        currentTime = DateUtil.addValueToDate(currentTime, Calendar.MINUTE, number);
        history.put(todaysDate, currentTime);
    }


    /**
     * A method to delete a Date from the data history
     * It remove the value from the current date key
     * @param date The date which we want to delete the data
     */
    public void deleteTime(Date date) {
        if(date == null) return;

        date = DateUtil.removeTime(date);

        history.remove(date);
    }

    /**
     * A method to delete a Date from the data history with the current date
     * It remove the value from the current date key
     */
    public void deleteTime() {
        deleteTime(new Date(System.currentTimeMillis()));
    }

    /**
     * This method remove all data of more than one year
     */
    @Override
    public void annualUpdate() {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.YEAR, -1);
        Date previousYear = cal.getTime();

        Iterator<Date> dateIterator = history.keySet().iterator();
        Date d;
        while (dateIterator.hasNext()) {
            d = dateIterator.next();
            if(d.before(previousYear)) { //If the date is more than one year ago
                history.remove(d);
            } else {
                break; //We are in a TreeMap, so dates are ordered
            }
        }
    }

    /**
     * To know the average of the data, in hours
     * @return The average of the data, in hours
     */
    @Override
    public float average() {
        return total() / history.values().size();
    }

    /**
     * To know the total of the data, in hours
     * @return The total of the data, in hours
     */
    @Override
    public float total() {
        float sum = 0;
        for(Date d : history.values()) {
            sum += DateUtil.getValueFromDate(d, Calendar.HOUR) * 60 + DateUtil.getValueFromDate(d, Calendar.MINUTE);
        }
        return sum / 60; //The approximate number of hours sleep
    }



    /**
     * A method to get the data of the date in the history
     * @param date The date from which we want the date. If null, from today date
     * @return The data of the date in the history
     */
    public Date getFromDate(@Nullable Date date) {
        if(date == null) return getFromDate(DateUtil.removeTime(new Date(System.currentTimeMillis())));

        date = DateUtil.removeTime(date);

        Date d = history.get(date);
        return d == null ? DateUtil.getDefaultDate() : d;
    }

    /**
     * A method to get the data of today's date in the history
     * @return The data of today's date in the history
     */
    public Date getCurrent() {
        return getFromDate(null);
    }

    /**
     * A method to get the sleep, in a String, of today's date in the history
     * @return The sleep, in a String, of today's date in the history
     */
    public String getStringCurrent() {
        return DateUtil.getStringFromDate(getCurrent(), "HH:mm");
    }

    public String getStringCurrentHours() {
        return DateUtil.getStringFromDate(getCurrent(), "HH");
    }

    public String getStringCurrentMinutes() {
        return DateUtil.getStringFromDate(getCurrent(), "mm");
    }
}
