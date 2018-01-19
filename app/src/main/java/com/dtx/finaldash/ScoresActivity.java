package com.dtx.finaldash;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ScoresActivity extends Activity 
{
	ArrayList<Puntuacion> arrPuntos = new ArrayList<Puntuacion>();
	AdapterPuntos adaptador = null;
	SQLiteManager db = null;
	ListView lv = null;
	Typeface tipodeLetra; 
	TextView titulo1;
	Button borrar;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		
		tipodeLetra = Typeface.createFromAsset(getAssets(), "fonts/Finalnew.ttf");
		
		titulo1 = (TextView) findViewById(R.id.txtPuntuaciones);
		titulo1.setTypeface(tipodeLetra);
		
		borrar = (Button) findViewById(R.id.button1);
		borrar.setTypeface(tipodeLetra);
		
		db = new SQLiteManager(this);
		arrPuntos = db.devolverPuntos();
		db.close();
		
		lv = (ListView) findViewById(R.id.listView1);
		adaptador = new AdapterPuntos(this, arrPuntos);
		lv.setAdapter(adaptador);
		
	}
	
	public void borrarPuntuaciones(View v)
	{
		db = new SQLiteManager(this);
		db.borrarTodo();
		db.close();
	}
}
