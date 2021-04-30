package iut.cours.lifehealth.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Date;

import iut.cours.lifehealth.R;
import iut.cours.lifehealth.model.Kind;
import iut.cours.lifehealth.model.Manager;
import iut.cours.lifehealth.view.codebehind.ActiviteAccueil;

/**
 * The ModifyProfileFragment to modify the personal user data
 */
public class ModifyProfileFragment extends Fragment {
    private Manager manager;

    public ModifyProfileFragment() {
        super(R.layout.fragment_modify_profile);
    }

    /**
     * the onViewCreated method of the ModifyProfileFragment
     * It show the one way binded data
     * It subscribed events to methods, like the onclick, the updateDate
     * @param view the view
     * @param savedInstanceState the saved data
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        manager = ((ActiviteAccueil) view.getContext()).getManager();

        ((DatePicker) getView().findViewById(R.id.date_picker_profile)).setMaxDate(new Date().getTime());

        if(manager.getProfile().getKind() == Kind.MAN) {
            ((RadioButton) getView().findViewById(R.id.radio_button_male)).setChecked(true);
            ((RadioButton) getView().findViewById(R.id.radio_button_female)).setChecked(false);
        } else {
            ((RadioButton) getView().findViewById(R.id.radio_button_male)).setChecked(false);
            ((RadioButton) getView().findViewById(R.id.radio_button_female)).setChecked(true);
        }

        Calendar calendarBirthday = Calendar.getInstance();
        calendarBirthday.setTime(manager.getProfile().getBirthday());
        ((DatePicker) getView().findViewById(R.id.date_picker_profile)).updateDate(calendarBirthday.get(Calendar.YEAR), calendarBirthday.get(Calendar.MONTH), calendarBirthday.get(Calendar.DAY_OF_MONTH));

        ((EditText) getView().findViewById(R.id.edit_text_height)).setText(String.valueOf(manager.getProfile().getHeight().getRecent()));

        ((EditText) getView().findViewById(R.id.edit_text_weight)).setText(String.valueOf(manager.getProfile().getWeight().getRecent()));

        ((Button) view.findViewById(R.id.save_profile_button)).setOnClickListener(this::clickSaveProfileButton);
        ((Button) view.findViewById(R.id.exit_modify_button_user_profile)).setOnClickListener(this::clickExitModifyButton);
    }

    /**
     *  a method to save the modifications and return to the DisplayProfileFragment
     * @param view the view for getting the event
     */
    private void clickSaveProfileButton(View view) {

        Kind kind = ((RadioButton) getView().findViewById(R.id.radio_button_male)).isChecked() ? Kind.MAN : Kind.WOMAN;

        Calendar calendarBirthday = Calendar.getInstance();
        DatePicker datePickerBirthday = ((DatePicker) getView().findViewById(R.id.date_picker_profile));
        calendarBirthday.set(datePickerBirthday.getYear(), datePickerBirthday.getMonth(), datePickerBirthday.getDayOfMonth());
        Date birthday = calendarBirthday.getTime();

        String string_height = String.valueOf(((EditText) getView().findViewById(R.id.edit_text_height)).getText());
        float height = Float.parseFloat(string_height.isEmpty() ? "0" : string_height);

        String string_weight = String.valueOf(((EditText) getView().findViewById(R.id.edit_text_weight)).getText());
        float weight = Float.parseFloat(string_weight.isEmpty() ? "0" : string_weight);

        manager.getProfile().setKind(kind);
        manager.getProfile().setBirthday(birthday);
        manager.getProfile().getHeight().addHeight(height);
        manager.getProfile().getWeight().addWeight(weight);

        manager.saveManagerProfile();
        ((ActiviteAccueil)view.getContext()).loadFragment(new DisplayProfileFragment());
    }

    /**
     * a method to cancel the modifications and return to the DisplayProfileFragment
     * @param view the view for getting the event
     */
    private void clickExitModifyButton(View view) {
        ((ActiviteAccueil)view.getContext()).loadFragment(new DisplayProfileFragment());
    }
}
