package iut.cours.lifehealth.model;

import java.io.Serializable;

/**
 * The kinds of humans
 */
public enum Kind implements Serializable {
    WOMAN, MAN;

    /**
     * The toString method to get a string from the Kind
     * @return The string from the Kind
     */
    @Override
    public String toString() {
        return this == Kind.MAN ? "Homme" : "Femme";
    }
}
