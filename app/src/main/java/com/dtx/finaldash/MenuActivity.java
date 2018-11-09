package com.dtx.finaldash;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

<<<<<<< HEAD
public class MenuActivity extends Activity 
{
	private MediaPlayer cancionMenu;
	private Typeface tipodeLetra;
	private int cancionMilis;

	private TextView titulo1;
	private TextView titulo2;

	private Button play;
	private Button scores;
	private Button exit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		tipodeLetra = Typeface.createFromAsset(getAssets(), "fonts/Finalnew.ttf"); //Cogemos nuestra fuente de assets
		titulo1 = (TextView) findViewById(R.id.tvTitulo1);
		titulo2 = (TextView) findViewById(R.id.tvTitulo2);
		play = (Button) findViewById(R.id.btn_play);
		scores = (Button) findViewById(R.id.btn_scores);
		exit = (Button) findViewById(R.id.btn_exit);
		titulo1.setTypeface(tipodeLetra);
		titulo2.setTypeface(tipodeLetra);
		play.setTypeface(tipodeLetra);
		scores.setTypeface(tipodeLetra);
		exit.setTypeface(tipodeLetra);
		cancionMenu = MediaPlayer.create(this, R.raw.menu);
		cancionMenu.setAudioStreamType(AudioManager.STREAM_MUSIC);
		cancionMenu.setLooping(true);
		cancionMenu.start();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}


	public void onClickPlay(View v)
	{
		Intent goPlay = new Intent(this,PlayActivity.class);
		cancionMilis = cancionMenu.getCurrentPosition();
		cancionMenu.pause();
		startActivity(goPlay);
	}
	
	public void onClickScores(View v)
	{
		Intent goScores = new Intent(this,ScoresActivity.class);
		cancionMilis = cancionMenu.getCurrentPosition();
		cancionMenu.pause();
		startActivity(goScores);
	}
	
	public void onClickExit(View v)
	{
		cancionMilis = cancionMenu.getCurrentPosition();
		cancionMenu.pause();
		this.finish();
	}
	
	@Override
	protected void onResume() 
	{
		cancionMenu.seekTo(cancionMilis);
		cancionMenu.start();
		super.onResume();
	}

	@Override
	protected void onPause() 
	{
		cancionMilis = cancionMenu.getCurrentPosition();
		cancionMenu.pause();
		super.onPause();
	}
=======
public class MenuActivity extends Activity {
    private MediaPlayer cancionMenu;
    private Typeface tipodeLetra;
    private int cancionMilis;

    private TextView titulo1;
    private TextView titulo2;

    private Button play;
    private Button scores;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tipodeLetra = Typeface.createFromAsset(getAssets(), "fonts/Finalnew.ttf"); //Cogemos nuestra fuente de assets
        titulo1 = (TextView) findViewById(R.id.tvTitulo1);
        titulo2 = (TextView) findViewById(R.id.tvTitulo2);
        play = (Button) findViewById(R.id.btn_play);
        scores = (Button) findViewById(R.id.btn_scores);
        exit = (Button) findViewById(R.id.btn_exit);
        titulo1.setTypeface(tipodeLetra);
        titulo2.setTypeface(tipodeLetra);
        play.setTypeface(tipodeLetra);
        scores.setTypeface(tipodeLetra);
        exit.setTypeface(tipodeLetra);
        cancionMenu = MediaPlayer.create(this, R.raw.menu);
        cancionMenu.setAudioStreamType(AudioManager.STREAM_MUSIC);
        cancionMenu.setLooping(true);
        cancionMenu.start();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    public void onClickPlay(View v) {
        Intent goPlay = new Intent(this, PlayActivity.class);
        cancionMilis = cancionMenu.getCurrentPosition();
        cancionMenu.pause();
        startActivity(goPlay);
    }

    public void onClickScores(View v) {
        Intent goScores = new Intent(this, ScoresActivity.class);
        cancionMilis = cancionMenu.getCurrentPosition();
        cancionMenu.pause();
        startActivity(goScores);
    }

    public void onClickExit(View v) {
        cancionMilis = cancionMenu.getCurrentPosition();
        cancionMenu.pause();
        this.finish();
    }

    @Override
    protected void onResume() {
        cancionMenu.seekTo(cancionMilis);
        cancionMenu.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        cancionMilis = cancionMenu.getCurrentPosition();
        cancionMenu.pause();
        super.onPause();
    }
>>>>>>> Arreglos de codigo y optimizacion
}
