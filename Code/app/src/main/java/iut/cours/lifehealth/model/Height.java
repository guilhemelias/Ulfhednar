package iut.cours.lifehealth.model;

import java.util.Date;

/**
 * A class for Height data
 */
public class Height extends DataFloat {
    /**
     * A method to add a height to the history data
     * @param date The date that we want to add it
     * @param height The height we want to add, in cm
     */
    public void addHeight(Date date, float height) {
        if(date == null || height < 0) return;
        addFloat(date, height);
    }

    /**
     * A method to add a height to the history data with current date
     * @param height The height we want to add, in cm
     */
    public void addHeight(float height) {
        addFloat(height);
    }

    /**
     * A method to deleteHeight a height from the history data
     * @param date The date that we want to add it
     */
    public void deleteHeight(Date date) {
        if(date == null) return;
        deleteFloat(date);
    }

    /**
     * A method to deleteHeight a height from the history data with current date
     */
    public void deleteHeight() {
        deleteHeight();
    }
}
