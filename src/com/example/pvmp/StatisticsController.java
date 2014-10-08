package com.example.pvmp;

import java.util.ArrayList;
import java.util.List;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendPosition;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

public class StatisticsController {
	public static void createGraph (PieChart mChart, Context context) {
	    mChart.setDescription("");
	    
	    List<Integer> valuesVotes = StatisticsDatabase.returnNumVotes(context);
	    
	    float sim = (((float)valuesVotes.get(1)/(float)valuesVotes.get(0))*100);
	    float nao = (((float)valuesVotes.get(2)/(float)valuesVotes.get(0))*100);
	    
	    Log.d("ARRAY", "TOTAL:"+valuesVotes.get(0));
	    Log.d("ARRAY", "SIM:"+valuesVotes.get(1));
	    Log.d("ARRAY", "NAO:"+valuesVotes.get(2));
	    
	    Log.d("SIM", "sim:"+sim);
	    Log.d("NAO", "nao:"+nao);
	    
	    
	    ArrayList<Entry> entries1 = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        
        xVals.add("Sim");
        xVals.add("Não");
        
        entries1.add(new Entry(sim, 0));
        entries1.add(new Entry(nao, 1));
        
        PieDataSet ds1 = new PieDataSet(entries1, "");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ds1.setSliceSpace(2f);
	    
	    Typeface tf = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC);
	    Typeface tfCenter = Typeface.SANS_SERIF;
	    
	    mChart.setValueTypeface(tf);
	    mChart.setUsePercentValues(true);
	    mChart.setValueTextSize(15f);
	    mChart.setCenterText("Resultado");
	    mChart.setCenterTextTypeface(tfCenter);
	    mChart.setCenterTextSize(22f);
	     
	    mChart.setHoleRadius(45f); 
	    mChart.setTransparentCircleRadius(50f);
	    mChart.setRotationEnabled(false);
        
        mChart.setData(new PieData(xVals, ds1));
        mChart.setScrollContainer(true);
        mChart.animateXY(800, 800);
        
	    Legend l = mChart.getLegend();
	    l.setPosition(LegendPosition.RIGHT_OF_CHART);
	    l.setFormSize(13f);
	}
	
}
