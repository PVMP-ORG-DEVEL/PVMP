package org.development.pvmp;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;

public class PropositionFragment extends Fragment {
	private Context context;
	private TextView textView_id;
	private TextView textView_year;
	private TextView textView_propositor;
	private TextView textView_menu;
	private PieChart chart1;
	private PieChart chart2;
	private PieChart chart3;
	ArrayList<String> values1 = new ArrayList<String>();
	ArrayList<Entry> entries1 = new ArrayList<Entry>();
	ArrayList<String> values2 = new ArrayList<String>();
	ArrayList<Entry> entries2 = new ArrayList<Entry>();
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View viewPropositionFragment = inflater.inflate(R.layout.proposition_fragment, container, false);
		context = inflater.getContext();

		takeDataFromView(viewPropositionFragment);
		this.textView_id.setText("1234");
		this.textView_year.setText("2014");
		this.textView_propositor.setText("Fulano de tal");
		this.textView_menu.setText("Institui o Programa Mais Médicos e dá outras providências.");
		
		this.defineTestValues();
		
		GraphUtil.createGraph(this.entries1, this.values1, this.chart1, "Resultado", context, 0);
		GraphUtil.createGraph(this.entries2, this.values2, this.chart2, "Votos a favor", context, 1);
		GraphUtil.createGraph(this.entries2, this.values2, this.chart3, "Votos contra", context, 2);
		
		return viewPropositionFragment;
	}
	
	private void takeDataFromView (View v) {
		this.textView_id = (TextView) v.findViewById(R.id.textView_id);
		this.textView_year = (TextView) v.findViewById(R.id.textView_year);
		this.textView_propositor = (TextView) v.findViewById(R.id.textView_propositor);
		this.textView_menu = (TextView) v.findViewById(R.id.textView_menu);
		this.chart1 = (PieChart) v.findViewById(R.id.chart1);
		this.chart2 = (PieChart) v.findViewById(R.id.chart2);
		this.chart3 = (PieChart) v.findViewById(R.id.chart3);
	}
	
	private void defineTestValues () {
		this.values1.add("Sim");
		this.values1.add("Não");
		
		this.entries1.add(new Entry(58.1f, 0));
		this.entries1.add(new Entry(41.9f, 1));
		
		this.values2.add("PT");
		this.values2.add("PSDB");
		this.values2.add("PMDB");
		this.values2.add("PSB");
		this.values2.add("Outros");
		
		this.entries2.add(new Entry(14.1f, 0));
		this.entries2.add(new Entry(23.9f, 1));
		this.entries2.add(new Entry(15.4f, 2));
		this.entries2.add(new Entry(28.5f, 3));
		this.entries2.add(new Entry(18.1f, 4));
	}
}
