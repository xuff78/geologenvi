package com.sichuan.geologenvi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.widget.Toast;

import com.esri.android.map.TiledServiceLayer;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.scgis.mmap.commons.HttpRequest;
import com.scgis.mmap.commons.Util;
import com.scgis.mmap.helper.Tile;
import com.scgis.mmap.helper.TileCacheDBManager;
import com.scgis.mmap.helper.TimeCache;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/26.
 */
public class SCGISTiledMapServiceLayer extends TiledServiceLayer {
    private final int WKID = 4326;
    private final double[] RESOLUTIONS = new double[]{1.40625D, 0.703125D, 0.3515625D, 0.17578125D, 0.087890625D, 0.0439453125D, 0.02197265625D, 0.010986328125D, 0.0054931640625D, 0.00274658203125D, 0.001373291015625D, 6.866455078125E-4D, 3.4332275390625E-4D, 1.71661376953125E-4D, 8.58306884765629E-5D, 4.29153442382814E-5D, 2.14576721191407E-5D, 1.07288360595703E-5D, 5.36441802978515E-6D, 2.68220901489258E-6D, 1.34110450744629E-6D};
    private final double[] SCALES = new double[]{4.0E8D, 2.954975985708346E8D, 1.47914677725E8D, 7.39573388625E7D, 3.697866943125E7D, 1.8489334715625E7D, 9244667.3578125D, 4622333.67890625D, 2311166.839453125D, 1155583.4197265625D, 577791.7098632812D, 288895.8549316406D, 144447.9274658203D, 72223.96373291015D, 36111.98186645508D, 18055.99093322754D, 9027.99546661377D, 4513.997733306885D, 2256.9988666534423D, 1128.4994333267211D, 564.2497166633606D};
    private double[] _resolutions = null;
    private double[] _scales = null;
    private String _baseUrl = "";
    private String _token = "";
    private int _startlevel = 0;
    private int _stoplevel = 20;
    private double _minresolution;
    private double _maxresolution;
    private double _minscale;
    private double _maxscale;
    private boolean _isCache = true;
    private TileCacheDBManager _tileCacheDBManager = null;
    private boolean _isCompress = true;
    private int _compressQuality = -1;
    private long _cacheSize = 100L;
    private Context _context = null;
    private long _expirationtime = 864000L;

    public void setStartLevel(int startlevel) {
        if(startlevel < 0) {
            startlevel = 0;
        }

        if(startlevel > 20) {
            startlevel = 20;
        }

        this._startlevel = startlevel;
        TileInfo tileInfo = this.getTileInfo();
        double[] res = tileInfo.getResolutions();
        double[] scales = tileInfo.getScales();
        this._maxresolution = res[startlevel] / 2.0D;
        this._minscale = scales[startlevel] * 2.0D;
    }

    public int getStartLevel() {
        return this._startlevel;
    }

    public void setStopLevel(int stoplevel) {
        if(stoplevel < 0) {
            stoplevel = 0;
        }

        if(stoplevel > 20) {
            stoplevel = 20;
        }

        this._stoplevel = stoplevel;
        TileInfo tileInfo = this.getTileInfo();
        double[] res = tileInfo.getResolutions();
        double[] scales = tileInfo.getScales();
        this._minresolution = res[stoplevel] / 2.0D;
        this._maxscale = scales[stoplevel] * 2.0D;
    }

    public int getStopLevel() {
        return this._stoplevel;
    }

    public double getMinResolution() {
        return this._minresolution;
    }

    public double getMaxResolution() {
        return this._maxresolution;
    }

    public double getMinScale() {
        return this._minscale;
    }

    public double getMaxScale() {
        return this._maxscale;
    }

    public void setIsCache(boolean iscache) {
        this._isCache = iscache;
    }

    public void setTileCacheDBManager(TileCacheDBManager tiledbman) {
        this._tileCacheDBManager = tiledbman;
    }

    public void setTileCompressAndQuality(boolean iscompress, int quality) {
        this._isCompress = iscompress;
        this._compressQuality = quality;
    }

    public void setCacheSize(long cachesize) {
        this._cacheSize = cachesize;
    }

    public long getCacheSize() {
        return this._cacheSize;
    }

    public long getCacheFileSize() {
        return this._tileCacheDBManager != null?this._tileCacheDBManager.getCacheFileSize():0L;
    }

    public void setContext(Context context) {
        this._context = context;
    }

    public void setExpirationTime(long secondtime) {
        this._expirationtime = secondtime;
    }

    public SCGISTiledMapServiceLayer(Context context, String baseurl, String token, boolean iscache, TileCacheDBManager tiledbman) {
        super(baseurl);
        this._context = context;
        this._token = token;
        this._baseUrl = baseurl;
        this._isCache = iscache;
        this._tileCacheDBManager = tiledbman;

        try {
            this.initLayer();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    protected void initLayer() {
        if(this.getID() == 0L) {
            this.nativeHandle = this.create();
        }

        try {
            this.initialTiledMapService();
            super.initLayer();
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    protected byte[] getTile(int level, int col, int row) {
        byte[] data = null;
        Tile mTile = this.getTileFromRCL(level, row, col);
        if(mTile != null) {
            data = mTile.getTile();
            return data;
        } else {
            if(level > this._startlevel) {
                data = this.getTileFromNextLevel(level, row, col, level - 1);
            }

            if(data == null) {
                data = this.getNullData();
            }

            return data;
        }
    }

    private byte[] getTileFromNextLevel(int level, int row, int col, int nextLevel) {
        byte[] data = null;
        TileInfo pTileInfo = this.getTileInfo();
        int w = pTileInfo.getTileWidth();
        int h = pTileInfo.getTileHeight();
        double[] res = pTileInfo.getResolutions();
        double res1 = res[level];
        double res2 = res[nextLevel];
        double r12 = res1 / res2;
        int col2 = (int)(((double)col + 0.5D) * r12);
        int row2 = (int)(((double)row + 0.5D) * r12);
        Tile mTile = this.getTileFromRCL(nextLevel, row2, col2);
        if(mTile == null) {
            if(nextLevel > this._startlevel) {
                data = this.getTileFromNextLevel(nextLevel, row2, col2, nextLevel - 1);
            }
        } else {
            data = mTile.getTile();
        }

        if(data == null) {
            return data;
        } else if(data.length == 0) {
            return null;
        } else {
            double x0 = ((double)col2 / r12 - (double)col) * (double)w;
            double y0 = ((double)row2 / r12 - (double)row) * (double)h;
            double ww = (double)w / r12;
            double hh = (double)h / r12;
            Bitmap img = BitmapFactory.decodeByteArray(data, 0, data.length);
            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            RectF dst = new RectF();
            dst.set((float)x0, (float)y0, (float)(x0 + ww), (float)(y0 + hh));
            canvas.drawBitmap(img, (Rect)null, dst, (Paint)null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        }
    }

    private Tile getTileFromRCL(int level, int row, int col) {
        Tile mTileResult = null;
        Log.i("开始读取瓦片", "new new new...");
        boolean networkstatus = Util.isNetworkConnected(this._context);
        Tile mTileOnCache;
        if(networkstatus) {
            if(this._isCache && this._tileCacheDBManager != null) {
                mTileOnCache = this._tileCacheDBManager.getOfflineCacheFile(level, row, col);
                Date mDateOnLine;
                if(mTileOnCache != null) {
                    TimeCache mTileOnLine = this._tileCacheDBManager.getTimeCache(level, row, col);
                    mDateOnLine = null;
                    if(mTileOnLine != null && !mTileOnLine.getExpirationTime().before(Calendar.getInstance().getTime())) {
                        mDateOnLine = mTileOnLine.getCacheTime();
                    } else {
                        mDateOnLine = this.getTileDateOnLine(level, row, col);
                        if(mDateOnLine != null) {
                            this._tileCacheDBManager.writeTimeCache(level, row, col, Util.getAfterTime(Calendar.getInstance().getTime(), this._expirationtime), mDateOnLine);
                        }
                    }

                    if(mDateOnLine != null && !Util.compareDate(mTileOnCache.getTime(), mDateOnLine)) {
                        Tile mTileOnLine1 = this.getTileOnLine(level, row, col, mDateOnLine);
                        if(mTileOnLine1 != null) {
                            mTileOnLine1.setFreq(mTileOnCache.getFreq());
                            mTileResult = mTileOnLine1;
                            Log.i("getTile", String.format("read from online with new tile:tile(%s %s %s", new Object[]{Integer.valueOf(level), Integer.valueOf(row), Integer.valueOf(col)}));
                            if(this._tileCacheDBManager.addOfflineCacheFile(mTileOnLine1, this._cacheSize)) {
                                if(mDateOnLine != null) {
                                    this._tileCacheDBManager.writeTimeCache(level, row, col, Util.getAfterTime(Calendar.getInstance().getTime(), this._expirationtime), mDateOnLine);
                                }

                                Log.i("setTile", String.format("update to cache table from online tile(%s %s %s", new Object[]{Integer.valueOf(level), Integer.valueOf(row), Integer.valueOf(col)}));
                            }
                        }
                    } else {
                        mTileResult = mTileOnCache;
                        Log.i("getTile", String.format("read from cache:tile(%s %s %s", new Object[]{Integer.valueOf(level), Integer.valueOf(row), Integer.valueOf(col)}));
                    }
                } else {
                    Tile mTileOnLine2 = this.getTileOnLine(level, row, col, (Date)null);
                    if(mTileOnLine2 != null) {
                        mTileResult = mTileOnLine2;
                        Log.i("getTile", String.format("read from online without cache:tile(%s %s %s", new Object[]{Integer.valueOf(level), Integer.valueOf(row), Integer.valueOf(col)}));
                        if(this._tileCacheDBManager.addOfflineCacheFile(mTileOnLine2, this._cacheSize)) {
                            mDateOnLine = this.getTileDateOnLine(level, row, col);
                            if(mDateOnLine != null) {
                                this._tileCacheDBManager.writeTimeCache(level, row, col, Util.getAfterTime(Calendar.getInstance().getTime(), this._expirationtime), mDateOnLine);
                            }
                        }

                        Log.i("setTile", String.format("write to cache table with tile:tile(%s %s %s", new Object[]{Integer.valueOf(level), Integer.valueOf(row), Integer.valueOf(col)}));
                    }
                }
            } else {
                mTileOnCache = this.getTileOnLine(level, row, col, (Date)null);
                if(mTileOnCache != null) {
                    mTileResult = mTileOnCache;
                    Log.i("getTile", String.format("read from online with cache is close:tile(%s %s %s", new Object[]{Integer.valueOf(level), Integer.valueOf(row), Integer.valueOf(col)}));
                }
            }
        } else if(this._isCache && this._tileCacheDBManager != null) {
            mTileOnCache = this._tileCacheDBManager.getOfflineCacheFile(level, row, col);
            if(mTileOnCache != null) {
                mTileResult = mTileOnCache;
                Log.i("getTile", String.format("read from cache with network disable:tile(%s %s %s", new Object[]{Integer.valueOf(level), Integer.valueOf(row), Integer.valueOf(col)}));
            }
        } else {
            Toast.makeText(this._context, "缓冲和网络同时不可用", 1).show();
        }

        return mTileResult;
    }

    private Date getTileDateOnLine(int level, int row, int col) {
        String requestStr1 = null;
        if(this._token == null) {
            requestStr1 = String.format("%s/gettileinfo?level=%s&row=%s&col=%s", new Object[]{this._baseUrl, Integer.valueOf(level), Integer.valueOf(row), Integer.valueOf(col)});
        } else {
            requestStr1 = String.format("%s/gettileinfo?level=%s&row=%s&col=%s&token=%s", new Object[]{this._baseUrl, Integer.valueOf(level), Integer.valueOf(row), Integer.valueOf(col), this._token});
        }

        try {
            String ex = HttpRequest.readContentFromGet(requestStr1);
            ObjectMapper objMap = new ObjectMapper();
            JsonNode node = objMap.readTree(ex);
            JsonNode node1 = node.get("success");
            if(node1.asBoolean() && node1.getBooleanValue()) {
                node1 = node.get("message");
                JsonNode node2 = node1.get("FromDate");
                String mDateStr = node2.getTextValue();
                int startIndex = mDateStr.indexOf("(") + 1;
                int subLenth = mDateStr.indexOf(")");
                mDateStr = mDateStr.substring(startIndex, subLenth);
                Date testD = new Date(Long.parseLong(mDateStr));
                Log.i("读取在线瓦片的时间", String.format("the date of tile online(level=%s,row=%s,col=%s) is %s", new Object[]{Integer.valueOf(level), Integer.valueOf(row), Integer.valueOf(col), testD.toGMTString() + " " + testD.toString()}));
                return testD;
            } else {
                return null;
            }
        } catch (Exception var14) {
            var14.printStackTrace();
            return null;
        }
    }

    private Tile getTileOnLine(int level, int row, int col, Date date) {
        Tile mTile = null;
        String requestStr = null;
        if(this._token == null) {
            this._token = "";
        }

        requestStr = String.format("%s/tile/%s/%s/%s?token=%s", new Object[]{this._baseUrl, Integer.valueOf(level), Integer.valueOf(row), Integer.valueOf(col), this._token});
        if(this._isCompress) {
            requestStr = requestStr + "&compress=true";
            if(this._compressQuality >= 0) {
                requestStr = requestStr + String.format("&quality=%s", new Object[]{Integer.valueOf(this._compressQuality)});
            }
        }

        InputStream inStream = null;

        try {
            URL ex = new URL(requestStr);
            HttpURLConnection connection = (HttpURLConnection)ex.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.connect();
            if(200 == connection.getResponseCode()) {
                inStream = connection.getInputStream();
            }

            int ocunt = inStream.available();
            String maxAge = connection.getHeaderField("Cache-Control");
            boolean isnotImage = false;
            if(maxAge != null && maxAge.equalsIgnoreCase("max-age=0")) {
                isnotImage = true;
            }

            Object data = null;
            if(date == null) {
                date = new Date();
            }

            if(!isnotImage) {
                byte[] data1 = HttpRequest.readInputStream(inStream);
                mTile = new Tile(level, row, col, data1, date);
                mTile.setFreq(0);
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        return mTile;
    }

    private byte[] getNullData() {
        try {
            InputStream e = this._context.getAssets().open("nulldata.png");
            byte[] buffer = new byte[512];

            ByteArrayOutputStream bytestream;
            int ch;
            for(bytestream = new ByteArrayOutputStream(); (ch = e.read(buffer, 0, 512)) != -1; buffer = new byte[512]) {
                bytestream.write(buffer, 0, ch);
            }

            Object buffer1 = null;
            byte[] imgdata = bytestream.toByteArray();
            bytestream.close();
            return imgdata;
        } catch (Exception var6) {
            return null;
        }
    }

    private void initialTiledMapService() {
        this.buildTileInfo();
        this.setFullExtent(new Envelope(-180.0D, -90.0D, 180.0D, 90.0D));
        this.setDefaultSpatialReference(SpatialReference.create(4326));
        this.setInitialExtent(new Envelope(95.0D, 24.0D, 110.0D, 35.0D));
    }

    private void buildTileInfo() {
        Point originalPoint = new Point(-180.0D, 90.0D);
        byte levels = 21;
        byte dpi = 96;
        short tileWidth = 256;
        short tileHeight = 256;
        TileInfo tiandituSiChuanTileInfo = new TileInfo(originalPoint, this.SCALES, this.RESOLUTIONS, levels, dpi, tileWidth, tileHeight);
        this.setTileInfo(tiandituSiChuanTileInfo);
        this._minresolution = this.RESOLUTIONS[levels - 1] / 2.0D;
        this._maxresolution = this.RESOLUTIONS[0] / 2.0D;
        this._minscale = this.SCALES[0] * 2.0D;
        this._maxscale = this.SCALES[levels - 1] * 2.0D;
    }

    private void InitialTiledMapServiceDynamic() {
        try {
            SpatialReference e = SpatialReference.create(4326);
            this.setDefaultSpatialReference(e);
            String mapserverUrl = null;
            String jsonstr = null;
            byte flag = 0;
            boolean networkstatus = Util.isNetworkConnected(this._context);
            if(networkstatus) {
                if(this._token != null && this._token.length() != 0) {
                    mapserverUrl = String.format("%s/mapserver?token=%s", new Object[]{this._baseUrl, this._token});
                } else {
                    mapserverUrl = String.format("%s/mapserver", new Object[]{this._baseUrl});
                }

                this.setUrl(mapserverUrl);

                try {
                    jsonstr = HttpRequest.readContentFromGet(mapserverUrl);
                    flag = 1;
                } catch (Exception var21) {
                    jsonstr = null;
                    var21.printStackTrace();
                }
            }

            if((jsonstr == null || jsonstr.length() == 0) && this._isCache && this._tileCacheDBManager != null) {
                jsonstr = this._tileCacheDBManager.getTileMapCacheConfigInfo();
                flag = 2;
            }

            if(flag == 0) {
                Log.e("初始化地图服务", "获取地图服务元数据信息为空");
                return;
            }

            ObjectMapper objMap;
            JsonNode node;
            JsonNode node1;
            Point orgPoint;
            int tileRows;
            int tileCols;
            int dpi;
            int levelCount;
            double[] scales;
            int[] levels;
            int i;
            double[] resolutions;
            JsonNode node2;
            if(flag == 1) {
                objMap = new ObjectMapper();
                node = objMap.readTree(jsonstr);
                JsonNode var23 = node.get("success");
                if(!var23.asBoolean() || !var23.getBooleanValue()) {
                    Log.e("初始化地图服务", "获取地图服务元数据信息失败");
                    return;
                }

                var23 = node.get("message");
                JsonNode var24 = var23.get("MapName");
                this.setName(var24.getTextValue());
                var24 = var23.get("fullExtent");
                this.setFullExtent(new Envelope(var24.get("xmin").getDoubleValue(), var24.get("ymin").getDoubleValue(), var24.get("xmax").getDoubleValue(), var24.get("ymax").getDoubleValue()));
                var24 = var23.get("initialExtent");
                this.setInitialExtent(new Envelope(var24.get("xmin").getDoubleValue(), var24.get("ymin").getDoubleValue(), var24.get("xmax").getDoubleValue(), var24.get("ymax").getDoubleValue()));
                var24 = var23.get("tileMapCacheInfo");
                node1 = var24.get("tileOrigin");
                orgPoint = new Point(node1.get("x").getDoubleValue(), node1.get("y").getDoubleValue());
                node1 = var24.get("tileRows");
                tileRows = node1.getIntValue();
                node1 = var24.get("tileCols");
                tileCols = node1.getIntValue();
                node1 = var24.get("dpi");
                dpi = node1.getIntValue();
                if(dpi == 0) {
                    dpi = 96;
                }

                node1 = var24.get("LODInfos");
                levelCount = node1.size();
                levels = new int[levelCount];
                scales = new double[levelCount];
                resolutions = new double[levelCount];

                for(i = 0; i < levelCount; ++i) {
                    node2 = node1.get(i);
                    levels[i] = node2.get("level").getIntValue();
                    scales[i] = node2.get("scale").getDoubleValue();
                    resolutions[i] = node2.get("resolution").getDoubleValue();
                }

                this._startlevel = levels[0];
                this._stoplevel = levels[levelCount - 1];
                this._minresolution = resolutions[levelCount - 1];
                this._maxresolution = resolutions[0];
                this.setTileInfo(new TileInfo(orgPoint, scales, resolutions, levelCount, dpi, tileCols, tileRows));
            } else if(flag == 2) {
                objMap = new ObjectMapper();
                node = objMap.readTree(jsonstr);
                this.setName(this._tileCacheDBManager.getTileTableNameQZ());
                String mFullExtentStr = this._tileCacheDBManager.getTileMapFullExtent();
                String[] mExtents = mFullExtentStr.split(",");
                if(mExtents.length != 4) {
                    this.setFullExtent(new Envelope(96.8064823D, 25.68096106D, 109.1252279D, 34.75215408D));
                } else {
                    this.setFullExtent(new Envelope(Double.parseDouble(mExtents[0]), Double.parseDouble(mExtents[1]), Double.parseDouble(mExtents[2]), Double.parseDouble(mExtents[3])));
                }

                node1 = node.get("tileOrigin");
                orgPoint = new Point(node1.get("x").getDoubleValue(), node1.get("y").getDoubleValue());
                node1 = node.get("tileRows");
                tileRows = node1.getIntValue();
                node1 = node.get("tileCols");
                tileCols = node1.getIntValue();
                node1 = node.get("dpi");
                dpi = node1.getIntValue();
                if(dpi == 0) {
                    dpi = 96;
                }

                node1 = node.get("LODInfos");
                levelCount = node1.size();
                levels = new int[levelCount];
                scales = new double[levelCount];
                resolutions = new double[levelCount];

                for(i = 0; i < levelCount; ++i) {
                    node2 = node1.get(i);
                    levels[i] = node2.get("level").getIntValue();
                    scales[i] = node2.get("scale").getDoubleValue();
                    resolutions[i] = node2.get("resolution").getDoubleValue();
                }

                this._startlevel = levels[0];
                this._stoplevel = levels[levelCount - 1];
                this._minresolution = resolutions[levelCount - 1];
                this._maxresolution = resolutions[0];
                this.setTileInfo(new TileInfo(orgPoint, scales, resolutions, levelCount, dpi, tileCols, tileRows));
            }
        } catch (Exception var22) {
            var22.printStackTrace();
            Toast.makeText(this._context, "初始化地图服务出错", 1).show();
        }

    }
}
