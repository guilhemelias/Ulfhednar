package iut.cours.lifehealth.model;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import iut.cours.lifehealth.model.utils.DateUtil;

/**
 * A class for Integer data
 */
public class DataInt implements Data {
    /**
     * The history of data
     */
    private Map<Date, Integer> history;

    /**
     * The constructor of the class DataInt
     */
    public DataInt() {
        setHistory(new TreeMap<>());
    }

    /**
     * The getter of the history of data
     * @return The history of data
     */
    public Map<Date, Integer> getHistory() {
        return history;
    }

    /**
     * The setter of the history of data
     * @param history The history of data
     */
    private void setHistory(Map<Date, Integer> history) {
        this.history = history;
    }

    /**
     * A method to add an integer to the data history
     * It add the value to the current date key
     * @param date The date which we want the data
     * @param i The data we want to add
     */
    public void addInteger(Date date, int i) {
        if(date == null) return;

        date = DateUtil.removeTime(date);

        Integer number = history.get(date);
        number = number == null ? 0 : number;
        number += i;
        number = number < 0 ? 0 : number;

        history.put(date, number);
    }

    /**
     * A method to add an integer to the data history with current date
     * It add the value to the current date key
     * @param i The data we want to add
     */
    public void addInteger(int i) {
        addInteger(new Date(System.currentTimeMillis()), i);
    }

    /**
     * A method to delete an integer from the data history
     * It remove the value from the current date key
     * @param date The date which we want to delete the data
     * @param i The data we want to delete
     */
    public void deleteInteger(Date date, int i) {
        addInteger(date, i * -1);
    }

    /**
     * A method to delete an integer from the data history with current date
     * It remove the value from the current date key
     * @param i The data we want to delete
     */
    public void deleteInteger(int i) {
        deleteInteger(new Date(System.currentTimeMillis()), i);
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
     * To know the average of the data
     * @return The average of the data
     */
    @Override
    public float average() {
        return total() / history.values().size();
    }

    /**
     * To know the total of the data
     * @return The total of the data
     */
    @Override
    public float total() {
        float sum = 0;
        for(float t : history.values()) {
            sum += t;
        }
        return sum;
    }

    /**
     * A method to get the more recent data in the history
     * @return The more recent data in the history
     */
    public int getRecent() {
        if (history.size() == 0) return 0;
        return (int) history.values().toArray()[history.size() - 1];
    }

    /**
     * A method to get the data of the date in the history
     * @param date The date from which we want the date. If null, from today date
     * @return The data of the date in the history
     */
    public int getFromDate(@Nullable Date date) {
        if(date == null) return getFromDate(new Date(System.currentTimeMillis()));

        date = DateUtil.removeTime(date);

        Integer f = history.get(date);
        return f == null ? 0 : f;
    }

    /**
     * A method to get the data of today's date in the history
     * @return The data of today's date in the history
     */
    public int getCurrent() {
        return getFromDate(null);
    }
}
