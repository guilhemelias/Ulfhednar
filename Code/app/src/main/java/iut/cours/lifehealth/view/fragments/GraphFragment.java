package iut.cours.lifehealth.view.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import iut.cours.lifehealth.R;
import iut.cours.lifehealth.model.Manager;
import iut.cours.lifehealth.model.utils.DateUtil;
import iut.cours.lifehealth.view.codebehind.ActiviteAccueil;

/**
 * The GraphFragment to display some graphics
 */
public class GraphFragment extends Fragment {
    /**
     * The instance of the manager of the application
     */
    Manager manager;

    /**
     * The constructor of the class GraphFragment
     */
    public GraphFragment() {
        super(R.layout.fragment_achievment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        manager = ((ActiviteAccueil) view.getContext()).getManager();
/*
        // Some Stub data
        // Weight
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("01/01/2021"), 45f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("01/02/2021"), 40f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("05/02/2021"), 52f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("04/03/2021"), 43f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("04/04/2021"), 43f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("04/05/2021"), 43f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("04/06/2021"), 43f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("04/07/2021"), 43f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("04/08/2021"), 43f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("04/09/2021"), 43f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("04/10/2021"), 43f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("04/11/2021"), 43f);
        manager.getProfile().getWeight().addWeight(DateUtil.getDateFromString("04/12/2021"), 43f);
        manager.getProfile().getWeight().addWeight(50f);
        // Height
        manager.getProfile().getHeight().addHeight(DateUtil.getDateFromString("01/01/2012"), 120f);
        manager.getProfile().getHeight().addHeight(DateUtil.getDateFromString("01/01/2013"), 125f);
        manager.getProfile().getHeight().addHeight(DateUtil.getDateFromString("01/01/2014"), 130f);
        manager.getProfile().getHeight().addHeight(DateUtil.getDateFromString("01/01/2015"), 135f);
        manager.getProfile().getHeight().addHeight(DateUtil.getDateFromString("01/01/2016"), 140f);
        manager.getProfile().getHeight().addHeight(DateUtil.getDateFromString("01/01/2017"), 145f);
        manager.getProfile().getHeight().addHeight(DateUtil.getDateFromString("01/01/2018"), 150f);
        manager.getProfile().getHeight().addHeight(DateUtil.getDateFromString("01/01/2019"), 155f);
        manager.getProfile().getHeight().addHeight(DateUtil.getDateFromString("01/01/2020"), 160f);
        manager.getProfile().getHeight().addHeight(DateUtil.getDateFromString("01/01/2021"), 165f);
        manager.getProfile().getHeight().addHeight(170f);
        // Step
        manager.getProfile().getSteps().addStep(DateUtil.getDateFromString("01/01/2012"), 1200);
        manager.getProfile().getSteps().addStep(DateUtil.getDateFromString("01/01/2013"), 1850);
        manager.getProfile().getSteps().addStep(DateUtil.getDateFromString("01/01/2014"), 1300);
        manager.getProfile().getSteps().addStep(DateUtil.getDateFromString("01/01/2015"), 1235);
        manager.getProfile().getSteps().addStep(DateUtil.getDateFromString("01/01/2016"), 1440);
        manager.getProfile().getSteps().addStep(DateUtil.getDateFromString("01/01/2017"), 5145);
        manager.getProfile().getSteps().addStep(DateUtil.getDateFromString("01/01/2018"), 9150);
        manager.getProfile().getSteps().addStep(DateUtil.getDateFromString("01/01/2019"), 1155);
        manager.getProfile().getSteps().addStep(DateUtil.getDateFromString("01/01/2020"), 1260);
        manager.getProfile().getSteps().addStep(DateUtil.getDateFromString("01/01/2021"), 165);
        manager.getProfile().getSteps().addStep(17000);
        // Hydratation
        manager.getProfile().getHydratation().addGlassDrink(DateUtil.getDateFromString("01/01/2012"), 12);
        manager.getProfile().getHydratation().addGlassDrink(DateUtil.getDateFromString("01/01/2013"), 18);
        manager.getProfile().getHydratation().addGlassDrink(DateUtil.getDateFromString("01/01/2014"), 13);
        manager.getProfile().getHydratation().addGlassDrink(DateUtil.getDateFromString("01/01/2015"), 12);
        manager.getProfile().getHydratation().addGlassDrink(DateUtil.getDateFromString("01/01/2016"), 40);
        manager.getProfile().getHydratation().addGlassDrink(DateUtil.getDateFromString("01/01/2017"), 55);
        manager.getProfile().getHydratation().addGlassDrink(DateUtil.getDateFromString("01/01/2018"), 90);
        manager.getProfile().getHydratation().addGlassDrink(DateUtil.getDateFromString("01/01/2019"), 15);
        manager.getProfile().getHydratation().addGlassDrink(DateUtil.getDateFromString("01/01/2020"), 10);
        manager.getProfile().getHydratation().addGlassDrink(DateUtil.getDateFromString("01/01/2021"), 15);
        manager.getProfile().getHydratation().addGlassDrink(150);
 */

        drawGraphFloat(view, R.id.line_chart_weight, R.string.weight_text_with_type, manager.getProfile().getWeight().getHistory().entrySet());
        drawGraphFloat(view, R.id.line_chart_height, R.string.height_text_with_type, manager.getProfile().getHeight().getHistory().entrySet());
        drawGraphInteger(view, R.id.line_chart_step, R.string.step_text_with_type, manager.getProfile().getSteps().getHistory().entrySet());
        drawGraphInteger(view, R.id.line_chart_hydratation, R.string.hydratation_text_with_type, manager.getProfile().getHydratation().getHistory().entrySet());

    }

    /**
     * A method to draw a Graph of Float in a LineChart
     * @param view The view of the LineChart
     * @param idLineChart The id of the Linechart in the application
     * @param idLabelText The id of the text that we want to name the Linechart
     * @param values The values that we want in the LineChart
     */
    private void drawGraphFloat(View view, int idLineChart, int idLabelText, Set<Map.Entry<Date, Float>> values) {
        LineChart weightLineChart = view.findViewById(idLineChart);
        List<Entry> entryValues = new ArrayList<>();
        for(Map.Entry<Date, Float> entry : values) {
            entryValues.add(new Entry((entry.getKey()).getTime(), entry.getValue()));
        }
        createGaph(entryValues, getString(idLabelText), weightLineChart);
    }

    /**
     * A method to draw a Graph of Integer in a LineChart
     * @param view The view of the LineChart
     * @param idLineChart The id of the Linechart in the application
     * @param idLabelText The id of the text that we want to name the Linechart
     * @param values The values that we want in the LineChart
     */
    private void drawGraphInteger(View view, int idLineChart, int idLabelText, Set<Map.Entry<Date, Integer>> values) {
        LineChart weightLineChart = view.findViewById(idLineChart);
        List<Entry> entryValues = new ArrayList<>();
        for(Map.Entry<Date, Integer> entry : values) {
            entryValues.add(new Entry((entry.getKey()).getTime(), entry.getValue()));
        }
        createGaph(entryValues, getString(idLabelText), weightLineChart);
    }

    /**
     * A method to draw a Graph of Entry in a LineChart
     * @param values The values that we want in the LineChart
     * @param label The name of the LineChart
     * @param mLineChart The LineChart
     */
    private void createGaph(List<Entry> values, String label, LineChart mLineChart) {
        LineDataSet setWeight = new LineDataSet(values, label);
        setWeight.setAxisDependency(YAxis.AxisDependency.LEFT);

        setWeight.setColor(ColorTemplate.getHoloBlue());
        setWeight.setValueTextColor(Color.rgb(13, 148, 187));
        setWeight.setLineWidth(3f);
        setWeight.setColor(Color.rgb(13, 148, 187));
        setWeight.setDrawCircles(true);
        setWeight.setCircleColor(Color.rgb(41, 207, 255));
        setWeight.setDrawValues(true);
        setWeight.setValueTextSize(16f);
        setWeight.setValueTextColor(Color.rgb(41, 207, 255));
        setWeight.setFillAlpha(65);
        setWeight.setFillColor(ColorTemplate.getHoloBlue());
        setWeight.setDrawCircleHole(false);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setWeight);

        LineData data = new LineData(dataSets);
        mLineChart.setData(data);
        mLineChart.zoom(1,1,1,1);

        // the labels that should be drawn on the XAxis
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return DateUtil.getStringFromDateMillis(value);
            }
        };

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(-20f);
        xAxis.setTextSize(13f);
        xAxis.setAxisLineColor(Color.rgb(44,44,44));
        xAxis.setAxisLineWidth(3f);

        mLineChart.getAxisRight().setEnabled(false);

        YAxis yAxis = mLineChart.getAxisLeft();
        yAxis.setTextSize(13f);
        yAxis.disableGridDashedLine();

        mLineChart.invalidate();
    }

}
