package com.skyline.teapi;

public final class I3DViewshed extends TEIUnknownHandle {
    private I3DViewshed(int handle)
    {
        super(handle);
    }    
    public static I3DViewshed fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new I3DViewshed(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("350E9ED5-B115-4EE5-B208-A36A2851334E");

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

    public double getFieldOfViewX() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetFieldOfViewX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFieldOfViewX(double value) throws ApiException
    {
        checkDisposed();
        NativeSetFieldOfViewX(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getFieldOfViewY() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetFieldOfViewY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFieldOfViewY(double value) throws ApiException
    {
        checkDisposed();
        NativeSetFieldOfViewY(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDistance() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDistance(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDistance(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDistance(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IColor getRayColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetRayColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setRayColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetRayColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IColor getVisibleAreaColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetVisibleAreaColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setVisibleAreaColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetVisibleAreaColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IColor getHiddenAreaColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetHiddenAreaColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setHiddenAreaColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetHiddenAreaColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getQuality() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetQuality(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setQuality(int value) throws ApiException
    {
        checkDisposed();
        NativeSetQuality(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getRefreshRate() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetRefreshRate(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setRefreshRate(int value) throws ApiException
    {
        checkDisposed();
        NativeSetRefreshRate(this.getHandle(),value);
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

private static native double NativeGetFieldOfViewX(int instance);

private static native void NativeSetFieldOfViewX(int instance,double pVal);

private static native double NativeGetFieldOfViewY(int instance);

private static native void NativeSetFieldOfViewY(int instance,double pVal);

private static native double NativeGetDistance(int instance);

private static native void NativeSetDistance(int instance,double pVal);

private static native int NativeGetRayColor(int instance);

private static native void NativeSetRayColor(int instance,IColor pVal);

private static native int NativeGetVisibleAreaColor(int instance);

private static native void NativeSetVisibleAreaColor(int instance,IColor pVal);

private static native int NativeGetHiddenAreaColor(int instance);

private static native void NativeSetHiddenAreaColor(int instance,IColor pVal);

private static native int NativeGetQuality(int instance);

private static native void NativeSetQuality(int instance,int pVal);

private static native int NativeGetRefreshRate(int instance);

private static native void NativeSetRefreshRate(int instance,int pVal);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
