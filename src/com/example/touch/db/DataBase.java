package com.example.touch.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

	public static final String BDNAME 	= "bd";
	public SQLiteDatabase myDataBase;


	public DataBase(Context context) {
		super(context, BDNAME, null, 1);
	} 

	private void createTables(SQLiteDatabase db){
		try {
			db.execSQL("CREATE TABLE IF NOT EXISTS USUARIO(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, token TEXT)");
			db.execSQL("CREATE TABLE IF NOT EXISTS GALERIA(id INTEGER PRIMARY KEY AUTOINCREMENT, idPublicacion TEXT, footer TEXT, description TEXT, image TEXT, station TEXT, link TEXT, like TEXT, ranking TEXT, facebook TEXT, twitter TEXT)");
			db.execSQL("CREATE TABLE IF NOT EXISTS COMENTARIO(id INTEGER PRIMARY KEY AUTOINCREMENT, idPublicacion TEXT, name TEXT, comment TEXT)");
			db.execSQL("CREATE TABLE IF NOT EXISTS RANKING(id INTEGER PRIMARY KEY AUTOINCREMENT, idPublicacion TEXT, footer TEXT, description TEXT, image TEXT, station TEXT, link TEXT, like TEXT, ranking TEXT, facebook TEXT, twitter TEXT)");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		createTables(db);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}	
	@Override
    public synchronized void close() {

    if (myDataBase != null)
        myDataBase.close();

    super.close();

    }

}