package com.skyline.teapi;

public final class ITerrainDynamicObject extends TEIUnknownHandle {
    private ITerrainDynamicObject(int handle)
    {
        super(handle);
    }    
    public static ITerrainDynamicObject fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITerrainDynamicObject(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("9F9731CE-1271-4782-A656-BBBD48209361");

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

    public IRouteWaypoints getWaypoints() throws ApiException
    {
        checkDisposed();
        IRouteWaypoints result = IRouteWaypoints.fromHandle(NativeGetWaypoints(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getMotionStyle() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetMotionStyle(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMotionStyle(int value) throws ApiException
    {
        checkDisposed();
        NativeSetMotionStyle(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getDynamicType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetDynamicType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDynamicType(int value) throws ApiException
    {
        checkDisposed();
        NativeSetDynamicType(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getFileName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetFileName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFileName(String value) throws ApiException
    {
        checkDisposed();
        NativeSetFileName(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getText() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetText(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setText(String value) throws ApiException
    {
        checkDisposed();
        NativeSetText(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IColor getTextColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetTextColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTextColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetTextColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IColor getBackgroundColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetBackgroundColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setBackgroundColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetBackgroundColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getFontName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetFontName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFontName(String value) throws ApiException
    {
        checkDisposed();
        NativeSetFontName(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getFontSize() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetFontSize(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFontSize(int value) throws ApiException
    {
        checkDisposed();
        NativeSetFontSize(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getFontStyle() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetFontStyle(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFontStyle(int value) throws ApiException
    {
        checkDisposed();
        NativeSetFontStyle(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
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

    public boolean getFlipTexture() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetFlipTexture(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFlipTexture(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetFlipTexture(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getAltitudeType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetAltitudeType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAltitudeType(int value) throws ApiException
    {
        checkDisposed();
        NativeSetAltitudeType(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getAcceleration() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetAcceleration(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAcceleration(double value) throws ApiException
    {
        checkDisposed();
        NativeSetAcceleration(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getTurnSpeed() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetTurnSpeed(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTurnSpeed(double value) throws ApiException
    {
        checkDisposed();
        NativeSetTurnSpeed(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getCircularRoute() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetCircularRoute(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCircularRoute(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetCircularRoute(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getPause() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetPause(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPause(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetPause(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getMoveByTime() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetMoveByTime(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMoveByTime(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetMoveByTime(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getPlayRouteOnStartup() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetPlayRouteOnStartup(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPlayRouteOnStartup(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetPlayRouteOnStartup(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object GetParam(int Code) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetParam(this.getHandle(),Code));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void RestartRoute(int Index) throws ApiException
    {
        checkDisposed();
        NativeRestartRoute(this.getHandle(),Index);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void RestartRoute() throws ApiException
    {
        RestartRoute(0);
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

private static native int NativeGetWaypoints(int instance);

private static native int NativeGetMotionStyle(int instance);

private static native void NativeSetMotionStyle(int instance,int pVal);

private static native int NativeGetDynamicType(int instance);

private static native void NativeSetDynamicType(int instance,int pVal);

private static native String NativeGetFileName(int instance);

private static native void NativeSetFileName(int instance,String pVal);

private static native String NativeGetText(int instance);

private static native void NativeSetText(int instance,String pVal);

private static native int NativeGetTextColor(int instance);

private static native void NativeSetTextColor(int instance,IColor pVal);

private static native int NativeGetBackgroundColor(int instance);

private static native void NativeSetBackgroundColor(int instance,IColor pVal);

private static native String NativeGetFontName(int instance);

private static native void NativeSetFontName(int instance,String pVal);

private static native int NativeGetFontSize(int instance);

private static native void NativeSetFontSize(int instance,int pVal);

private static native int NativeGetFontStyle(int instance);

private static native void NativeSetFontStyle(int instance,int pVal);

private static native double NativeGetScaleFactor(int instance);

private static native void NativeSetScaleFactor(int instance,double pVal);

private static native boolean NativeGetFlipTexture(int instance);

private static native void NativeSetFlipTexture(int instance,boolean pVal);

private static native int NativeGetAltitudeType(int instance);

private static native void NativeSetAltitudeType(int instance,int pVal);

private static native double NativeGetAcceleration(int instance);

private static native void NativeSetAcceleration(int instance,double pVal);

private static native double NativeGetTurnSpeed(int instance);

private static native void NativeSetTurnSpeed(int instance,double pVal);

private static native boolean NativeGetCircularRoute(int instance);

private static native void NativeSetCircularRoute(int instance,boolean pVal);

private static native boolean NativeGetPause(int instance);

private static native void NativeSetPause(int instance,boolean pVal);

private static native boolean NativeGetMoveByTime(int instance);

private static native void NativeSetMoveByTime(int instance,boolean pVal);

private static native boolean NativeGetPlayRouteOnStartup(int instance);

private static native void NativeSetPlayRouteOnStartup(int instance,boolean pVal);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeRestartRoute(int instance,int Index);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
