package com.skyline.teapi;

public final class IMeshLayer extends TEIUnknownHandle {
    private IMeshLayer(int handle)
    {
        super(handle);
    }    
    public static IMeshLayer fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IMeshLayer(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("3A63C1BB-AB2E-46DE-9B52-B546E5F6F39B");

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

    public ITreeItem getTreeItem() throws ApiException
    {
        checkDisposed();
        ITreeItem result = ITreeItem.fromHandle(NativeGetTreeItem(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getGroundObject() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetGroundObject(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setGroundObject(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetGroundObject(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IVisibility getVisibility() throws ApiException
    {
        checkDisposed();
        IVisibility result = IVisibility.fromHandle(NativeGetVisibility(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getScaleFactor() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetScaleFactor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setScaleFactor(double value) throws ApiException
    {
        checkDisposed();
        NativeSetScaleFactor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getScaleX() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetScaleX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setScaleX(double value) throws ApiException
    {
        checkDisposed();
        NativeSetScaleX(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getScaleY() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetScaleY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setScaleY(double value) throws ApiException
    {
        checkDisposed();
        NativeSetScaleY(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getScaleZ() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetScaleZ(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setScaleZ(double value) throws ApiException
    {
        checkDisposed();
        NativeSetScaleZ(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IPosition getPosition() throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeGetPosition(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPosition(IPosition value) throws ApiException
    {
        checkDisposed();
        NativeSetPosition(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IBBox3D getBBox() throws ApiException
    {
        checkDisposed();
        IBBox3D result = IBBox3D.fromHandle(NativeGetBBox(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getPath() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetPath(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPath(String value) throws ApiException
    {
        checkDisposed();
        NativeSetPath(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public I3DMLFeatureLayers getFeatureLayers() throws ApiException
    {
        checkDisposed();
        I3DMLFeatureLayers result = I3DMLFeatureLayers.fromHandle(NativeGetFeatureLayers(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object GetParam(int Code) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetParam(this.getHandle(),Code));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void Refresh() throws ApiException
    {
        checkDisposed();
        NativeRefresh(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
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

private static native int NativeGetTreeItem(int instance);

private static native boolean NativeGetGroundObject(int instance);

private static native void NativeSetGroundObject(int instance,boolean pVal);

private static native int NativeGetVisibility(int instance);

private static native double NativeGetScaleFactor(int instance);

private static native void NativeSetScaleFactor(int instance,double pVal);

private static native double NativeGetScaleX(int instance);

private static native void NativeSetScaleX(int instance,double pVal);

private static native double NativeGetScaleY(int instance);

private static native void NativeSetScaleY(int instance,double pVal);

private static native double NativeGetScaleZ(int instance);

private static native void NativeSetScaleZ(int instance,double pVal);

private static native int NativeGetPosition(int instance);

private static native void NativeSetPosition(int instance,IPosition pVal);

private static native int NativeGetBBox(int instance);

private static native String NativeGetPath(int instance);

private static native void NativeSetPath(int instance,String pVal);

private static native int NativeGetFeatureLayers(int instance);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeRefresh(int instance);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
