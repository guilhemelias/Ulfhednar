package iut.cours.lifehealth.model.data;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * A class to save Objects in a File
 */
public class FileSaver implements ISave {
    /**
     * A method to save an Object in a file
     * @param file The OutputStream where we want to save the Object
     * @param o The Object that we want to save
     */
    @Override
    public void save(@NonNull OutputStream file, @NonNull Object o) {

        try (ObjectOutputStream outputStream = new ObjectOutputStream(file)) {
            outputStream.writeObject(o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
