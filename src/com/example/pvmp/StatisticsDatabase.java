package com.example.pvmp;

import parser.DatabaseInterface;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StatisticsDatabase {
	
	private static final String NUMVOTE = "SELECT VOTO FROM VOTE WHERE CODSESSAO = '4976'";
	
		public static void returnNumVotes(Context context){
			DatabaseInterface helper = new DatabaseInterface(context);
			SQLiteDatabase db = helper.getWritableDatabase();
			Cursor c = db.rawQuery(NUMVOTE, null);	
			Integer sim = 0;
			Integer count = 0;
			c.moveToFirst();
			while(count < c.getCount()){
				Log.d("Espa�o","Teste"+c.getString(0).length());
				String newVote = removeEmptyChar(c.getString(0));
				Log.d("VOTOS SIM", "Votos: "+newVote.length());
				if(newVote.equals("Sim")){
					Log.d("TESTE", "TESTE: "+newVote);
					sim++;
				}
				count++;
				c.moveToPosition(count);
			}
			Log.d("VOTOS SIM", "Num Votos: "+sim);
		}
		
		private static String removeEmptyChar(String party){
			String space = " ";
			Integer position = party.indexOf(space);
			if(position >= 0){
				String new_party = party.substring(0, position);
				return new_party;
			}
			else
				return party;
		}
}
