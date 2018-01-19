package com.dtx.finaldash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class Obstaculo 
{
	private float x;
	private float y;
	private RectF rect;
	private Bitmap bitmap;

	public final int UP = 0;
	public final int DOWN = 1;

	int heading = -1;
	float speed =  350;
	 
	private int width = 100;
	private int height = 100;
	 
	private boolean isActive;
		
	public Obstaculo(Context context, int screenY, int tipo) 
	{
		isActive = false;
		rect = new RectF();
		
		switch (tipo) 
		{
		case 1:
			bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.rock);
			width = 100;
			break;
		case 2:
			bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.nube);
			width = 200;
			break;
		case 3:
			bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.boost);
			width = 70;
			break;
		default:
			break;
		}
 		bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
	}
	
	public RectF getRect()
	{
	    return  rect;
	}
	
	public Bitmap getBitmap()
	{
	    return bitmap;
	}
	
	public boolean getStatus()
	{
	    return isActive;
	}
	 
	public void setInactive()
	{
	    isActive = false;
	}
	 
	public float getImpactPointY()
	{
	    if (heading == DOWN)
	    {
	        return y + height;
	    }
	    else
	    {
	        return  y;
	    }
	}
	
	public boolean shoot(float startX, float startY, int direction, int speed) 
	{
	    if (!isActive) 
	    {
	        x = startX;
	        y = startY;
	        this.speed = speed;
	        heading = direction;
	        isActive = true; //AQUI ES TRUE
	        return true;
	    }
	    return false;
	}
	
	public void update(long fps)
	{ 
	    if(heading == UP)
	    {
	        y = y - speed / fps;
	    }
	    else
	    {
	        y = y + speed / fps;
	    }
	    // actu rect
	    rect.left = x;
	    rect.right = x + width;
	    rect.top = y;
	    rect.bottom = y + height;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}