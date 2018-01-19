package com.dtx.finaldash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class RainbowdashPlayer 
{

	RectF rect;
		 
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
		
		length = 100; //estaba a 10
		height = 166;
		 
		x = screenX / 2 - 50; //estaba a 2
		y = screenY - height; //estaba a 20
		 

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
		rect.top = y - 55;
		rect.bottom = y + height/2;
		rect.left = x + 20;
		rect.right = x + length -20;
	}
}
