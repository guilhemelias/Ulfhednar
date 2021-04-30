package iut.cours.lifehealth.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import iut.cours.lifehealth.model.data.FileLoader;
import iut.cours.lifehealth.model.data.FileSaver;
import iut.cours.lifehealth.model.data.ILoad;
import iut.cours.lifehealth.model.data.ISave;

/**
 * The class to manage a profile in the application
 */
public class Manager {
    /**
     * The profile of the user
     */
    private Profile profile;
    /**
     * The instance of ILoad that will be used by the Manager class to load the profile from a file
     */
    private final ILoad loader = new FileLoader();
    /**
     * The instance of ISave that will be used by the Manager class to save the profile in a file
     */
    private final ISave saver = new FileSaver();
    /**
     * The name of the file where will be save the profile of the use
     */
    private final String FILE_PROFILE = "profile";
    /**
     * The Context from which we create the Manager
     */
    private final Context context;

    /**
     * The constructor of the class Manager
     * @param context The Context from which we create the Manager. It's useful to save and load the profile
     */
    public Manager(Context context) {
        this.context = context;
        loadManagerProfile();
    }

    /**
     * The getter of the ILoad of the Manager
     * @return The ILoad of the Manager
     */
    public ILoad getLoader() {
        return loader;
    }

    /**
     * The getter of the ISave of the Manager
     * @return The ISave of the Manager
     */
    public ISave getSaver() {
        return saver;
    }

    /**
     * The getter of the name of the file where will be save the profile of the use
     * @return The name of the file where will be save the profile of the use
     */
    public String getFILE_PROFILE() {
        return FILE_PROFILE;
    }

    /**
     * The getter of the Context from which we create the Manager
     * @return The Context from which we create the Manager
     */
    public Context getContext() {
        return context;
    }

    /**
     * The getter of the profile of the Manager
     * @return The profile of the Manager
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * The setter of the profile of the Manager
     * @param profile The profile of the Manager
     */
    private void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * The method to save the Profile of the Manager in a file
     * @return Return true if the profile as been correctly save, false otherwise
     */
    public boolean saveManagerProfile() {
        try(FileOutputStream fos = context.openFileOutput(FILE_PROFILE, Context.MODE_PRIVATE)) {
           // File file = new File(context.getFilesDir(), FILE_PROFILE);
            saveProfile(fos, getProfile());
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * The method to load the Profile in the Manager from a file
     */
    public void loadManagerProfile() {
        Profile profile = null;

        try {
            Log.d("LOADMYMANAGER", "Ici loading manager : loadManagerProfile");
            profile = loadProfile(context.openFileInput(FILE_PROFILE));
        } catch (FileNotFoundException ignored) { }

        if(profile == null) {
            profile = new Profile();
        }

        profile.annualUpdate();
        setProfile(profile);
    }

    /**
     * A method to save a Profile in a file
     * @param file The OutputStream where we want to save the Profile
     * @param profile The Profile that we want to save
     */
    public void saveProfile(@NonNull OutputStream file, Profile profile) {
        saver.save(file, profile);
    }

    /**
     * A method to load a Profile from a file
     * @param file The InputStream where we want to load the Profile
     * @return The Profile that as been find, or null if the file doesn't exist
     */
    public Profile loadProfile(@NonNull InputStream file) {
        return (Profile) loader.load(file);
    }
}
