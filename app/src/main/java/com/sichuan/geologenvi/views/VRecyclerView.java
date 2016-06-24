package com.sichuan.geologenvi.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class VRecyclerView extends RecyclerView {

	float xLast, yLast, xDistance, yDistance;

	public VRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
          
        switch (ev.getAction()) {    
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;    
                xLast = ev.getX();    
                yLast = ev.getY();    
                break;    
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();    
                final float curY = ev.getY();    
                    
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;    
                yLast = curY;    
                    
                if(xDistance > yDistance){    
                    return false;    
                }      
        }  
    
        return super.onInterceptTouchEvent(ev);    
    }  

}
