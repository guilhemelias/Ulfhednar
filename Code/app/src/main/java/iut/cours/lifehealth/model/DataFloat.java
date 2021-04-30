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
 * A class for Float data
 */
public class DataFloat implements Data {
    /**
     * The history of data
     */
    private Map<Date, Float> history;

    /**
     * The constructor of the class DataFloat
     */
    public DataFloat() {
        setHistory(new TreeMap<>());
    }

    /**
     * The getter of the history of data
     * @return The history of data
     */
    public Map<Date, Float> getHistory() {
        return history;
    }

    /**
     * The setter of the history of data
     * @param history The history of data
     */
    private void setHistory(Map<Date, Float> history) {
        this.history = history;
    }

    /**
     * A method to add a float to the data history
     * It replace the date key
     * @param date The date which we want the data
     * @param f The data we want to add
     */
    public void addFloat(Date date, float f) {
        if(date == null) return;

        date = DateUtil.removeTime(date);

        history.put(date, f);
    }

    /**
     * A method to add a float to the data history with current date
     * It replace the date key
     * @param f The data we want to add
     */
    public void addFloat(float f) {
        addFloat(new Date(System.currentTimeMillis()), f);
    }

    /**
     * A method to delete a float from the data history
     * It removes the date key
     * @param date The date which we want to delete the data
     */
    public void deleteFloat(Date date) {
        if(date == null) return;

        date = DateUtil.removeTime(date);

        history.remove(date);
    }

    /**
     * A method to delete a float from the data history with current date
     * It removes the date key
     */
    public void deleteFloat() {
        deleteFloat(new Date(System.currentTimeMillis()));
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
    public float getRecent() {
        if (history.size() ==0) return 0;
        return (float) history.values().toArray()[history.size() - 1];
    }

    /**
     * A method to get the data of the date in the history
     * @param date The date from which we want the date. If null, from today date
     * @return The data of the date in the history
     */
    public float getFromDate(@Nullable Date date) {
        if(date == null) return getFromDate(new Date(System.currentTimeMillis()));

        date = DateUtil.removeTime(date);

        Float f = history.get(date);
        return f == null ? 0 : f;
    }

    /**
     * A method to get the data of today's date in the history
     * @return The data of today's date in the history
     */
    public float getCurrent() {
        return getFromDate(null);
    }
}
