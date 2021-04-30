package iut.cours.lifehealth.model.sensor;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 *A class which use the accelerometer sensor data to create a pedometer
 * It implement SensorEventListener, to get the data of the accelerometer, and extends Service to run the pedometer
 * on an other thread that the principal thread
 */
public class Pedometer extends Service implements SensorEventListener {

    private double magnitudePrevious = 0;
    private OnPedometerChangedListener onPedometerChangedListener;

    /**
     * a method for the SensorEventListener which is called every time it detected a new value
     * @param event the data matrix of a three-dimensional vector representing acceleration along each device axis
     * Here is the calculation of the substract between the previous and the new magnitude , when the sensor detect a new value.
     * More the result is high, less the pedometer is sensible. If the result is high enough, that call subscribed classes of the OnPedometerChangedListener
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event != null) {
            float x_acceleration = event.values[0];
            float y_acceleration = event.values[1];
            float z_acceleration = event.values[2];

            double magnitude = Math.sqrt(x_acceleration * x_acceleration + y_acceleration * y_acceleration + z_acceleration * z_acceleration);
            double magnitudeDelta = magnitude - magnitudePrevious;
            magnitudePrevious = magnitude;

            if (magnitudeDelta > 5) {
                onPedometerChangedListener.onPedometerChanged();
            }
        }
    }

    /**
     * Called when the accuracy of a sensor has changed
     * @param sensor the sensor
     * @param accuracy the precision
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * a method to set the OnPedometerChangedListener on this class
     * @param onPedometerChangedListener the OnPedometerChangedListener
     */
    public void setOnPedometerChangedListener(OnPedometerChangedListener onPedometerChangedListener){
        this.onPedometerChangedListener = onPedometerChangedListener;
    }

    /**
     * provides the getService() method for clients to retrieve the current instance of LocalService.
     * This allows clients to call public methods in the service
     * @param intent the Intent
     * @return null
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
