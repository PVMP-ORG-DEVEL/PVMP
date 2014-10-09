package com.example.pvmp;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import com.github.mikephil.charting.charts.PieChart;

import parser.DatabaseInterface;
import parser.ParserController;
import parser.helpers.ParserHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class ParserActivity extends Activity {

	private PieChart chart;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);
		
		chart = (PieChart) findViewById(R.id.chart);
		
		StatisticsController.createGraph(chart, getApplicationContext());
	}

	public void loadParser(View view){
		ConnectivityManager connecManager = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connecManager.getActiveNetworkInfo();
		new PartyFromFile().execute();
		if (networkInfo != null && networkInfo.isConnected()) {
			new Download().execute();
		} else {
			Toast.makeText(getApplicationContext(), "Confira sua conexão", 
					Toast.LENGTH_SHORT).show();
		}

	}

	class Download extends AsyncTask<String, Void, Integer>{
		private Context context = getApplicationContext();
		@Override
		protected Integer doInBackground(String... params) {
			Integer value = 0;
			try{
				value = ParserController.requestPlenario(context);
			}	
			catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return value;
		}
	}
	class PartyFromFile extends AsyncTask<String, Void, Integer>{
		@Override
		protected Integer doInBackground(String... params) {
			Integer value = 0;
			Log.d("Teste", "Teste");
			try {
				ParserController.importPartyFile(getAssets(),getApplicationContext());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return value;
		}
	}

}