package com.dtx.finaldash;

import java.io.IOException;
import java.util.Random;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FinaldashView extends SurfaceView implements Runnable
{
	private Context context;
	private SQLiteManager db;
	private Puntuacion puntos;
	private boolean gameOver = false;
	private Thread hiloJuego = null;
	private SurfaceHolder ourHolder;
	private volatile boolean jugando;
	private boolean pausa = true;
	private Canvas canvas;
	private Paint paint, paintLetras, fondoLetras;
	private RectF rectFondo;
	private Bitmap fondo;
	private int velocidadFondo = 0;
	private long fps;
	private long timeThisFrame;
	private MediaPlayer cancionJuego;
	private int cancionJuegoMilis;
 
	private int screenX;
	private int screenY;
 
	private RainbowdashPlayer playerShip;
	private Obstaculo roca1, roca2;
	private Obstaculo nube1, nube2, nube3;
	private Obstaculo boost;
	private int tipoRoca = 1;
	private int tipoNube = 2;
	private int tipoBoost = 3;

	private int puntuacion = 0;
	private int vidas = 3;
	
	private SoundPool soundPool;
    private int contraNube = -1;
    private int contraRoca = -1;
    private int contraBoost = -1;

	private Vibrator vb;

	public FinaldashView(Context context, int x, int y) 
	{
		super(context);
		this.context = context;
		  
		ourHolder = getHolder();
		paint = new Paint();
		paintLetras = new Paint();
		fondoLetras = new Paint();

		vb = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		
		try
		{
		    AssetManager assetManager = context.getAssets();
		    AssetFileDescriptor descriptor;
		
		    descriptor = assetManager.openFd("contraNube.mp3");
		    contraNube = soundPool.load(descriptor, 0);
		 
		    descriptor = assetManager.openFd("contraRoca.mp3");
		    contraRoca = soundPool.load(descriptor, 0);
		 
		    descriptor = assetManager.openFd("contraBoost.mp3");
		    contraBoost = soundPool.load(descriptor, 0);
		}
		catch(IOException e)
		{
		    // Print an error message to the console
		    Log.e("error", "Fallo al cargar sonidos.");
		}
		  
		screenX = x;
		screenY = y;
		
		prepareLevel();
	}
  
	private void prepareLevel()
	{
		puntuacion = 0;
		vidas = 3;
		gameOver = false;

		fondo = BitmapFactory.decodeResource(context.getResources(),R.drawable.road);
		fondo = Bitmap.createScaledBitmap(fondo, screenX, screenY, false);

		playerShip = new RainbowdashPlayer(context, screenX, screenY);

		roca1 = new Obstaculo(context, screenY, tipoRoca);
		roca2 = new Obstaculo(context, screenY, tipoRoca);
		nube1 = new Obstaculo(context, screenY, tipoNube);
		nube2 = new Obstaculo(context, screenY, tipoNube);
		nube3 = new Obstaculo(context, screenY, tipoBoost);
		boost = new Obstaculo(context, screenY, tipoBoost);
		
		cancionJuego = MediaPlayer.create(context, R.raw.playcts);
		cancionJuego.setAudioStreamType(AudioManager.STREAM_MUSIC);
		cancionJuego.setLooping(true);
	}

	@Override
	public void run() 
	{
		while (jugando) 
		{
			long startFrameTime = System.currentTimeMillis();

			if(!pausa)
			{
				update();
			}
	  
			draw();
		  
			Random rand = new Random();
			int rocaPos = rand.nextInt(screenX-100);
			int nubePos = rand.nextInt(screenX-100);
			int boostPos = rand.nextInt(screenX-100);
			int rocaVel = rand.nextInt(250);
			int rocaVel2 = rand.nextInt(250);
			int nubeVel = rand.nextInt(250);
			int nubeVel2 = rand.nextInt(250);
			int nubeVel3 = rand.nextInt(250);
			int boostVel = rand.nextInt(250);
			
			roca1.shoot(rocaPos, -100, roca1.DOWN, rocaVel+200);
			roca2.shoot(rocaPos, -100, roca2.DOWN, rocaVel2+200);
			nube1.shoot(nubePos, -100, nube1.DOWN, nubeVel+300);
			nube2.shoot(nubePos, -100, nube2.DOWN, nubeVel2+300);
			nube3.shoot(nubePos, -100, nube3.DOWN, nubeVel3+300);
			boost.shoot(boostPos, -100, nube1.DOWN, boostVel+150);
			
			timeThisFrame = System.currentTimeMillis() - startFrameTime;
			if (timeThisFrame >= 1) 
			{
				fps = 1000 / timeThisFrame;
			} 
		}
	}
 
	private void update()
	{
		playerShip.update(fps);

		if(gameOver)
		{
			pausa = true;
			cancionJuegoMilis = cancionJuego.getCurrentPosition();
			cancionJuego.pause();
			prepareLevel();
		}
		  
		if(roca1.getStatus())
		{
			roca1.update(fps);
		}
		if(roca2.getStatus())
		{
			roca2.update(fps);
		}
		if(nube1.getStatus())
		{
			nube1.update(fps);
		}
		if(nube2.getStatus())
		{
			nube2.update(fps);
		}
		if(nube3.getStatus())
		{
			nube3.update(fps);
		}
		if(boost.getStatus())
		{
			boost.update(fps);
		}
		 
		// AQUI SE COMPRUEBA QUE HASTA QUE EL OBSTACULO NO TOQUE EL SUELO NO SALE OTRO
		if(roca1.getImpactPointY() > screenY+200)
		{
		    roca1.setInactive();
		    puntuacion = puntuacion + 1000;
		} 
		if(roca2.getImpactPointY() > screenY+200)
		{
		    roca2.setInactive();
		    puntuacion = puntuacion + 1000;
		}  
		if(nube1.getImpactPointY() > screenY+200)
		{
			nube1.setInactive();
			puntuacion = puntuacion + 100;
		} 
		if(nube2.getImpactPointY() > screenY+200)
		{
			nube2.setInactive();
			puntuacion = puntuacion + 100;
		} 
		if(nube3.getImpactPointY() > screenY+200)
		{
			nube3.setInactive();
			puntuacion = puntuacion + 100;
		} 
		if(boost.getImpactPointY() > screenY+200)
		{
			boost.setInactive();
		} 

		puntuacion++;
		  
		// AQUI SE COMPRUEBA QUE EL OBSTACULO CHOCA CONTRA EL JUGADOR
		if(roca1.getStatus())
		{
        	if(RectF.intersects(playerShip.getRect(), roca1.getRect()))
        	{
        		roca1.setInactive();
        		soundPool.play(contraRoca, 1, 1, 0, 0, 1);
        		vb.vibrate(600);
                vidas = vidas - 3;
                puntuacion = 0;
                if(vidas <= 0)
                {
                	db = new SQLiteManager(context);
                	puntos = new Puntuacion(1, puntuacion);
                	db.agregarPuntos(puntos);
                	db.close();
                	gameOver = true;
                }
            }
        }
		if(roca2.getStatus())
		{
        	if(RectF.intersects(playerShip.getRect(), roca2.getRect()))
        	{
        		roca2.setInactive();
        		soundPool.play(contraRoca, 1, 1, 0, 0, 1);
        		vb.vibrate(1000);
        		vidas = vidas - 3;
				puntuacion = 0;
                if(vidas <= 0)
                {
                	db = new SQLiteManager(context);
                	puntos = new Puntuacion(1, puntuacion);
                	db.agregarPuntos(puntos);
                	db.close();
                	gameOver = true;
                }
            }
        }
		if(nube1.getStatus())
		{
        	if(RectF.intersects(playerShip.getRect(), nube1.getRect()))
        	{
        		nube1.setInactive();
        		soundPool.play(contraNube, 1, 1, 0, 0, 1);
        		vb.vibrate(250);
        		puntuacion = puntuacion - 50;
        		vidas --;
                if(vidas <= 0)
                {
                	db = new SQLiteManager(context);
                	puntos = new Puntuacion(1, puntuacion);
                	db.agregarPuntos(puntos);
                	db.close();
                	gameOver = true;
                }
            }
        }
		if(nube2.getStatus())
		{
        	if(RectF.intersects(playerShip.getRect(), nube2.getRect()))
        	{
        		nube2.setInactive();
        		soundPool.play(contraNube, 1, 1, 0, 0, 1);
        		vb.vibrate(250);
        		puntuacion = puntuacion - 50;
        		vidas --;
                if(vidas <= 0)
                {
                	db = new SQLiteManager(context);
                	puntos = new Puntuacion(1, puntuacion);
                	db.agregarPuntos(puntos);
                	db.close();
                	gameOver = true;
                }
            }
        }
		if(nube3.getStatus())
		{
        	if(RectF.intersects(playerShip.getRect(), nube3.getRect()))
        	{
        		nube3.setInactive();
        		soundPool.play(contraNube, 1, 1, 0, 0, 1);
        		vb.vibrate(250);
        		puntuacion = puntuacion - 50;
        		vidas --;
                if(vidas <= 0)
                {
                	db = new SQLiteManager(context);
                	puntos = new Puntuacion(1, puntuacion);
                	db.agregarPuntos(puntos);
                	db.close();
                	gameOver = true;
                }
            }
        }
		if(boost.getStatus())
		{
        	if(RectF.intersects(playerShip.getRect(), boost.getRect()))
        	{
        		boost.setInactive();
        		soundPool.play(contraBoost, 1, 1, 0, 0, 1);
        		puntuacion = puntuacion + 25;
//                if(vidas < 3)
//                {
                	vidas ++;
//                }
            }
        }
	}
  
	private void draw()
	{
		if (ourHolder.getSurface().isValid()) 
		{
			canvas = ourHolder.lockCanvas();
			canvas.drawColor(Color.argb(255, 0, 0, 0));
			
			dibujarFondo(canvas); 
			
			paint.setColor(Color.argb(255, 0, 0, 0));
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(1);

			//Las siguientes lineas comentadas se pueden descomentar si quieres ver el area
			//de colision de los objetos en la pantalla.

			//canvas.drawRect(playerShip.getRect(), paint);
			canvas.drawBitmap(playerShip.getBitmap(), playerShip.getX(), screenY-220, paint);
			
			if(roca1.getStatus())
			{
				//canvas.drawRect(roca1.getRect(), paint);
				canvas.drawBitmap(roca1.getBitmap(), roca1.getX(), roca1.getY(), paint);
			}
			
			if(roca2.getStatus())
			{
				//canvas.drawRect(roca2.getRect(), paint);
				canvas.drawBitmap(roca2.getBitmap(), roca2.getX(), roca2.getY(), paint);
			}
			
			if(nube1.getStatus())
			{
				//canvas.drawRect(nube1.getRect(), paint);
				canvas.drawBitmap(nube1.getBitmap(), nube1.getX(), nube1.getY(), paint);
			}
			
			if(nube2.getStatus())
			{
				//canvas.drawRect(nube2.getRect(), paint);
				canvas.drawBitmap(nube2.getBitmap(), nube2.getX(), nube2.getY(), paint);
			}
			
			if(nube3.getStatus())
			{
				//canvas.drawRect(nube3.getRect(), paint);
				canvas.drawBitmap(nube3.getBitmap(), nube3.getX(), nube3.getY(), paint);
			}
			
			if(boost.getStatus())
			{
				//canvas.drawRect(boost.getRect(), paint);
				canvas.drawBitmap(boost.getBitmap(), boost.getX(), boost.getY(), paint);
			}

			fondoLetras.setStyle(Paint.Style.FILL);
			fondoLetras.setColor(Color.CYAN);
			rectFondo = new RectF(10,15,480,55);
			canvas.drawRoundRect(rectFondo, 5, 5, fondoLetras);

			paintLetras.setColor(Color.argb(255, 0, 0, 0));
			paintLetras.setTextSize(40);
			canvas.drawText("Puntuacion: " + puntuacion + "   Vidas: " + vidas, 10, 50, paintLetras);

			ourHolder.unlockCanvasAndPost(canvas);
		}
	}
	
	public void pause() 
	{
		jugando = false;
		cancionJuegoMilis = cancionJuego.getCurrentPosition();
		cancionJuego.pause();
		try 
		{
			hiloJuego.join();
		} 
		catch (InterruptedException e) 
		{
			Log.e("Error:", "joining thread");
		}  
	}
 
	public void resume() 
	{
		jugando = true;
		cancionJuego.seekTo(cancionJuegoMilis);
		if(cancionJuego.isPlaying())
		{
			cancionJuego.start();
		}
		hiloJuego = new Thread(this);
		hiloJuego.start();
	}
  
	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) 
	{
		switch (motionEvent.getAction() & MotionEvent.ACTION_MASK)
		{
			case MotionEvent.ACTION_DOWN:
				pausa = false;
				cancionJuego.start();
				if (motionEvent.getX() > screenX / 2)
				{
					playerShip.setMovementState(playerShip.RIGHT);
				} 
				else 
				{
					playerShip.setMovementState(playerShip.LEFT);
				}
				break;

			case MotionEvent.ACTION_UP:
				playerShip.setMovementState(playerShip.STOPPED);
			break;
		}
	return true;
	}
	
	private void dibujarFondo(Canvas canvas)
	{
		if(!pausa)
		{
			velocidadFondo = velocidadFondo + 2;
	
			int newFarY = fondo.getHeight() - (velocidadFondo);
	
			if (newFarY <= 0) 
			{
				velocidadFondo = 0;
				canvas.drawBitmap(fondo, 0, velocidadFondo, null);
			} 
			else 
			{
				canvas.drawBitmap(fondo, 0, velocidadFondo, null);
				canvas.drawBitmap(fondo, 0, -newFarY, null);
			}
		}
	}
}

