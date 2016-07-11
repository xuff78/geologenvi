package com.sichuan.geologenvi.act;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import sdkdemo.com.R;

import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;
import com.tianditu.android.maps.Overlay;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MyLocationDemo extends Activity{
	private MapView mMapView = null;
	protected View   mViewLayout   = null;
	protected Context mCon   = null;
	int   mCount = 0;
	Handler handl;
	 MyLocationOverlay mMyLocation = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = LayoutInflater.from(this);
		//�������й���һ��
		mViewLayout = inflater.inflate(R.layout.mylocation, null);
		setContentView(mViewLayout);
		mMapView = (MapView)findViewById(R.id.mapview);
		mMapView.displayZoomControls(true);
		mCon = this;
		List<Overlay> list = mMapView.getOverlays();
		mMyLocation = new MyOverlay(this, mMapView);
		mMyLocation.enableCompass();
		mMyLocation.enableMyLocation();
		list.add(mMyLocation);

		handl = new Handler()
		{
			public void handleMessage(Message msg)
			{
				Location ll = new Location(LocationManager.GPS_PROVIDER);
				if(mCount%2==1)
				{
					ll.setLatitude(39.880000);
					ll.setLongitude(116.310000);
				}else
				{
					ll.setLatitude(39.890000);
					ll.setLongitude(116.310000);
				}
				mCount++;
				mMyLocation.onLocationChanged(ll);
			}
		};
		TimerTask MoveTask = new TimerTask() {  
	  		@Override public void run() {
	  			Message msg = handl.obtainMessage(2);
	  			handl.sendMessage(msg);
	  			
	  	    }
	  	};
	  	Timer 			mMoveTimer = new Timer();
	  	mMoveTimer.schedule(MoveTask,1000,2000);
		LocationManager    m_locationManager = ( LocationManager ) getSystemService( Context.LOCATION_SERVICE );
		if(m_locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
		    m_locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,  
			    	1000, 0, mMyLocation); 
		}
	
        ImageButton location = (ImageButton)findViewById(R.id.location);
		location.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GeoPoint point = mMyLocation.getMyLocation();
				if(point != null)
					mMapView.getController().animateTo(point);
			}
			
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		mMapView.getController().stopAnimation(false);
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
			System.exit(0);
		return super.onKeyUp(keyCode, event);
	}

	class MyOverlay extends MyLocationOverlay 
	{
		public MyOverlay(Context context, MapView mapView) {
			super(context, mapView);
			// TODO Auto-generated constructor stub
		}
		/*
		 * ������"�ҵ�λ��"�ϵĵ���¼�
		 * */
		protected boolean dispatchTap()
		{
			Toast.makeText(mCon, "�������ҵ�λ��", Toast.LENGTH_SHORT).show();
			return true;
		}
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			super.onLocationChanged(location);
			if(location != null){
				String strLog = String.format("��ǰ��λ��:\r\n" +
						"γ��:%f\r\n" +
						"����:%f",
						location.getLongitude(), location.getLatitude());
				Toast.makeText(mCon, strLog, Toast.LENGTH_SHORT).show();
			}
			GeoPoint point = mMyLocation.getMyLocation();
			if(point != null)
				mMapView.getController().animateTo(point);
		}
	}
}
