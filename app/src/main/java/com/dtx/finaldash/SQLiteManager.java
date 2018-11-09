package com.dtx.finaldash;


import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

<<<<<<< HEAD
public class SQLiteManager extends SQLiteOpenHelper
{
	private static String DB_NAME = "finaldash.db";
	private static int DB_VERSION = 1;
	private SQLiteDatabase db = null;
	
	
	public SQLiteManager(Context context) 
	{
		super(context, DB_NAME, null, DB_VERSION);
		this.db = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL("CREATE TABLE IF NOT EXISTS PUNTUACIONES(ID INTEGER PRIMARY KEY, PUNTOS TEXT)");
	}
	
	public void agregarPuntos(Puntuacion puntuacion)
	{
		db.execSQL("INSERT INTO PUNTUACIONES (PUNTOS) VALUES('"+puntuacion.getPuntos()+"')");
	}

	public ArrayList<Puntuacion> devolverPuntos()
	{
		ArrayList<Puntuacion> arrPuntos = new ArrayList<Puntuacion>();
		Cursor fila = db.rawQuery("SELECT * FROM PUNTUACIONES", null);
		if (fila!=null && fila.moveToFirst()) {
		     do 
		     {
		    	 Puntuacion puntos = new Puntuacion(fila.getInt(0), fila.getInt(1));
		    	 arrPuntos.add(puntos);
		     }
		     while(fila.moveToNext());
		}
		return arrPuntos;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public void borrarTodo()
	{
		db.execSQL("DELETE FROM PUNTUACIONES");
	}
	
	public void borrarPuntos(int id)
	{
		db.execSQL("DELETE FROM PUNTUACIONES WHERE ID ='"+id+"'");
	}
	
	public Puntuacion sacarPuntos(int id)
	{
		Puntuacion puntos = null;
		Cursor fila = db.rawQuery("SELECT * FROM NOTAS WHERE ID ='"+id+"'", null);
		if (fila!=null && fila.moveToFirst()) 
		{
			puntos = new Puntuacion(fila.getInt(0), fila.getInt(1));
		}
		return puntos;
	}	
=======
public class SQLiteManager extends SQLiteOpenHelper {
    private static String DB_NAME = "finaldash.db";
    private static int DB_VERSION = 1;
    private SQLiteDatabase db = null;


    public SQLiteManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS PUNTUACIONES(ID INTEGER PRIMARY KEY, PUNTOS TEXT)");
    }

    public void agregarPuntos(Puntuacion puntuacion) {
        db.execSQL("INSERT INTO PUNTUACIONES (PUNTOS) VALUES('" + puntuacion.getPuntos() + "')");
    }

    public ArrayList<Puntuacion> devolverPuntos() {
        ArrayList<Puntuacion> arrPuntos = new ArrayList<Puntuacion>();
        Cursor fila = db.rawQuery("SELECT * FROM PUNTUACIONES", null);
        if (fila != null && fila.moveToFirst()) {
            do {
                Puntuacion puntos = new Puntuacion(fila.getInt(0), fila.getInt(1));
                arrPuntos.add(puntos);
            }
            while (fila.moveToNext());
        }
        return arrPuntos;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public void borrarTodo() {
        db.execSQL("DELETE FROM PUNTUACIONES");
    }

    public void borrarPuntos(int id) {
        db.execSQL("DELETE FROM PUNTUACIONES WHERE ID ='" + id + "'");
    }

    public Puntuacion sacarPuntos(int id) {
        Puntuacion puntos = null;
        Cursor fila = db.rawQuery("SELECT * FROM NOTAS WHERE ID ='" + id + "'", null);
        if (fila != null && fila.moveToFirst()) {
            puntos = new Puntuacion(fila.getInt(0), fila.getInt(1));
        }
        return puntos;
    }
>>>>>>> Arreglos de codigo y optimizacion
}
