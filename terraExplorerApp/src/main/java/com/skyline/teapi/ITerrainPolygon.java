package com.skyline.teapi;

public final class ITerrainPolygon extends TEIUnknownHandle {
    private ITerrainPolygon(int handle)
    {
        super(handle);
    }    
    public static ITerrainPolygon fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITerrainPolygon(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("AC636064-D3BD-487F-AB97-A22FFED70AB2");

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

    public IMessageObject getMessage() throws ApiException
    {
        checkDisposed();
        IMessageObject result = IMessageObject.fromHandle(NativeGetMessage(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IAction getAction() throws ApiException
    {
        checkDisposed();
        IAction result = IAction.fromHandle(NativeGetAction(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
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

    public ITerrainObject getTerrain() throws ApiException
    {
        checkDisposed();
        ITerrainObject result = ITerrainObject.fromHandle(NativeGetTerrain(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITooltip getTooltip() throws ApiException
    {
        checkDisposed();
        ITooltip result = ITooltip.fromHandle(NativeGetTooltip(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IAttachment getAttachment() throws ApiException
    {
        checkDisposed();
        IAttachment result = IAttachment.fromHandle(NativeGetAttachment(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IVisibility getVisibility() throws ApiException
    {
        checkDisposed();
        IVisibility result = IVisibility.fromHandle(NativeGetVisibility(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITimeSpan getTimeSpan() throws ApiException
    {
        checkDisposed();
        ITimeSpan result = ITimeSpan.fromHandle(NativeGetTimeSpan(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ILineStyle getLineStyle() throws ApiException
    {
        checkDisposed();
        ILineStyle result = ILineStyle.fromHandle(NativeGetLineStyle(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFillStyle getFillStyle() throws ApiException
    {
        checkDisposed();
        IFillStyle result = IFillStyle.fromHandle(NativeGetFillStyle(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
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

    public boolean getSpline() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetSpline(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setSpline(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetSpline(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getExtendToGround() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetExtendToGround(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setExtendToGround(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetExtendToGround(this.getHandle(),value);
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

private static native int NativeGetTreeItem(int instance);

private static native int NativeGetMessage(int instance);

private static native int NativeGetAction(int instance);

private static native int NativeGetPosition(int instance);

private static native void NativeSetPosition(int instance,IPosition pVal);

private static native int NativeGetTerrain(int instance);

private static native int NativeGetTooltip(int instance);

private static native int NativeGetAttachment(int instance);

private static native int NativeGetVisibility(int instance);

private static native int NativeGetTimeSpan(int instance);

private static native int NativeGetLineStyle(int instance);

private static native int NativeGetFillStyle(int instance);

private static native int NativeGetGeometry(int instance);

private static native void NativeSetGeometry(int instance,IGeometry pVal);

private static native boolean NativeGetSpline(int instance);

private static native void NativeSetSpline(int instance,boolean pVal);

private static native boolean NativeGetExtendToGround(int instance);

private static native void NativeSetExtendToGround(int instance,boolean pVal);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
