package com.dtx.finaldash;

import java.io.IOException;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


<<<<<<< HEAD
public class PlayActivity extends Activity 
{
	// spaceInvadersView will be the view of the game
=======
public class PlayActivity extends Activity {
    // spaceInvadersView will be the view of the game
>>>>>>> Arreglos de codigo y optimizacion
    // It will also hold the logic of the game
    // and respond to screen touches as well
    private FinaldashView juego;
    private Display display;
    private Point size;
    private ImageView dash;
<<<<<<< HEAD
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
=======

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

>>>>>>> Arreglos de codigo y optimizacion
        // Get a Display object to access screen details
        display = getWindowManager().getDefaultDisplay();
        // Load the resolution into a Point object
        size = new Point();
        display.getSize(size);
<<<<<<< HEAD
 
        // Initialize gameView and set it as the view
        juego = new FinaldashView(this, size.x, size.y);
        setContentView(juego);
 
    }
 
=======

        // Initialize gameView and set it as the view
        juego = new FinaldashView(this, size.x, size.y);
        setContentView(juego);

    }

>>>>>>> Arreglos de codigo y optimizacion
    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();
<<<<<<< HEAD
 
        // Tell the gameView resume method to execute
        juego.resume();
    }
 
=======

        // Tell the gameView resume method to execute
        juego.resume();
    }

>>>>>>> Arreglos de codigo y optimizacion
    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();
<<<<<<< HEAD
 
        // Tell the gameView pause method to execute
        juego.pause();
    }
    
=======

        // Tell the gameView pause method to execute
        juego.pause();
    }

>>>>>>> Arreglos de codigo y optimizacion
}
