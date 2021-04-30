package iut.cours.lifehealth.view.DialogFragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;
import java.util.Date;

import iut.cours.lifehealth.R;
import iut.cours.lifehealth.model.Kind;
import iut.cours.lifehealth.model.Manager;
import iut.cours.lifehealth.view.codebehind.ActiviteAccueil;
import iut.cours.lifehealth.view.fragments.DisplayProfileFragment;
import iut.cours.lifehealth.view.fragments.HomeFragment;

/**
 * A dialog Fragment to put the user's our sleep during the day
 */
public class SleepDialog extends DialogFragment {

    private Manager manager;

    private ImageView closeSleepDialog;


    /**
     * the onCreateView method of the SleepDialog Fragment
     * It inflate the view
     * @param inflater the inflater
     * @param container the View to inflate
     * @param savedInstanceState the bundle
     * @return the view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sleep,container,false);
        return view;

    }

    /**
     * the onViewCreated method of the SleepDialog Fragment
     * It show the one way binded data
     * It subscribed click to methods for saving the data
     * @param view the view
     * @param savedInstanceState the memorized data
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manager = ((ActiviteAccueil) getActivity()).getManager();

        closeSleepDialog =  ((ImageView) view.findViewById(R.id.close_sleep_dialog));
        closeSleepDialog.setOnClickListener(this::clickSleepClosing);

        ((EditText) getView().findViewById(R.id.text_view_hours_sleep)).setText(String.valueOf(manager.getProfile().getSleep().getStringCurrentHours()));
        ((EditText) getView().findViewById(R.id.text_view_minutes_sleep)).setText(String.valueOf(manager.getProfile().getSleep().getStringCurrentMinutes()));

        ((TextView) view.findViewById(R.id.sleep_dialog_saving)).setOnClickListener(this::clickSaveSleepButton);


    }

    /**
     * the onStart method of the SleepDialog Fragment
     *  It instanciate the properties of the Dialog
     */
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog!= null){
            int width = (ViewGroup.LayoutParams.MATCH_PARENT);
            int height = 900;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(width,height);
        }
    }

    /**
     * a methode to save the modified data by a clicking event
     * @param view the view to get the button event
     */
    private void clickSaveSleepButton(View view) {

        String string_hours = String.valueOf(((EditText) getView().findViewById(R.id.text_view_hours_sleep)).getText());
        int hours = Integer.parseInt(string_hours.isEmpty() ? "0" : string_hours);

        String string_minutes = String.valueOf(((EditText) getView().findViewById(R.id.text_view_minutes_sleep)).getText());
        int minutes =  Integer.parseInt(string_minutes.isEmpty() ? "0" : string_minutes);



        manager.getProfile().getSleep().addHours(hours);
        manager.getProfile().getSleep().addMinutes(minutes);

        manager.saveManagerProfile();
        ActiviteAccueil activiteAccueil = (ActiviteAccueil) getActivity();
        if(activiteAccueil != null) {
            FragmentManager fragmentManager = activiteAccueil.getSupportFragmentManager();
            HomeFragment homeFragment = (HomeFragment) fragmentManager.findFragmentByTag("HomeFragment");
            if(homeFragment != null) homeFragment.updateTimeSleep();
        }
        dismiss();
    }

    /**
     * a methode for close the dialog
     * @param view the view to get the button event
     */
    private void clickSleepClosing(View view) {
        dismiss();
    }

}
