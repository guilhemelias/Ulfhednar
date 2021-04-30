package iut.cours.lifehealth.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import iut.cours.lifehealth.R;
import iut.cours.lifehealth.model.utils.DateUtil;
import iut.cours.lifehealth.view.holder.StepViewHolder;

/**
 * A clas to adapt the display of the stepHistory
 */
public class AdapterStepView extends RecyclerView.Adapter {


    private Map<Date, Integer> stepHistory;
    private ArrayList<Date> date;
    private ArrayList<Integer> step;

    /**
     * The constructor which create two list, splitting the map in two, for facilitate the display
     * @param history the History Step Map
     */
    public AdapterStepView(Map<Date,Integer> history){
        this.stepHistory = history;
        date = new ArrayList<Date>(stepHistory.keySet());
        step = new ArrayList<Integer>(stepHistory.values());
    }

    /**
     * a method for instanciete the view of a cell of the display
     * @param parent the parent to get the context
     * @param viewType the view
     * @return the a new Holder with the cell
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout stepLayout = (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.step_view_cell,parent,false);
        return new StepViewHolder(stepLayout);
    }

    /**
     * a method to bind the list to the modek
     * @param holder  the recycler view
     * @param position the index of the lists
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StepViewHolder)holder).getTextViewDate().setText(DateUtil.getStringFromDate(date.get(position)));
        ((StepViewHolder)holder).getTextViewStep().setText(step.get(position).toString());
    }

    /**
     * a method to get the size of the map
     * @return the size of the map
     */
    @Override
    public int getItemCount() {
        return stepHistory.size();
    }


}
