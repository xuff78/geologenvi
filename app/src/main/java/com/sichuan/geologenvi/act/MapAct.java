package com.sichuan.geologenvi.act;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.esri.android.map.Callout;
import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.ImageFilter;
import com.esri.android.map.LocationDisplayManager;
import com.esri.android.map.MapView;
import com.esri.android.map.event.OnLongPressListener;
import com.esri.android.map.event.OnPanListener;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.android.map.event.OnZoomListener;
import com.esri.android.runtime.ArcGISRuntime;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleFillSymbol;
import com.scgis.mmap.helper.TileCacheDBManager;
import com.scgis.mmap.map.SCGISTiledMapServiceLayer;
import com.sichuan.geologenvi.DataBase.QueryStr;
import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.adapter.ActivityInfoAdapter;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.bean.MapPoint;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;
import com.sichuan.geologenvi.utils.ToastUtils;
import com.sichuan.geologenvi.views.MarkerSupportView;
import com.sichuan.geologenvi.views.MenuPopup;
import com.sichuan.geologenvi.views.PSDdialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/23.
 */
public class MapAct  extends AppFrameAct {

    private final String dlgUrl = "http://www.scgis.net.cn/iMap/iMapServer/DefaultRest/services/scmobile_dlg";
    private final String imgUrl = "http://www.scgis.net.cn/iMap/iMapServer/DefaultRest/services/NewTiandituDOM/";
    private final String infoUrl = "http://www.scgis.net.cn/iMap/iMapServer/DefaultRest/services/sctilemap_dom_dlg/";

    private MarkerSupportView mPopView = null;
    private String addr = "";
    private boolean firstMove = false;
    private MapView mMapView;
    private LocationDisplayManager lDisplayManager;
    private LocationManager lm;
    private String InfoType = "infotype";
    private String bestProvider;
    private double lat = -1, lon = -1;
    private SqlHandler handler;
    private Point pressPoint;
    private GraphicsLayer mGraphicsLayer;
    private Map<Long, MapPoint> points = new LinkedHashMap<>();
    private ArrayList<Map<String, String>> datalist = new ArrayList<>();
    private Map<String, Map<String, String>> datamap = new HashMap();
    private Map<String, Map<String, String>> tempdataMap = new HashMap();
    private MenuPopup popup;
    private String[] typeNames = new String[]{"地质灾害点", "地下水", "矿山", "地质遗迹"};
    private int toLocation = 0;
    private int showType = 0; //-1 用户设置的点， 0，地址灾害
    private float mx = -1, my = -1; //滑动屏幕的坐标
    private LayoutInflater inflater;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private boolean isImg=true;
    private SCGISTiledMapServiceLayer mDLGTileMapServiceLayer, mDLGTileImgMapServiceLayer;
    private com.sichuan.geologenvi.SCGISTiledMapServiceLayer mDLGTileInfoMapServiceLayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        _setHeaderTitle("地图");
        handler = new SqlHandler(this);
        inflater = LayoutInflater.from(MapAct.this);
//        initGPS();
//        openGPS(this);
        initView();
        _setRightHomeText("灾害点", listener);
        addMarker();
        showUserMarker();

        openArcgisLocation();
        openBaiduLocation();

//        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        bestProvider = lm.getBestProvider(getCriteria(), true);
//        LogUtil.i("Location", "bestProvider: " + bestProvider);
//        Location location = lm.getLastKnownLocation(bestProvider);
//        lm.requestLocationUpdates(bestProvider, 2000, 50, locationListener);
//        if (location != null) {
//            LogUtil.i("Location", "getLastLocation: " + location.getLongitude() + "    " + location.getLatitude());
//            lon = location.getLongitude();
//            lat = location.getLatitude();
//        }

        //updateWithNewLocation(location);
    }

    private Criteria getCriteria() {
        // TODO Auto-generated method stub
        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_COARSE);
        c.setCostAllowed(true);
        c.setBearingRequired(false);
        c.setAltitudeRequired(false);
        c.setPowerRequirement(Criteria.POWER_LOW);
        return c;
    }

    private void showUserMarker() {
        String json = SharedPreferencesUtil.getString(this, ConstantUtil.UserPoint);
        points = JsonUtil.getUserPointByJson(json);
        for (Long key : points.keySet()) {
            MapPoint point = points.get(key);
            Map<String, Object> map = new HashMap<>();
            map.put("desc", point.getDesc());
            map.put("timeid", key);
            map.put(InfoType, -1);
            Graphic gp1 = CreateGraphic(point.getP(), map, R.mipmap.of_location_icon, 16);
            getGraphicLayer().addGraphic(gp1);
        }
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
    }

    private void initView() {
        ArcGISRuntime.setClientId("zzzz4r7FQDyeNu12");

        mMapView = (MapView) findViewById(R.id.tdtMap);
        // mMapView.setResolution(0.010986328125);
//        mMapView.centerAt(new Point(105.444402, 28.871906), true);

        String mToken = "YgA6oKQf_zMiFPwkc3GerqZq9dvmjc0smYCnhwEZPngytdYrPjwSa22d90lgVZ4q";//token ʹ�����ͼ�Ĵ����û�������

        long dlgdbsize = 200;//  100MB
        int dlgTileCompress = 75;  //ͼƬѹ��75%

        TileCacheDBManager mDLGTileDBManager = new TileCacheDBManager(this, "iDLGTile1.db");  //���
        ActUtil.setNamesDB(this, "iDLGTile2.db");
        TileCacheDBManager mDLGTileDBManager2 = new TileCacheDBManager(this, "iDLGTile2.db");

        mDLGTileMapServiceLayer = new SCGISTiledMapServiceLayer(this, dlgUrl, mToken, false, mDLGTileDBManager);
//        mDLGTileMapServiceLayer.setCacheSize(dlgdbsize); //�����ļ���С����
        mDLGTileMapServiceLayer.setTileCompressAndQuality(true, dlgTileCompress);  //

        mDLGTileImgMapServiceLayer = new SCGISTiledMapServiceLayer(this, imgUrl, mToken, true, mDLGTileDBManager);
        mDLGTileImgMapServiceLayer.setCacheSize(dlgdbsize); //�����ļ���С����
        mDLGTileImgMapServiceLayer.setTileCompressAndQuality(true, dlgTileCompress);  //


        mDLGTileInfoMapServiceLayer = new com.sichuan.geologenvi.SCGISTiledMapServiceLayer(this, infoUrl, mToken, true, mDLGTileDBManager2){};
        mDLGTileInfoMapServiceLayer.setCacheSize(50); //�����ļ���С����
        mDLGTileInfoMapServiceLayer.setTileCompressAndQuality(false, 100);  //

        mMapView.addLayer(mDLGTileImgMapServiceLayer);
        mMapView.addLayer(mDLGTileInfoMapServiceLayer);

        mMapView.setExtent(new Envelope(95, 24, 110, 35));
        mMapView.centerAt(28.871906, 105.444402, true);
        mMapView.zoomin();
        mDLGTileMapServiceLayer.setVisible(true);
        mMapView.setMinScale(mDLGTileMapServiceLayer.getMinScale());
        mMapView.setMaxScale(mDLGTileMapServiceLayer.getMaxScale());
        mMapView.setOnSingleTapListener(singleTapListener);
        mMapView.setOnZoomListener(new OnZoomListener() {
            @Override
            public void preAction(float v, float v1, double v2) {

            }

            @Override
            public void postAction(float v, float v1, double v2) {
                getDataOnScreen();
            }
        });
        mMapView.setOnPanListener(new OnPanListener() {
            @Override
            public void prePointerMove(float v, float v1, float v2, float v3) {
//                if(mx-1!=-1&&my!=-1){
//                    mx=v;
//                    my=v1;
//                }
            }

            @Override
            public void postPointerMove(float v, float v1, float v2, float v3) {

            }

            @Override
            public void prePointerUp(float v, float v1, float v2, float v3) {

            }

            @Override
            public void postPointerUp(float v, float v1, float v2, float v3) {
//                float endX = v - mx;
//                float endY = v1 - my;
//                double distance = Math.sqrt(endX * endX + endY * endY);//
//                LogUtil.i("ppp", "distance: "+distance);
//                if (distance > 100) {
//                    getDataOnScreen();
//                }
                getDataOnScreen();
            }
        });

        mMapView.setOnLongPressListener(new OnLongPressListener() {
            @Override
            public boolean onLongPress(float x, float y) {
                pressPoint = mMapView.toMapPoint(new Point(x, y));
                if (points.size() < 5) {
                    new PSDdialog(MapAct.this, cb, "请输入该点信息", false).show();
                } else
                    ToastUtils.displayTextShort(MapAct.this, "表计数已达上限，请删除部分");
                return false;
            }
        });

        findViewById(R.id.myLoc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lat > 0 && lon > 0)
                    mMapView.centerAt(lat, lon, true);
                else
                    ToastUtils.displayTextShort(MapAct.this, "暂未获取位置信息");
            }
        });
        findViewById(R.id.mapType).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isImg) {
                    mMapView.removeLayer(mDLGTileImgMapServiceLayer);
                    mMapView.addLayer(mDLGTileMapServiceLayer, 0);
                }else {
                    mMapView.removeLayer(mDLGTileMapServiceLayer);
                    mMapView.addLayer(mDLGTileImgMapServiceLayer, 0);
                }
                isImg=!isImg;
            }
        });
        findViewById(R.id.searchImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MapAct.this, MapSearchAct.class), 0x10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0x11){
            MapBean mapBean = (MapBean) data.getSerializableExtra("InfoMap");
            Map<String, String> dataMap=mapBean.getMap();
            String sid="";
            int showTypeSearch=-1;
            Point point=null;
            int res = R.mipmap.search_map_img;

            if(dataMap.containsKey("ZHAA01A010")) {
                showTypeSearch=0;
                res = getDisasterIcon(dataMap);
                point = new Point(Double.valueOf(dataMap.get("ZHAA01A190")), Double.valueOf(dataMap.get("ZHAA01A200")));
                sid = dataMap.get("ZHAA01A010");
            }else  if(dataMap.containsKey("TONGYICODE")) {
                showTypeSearch=1;
                res = R.mipmap.mapicon_water;
                point = new Point(Double.valueOf(dataMap.get("JINGDU")), Double.valueOf(dataMap.get("WEIDU")));
                sid = dataMap.get("TONGYICODE");
            }else  if(dataMap.containsKey("ID")) {
                showTypeSearch=3;
                res = R.mipmap.mapicon_d2;
                sid = dataMap.get("ID");
                point = new Point(Double.valueOf(dataMap.get("COORDX")), Double.valueOf(dataMap.get("COORDY")));
            }

            Map<String, Object> map = new HashMap<>();
            map.put("id", sid);
            map.put(InfoType, showTypeSearch);
            Graphic gp1 = CreateGraphic(point, map, res, 0);
            int uid = getGraphicLayer().addGraphic(gp1);
            dataMap.put("markerUid", "" + uid);
            datamap.put(sid, dataMap);
            showInfoPop(gp1, showTypeSearch);
            final Point finalPoint = point;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mMapView.centerAt(finalPoint, true);
                }
            }, 500);
        }
    }

    private void getDataOnScreen() {
        GraphicsLayer gLayer = getGraphicLayer();
        Envelope rExtent = new Envelope();
        mMapView.getExtent().queryEnvelope(rExtent);
        double leftB_x = rExtent.getXMin();
        double leftB_y = rExtent.getYMin();
        double topR_x = rExtent.getXMax();
        double topR_y = rExtent.getYMax();
//        LogUtil.i("Location", "left_bottomX:  "+leftB_x+"   left_bottomY:  "+leftB_y);
//        LogUtil.i("Location", "right_topX:  "+topR_x+"   right_topY:  "+topR_y);
        switch (showType) {
            case 0:
                datalist = handler.getQueryResult(QueryStr.yinhuandian, "SL_ZHAA01A", " where ZHAA01A190 > '" + leftB_x + "' and ZHAA01A190 < '" + topR_x + "' and" +
                        " ZHAA01A200 > '" + leftB_y + "' and ZHAA01A200 < '" + topR_y + "' limit 100");
                tempdataMap.clear();
                tempdataMap.putAll(datamap);
                for (int i = 0; i < datalist.size(); i++) {
                    Map<String, String> dataMap = datalist.get(i);
                    String sid = dataMap.get("ZHAA01A010"); //灾害点ID
                    if (needAddMarker(sid)) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", sid);
                        map.put(InfoType, showType);
                        int res = getDisasterIcon(dataMap);
                        Point point = new Point(Double.valueOf(dataMap.get("ZHAA01A190")), Double.valueOf(dataMap.get("ZHAA01A200")));
                        Graphic gp1 = CreateGraphic(point, map, res, 0);
                        int uid = gLayer.addGraphic(gp1);
                        dataMap.put("markerUid", "" + uid);
                        datamap.put(sid, dataMap);
                    }
                }
                removeleftMarkers();
                break;
            case 1:
                datalist = handler.getQueryResult("SL_TBLJING", " where JINGDU > '" + leftB_x + "' and JINGDU < '" + topR_x + "' and" +
                        " WEIDU > '" + leftB_y + "' and WEIDU < '" + topR_y + "' limit 100");
                tempdataMap.clear();
                tempdataMap.putAll(datamap);
                for (int i = 0; i < datalist.size(); i++) {
                    Map<String, String> dataMap = datalist.get(i);
                    String sid = dataMap.get("TONGYICODE"); //地下水ID
                    if (needAddMarker(sid)) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", sid);
                        map.put(InfoType, showType);
                        int res = R.mipmap.mapicon_water;
                        Point point = new Point(Double.valueOf(dataMap.get("JINGDU")), Double.valueOf(dataMap.get("WEIDU")));
                        Graphic gp1 = CreateGraphic(point, map, res, 0);
                        int uid = gLayer.addGraphic(gp1);
                        dataMap.put("markerUid", "" + uid);
                        datamap.put(sid, dataMap);
                    }
                }
                removeleftMarkers();
                break;
            case 2:
                datalist = handler.getQueryResult(ConstantUtil.Mine,
                        "SL_KS_DZHJ_XX left join SL_XMDA on SL_KS_DZHJ_XX.KS_CK_GUID=SL_XMDA.CK_GUID", "");
                tempdataMap.clear();
                tempdataMap.putAll(datamap);
                for (int i=0;i<datalist.size();i++){
                    Map<String, String> dataMap=datalist.get(i);
                    String sid=dataMap.get("KS_CK_GUID"); //矿山ID
                    if(needAddMarker(sid)) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", sid);
                        map.put(InfoType, showType);
                        int res = R.mipmap.mapicon_d9;
                        Point point = new Point(Double.valueOf(dataMap.get("ZHAA01A190")), Double.valueOf(dataMap.get("ZHAA01A200")));
                        Graphic gp1 = CreateGraphic(point, map, res, 0);
                        int uid=gLayer.addGraphic(gp1);
                        dataMap.put("markerUid", ""+uid);
                        datamap.put(sid, dataMap);
                    }
                }
                removeleftMarkers();
                break;
            case 3:
                datalist = handler.getQueryResult("SL_DZYJBH", " where COORDX > '" + leftB_x + "' and COORDX < '" + topR_x + "' and" +
                        " COORDY > '" + leftB_y + "' and COORDY < '" + topR_y + "' limit 100");
                tempdataMap.clear();
                tempdataMap.putAll(datamap);
                for (int i = 0; i < datalist.size(); i++) {
                    Map<String, String> dataMap = datalist.get(i);
                    String sid = dataMap.get("ID"); //遗迹
                    if (needAddMarker(sid)) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", sid);
                        map.put(InfoType, showType);
                        int res = R.mipmap.mapicon_d8;
                        Point point = new Point(Double.valueOf(dataMap.get("COORDX")), Double.valueOf(dataMap.get("COORDY")));
                        Graphic gp1 = CreateGraphic(point, map, res, 0);
                        int uid = gLayer.addGraphic(gp1);
                        dataMap.put("markerUid", "" + uid);
                        datamap.put(sid, dataMap);
                    }
                }
                removeleftMarkers();
                break;
        }
    }

    private int getDisasterIcon(Map<String, String> dataMap){
        int res = R.mipmap.shanchu;
        String disasterType = dataMap.get("ZHAA01A210");
        if (disasterType.equals("不稳定斜坡"))
            res = R.mipmap.mapicon_d0;
        else if (disasterType.equals("滑坡"))
            res = R.mipmap.mapicon_d1;
        else if (disasterType.equals("崩塌"))
            res = R.mipmap.mapicon_d2;
        else if (disasterType.equals("泥石流"))
            res = R.mipmap.mapicon_d3;
        else if (disasterType.equals("地面塌陷"))
            res = R.mipmap.mapicon_d4;
        else if (disasterType.equals("地裂缝"))
            res = R.mipmap.mapicon_d5;
        else if (disasterType.equals("地面沉降"))
            res = R.mipmap.mapicon_d6;
        else if (disasterType.equals("其它"))
            res = R.mipmap.mapicon_d7;
        return res;
    }

    private void removeleftMarkers() {
        int i = 0;
        for (Map.Entry<String, Map<String, String>> entry : tempdataMap.entrySet()) {
            i++;
//            Graphic marker=markersMap.get(entry.getKey());
            getGraphicLayer().removeGraphic(Integer.valueOf(entry.getValue().get("markerUid")));
//            markersMap.remove(entry.getKey());
            datamap.remove(entry.getKey());
        }
        LogUtil.i("ppp", "removed: " + i);
    }

    private boolean needAddMarker(String code) {
        boolean addMarker = true;
        if (tempdataMap.containsKey(code)) {
            addMarker = false;
            tempdataMap.remove(code);
        }
        return addMarker;
    }


    @Override
    protected void onDestroy() {
        if(lm!=null)
            lm.removeUpdates(locationListener);
        SharedPreferencesUtil.setString(this, ConstantUtil.UserPoint, JsonUtil.getJsonStrUserPoint(points));
        if (lDisplayManager != null)
            lDisplayManager.stop();
        if(mLocationClient!=null)
            mLocationClient.stop();
        mMapView.destroyDrawingCache();
        LogUtil.i("Location", "destory!");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.unpause();
        if (lDisplayManager != null)
            lDisplayManager.start();
    }
//    /storage/emulated/0/com.sichuan.geologenvi/sctiledatabase/iDLGTile1.db
    private Graphic CreateGraphic(Point geometry, Map<String, Object> map, int imgRes, int yoffset) {
        Drawable image = getResources().getDrawable(imgRes);
        image.setBounds(5, 5, 5, 5);
        PictureMarkerSymbol symbol = new PictureMarkerSymbol(image);
        symbol.setOffsetY(yoffset);
        Graphic g = new Graphic(geometry, symbol, map);
        return g;
    }

    private GraphicsLayer getGraphicLayer() {
        if (mGraphicsLayer == null) {
            mGraphicsLayer = new GraphicsLayer();
            mMapView.addLayer(mGraphicsLayer);
        }
        return mGraphicsLayer;
    }

    PSDdialog.CallBack cb = new PSDdialog.CallBack() {
        @Override
        public void cancel() {

        }

        @Override
        public void editfinish(String psw) {
            Map<String, Object> map = new HashMap<>();
            map.put("desc", psw);
            long timeid = System.currentTimeMillis();
            map.put("timeid", timeid);
            map.put(InfoType, -1);
            MapPoint mapPoint = new MapPoint();
            mapPoint.setP(new Point(pressPoint.getX(), pressPoint.getY()));
            mapPoint.setDesc(psw);
            mapPoint.setId(timeid);
            points.put(timeid, mapPoint);
            Graphic gp1 = CreateGraphic(pressPoint, map, R.mipmap.of_location_icon, 16);
            getGraphicLayer().addGraphic(gp1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActUtil.closeSoftPan(MapAct.this);
                }
            }, 300);
        }
    };

    LocationListener locationListener = new LocationListener() {
        //boolean locationChanged = false;

        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
//                currentLocation=location;
            LogUtil.i("Location", "getLocationCallback");
            if (location != null) {
                LogUtil.i("Location", "getLocation: " + location.getLongitude() + "    " + location.getLatitude());
                lon = location.getLongitude();
                lat = location.getLatitude();
                if(mLocationClient!=null)
                    mLocationClient.stop();
                if(locationId!=-1)
                    getGraphicLayer().removeGraphic(locationId);
            }

        }

        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            LogUtil.i("Location", "disable: " + provider);

        }

        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            LogUtil.i("Location", "enable: " + provider);

        }

        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {
            // TODO Auto-generated method stub
            LogUtil.i("Location", "status change!!");

        }
    };

    OnSingleTapListener singleTapListener = new OnSingleTapListener() {
        public void onSingleTap(float x, float y) {
            // TODO Auto-generated method stub
            int[] graphicIDs = getGraphicLayer().getGraphicIDs(x, y, 25);
            if (graphicIDs != null && graphicIDs.length > 0) {
                Graphic gr = getGraphicLayer().getGraphic(graphicIDs[0]);
                int infotype = (int) (gr.getAttributes().get(InfoType));

                if (infotype == -1) {
                    Callout callout = mMapView.getCallout();
                    callout.setMaxWidthDp(340);
                    callout.setOffset(0, 50);
                    View view = inflater.inflate(R.layout.user_marker_pop, null);
//                      view.setLayoutParams(new ViewGroup.LayoutParams(ImageUtil.dip2px(MapAct.this, 280), -2));
                    view.findViewById(R.id.okBtn).setOnClickListener(listener);
                    View del = view.findViewById(R.id.delBtn);
                    Object id = gr.getAttributes().get("timeid");
                    del.setTag(R.id.tag_1, id);
                    del.setTag(R.id.tag_2, graphicIDs[0]);
                    del.setOnClickListener(listener);
                    TextView contentTxt = (TextView) view.findViewById(R.id.contentTxt);
                    contentTxt.setText((String) (gr.getAttributes().get("desc")));
                    Point popPositon = points.get(id).getP();
                    callout.show(popPositon, view);
                } else {
                    showInfoPop(gr, infotype);
                }

            }
        }
    };


    private void showInfoPop(Graphic gr, int infotype){
        Callout callout = mMapView.getCallout();
        callout.setMaxWidthDp(340);
        callout.setOffset(0, 50);
        final Map<String, String> infoMap0 = datamap.get(gr.getAttributes().get("id"));
        View view0 = inflater.inflate(R.layout.marker_info_pop, null);
        view0.findViewById(R.id.okBtn).setOnClickListener(listener);
        Point point = null;
        if (infotype == 0) {
            String[] itemTxtId = {"ZHAA01A150", "ZHAA01A210", "ZHAA01A370", "ZHAA01A410"};
            point = new Point(Double.valueOf(infoMap0.get("ZHAA01A190")), Double.valueOf(infoMap0.get("ZHAA01A200")));
            setMarkerInfo(itemTxtId, "ZHAA01A020", "SL_ZHAA01A", "灾害点详情", infoMap0, view0);
        } else if (infotype == 1) {
            String[] itemTxtId = {"SHUILEIXING", "QUXIAN", "KOUJING"};
            point = new Point(Double.valueOf(infoMap0.get("JINGDU")), Double.valueOf(infoMap0.get("WEIDU")));
            setMarkerInfo(itemTxtId, "QUYU", "SL_TBLJING", "地下水", infoMap0, view0);
        } else if (infotype == 2) {

        } else if (infotype == 3) {
            String[] itemTxtId = {"NAME", "XZQH", "BHJB"};
            point = new Point(Double.valueOf(infoMap0.get("COORDX")), Double.valueOf(infoMap0.get("COORDY")));
            setMarkerInfo(itemTxtId, "NAME", "SL_DZYJBH", "地址遗迹", infoMap0, view0);
        }
        callout.show(point, view0);
    }

    private void setMarkerInfo(String[] itemTxtId, String titleId, final String table, final String titleTxt, final Map<String, String> dataMap, View view0) {
        View detailBtn = view0.findViewById(R.id.detailBtn);
        TextView title = (TextView) view0.findViewById(R.id.titleTxt);
        Map<String, String> partData = new LinkedHashMap<>();
        for (int i = 0; i < itemTxtId.length; i++) {
            partData.put(itemTxtId[i], dataMap.get(itemTxtId[i]));
        }
        title.setText(dataMap.get(titleId));
        ListView infoList = (ListView) view0.findViewById(R.id.infoList);
        infoList.setAdapter(new ActivityInfoAdapter(MapAct.this, partData, table));
        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MapAct.this, ItemDetailAct.class);
                MapBean mapBean = new MapBean();
                mapBean.setMap(dataMap);
                i.putExtra("Title", titleTxt);
                i.putExtra("InfoMap", mapBean);
                i.putExtra("TableName", table);
                startActivity(i);
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.okBtn:
                    mMapView.getCallout().animatedHide();
                    break;
                case R.id.delBtn:
                    points.remove(view.getTag(R.id.tag_1));
                    getGraphicLayer().removeGraphic((int) view.getTag(R.id.tag_2));
                    mMapView.getCallout().animatedHide();
                    break;
                case R.id.right_txt:
                    popup = new MenuPopup(MapAct.this, typeNames, new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            rightTxtBtn.setText(typeNames[i]);
                            showType = i;
                            mMapView.getCallout().animatedHide();
                            getDataOnScreen();
                            popup.dismiss();
                        }
                    });
                    popup.showPopupWindow(rightTxtBtn);
                    break;
            }
        }
    };

    private void initGPS() {
        LocationManager locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        // 判断GPS模块是否开启，如果没有则开启
        if (!locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER) ||
                !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("请打开GPS和网络定位");
            dialog.setPositiveButton("确定",
                    new android.content.DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            // 转到手机设置界面，用户设置GPS
                            Intent intent = new Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, 0); // 设置完成后返回到原来的界面

                        }
                    });
            dialog.setNeutralButton("取消", new android.content.DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                }
            });
            dialog.show();
        } else {
            // 弹出Toast
        }
    }

    public static final void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }


    private void openArcgisLocation() {
        lDisplayManager = mMapView.getLocationDisplayManager();
        lDisplayManager.setLocationListener(locationListener);
        lDisplayManager.setAllowNetworkLocation(true);   //是否允许网络定位
        lDisplayManager.setAccuracyCircleOn(false);       //是否要圈
        lDisplayManager.setShowLocation(true);
        try {
            PictureMarkerSymbol symbol=new PictureMarkerSymbol(getResources().getDrawable(R.mipmap.icon_oval0));
            lDisplayManager.setAccuracySymbol(new SimpleFillSymbol(getResources().getColor(R.color.normal_blue)).setAlpha(20));
            lDisplayManager.setDefaultSymbol(symbol);
            lDisplayManager.setLocationAcquiringSymbol(symbol);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i("Location", "failed set location icon");
        }
        lDisplayManager.setAutoPanMode(LocationDisplayManager.AutoPanMode.LOCATION);
        lDisplayManager.start();
    }

    int locationId=-1;
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            if (location != null) {
                LogUtil.i("Location", "getBaiduLocation: " + location.getLongitude() + "    " + location.getLatitude());
                lon = location.getLongitude();
                lat = location.getLatitude();

//                lat=location.getLatitude()-0.00374531687912;
//                lon=location.getLongitude()-0.008774687519;

//                bd_decrypt(location.getLatitude(), location.getLongitude());

                if(locationId!=-1)
                    getGraphicLayer().removeGraphic(locationId);
                Point point = new Point(lon, lat);
                Graphic gp1 = CreateGraphic(point, null, R.mipmap.icon_oval1, 0);
                locationId=getGraphicLayer().addGraphic(gp1);
            }
        }

        private void bd_decrypt(double bd_lat, double bd_lon)
        {
            double x = bd_lon - 0.0065, y = bd_lat - 0.006;
            double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * Math.PI);
            double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * Math.PI);
            lon=z * Math.cos(theta);
            lat=z * Math.sin(theta);
        }
    }


    private void openBaiduLocation() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("wgs84"); //wgs84, gcj02, bd09ll
        option.setScanSpan(5000);
        option.disableCache(true);//禁止启用缓存定位
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
    }


}
