package com.android.sqlite;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DataBase extends Activity {

	private TextView login;
	private TextView nom;
	private TextView prenom;
	private int current;
	private ImageButton moveNext;
	private ImageButton movePrev;
	/*private ImageButton add;
	private ImageButton remove;
	private ImageButton update;*/
	private ImageButton nouveau;
	private dao db;
	private List<user> l;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_base);

		if (db == null) {
			db = new dao();
		}

		login = (TextView) findViewById(R.id.login);
		nom = (TextView) findViewById(R.id.nom);
		prenom = (TextView) findViewById(R.id.prenom);
		moveNext = (ImageButton) findViewById(R.id.next);
		moveNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (current < l.size() - 1) {
					current++;
					print();
				}
				else
					Toast.makeText(getBaseContext(),"Vous pointez sur le dernier élement", Toast.LENGTH_SHORT).show();
			}
		});
		movePrev = (ImageButton) findViewById(R.id.previous);
		movePrev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (current > 0) {
					current--;
					print();
				}
				else
					Toast.makeText(getBaseContext(),"Vous pointez sur le premier élement", Toast.LENGTH_SHORT).show();
			}
		});
		/*add = (ImageButton) findViewById(R.id.add);
		add.setEnabled(false);
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				user u = new user();
				u.setLogin(login.getText().toString());
				u.setNom(nom.getText().toString());
				u.setPrenom(prenom.getText().toString());
				l.add(u);
				db.insert(u);
				add.setEnabled(false);
				Toast.makeText(getBaseContext(),u.getLogin() + " Ajouter avec succes !!", Toast.LENGTH_SHORT).show();
			}
		});
		remove = (ImageButton) findViewById(R.id.supprimer);
		remove.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				db.remove(login.getText().toString());
				l.remove(pos(login.getText().toString()));
				if(current==l.size()-1)
					current--;
				Toast.makeText(getBaseContext(),login.getText().toString() + " Supprimer avec succes!!", Toast.LENGTH_SHORT).show();
				print();
			}
		});
		update = (ImageButton) findViewById(R.id.modifier);
		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				user u = new user();
				u.setLogin(login.getText().toString());
				u.setNom(nom.getText().toString());
				u.setPrenom(prenom.getText().toString());
				db.update(u);
				int i = pos(login.getText().toString());
				l.get(i).setNom(u.getNom());
				l.get(i).setPrenom(u.getPrenom());
				Toast.makeText(getBaseContext(),u.getLogin() + " Modifier avec succes!!", Toast.LENGTH_SHORT).show();
			}
		});
		nouveau = (ImageButton) findViewById(R.id.nouveau);
		nouveau.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				login.setText("");
				nom.setText("");
				prenom.setText("");
				add.setEnabled(true);
			}
		});
		*/
		l = db.getAll();
		current = 0;
		if (l.size() > 0)
			print();
	}

	public void print() {
		if (l.size() > 0) {
			login.setText(l.get(current).getLogin());
			nom.setText(l.get(current).getNom());
			prenom.setText(l.get(current).getPrenom());
		} else {
			login.setText("Vide");
			nom.setText("");
			prenom.setText("");
		}

	}

	public int pos(String login) {
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i).getLogin().equals(login))
				return i;
		}
		return -1;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.add:
			user u = new user();
			u.setLogin(login.getText().toString());
			u.setNom(nom.getText().toString());
			u.setPrenom(prenom.getText().toString());
			l.add(u);
			db.insert(u);
			Toast.makeText(getBaseContext(),u.getLogin() + " Ajouter avec succes !!", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return true;
	}
}
