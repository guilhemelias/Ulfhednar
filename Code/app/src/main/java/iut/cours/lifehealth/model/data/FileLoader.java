package iut.cours.lifehealth.model.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * A class to load Objects from a File
 */
public class FileLoader implements ILoad {
    /**
     * A method to load an Object from a file
     * @param file The InputStream from where we want to load the Object
     * @return The Object that as been find, or null if the file doesn't exist
     */
    @Nullable
    @Override
    public Object load(@NonNull InputStream file) {
        Object objectLoaded = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(file)) {
            objectLoaded = inputStream.readObject();
        } catch (FileNotFoundException e) {
          //  e.printStackTrace();
            Log.e("FileNotFoundException", "Loading with no file found. If for Profile that's normal");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objectLoaded;
    }
}
