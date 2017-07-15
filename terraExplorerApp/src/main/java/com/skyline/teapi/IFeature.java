package com.skyline.teapi;

public final class IFeature extends TEIUnknownHandle {
    private IFeature(int handle)
    {
        super(handle);
    }    
    public static IFeature fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IFeature(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("AB641B7C-4415-4634-B2FE-E38B55A83CEB");

    public String getID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getObjectType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetObjectType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String get_ClientData(String Namespace) throws ApiException
    {
        checkDisposed();
        String result = (NativeGetClientData(this.getHandle(),Namespace));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void set_ClientData(String Namespace,String pVal) throws ApiException
    {
        checkDisposed();
        NativeSetClientData(this.getHandle(),Namespace,pVal);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getSaveInFlyFile() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetSaveInFlyFile(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setSaveInFlyFile(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetSaveInFlyFile(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IGeometry getGeometry() throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeGetGeometry(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setGeometry(IGeometry value) throws ApiException
    {
        checkDisposed();
        NativeSetGeometry(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IGeometry getGeometryZ() throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeGetGeometryZ(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatureAttributes getFeatureAttributes() throws ApiException
    {
        checkDisposed();
        IFeatureAttributes result = IFeatureAttributes.fromHandle(NativeGetFeatureAttributes(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getState() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetState(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getDataSourceFeatureID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetDataSourceFeatureID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getParentGroupID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetParentGroupID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getLayerID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetLayerID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IColor getTint() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetTint(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTint(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetTint(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getShow() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetShow(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setShow(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetShow(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object GetParam(int Code) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetParam(this.getHandle(),Code));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void SetParam(int Code,Object Param) throws ApiException
    {
        checkDisposed();
        NativeSetParam(this.getHandle(),Code,Param);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native String NativeGetID(int instance);

private static native int NativeGetObjectType(int instance);

private static native String NativeGetClientData(int instance,String Namespace);

private static native void NativeSetClientData(int instance,String Namespace,String pVal);

private static native boolean NativeGetSaveInFlyFile(int instance);

private static native void NativeSetSaveInFlyFile(int instance,boolean pVal);

private static native int NativeGetGeometry(int instance);

private static native void NativeSetGeometry(int instance,IGeometry pVal);

private static native int NativeGetGeometryZ(int instance);

private static native int NativeGetFeatureAttributes(int instance);

private static native int NativeGetState(int instance);

private static native String NativeGetDataSourceFeatureID(int instance);

private static native String NativeGetParentGroupID(int instance);

private static native String NativeGetLayerID(int instance);

private static native int NativeGetTint(int instance);

private static native void NativeSetTint(int instance,IColor pVal);

private static native boolean NativeGetShow(int instance);

private static native void NativeSetShow(int instance,boolean pVal);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
