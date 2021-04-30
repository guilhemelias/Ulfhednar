package iut.cours.lifehealth.model;

import java.util.Date;

/**
 * A class for Weight data
 */
public class Weight extends DataFloat {
    /**
     * A method to add a weight to the history data
     * @param date The date that we want to add it
     * @param weight The weight we want to add, in kg
     */
    public void addWeight(Date date, float weight) {
        if(date == null || weight < 0) return;
        addFloat(date, weight);
    }

    /**
     * A method to add a weight to the history data with current date
     * @param weight The weight we want to add, in kg
     */
    public void addWeight(float weight) {
        addWeight(new Date(System.currentTimeMillis()), weight);
    }

    /**
     * A method to delete a weight from the history data
     * @param date The date that we want to add it
     */
    public void deleteWeight(Date date) {
        if(date == null) return;
        deleteFloat(date);
    }

    /**
     * A method to delete a weight from the history data with current date
     */
    public void deleteWeight() {
        deleteWeight(new Date(System.currentTimeMillis()));
    }
}
