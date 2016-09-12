//package com.sichuan.geologenvi.views;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.drawable.Drawable;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.sichuan.geologenvi.R;
//import com.tianditu.android.maps.GeoPoint;
//import com.tianditu.android.maps.ItemizedOverlay;
//import com.tianditu.android.maps.MapView;
//import com.tianditu.android.maps.Overlay;
//import com.tianditu.android.maps.OverlayItem;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ItemOverlay extends ItemizedOverlay<OverlayItem> implements Overlay.Snappable {
//
//	private MapView mMapView;
//	private View mPopView;
//	private List<OverlayItem> GeoList = new ArrayList<OverlayItem>();
//	private Activity mContext;
//
//	public ItemOverlay(Drawable marker, Activity context, MapView mMapView, View mPopView) {
//		super((boundCenterBottom(marker)));
//
//		this.mMapView=mMapView;
//		this.mPopView=mPopView;
//		mContext = context;
//		GeoPoint p1 = new GeoPoint((int) (30.67 * 1E6), (int) (104.06 * 1E6));
//		GeoList.add(new OverlayItem(p1, "P1", "point1"));
//		populate();
//	}
//
//	@Override
//	protected OverlayItem createItem(int i) {
//		return GeoList.get(i);
//	}
//
//	@Override
//	public int size() {
//		return GeoList.size();
//	}
//
//	@Override
//	protected boolean onTap(int i) {
//		GeoPoint pt = GeoList.get(i).getPoint();
//		mMapView.updateViewLayout( mPopView,
//				new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT, MapView.LayoutParams.WRAP_CONTENT,
//						pt, MapView.LayoutParams.BOTTOM_CENTER));
//		mPopView.setVisibility(View.VISIBLE);
////		mText.setText(GeoList.get(i).getTitle());
//		return true;
//	}
//
//	@Override
//	public boolean onTouchEvent(MotionEvent event, MapView mapView) {
//		// TODO Auto-generated method stub
//		return super.onTouchEvent(event, mapView);
//	}
//
//	@Override
//	public boolean onKeyUp(int keyCode, KeyEvent event, MapView mapView) {
//		// TODO Auto-generated method stub
//		return super.onKeyUp(keyCode, event, mapView);
//	}
//
//	@Override
//	public boolean onTrackballEvent(MotionEvent event, MapView mapView) {
//		// TODO Auto-generated method stub
//		return super.onTrackballEvent(event, mapView);
//	}
//
//	@Override
//	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
//		// TODO Auto-generated method stub
//		super.draw(canvas, mapView, shadow);
//	}
//
//	@Override
//	public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
//						long when) {
//		// TODO Auto-generated method stub
//		return super.draw(canvas, mapView, shadow, when);
//	}
//
//	@Override
//	public boolean onTap(GeoPoint p, MapView mapView) {
//		// TODO Auto-generated method stub
////		mMapView.removeView(mPopView);
//		return super.onTap(p, mapView);
//	}
//
//}