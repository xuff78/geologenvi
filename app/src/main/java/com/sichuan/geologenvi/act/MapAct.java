package com.sichuan.geologenvi.act;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.utils.ToastUtils;
import com.sichuan.geologenvi.views.ItemOverlay;
import com.sichuan.geologenvi.views.MarkerSupportView;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;
import com.tianditu.android.maps.Overlay;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 */
public class MapAct  extends AppFrameAct {

    private MapView mMapView = null;
    protected MapController mController = null;
    private MarkerSupportView  mPopView=null;

    public final static String savePath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/scgeoev/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        _setHeaderTitle("地图");
        initView();
        addMarker();
    }

    private void addMarker() {

        mPopView = new MarkerSupportView(this, "题目", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopView.setVisibility(View.GONE);
            }
        });
        mMapView.addView(mPopView.getPopView());
        mPopView.setVisibility(View.GONE);

        List<Overlay> list = mMapView.getOverlays();

        Resources res = getResources();
        Drawable marker = res.getDrawable(R.mipmap.icon_location_target);
        ItemOverlay mOverlay = new ItemOverlay(marker, this, mMapView, mPopView.getPopView());
        list.add(mOverlay);
    }

    private void initView() {
        _setRightHomeText("切换模式", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMapView.getMapType()==MapView.TMapType.MAP_TYPE_IMG) {
                    mMapView.setMapType(MapView.TMapType.MAP_TYPE_VEC);
                    ToastUtils.displayTextShort(MapAct.this, "切换为矢量图");
                }else {
                    mMapView.setMapType(MapView.TMapType.MAP_TYPE_IMG);
                    ToastUtils.displayTextShort(MapAct.this, "切换为影像图");
                }
            }
        });
        TOfflineMapManager offlineMapMng = new TOfflineMapManager(null);
        offlineMapMng.setMapPath(savePath);
        mMapView = (MapView)findViewById(R.id.mapview);
        mMapView.setMinZoomLevel(5);
        mMapView.setOfflineMaps(offlineMapMng.searchLocalMaps());
        mMapView.setPlaceName(true);
        mMapView.setLogoPos(MapView.LOGO_RIGHT_BOTTOM);
        mMapView.setSatellite(true);
        mMapView.setMapType(MapView.TMapType.MAP_TYPE_IMG);
        mController = mMapView.getController();
        mController.setZoom(5);
//        mController.setCenter(new GeoPoint((int)(30.67*1000000), (int)(104.06*1000000)));


        MyOverlay myLocation = new MyOverlay(this, mMapView);
        myLocation.enableCompass();  //显示指南针
        myLocation.enableMyLocation(); //显示我的位置
        mMapView.getOverlays().add(myLocation);

        LocationManager m_locationManager = ( LocationManager ) getSystemService( Context.LOCATION_SERVICE );
        if(m_locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            m_locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000, 0, myLocation);
        }

    }

    class MyOverlay extends MyLocationOverlay
    {
        public MyOverlay(Context context, MapView mapView) {
            super(context, mapView);
            // TODO Auto-generated constructor stub
        }
        protected boolean dispatchTap()
        {
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
                Toast.makeText(MapAct.this, strLog, Toast.LENGTH_SHORT).show();
            }
            GeoPoint point = getMyLocation();
            if(point != null)
                mMapView.getController().animateTo(point);
        }
    }
}
