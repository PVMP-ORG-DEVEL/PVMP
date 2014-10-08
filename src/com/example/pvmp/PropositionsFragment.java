package com.example.pvmp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class PropositionsFragment extends Fragment {
	private ParserActivity parserActivity;
	private Context context;
	private ListView listView_propositions;
	private String[] propositions = {"proposicao 1", "proposicao 2", "proposicao 3",
									 "proposicao 4", "proposicao 5", "proposicao 6",
									 "proposicao 7", "proposicao 8", "proposicao 9",
									 "proposicao 10", "proposicao 11", "proposicao 12",
									 "proposicao 13", "proposicao 14", "proposicao 15"};
							
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 				 ViewGroup container,
			 				 Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		parserActivity = (ParserActivity) getActivity();
		context = parserActivity.getApplicationContext();
		
		View viewStatistics = inflater.inflate(R.layout.propositions_fragment, container, false);
		listView_propositions = (ListView) viewStatistics.findViewById(R.id.listView_propositions);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.list_helper, StatisticsDatabase.returnAllProp(context));
		listView_propositions.setAdapter(adapter);
 		
		return viewStatistics;
	}
	
}