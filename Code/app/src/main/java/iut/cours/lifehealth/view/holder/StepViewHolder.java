package iut.cours.lifehealth.view.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iut.cours.lifehealth.R;

/**
 * a method to personalize the display of a cell of the AdapterSTepView
 */
public class StepViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewDate;
    private TextView textViewStep;


    /**
     * a method for personalize the display of a cell of the AdapterSTepView
     * @param itemView the item (or cell)  to personalize
     */
    public StepViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewDate =  (TextView) itemView.findViewById(R.id.textViewDate);
        textViewStep =  (TextView) itemView.findViewById(R.id.textViewStep);
    }

    /**
     * the getter of the textViewStep
     * @return the textViewStep
     */
    public TextView getTextViewStep(){
        return textViewStep;
    }


    /**
     * the getter of the textViewDate
     * @return the textViewDate
     */
    public TextView getTextViewDate(){
        return textViewDate;
    }
}
