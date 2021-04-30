package iut.cours.lifehealth.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import iut.cours.lifehealth.R;
import iut.cours.lifehealth.model.Manager;
import iut.cours.lifehealth.view.codebehind.ActiviteAccueil;

/**
 * The DisplayProfileFragment to display the personal user data
 */
public class DisplayProfileFragment extends Fragment {
    private Manager manager;

    public DisplayProfileFragment() {
        super(R.layout.fragment_display_profile);
    }

    /**
     * the onViewCreated method of the DisplayProfileFragment
     * It show the one way binded data
     * It subscribed click to methods
     * @param view the view for getting the event
     * @param savedInstanceState the saved data
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        manager = ((ActiviteAccueil) view.getContext()).getManager();

        //Kind
        ((TextView) view.findViewById(R.id.text_view_sexe_user)).setText(manager.getProfile().getKind().toString());

        //Age
        ((TextView) view.findViewById(R.id.text_view_age_user)).setText(String.valueOf(manager.getProfile().getAge()));

        //Height
        ((TextView) view.findViewById(R.id.text_view_height_user)).setText(String.valueOf(manager.getProfile().getHeight().getRecent()));

        //Weight
        ((TextView) view.findViewById(R.id.text_view_weight_user)).setText(String.valueOf(manager.getProfile().getWeight().getRecent()));

        //Steps
        ((TextView) view.findViewById(R.id.text_view_step_user)).setText(String.valueOf(manager.getProfile().getSteps().total()));

        //Hydratation
        ((TextView) view.findViewById(R.id.text_view_hydratation_user)).setText(String.valueOf(manager.getProfile().getHydratation().total()));

        ((Button) view.findViewById(R.id.modify_button_user_profile)).setOnClickListener(this::clickModifyProfileButton);
    }

    /**
     * a method to call the ModifyProfileFragment to modify the personal user data
     * @param view the view for getting the event
     */
    private void clickModifyProfileButton(View view) {
        ((ActiviteAccueil)view.getContext()).loadFragment(new ModifyProfileFragment());
    }

}
