package jp.ac.kanto_pc.ohmori.s_sqy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABAE_NAME = "meibo_kanri";
	private static final int DATABASE_VERSION = 1;
	private static final String CREATE_MEIBO_TABLE = 
		"create table meibo ("
		+ "rowid integer primary key autoincrement, "
		+ "name text not null, "
		+ "phone text,"
		+ "address text, "
		+ "age integer "
		+")";
	private static final String DROP_MEIBO_TABLE =
		"drop table if exists meibo";

	public DatabaseHelper(Context context) {
		super(context, DATABAE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_MEIBO_TABLE);
		db.execSQL("insert into meibo (name, phone, address, age) values ('関東太郎', '0285-31-1799', '小山市横倉', 50)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(DROP_MEIBO_TABLE);
		onCreate(db);
	}

}
