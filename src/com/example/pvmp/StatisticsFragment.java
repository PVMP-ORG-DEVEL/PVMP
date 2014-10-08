package com.example.pvmp;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StatisticsFragment extends Fragment {
	private MainActivity mainActivity;
	private Context context;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,
			 Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainActivity = (MainActivity) getActivity();
		context = mainActivity.getApplicationContext();
		
		View viewStatistics = inflater.inflate(R.layout.activity_main, container, false);
		return viewStatistics;
	}
}
