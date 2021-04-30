package iut.cours.lifehealth.model.data;

import java.io.InputStream;

/**
 * An interface to define a class that load Objects from a File
 */
public interface ILoad {
    /**
     * A method to load an Object from a file
     * @param file The InputStream from where we want to load the Object
     * @return The Object that as been find, or null if the file doesn't exist
     */
    Object load(InputStream file);
}
