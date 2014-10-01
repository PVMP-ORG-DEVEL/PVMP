package parser;

import java.util.ArrayList;

import model.Proposition;
import model.Vote;
import model.Voting;

import org.w3c.dom.Text;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

public class DatabaseInterface extends SQLiteOpenHelper {

	public static final String PROP_TABLE_NAME = "PROPOSITION";
	public static final String IDPROP = "IDPROP";
	public static final String ANOPROP = "ANOPROP";
	public static final String EMENTAPROP = "EMENTAPROP";
	public static final String AUTORPROP = "AUTORPROP";
	/* TIPOPROP é a sigla da proposicao */
	public static final String SIGLAPROP = "SIGLAPROP";
	public static final String NUMEROPROP = "NUMEROPROP";
	public static final String SITUACAOPROP = "SITUACAOPROP";
	
	public static final String CREATE_TABLE_PROPOSITION = 
			"CREATE TABLE " + PROP_TABLE_NAME + "(" 
		    + IDPROP + " INTEGER NOT NULL PRIMARY KEY, " 
			+ ANOPROP + " INTEGER NOT NULL, "
			+ EMENTAPROP + " TEXT, "
			+ AUTORPROP + " TEXT, "
			+ SIGLAPROP + " TEXT, "
			+ SITUACAOPROP + " TEXT, "
			+ NUMEROPROP + " TEXT "
			+ ");";
	
	
	public static final String VOTING_TABLE_NAME = "VOTING";
	public static final String COD_SESSAO = "CODSESSAO";
	public static final String RESUMO = "RESUMO";
	public static final String DATA_VOTACAO = "DATA_VOTACAO";

	public static final String CREATE_TABLE_VOTATING = 
			"CREATE TABLE " + VOTING_TABLE_NAME + "(" 
		    + COD_SESSAO + " INTEGER NOT NULL PRIMARY KEY, " 
			+ RESUMO + " TEXT, "
			+ DATA_VOTACAO + " TEXT, "
			+ IDPROP + " INTEGER, "
			+ "FOREIGN KEY(IDPROP) REFERENCES PROPOSITION(IDPROP) "
			+ ");";
	
	
	public static final String VOTE_TABLE_NAME = "VOTE";
	public static final String VOTO = "VOTO";
	public static final String CREATE_TABLE_VOTE = 
			"CREATE TABLE " + VOTE_TABLE_NAME + "(" 
		    + VOTO + " TEXT, " 
			+ COD_SESSAO + " INTEGER, "
			+ "FOREIGN KEY(CODSESSAO) REFERENCES VOTING(CODSESSAO) "
			+ ");";

	

	public static final String BANCO_DADOS = "PVMP";
	private static int VERSAO = 1; 

	
	public DatabaseInterface(Context context) {
		super(context, BANCO_DADOS, null, VERSAO);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_PROPOSITION);
		db.execSQL(CREATE_TABLE_VOTATING);
		db.execSQL(CREATE_TABLE_VOTE);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	} 
	
	public static void saveProp(ArrayList<Proposition> propList,ArrayList<Voting> votingList,Context context) {

		saveVoting(votingList, propList.get(0).getIdProp(),context);
		DatabaseInterface helper = new DatabaseInterface(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		for (int i = 0; i < propList.size(); i++) {
			Cursor c = db.rawQuery(
					"SELECT * FROM PROPOSITION WHERE  IDPROP= ' "
							+ propList.get(i).getIdProp() + "'", null);
			Log.d("ENTROU2", "ENTROU2");
			if (c.getCount() == 0) {
				Log.d("ENTROU", "ENTROU");
				values.put("IDPROP", propList.get(i).getIdProp());
				values.put("ANOPROP", propList.get(i).getAnoProp());
				values.put("AUTORPROP", propList.get(i).getAnoProp());
				values.put("EMENTAPROP", propList.get(i).getEmentaProp());
				values.put("SIGLAPROP", propList.get(i).getSiglaProp());
				values.put("NUMEROPROP", propList.get(i).getNumeroProp());
				values.put("SITUACAOPROP", propList.get(i).getSituacaoProp());
				long log_res = db.insert("PROPOSITION", null, values);
				if (log_res != -1) {
					Log.d("prop ", "Proposição salva");
				} else {
					Log.d("prop ", "Proposição não salva");
				}
			}
		}
		db.close();
	}
	
	
	public static void saveVoting(ArrayList<Voting> votList,Integer idProp, Context context) {

		saveVote(votList.get(0).getVote(),context);
		DatabaseInterface helper = new DatabaseInterface(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		for (int i = 0; i < votList.size(); i++) {
			Cursor c = db.rawQuery(
					"SELECT * FROM VOTING WHERE CODSESSAO = ' "
							+ votList.get(i).getCodSessaoVot() + "'", null);
			Log.d("ENTROU2", "ENTROU2");
			if (c.getCount() == 0) {
				values.put("DATA_VOTACAO", votList.get(i).getDataVot());
				values.put("RESUMO", votList.get(i).getResumoVot());
				values.put("CODSESSAO", votList.get(i).getCodSessaoVot());
				values.put("IDPROP", idProp);
				long log_res = db.insert("VOTING", null, values);
				if (log_res != -1) {
					Log.d("prop ", "Votação salva");
				} else {
					Log.d("prop ", "Votação não salva");
				}
			}
		}
		db.close();
	}
	
	public static void saveVote (ArrayList<Vote> voteList,Context context){
		DatabaseInterface helper = new DatabaseInterface(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		for (int i = 0; i < voteList.size(); i++) {
				values.put("VOTO", voteList.get(i).getVoto());
				values.put("CODSESSAO", voteList.get(i).getCodSessao());
				long log_res = db.insert("VOTE", null, values);
				if (log_res != -1) {
					Log.d("prop ", "Voto salva");
				} else {
					Log.d("prop ", "Voto não salva");
				}
		}
		db.close();
		
		
	}
}