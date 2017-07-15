package com.skyline.teapi;

public final class ITerrainVideo extends TEIUnknownHandle {
    private ITerrainVideo(int handle)
    {
        super(handle);
    }    
    public static ITerrainVideo fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITerrainVideo(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("B8E3767F-31CD-41AC-808A-90C89D3F1A7F");

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

    public String getVideoFileName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetVideoFileName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setVideoFileName(String value) throws ApiException
    {
        checkDisposed();
        NativeSetVideoFileName(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getTelemetryFileName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetTelemetryFileName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTelemetryFileName(String value) throws ApiException
    {
        checkDisposed();
        NativeSetTelemetryFileName(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getUseTelemetry() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetUseTelemetry(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setUseTelemetry(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetUseTelemetry(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getProjectionFieldOfView() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetProjectionFieldOfView(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setProjectionFieldOfView(double value) throws ApiException
    {
        checkDisposed();
        NativeSetProjectionFieldOfView(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getVideoOpacity() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetVideoOpacity(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setVideoOpacity(double value) throws ApiException
    {
        checkDisposed();
        NativeSetVideoOpacity(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getShowProjectionLines() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetShowProjectionLines(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setShowProjectionLines(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetShowProjectionLines(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getProjectionLinesLength() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetProjectionLinesLength(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setProjectionLinesLength(double value) throws ApiException
    {
        checkDisposed();
        NativeSetProjectionLinesLength(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getShowProjector() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetShowProjector(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setShowProjector(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetShowProjector(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getProjectorLength() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetProjectorLength(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setProjectorLength(double value) throws ApiException
    {
        checkDisposed();
        NativeSetProjectorLength(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getVolume() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetVolume(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setVolume(int value) throws ApiException
    {
        checkDisposed();
        NativeSetVolume(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getMute() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetMute(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMute(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetMute(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getPlayVideoOnStartup() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetPlayVideoOnStartup(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPlayVideoOnStartup(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetPlayVideoOnStartup(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getVideoLength() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetVideoLength(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getVideoPosition() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetVideoPosition(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setVideoPosition(double value) throws ApiException
    {
        checkDisposed();
        NativeSetVideoPosition(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getCanSeek() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetCanSeek(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getPlayStatus() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetPlayStatus(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getMaximumProjectionDistance() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMaximumProjectionDistance(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMaximumProjectionDistance(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMaximumProjectionDistance(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getProjectOnHiddenSurfaces() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetProjectOnHiddenSurfaces(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setProjectOnHiddenSurfaces(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetProjectOnHiddenSurfaces(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object GetParam(int Code) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetParam(this.getHandle(),Code));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void PlayVideo(int Play) throws ApiException
    {
        checkDisposed();
        NativePlayVideo(this.getHandle(),Play);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void PlayVideo() throws ApiException
    {
        PlayVideo(VideoPlayStatus.VPS_PLAY);
    }

    public void SetImage(Object Image,int Format,Object Param) throws ApiException
    {
        checkDisposed();
        NativeSetImage(this.getHandle(),Image,Format,Param);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetImage(Object Image) throws ApiException
    {
        SetImage(Image,0,null);
    }

    public void SetImage(Object Image,int Format) throws ApiException
    {
        SetImage(Image,Format,null);
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

private static native String NativeGetVideoFileName(int instance);

private static native void NativeSetVideoFileName(int instance,String pVal);

private static native String NativeGetTelemetryFileName(int instance);

private static native void NativeSetTelemetryFileName(int instance,String pVal);

private static native boolean NativeGetUseTelemetry(int instance);

private static native void NativeSetUseTelemetry(int instance,boolean pVal);

private static native double NativeGetProjectionFieldOfView(int instance);

private static native void NativeSetProjectionFieldOfView(int instance,double pVal);

private static native double NativeGetVideoOpacity(int instance);

private static native void NativeSetVideoOpacity(int instance,double pVal);

private static native boolean NativeGetShowProjectionLines(int instance);

private static native void NativeSetShowProjectionLines(int instance,boolean pVal);

private static native double NativeGetProjectionLinesLength(int instance);

private static native void NativeSetProjectionLinesLength(int instance,double pVal);

private static native boolean NativeGetShowProjector(int instance);

private static native void NativeSetShowProjector(int instance,boolean pVal);

private static native double NativeGetProjectorLength(int instance);

private static native void NativeSetProjectorLength(int instance,double pVal);

private static native int NativeGetVolume(int instance);

private static native void NativeSetVolume(int instance,int pVal);

private static native boolean NativeGetMute(int instance);

private static native void NativeSetMute(int instance,boolean pVal);

private static native boolean NativeGetPlayVideoOnStartup(int instance);

private static native void NativeSetPlayVideoOnStartup(int instance,boolean pVal);

private static native double NativeGetVideoLength(int instance);

private static native double NativeGetVideoPosition(int instance);

private static native void NativeSetVideoPosition(int instance,double pVal);

private static native boolean NativeGetCanSeek(int instance);

private static native int NativeGetPlayStatus(int instance);

private static native double NativeGetMaximumProjectionDistance(int instance);

private static native void NativeSetMaximumProjectionDistance(int instance,double pVal);

private static native boolean NativeGetProjectOnHiddenSurfaces(int instance);

private static native void NativeSetProjectOnHiddenSurfaces(int instance,boolean pVal);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativePlayVideo(int instance,int Play);

private static native void NativeSetImage(int instance,Object Image,int Format,Object Param);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
