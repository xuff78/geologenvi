package com.skyline.teapi;

public final class IFeatureGroups extends TEIUnknownHandle {
    private IFeatureGroups(int handle)
    {
        super(handle);
    }    
    public static IFeatureGroups fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IFeatureGroups(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("0DC57744-6723-47A0-A6F8-121E767C1754");

    public Object get_Item(Object Index) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetItem(this.getHandle(),Index));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getCount() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCount(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatureGroup getPolyline() throws ApiException
    {
        checkDisposed();
        IFeatureGroup result = IFeatureGroup.fromHandle(NativeGetPolyline(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatureGroup getPolygon() throws ApiException
    {
        checkDisposed();
        IFeatureGroup result = IFeatureGroup.fromHandle(NativeGetPolygon(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatureGroup getPoint() throws ApiException
    {
        checkDisposed();
        IFeatureGroup result = IFeatureGroup.fromHandle(NativeGetPoint(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatureGroup getAnnotation() throws ApiException
    {
        checkDisposed();
        IFeatureGroup result = IFeatureGroup.fromHandle(NativeGetAnnotation(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatureGroup getText() throws ApiException
    {
        checkDisposed();
        IFeatureGroup result = IFeatureGroup.fromHandle(NativeGetText(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void SetClassification(String Name,String Value) throws ApiException
    {
        checkDisposed();
        NativeSetClassification(this.getHandle(),Name,Value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetProperty(String Name,Object Value) throws ApiException
    {
        checkDisposed();
        NativeSetProperty(this.getHandle(),Name,Value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native Object NativeGetItem(int instance,Object Index);

private static native int NativeGetCount(int instance);

private static native int NativeGetPolyline(int instance);

private static native int NativeGetPolygon(int instance);

private static native int NativeGetPoint(int instance);

private static native int NativeGetAnnotation(int instance);

private static native int NativeGetText(int instance);

private static native void NativeSetClassification(int instance,String Name,String Value);

private static native void NativeSetProperty(int instance,String Name,Object Value);
};
