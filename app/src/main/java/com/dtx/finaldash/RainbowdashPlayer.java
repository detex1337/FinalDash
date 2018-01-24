package com.dtx.finaldash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class RainbowdashPlayer 
{

	private RectF rect;
		 
	private Bitmap bitmap;
	 
	private float length;
	private float height;
	
	private float x;
	private float y;
	 
	private float shipSpeed;

	public final int STOPPED = 0;
	public final int LEFT = 1;
	public final int RIGHT = 2;
	
	private int anchopantalla;

	private int shipMoving = STOPPED;

	public RainbowdashPlayer(Context context, int screenX, int screenY)
	{
		rect = new RectF();
		anchopantalla = screenX;

		length = screenX / 5; //estaba a 10
		height = screenY / 5;

		x = screenX / 2 - length / 2; //estaba a 2
		y = screenY / 2 - height / 2; //estaba a 20
		 

		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.rdtop);
		bitmap = Bitmap.createScaledBitmap(bitmap, (int) (length), (int) (height), false);
		
		shipSpeed = 350;
	}
    
	public RectF getRect()
	{
		return rect;
	}
 
	public Bitmap getBitmap()
	{
	    return bitmap;
	}
 
	public float getX()
	{
	    return x;
	}
	
	public float getY()
	{
	    return y;
	}
	 
	public float getLength()
	{
	    return length;
	}
		 
	public void setMovementState(int state)
	{
		shipMoving = state;
	}

	public void update(long fps)
	{
		if(shipMoving == LEFT && x > anchopantalla-anchopantalla+15)
		{
			x = x - shipSpeed / fps;
		}
		if(shipMoving == RIGHT && x < anchopantalla-length)
		{
			x = x + shipSpeed / fps;
		}
		// actu rect 
		rect.top = y;
		rect.bottom = y + height;
		rect.left = x;
		rect.right = x + length;
	}
}
