package iut.cours.lifehealth.model.data;

import java.io.OutputStream;

/**
 * An interface to define a class that save Objects in a File
 */
public interface ISave {
    /**
     * A method to save an Object in a file
     * @param file The OutputStream where we want to save the Object
     * @param o The Object that we want to save
     */
    void save(OutputStream file, Object o);
}
