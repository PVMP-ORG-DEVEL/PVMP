package com.example.pvmp;

import java.util.ArrayList;
import java.util.List;

import model.Proposition;

import parser.DatabaseInterface;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StatisticsDatabase {
		
	private static final String NUMVOTE = "SELECT VOTO FROM VOTE WHERE CODSESSAO = '4976'";
	private static final String LISTPROP = "SELECT * FROM PROPOSITION";
	
		public static List<Integer> returnNumVotes(Context context){
			List<Integer> voteList = new ArrayList<Integer>();
			DatabaseInterface helper = new DatabaseInterface(context);
			SQLiteDatabase db = helper.getWritableDatabase();
			Cursor c = db.rawQuery(NUMVOTE, null);	
			Integer sim = 0;
			Integer nao = 0;
			Integer count = 0;
			Integer total;
			c.moveToFirst();
			while(count < c.getCount()){
				Log.d("Espa�o","Teste"+c.getString(0).length());
				String newVote = removeEmptyChar(c.getString(0));
				Log.d("VOTOS SIM", "Votos: "+newVote.length());
				if(newVote.equals("Sim")){
					Log.d("TESTE", "TESTE: "+newVote);
					sim++;
				}
				else if(newVote.equals("N�o"))
					nao++;
				count++;
				c.moveToPosition(count);
			}
			total = sim + nao;
			
			voteList.add(total);
			voteList.add(sim);
			voteList.add(nao);
			
			return voteList;
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
		public static ArrayList<String> returnAllProp(Context context){
			ArrayList<String> propList = new ArrayList<String>();
			DatabaseInterface helper = new DatabaseInterface(context);
			SQLiteDatabase db = helper.getWritableDatabase();
			Cursor c = db.rawQuery(LISTPROP, null);
			c.moveToFirst();
			Integer count = 0;
			while(count < c.getCount()){ 
				String situation;
				situation = c.getString(c.getColumnIndex("EMENTAPROP"));
				propList.add(situation);
				c.moveToPosition(count++);
			}
			return propList;
		}
}
