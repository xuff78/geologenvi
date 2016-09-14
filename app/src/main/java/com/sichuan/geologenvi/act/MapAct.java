package com.sichuan.geologenvi.act;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.esri.android.map.LocationDisplayManager;
import com.esri.android.map.MapView;
import com.esri.android.runtime.ArcGISRuntime;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.scgis.mmap.helper.TileCacheDBManager;
import com.scgis.mmap.map.SCGISTiledMapServiceLayer;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.bean.PopupInfoItem;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;
import com.sichuan.geologenvi.utils.ToastUtils;
import com.sichuan.geologenvi.views.MarkerSupportView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 */
public class MapAct  extends AppFrameAct {

    private final String dlgUrl="http://www.scgis.net.cn/iMap/iMapServer/DefaultRest/services/scmobile_dlg";
    private MarkerSupportView  mPopView=null;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private Location lct;
    private String addr="";
    private boolean firstMove=false;
    private MapView mMapView;
    private LocationDisplayManager lDisplayManager;


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


//        Resources res = getResources();
//        Drawable marker = res.getDrawable(R.mipmap.icon_location_target);
//        ItemOverlay mOverlay = new ItemOverlay(marker, this, mMapView, mPopView.getPopView());
//        list.add(mOverlay);
    }

    private void initView() {
//        _setRightHomeText("切换模式", new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(mMapView.getMapType()==MapView.TMapType.MAP_TYPE_IMG) {
//                    mMapView.setMapType(MapView.TMapType.MAP_TYPE_VEC);
//                    ToastUtils.displayTextShort(MapAct.this, "切换为矢量图");
//                }else {
//                    mMapView.setMapType(MapView.TMapType.MAP_TYPE_IMG);
//                    ToastUtils.displayTextShort(MapAct.this, "切换为影像图");
//                }
//            }
//        });
        ArcGISRuntime.setClientId("zzzz4r7FQDyeNu12");

        mMapView=(MapView)findViewById(R.id.tdtMap);
        // mMapView.setResolution(0.010986328125);
        mMapView.centerAt(new Point(105.444402, 28.871906), true);

        String mToken="YgA6oKQf_zMiFPwkc3GerqZq9dvmjc0smYCnhwEZPngytdYrPjwSa22d90lgVZ4q";//token ʹ�����ͼ�Ĵ����û�������

        long dlgdbsize = 100;//  100MB
        int dlgTileCompress=75;  //ͼƬѹ��75%

        TileCacheDBManager mDLGTileDBManager=new TileCacheDBManager(this,"iDLGTile1.db");  //�������߻����
        final SCGISTiledMapServiceLayer mDLGTileMapServiceLayer=new SCGISTiledMapServiceLayer(this,dlgUrl,mToken,true,mDLGTileDBManager);

        mDLGTileMapServiceLayer.setCacheSize(dlgdbsize); //�����ļ���С����
        mDLGTileMapServiceLayer.setTileCompressAndQuality(true, dlgTileCompress);  //����Ƭ����ѹ��
        mMapView.addLayer(mDLGTileMapServiceLayer);
        mMapView.setExtent(new Envelope(95,24,110,35));
        mMapView.centerAt(28.871906, 105.444402, true);
        mDLGTileMapServiceLayer.setVisible(true);
        //************************************************************************
        //mDLGTileMapServiceLayer.setStartLevel(3);
        //mDLGTileMapServiceLayer.setStopLevel(20);
        mMapView.setMinScale(mDLGTileMapServiceLayer.getMinScale());
        mMapView.setMaxScale(mDLGTileMapServiceLayer.getMaxScale());
        // mMapView.zo(mDLGTileMapServiceLayer.getMaxScale(), true);
        //mMapView.setMaxResolution(mDLGTileMapServiceLayer.getMaxResolution());//ͨ��������Կ��Ƶ�ͼ����ʾ����
        // mMapView.setMinResolution(mDLGTileMapServiceLayer.getMinResolution());//ͨ��������Կ��Ƶ�ͼ����ʾ����
        //************************************************************************
        //ArcGISDynamicMapServiceLayer dynamicLayer=new ArcGISDynamicMapServiceLayer("http://apps.tianditucd.cn/cdmap/rest/services/SampleWorldCities/MapServer");
        //dynamicLayer.setOpacity(0.5f);
        //mMapView.addLayer(dynamicLayer);
        //"http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/Demographics/ESRI_Population_World/MapServer"));




        lDisplayManager = mMapView.getLocationDisplayManager();
        lDisplayManager.setLocationListener(new LocationListener() {
            //boolean locationChanged = false;

            public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub
//                currentLocation=location;
                if(location!=null){
                    LogUtil.i("Location", "getLocation: "+location.getLatitude()+"    "+location.getLongitude());
                    mMapView.centerAt(location.getLatitude(), location.getLongitude(), true);
                }

            }

            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub

            }

            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub

            }

            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                // TODO Auto-generated method stub

            }


        });
        lDisplayManager.setAccuracyCircleOn(true);
        lDisplayManager.setAutoPanMode(LocationDisplayManager.AutoPanMode.OFF);
        //lDisplayManager.set
        lDisplayManager.start();

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

//                GeoPoint point = myLocation.getMyLocation();
//                if(point != null)
//                    mMapView.getController().animateTo(point);
            }
        });
    }


    @Override
    protected void onDestroy() {
        if(mLocationClient!=null){
            mLocationClient.stop();
            mMapView.destroyDrawingCache();
            lDisplayManager.stop();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.pause();
    }
    @Override 	protected void onResume() {
        super.onResume();
        mMapView.unpause();

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
//            myLocation.onLocationChanged(lct);
            addr=location.getAddrStr();

            if(!firstMove) {
//                GeoPoint point = myLocation.getMyLocation();
//                if (point != null) {
//                    mMapView.getController().animateTo(point);
//                    firstMove = true;
//                }
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
