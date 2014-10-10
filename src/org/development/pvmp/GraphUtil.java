package org.development.pvmp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendPosition;

public class GraphUtil {
	public static void createGraph (ArrayList<Entry> entries, ArrayList<String> values, PieChart mChart,
									String textCenter, Context context, int color) {
	    mChart.setDescription("");

        PieDataSet ds1 = new PieDataSet(entries, "");
        if (color == 0)
        	ds1.setColors(ColorTemplate.JOYFUL_COLORS);
        else if (color == 1)
        	ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        else
        	ds1.setColors(ColorTemplate.LIBERTY_COLORS);
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
        
        mChart.setData(new PieData(values, ds1));
        mChart.setScrollContainer(true);
        mChart.animateXY(800, 800);
        
	    Legend l = mChart.getLegend();
	    l.setPosition(LegendPosition.RIGHT_OF_CHART);
	    l.setFormSize(13f);
	}
}
