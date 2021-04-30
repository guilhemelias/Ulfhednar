package iut.cours.lifehealth.view.codebehind;

import android.content.Intent;
import android.hardware.Sensor;

import android.hardware.SensorManager;

import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;

import iut.cours.lifehealth.R;

import iut.cours.lifehealth.model.Manager;

import iut.cours.lifehealth.model.sensor.Pedometer;

import iut.cours.lifehealth.view.fragments.GraphFragment;

import iut.cours.lifehealth.view.fragments.DisplayProfileFragment;

import iut.cours.lifehealth.view.fragments.HomeFragment;

/**
 * The principal activity
 */
public class ActiviteAccueil extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    public Sensor sensor;
    public SensorManager sensorManager;
    public Pedometer pedometer = new Pedometer();
    private Fragment fragment = new HomeFragment();
    private Manager manager;
    private String fragmentTag = "HomeFragment";


    /**
     * the onCreate method of the principal activity
     * This is here that the  the default view is set  on the HomeFragment
     * or the current fragment if the device rotate,
     * the bottom navigation is got view and the listener navigation is attached,
     * the manager is instantiate to bind the model to the view,
     * the sensor is instantiate and begin to register with the Service system
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.manager = new Manager(getApplicationContext());

        setContentView(R.layout.activite_acceuil);

        //Commencer a detecter

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        registerDetector();

        startService(new Intent(ActiviteAccueil.this,Pedometer.class));

        if(savedInstanceState != null) {

            fragment=getSupportFragmentManager().getFragment(savedInstanceState,"Key");

        }

        loadFragment(fragment);

        //getting

        BottomNavigationView navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(this);

    }

    /**
     *the onStart method of the principal activity
     * This is here that the serialized data is got  to restore the memory
     */
    @Override
    protected void onStart() {
        super.onStart();
        manager.loadManagerProfile();
    }

    /**
     * a method to load the fragment chosen by the user by clicking on the menu item
     * @param item the menu item in the navbar
     * @return the fragment to load
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.home:

                fragment = new HomeFragment();
                fragmentTag = "HomeFragment";
                break;

            case R.id.profile:

                fragment = new DisplayProfileFragment();
                fragmentTag = "DisplayProfileFragment";
//                fragment = new ModifyProfileFragment();

                break;

            case R.id.graphics:

                fragment = new GraphFragment();
                fragmentTag = "AchievmentFragment";
                break;

        }

        return loadFragment(fragment);

    }

    /**
     * a method to load a fragment
     * @param fragment the fragment to load
     * @return the fragment loaded
     */
    public boolean loadFragment(Fragment fragment) {

        //switching fragment

        if (fragment != null) {

            getSupportFragmentManager()

                    .beginTransaction()

                    .replace(R.id.fragment_container, fragment, fragmentTag)

                    .commit();

            this.fragment = fragment;

            return true;

        }

        return false;

    }




    /**
     * a method for call the SupportFragmentManager to load the fragment
     * @param outState used for passing data
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, "Key", fragment);

    }


    /**
     *a method to register the accelerometer sensor
     */
    private void registerDetector(){

        sensor = sensorManager.getDefaultSensor(

                Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(pedometer,

                sensor,

                SensorManager.SENSOR_DELAY_NORMAL);

    }


    /**
     * The getter of the manager
     * @return the manager
     */
    public Manager getManager() {

        return manager;

    }

    /**
     * The getter of the Pedometer
     * @return the pedometer
     */
    public Pedometer getPedometer() {
        return pedometer;
    }

    /**
     * the onStop method of the principal activity
     * This is here that the data is serialized for save the memory
     */
    @Override
    protected void onStop() {
        manager.saveManagerProfile();
        stopService(new Intent(ActiviteAccueil.this,Pedometer.class));
        super.onStop();
    }





}


