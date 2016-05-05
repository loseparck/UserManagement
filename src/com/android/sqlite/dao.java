package com.android.sqlite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class dao {
	private SQLiteDatabase db;

	public dao() {
		File f = new File("/data/data/com.android.sqlite/databases");
		if (!f.exists())
			f.mkdir();
		db = SQLiteDatabase.openOrCreateDatabase(
				"/data/data/com.android.sqlite/databases/members.db", null);
		db.execSQL("CREATE TABLE IF NOT EXISTS member(login varchar(10) primary key,nom varchar(10),prenom varchar(10));");
	}

	public void insert(user u) {
		// db.execSQL("insert into user values('" + u.getLogin() + "','"
		// + u.getPassword() + "');");
		ContentValues values = new ContentValues();
		values.put("login", u.getLogin());
		values.put("nom", u.getNom());
		values.put("prenom", u.getPrenom());
		db.insert("member", null, values);
	}

	public List<user> getAll() {
		List<user> l = new ArrayList<user>();
		Cursor users = db.rawQuery("select * from member", null);
		if(users.getCount()==0)
			return l;
		users.moveToFirst();
		user s;
		do {
			s = new user();
			s.setLogin(users.getString(0));
			s.setNom(users.getString(1));
			s.setPrenom(users.getString(2));
			l.add(s);
		} while (users.moveToNext());
		return l;
	}

	public void update(user u){
		ContentValues values = new ContentValues();
		values.put("login", u.getLogin());
		values.put("nom", u.getNom());
		values.put("prenom", u.getPrenom());
		db.update("member", values, "login='"+ u.getLogin()+"'",null );
	}
	
	public void remove(String login){
		db.execSQL("delete from member where login='" + login + "';");
	}
}
