package iut.cours.lifehealth.model;

import java.io.Serializable;

/**
 * An interface to have some information about some data
 */
public interface Data extends Serializable {
    /**
     * This method remove all data of more than one year
     */
    void annualUpdate();

    /**
     * To know the average of the data
     * @return The average of the data
     */
    float average();

    /**
     * To know the total of the data
     * @return The total of the data
     */
    float total();
}
