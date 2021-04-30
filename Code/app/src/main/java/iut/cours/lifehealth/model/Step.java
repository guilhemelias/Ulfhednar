package iut.cours.lifehealth.model;

import java.util.Date;

/**
 * A class for steps data
 */
public class Step extends DataInt {
    /**
     * A method to add a number of steps to the history data
     * @param date The date that we want to add it
     * @param number The number of steps we want to add
     */
    public void addStep(Date date, int number) {
        if(date == null || number < 0) return;
        addInteger(date, number);
    }

    /**
     * A method to add a number of steps to the history data with current date
     * @param number The number of steps we want to add, in kg
     */
    public void addStep(int number) {
        addStep(new Date(System.currentTimeMillis()), number);
    }

    /**
     * A method to add a step to the current date
     */
    public void add1Step() {
        addStep(1);
    }

    /**
     * A method to delete a number of steps from the history data
     * @param date The date that we want to add it
     * @param number The number of steps we want to add
     */
    public void deleteStep(Date date, int number) {
        if(date == null || number < 0) return;
        deleteInteger(date, number);
    }

    /**
     * A method to delete a number of steps from the history data with current date
     * @param number The number of steps we want to add, in kg
     */
    public void deleteStep(int number) {
        deleteStep(new Date(System.currentTimeMillis()), number);
    }

    /**
     * A method to delete a step from the current date
     */
    public void delete1Step() {
        deleteStep(1);
    }

}
