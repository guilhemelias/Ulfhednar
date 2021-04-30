package iut.cours.lifehealth.view.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import iut.cours.lifehealth.R;
import iut.cours.lifehealth.model.Manager;
import iut.cours.lifehealth.view.adapter.AdapterStepView;
import iut.cours.lifehealth.view.codebehind.ActiviteAccueil;

/**
 * A dialog Fragment to display the step data history
 */
public class StepGoalDialog extends DialogFragment {

    private Manager manager;
    private RecyclerView listViewStep;
    private ImageView close;


    /**
     * the onCreateView method of the StepGoalDialog Fragment
     * It inflate the view
     * @param inflater the inflater
     * @param container the View to inflate
     * @param savedInstanceState the bundle
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_step_goal,container,false);
        return view;

    }

    /**
     * the onViewCreated method of the StepGoalDialog Fragment
     * It show the one way binded data
     * By instantiate the adapter to have a personalized display
     *  @param view the view
     *  @param savedInstanceState the memorized data
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manager = ((ActiviteAccueil) getActivity()).getManager();
        
        listViewStep = (RecyclerView) view.findViewById(R.id.la_list_step);
        listViewStep.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listViewStep.setAdapter(new AdapterStepView(manager.getProfile().getSteps().getHistory()));

        close =  ((ImageView) view.findViewById(R.id.step_dialog_closing));
        close.setOnClickListener(this::clickStepClosing);
    }

    /**
     * the onStart method of the StepGoalDialog Fragment
     *  It instanciate the properties of the Dialog
     */
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog!= null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = 1250;
            dialog.getWindow().setLayout(width,height);
        }
    }

    /**
     * a methode for close the dialog
     * @param view the view to get the button event
     */
    private void clickStepClosing(View view) {
        dismiss();
    }
}
