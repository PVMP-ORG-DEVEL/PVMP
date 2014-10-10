package org.development.pvmp;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PropositionsFragment extends ListFragment {
	private HomeActivity homeActivity;
	private Context context;
	private ListView listView_propositions;
	private String[] propositions = new String[10];
							
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 				 ViewGroup container,
			 				 Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		homeActivity = (HomeActivity) getActivity();
		context = inflater.getContext(); 
		
		for (int i = 0; i < 10; i ++) {
			propositions[i] = "proposicao " + i;
		}
		
		Log.d("X", "antes adapter");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, propositions);
		setListAdapter(adapter);
		Log.d("X", "depois adpater");
 		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		homeActivity.changeFragment(6);
	}
}
