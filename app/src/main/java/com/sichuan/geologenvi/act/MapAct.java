package com.sichuan.geologenvi.act;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.sichuan.geologenvi.R;
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
        TOfflineMapManager offlineMapMng = new TOfflineMapManager(null);
        offlineMapMng.setMapPath("/sdcard/scgeoev/");
        mMapView = (MapView)findViewById(R.id.mapview);
        mMapView.setMinZoomLevel(5);
        mMapView.setOfflineMaps(offlineMapMng.searchLocalMaps());
        mMapView.setPlaceName(true);
        mMapView.setLogoPos(MapView.LOGO_RIGHT_BOTTOM);
        mMapView.setSatellite(true);
        mMapView.setMapType(MapView.TMapType.MAP_TYPE_VEC);
//        mMapView.setBuiltInZoomControls(true);
        mController = mMapView.getController();
        mController.setZoom(5);
        mController.setCenter(new GeoPoint((int)(30.67*1000000), (int)(104.06*1000000)));
    }
}
