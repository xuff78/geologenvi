package com.sichuan.geologenvi.act;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.bean.PopupInfoItem;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;
import com.sichuan.geologenvi.utils.ToastUtils;
import com.sichuan.geologenvi.views.ItemOverlay;
import com.sichuan.geologenvi.views.MarkerSupportView;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;
import com.tianditu.android.maps.Overlay;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 */
public class MapAct  extends AppFrameAct {

    private MapView mMapView = null;
    protected MapController mController = null;
    private MarkerSupportView  mPopView=null;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private MyOverlay myLocation;
    private Location lct;
    private String addr="";
    private boolean firstMove=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        _setHeaderTitle("地图");
        SharedPreferencesUtil.setInt(this, ConstantUtil.map_open, 1);
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );    //注册监听函数
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

//        Resources res = getResources();
//        Drawable marker = res.getDrawable(R.mipmap.icon_location_target);
//        ItemOverlay mOverlay = new ItemOverlay(marker, this, mMapView, mPopView.getPopView());
//        list.add(mOverlay);
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
        offlineMapMng.setMapPath(ConstantUtil.OfflinePath);
        mMapView = (MapView)findViewById(R.id.mapview);
        mMapView.setMinZoomLevel(5);
        mMapView.setOfflineMaps(offlineMapMng.searchLocalMaps());
        mMapView.setPlaceName(true);
        mMapView.setLogoPos(MapView.LOGO_RIGHT_BOTTOM);
        mMapView.setSatellite(true);
        mMapView.setMapType(MapView.TMapType.MAP_TYPE_IMG);
        mController = mMapView.getController();
        mController.setZoom(8);
        mMapView.setOfflineMaps(offlineMapMng.searchLocalMaps());
//        mController.setCenter(new GeoPoint((int)(30.67*1000000), (int)(104.06*1000000)));


        myLocation = new MyOverlay(this, mMapView);
        myLocation.enableMyLocation(); //显示我的位置
        mMapView.getOverlays().add(myLocation);

        LocationManager m_locationManager = ( LocationManager ) getSystemService( Context.LOCATION_SERVICE );
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = m_locationManager.getBestProvider(criteria, true);
        lct=new Location(provider);
        mLocationClient.start();

//        if(provider!=null) {
//            m_locationManager.requestLocationUpdates(provider, 1000, 0, myLocation);
//            Location location = m_locationManager.getLastKnownLocation(provider);
//            myLocation.onLocationChanged(location);
//        }
//
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        option.setCoorType("bd09ll");
        option.setCoorType("gcj02");
        int span=10000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
        findViewById(R.id.myLoc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GeoPoint point = myLocation.getMyLocation();
                if(point != null)
                    mMapView.getController().animateTo(point);
            }
        });
    }

    class MyOverlay extends MyLocationOverlay
    {
        public MyOverlay(Context context, MapView mapView) {
            super(context, mapView);
            // TODO Auto-generated constructor stub
        }
        protected boolean dispatchTap()
        {
            if(!mPopView.getPopView().isShown()){
                GeoPoint geo=new GeoPoint((int)(lct.getLatitude() * 1000000.0D), (int)(lct.getLongitude() * 1000000.0D));
                ArrayList<PopupInfoItem> datas=new ArrayList<>();
                datas.add(new PopupInfoItem("经度", lct.getLongitude()+""));
                datas.add(new PopupInfoItem("纬度度", lct.getLatitude()+""));
                if(addr!=null&&addr.length()>0)
                    datas.add(new PopupInfoItem("位置", addr));
                mPopView.setListView(datas);
                mMapView.updateViewLayout( mPopView.getPopView(),
                        new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT, MapView.LayoutParams.WRAP_CONTENT,
                                geo, MapView.LayoutParams.BOTTOM_CENTER));
                mPopView.getPopView().setVisibility(View.VISIBLE);
            }else
                mPopView.getPopView().setVisibility(View.GONE);
            return true;
        }
        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            super.onLocationChanged(location);
        }
    }

    @Override
    protected void onDestroy() {
        if(mLocationClient!=null){
            mLocationClient.stop();
            mMapView.destroyDrawingCache();
        }
        super.onDestroy();
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location

//            bd_decrypt(location.getLatitude(), location.getLongitude());
//            lct.setLatitude(location.getLatitude());
//            lct.setLongitude(location.getLongitude());
            lct.setLatitude(location.getLatitude()-0.00374531687912);
            lct.setLongitude(location.getLongitude()-0.008774687519);
            myLocation.onLocationChanged(lct);
            addr=location.getAddrStr();

            if(!firstMove) {
                GeoPoint point = myLocation.getMyLocation();
                if (point != null) {
                    mMapView.getController().animateTo(point);
                    firstMove = true;
                }
            }

            LogUtil.i("Location", "location: "+location.getLatitude()+"   "+location.getLongitude());
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
        }
    }

    private void bd_decrypt(double bd_lat, double bd_lon)
    {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * Math.PI);
        lct.setLongitude(z * Math.cos(theta));
        lct.setLatitude(z * Math.sin(theta));
    }
}
