package iut.cours.lifehealth.view.fragments;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import iut.cours.lifehealth.R;
import iut.cours.lifehealth.model.Manager;
import iut.cours.lifehealth.model.sensor.OnPedometerChangedListener;
import iut.cours.lifehealth.model.sensor.Pedometer;
import iut.cours.lifehealth.view.DialogFragment.SleepDialog;
import iut.cours.lifehealth.view.DialogFragment.StepGoalDialog;
import iut.cours.lifehealth.view.codebehind.ActiviteAccueil;


/**
 * The home Fragment, to display the user data
 */
public class HomeFragment extends Fragment implements OnPedometerChangedListener {

    public static final String TAG = "StepGoalDialog";

    private Manager manager;
    private Pedometer myPedometer;
    private TextView textViewNbStep;
    private TextView textViewSleep;
    private TextView textViewNbGlassWater;
    private TextView textWeight;
    private Context mContext ;
    private LinearLayout mRelativeLayout;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    /**
     * the onViewCreated method of the Home Fragment
     * It show the one way binded data
     * It subscribed click to methods
     * @param view the view
     * @param savedInstanceState the saved data
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        manager = ((ActiviteAccueil) view.getContext()).getManager();

        myPedometer= ((ActiviteAccueil) view.getContext()).getPedometer();
        myPedometer.setOnPedometerChangedListener(this);


        mContext = getActivity().getApplicationContext();
        mRelativeLayout = (LinearLayout) view.findViewById(R.id.linear_layout_body_application);


        textViewNbStep = ((TextView) view.findViewById(R.id.text_view_detail_number_steps));
        textViewNbStep.setText(Integer.toString(manager.getProfile().getSteps().getRecent()));
        textViewNbStep.setOnClickListener(this::clickStepDetail);

        textViewSleep =  ((TextView) view.findViewById(R.id.text_view_hours_sleep));
        textViewSleep.setText(manager.getProfile().getSleep().getStringCurrent());
        textViewSleep.setOnClickListener(this::clickSleepDetail);

        textViewNbGlassWater = ((TextView) view.findViewById(R.id.text_view_number_glass_drink));
        textViewNbGlassWater.setText(Integer.toString(manager.getProfile().getHydratation().getRecent()));

        textWeight = ((TextView) view.findViewById(R.id.text_view_weight_user));
        textWeight.setText(Float.toString(manager.getProfile().getWeight().getRecent()));

        ((Button) view.findViewById(R.id.button_add_glass_drink)).setOnClickListener(this::clickIncreaseWater);
        ((Button) view.findViewById(R.id.button_remove_glass_drink)).setOnClickListener(this::clickDecreaseWater);
    }

    /**
     * A method to update the string in the textViewSleep
     */
    public void updateTimeSleep() {
        textViewSleep.setText(manager.getProfile().getSleep().getStringCurrent());
    }

    /**
     * a method to open a dialog to view the step detail
     * @param view the view for getting the event
     */
    private void clickStepDetail(View view) {
        StepGoalDialog stepGoalDialog = new StepGoalDialog();
        stepGoalDialog.show(((ActiviteAccueil) view.getContext()).getSupportFragmentManager(), TAG);
    }

    /**
     * a method to open a dialog to view the sleep detail
     * @param view the view for getting the event
     */
    private void clickSleepDetail(View view) {
      SleepDialog sleepDialog = new SleepDialog();
      sleepDialog.show(((ActiviteAccueil) view.getContext()).getSupportFragmentManager(), TAG);


    }

    /**
     * a method which subscibe the HomeFragment to the the Pedometer when the OnsensorListener is called and the result is validate
     */
    @Override
    public void onPedometerChanged() {
        manager.getProfile().getSteps().add1Step();
        textViewNbStep.setText(Integer.toString(manager.getProfile().getSteps().getRecent()));
    }

    /**
     * a method to increase the number of  glasses drunk
     * @param view the view for getting the event
     */
    private void clickIncreaseWater(View view) {
        manager.getProfile().getHydratation().add1GlassDrink();
        textViewNbGlassWater.setText(Integer.toString(manager.getProfile().getHydratation().getRecent()));
    }

    /**
     * a method to decrease the number of  glasses drunk
     * @param view the view for getting the event
     */
    private void clickDecreaseWater(View view) {
        manager.getProfile().getHydratation().delete1GlassDrink();
        textViewNbGlassWater.setText(Integer.toString(manager.getProfile().getHydratation().getRecent()));
    }
}
