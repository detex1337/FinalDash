package com.dtx.finaldash;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class AdapterPuntos extends ArrayAdapter<Puntuacion>  {
	
	private Context context;
	ArrayList<Puntuacion> arrPuntos;
	Typeface tipodeLetra;	
	
	public AdapterPuntos(Context context, ArrayList<Puntuacion> array) 
	{
		super(context, R.layout.activity_puntuacion, array);
		this.context = context;
		this.arrPuntos = array;
		
		tipodeLetra = Typeface.createFromAsset(context.getAssets(), "fonts/Finalnew.ttf");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View item = LayoutInflater.from(this.context).inflate(R.layout.activity_puntuacion, null);
		
		TextView tvPuntos = (TextView) item.findViewById(R.id.tvPuntos);
		tvPuntos.setTypeface(tipodeLetra);
		
		tvPuntos.setText(Integer.toString(arrPuntos.get(position).getPuntos()));

		return item;
	}
}
