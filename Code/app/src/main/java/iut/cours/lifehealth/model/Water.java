package iut.cours.lifehealth.model;

import java.util.Date;

/**
 * A class for water data
 */
public class Water extends DataInt {
    /**
     * A method to add a number of glass drink to the history data
     * @param date The date that we want to add it
     * @param number The number of steps we want to add
     */
    public void addGlassDrink(Date date, int number) {
        if(date == null || number < 0) return;
        addInteger(date, number);
    }

    /**
     * A method to add a number of glass drink to the history data with current date
     * @param number The number of steps we want to add, in kg
     */
    public void addGlassDrink(int number) {
        addGlassDrink(new Date(System.currentTimeMillis()), number);
    }

    /**
     * A method to add a glass drink to the current date
     */
    public void add1GlassDrink() {
        addGlassDrink(1);
    }

    /**
     * A method to delete a number of glass drink from the history data
     * @param date The date that we want to add it
     * @param number The number of steps we want to add
     */
    public void deleteGlassDrink(Date date, int number) {
        if(date == null || number < 0) return;
        deleteInteger(date, number);
    }

    /**
     * A method to delete a number of glass drink from the history data with current date
     * @param number The number of steps we want to add, in kg
     */
    public void deleteGlassDrink(int number) {
        deleteGlassDrink(new Date(System.currentTimeMillis()), number);
    }

    /**
     * A method to delete a glass drink from the current date
     */
    public void delete1GlassDrink() {
        deleteGlassDrink(1);
    }
}
