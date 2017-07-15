package com.skyline.teapi;

public final class ITerrainPointCloudModel extends TEIUnknownHandle {
    private ITerrainPointCloudModel(int handle)
    {
        super(handle);
    }    
    public static ITerrainPointCloudModel fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITerrainPointCloudModel(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("813103FF-133F-4AF7-B617-5A0FAE90AB17");

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

    public String getModelFileName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetModelFileName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setModelFileName(String value) throws ApiException
    {
        checkDisposed();
        NativeSetModelFileName(this.getHandle(),value);
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

    public boolean getFlattenBelowTerrain() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetFlattenBelowTerrain(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFlattenBelowTerrain(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetFlattenBelowTerrain(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getHideBelowImagery() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetHideBelowImagery(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setHideBelowImagery(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetHideBelowImagery(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getMinIntensity() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMinIntensity(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMinIntensity(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMinIntensity(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getMaxIntensity() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMaxIntensity(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMaxIntensity(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMaxIntensity(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getPaletteOpacity() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetPaletteOpacity(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPaletteOpacity(double value) throws ApiException
    {
        checkDisposed();
        NativeSetPaletteOpacity(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getPaletteFileName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetPaletteFileName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPaletteFileName(String value) throws ApiException
    {
        checkDisposed();
        NativeSetPaletteFileName(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getDistributionDirection() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetDistributionDirection(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDistributionDirection(int value) throws ApiException
    {
        checkDisposed();
        NativeSetDistributionDirection(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDistributionStart() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDistributionStart(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDistributionStart(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDistributionStart(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDistributionEnd() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDistributionEnd(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDistributionEnd(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDistributionEnd(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getPointSamplingInterval() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetPointSamplingInterval(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPointSamplingInterval(double value) throws ApiException
    {
        checkDisposed();
        NativeSetPointSamplingInterval(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getPointScalingFactor() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetPointScalingFactor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPointScalingFactor(double value) throws ApiException
    {
        checkDisposed();
        NativeSetPointScalingFactor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getPointSizeLimit() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetPointSizeLimit(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPointSizeLimit(double value) throws ApiException
    {
        checkDisposed();
        NativeSetPointSizeLimit(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getChangeSampleOnMove() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetChangeSampleOnMove(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setChangeSampleOnMove(double value) throws ApiException
    {
        checkDisposed();
        NativeSetChangeSampleOnMove(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getDataFormat() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetDataFormat(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPointCloudDefaultLocation getDefaultLocation() throws ApiException
    {
        checkDisposed();
        IPointCloudDefaultLocation result = IPointCloudDefaultLocation.fromHandle(NativeGetDefaultLocation(this.getHandle()));
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

    public void SetAutoIntensity() throws ApiException
    {
        checkDisposed();
        NativeSetAutoIntensity(this.getHandle());
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

private static native int NativeGetMessage(int instance);

private static native int NativeGetAction(int instance);

private static native int NativeGetPosition(int instance);

private static native void NativeSetPosition(int instance,IPosition pVal);

private static native int NativeGetTerrain(int instance);

private static native int NativeGetTooltip(int instance);

private static native int NativeGetAttachment(int instance);

private static native int NativeGetVisibility(int instance);

private static native int NativeGetTimeSpan(int instance);

private static native String NativeGetModelFileName(int instance);

private static native void NativeSetModelFileName(int instance,String pVal);

private static native double NativeGetScaleFactor(int instance);

private static native void NativeSetScaleFactor(int instance,double pVal);

private static native boolean NativeGetFlattenBelowTerrain(int instance);

private static native void NativeSetFlattenBelowTerrain(int instance,boolean pVal);

private static native boolean NativeGetHideBelowImagery(int instance);

private static native void NativeSetHideBelowImagery(int instance,boolean pVal);

private static native double NativeGetMinIntensity(int instance);

private static native void NativeSetMinIntensity(int instance,double pVal);

private static native double NativeGetMaxIntensity(int instance);

private static native void NativeSetMaxIntensity(int instance,double pVal);

private static native double NativeGetPaletteOpacity(int instance);

private static native void NativeSetPaletteOpacity(int instance,double pVal);

private static native String NativeGetPaletteFileName(int instance);

private static native void NativeSetPaletteFileName(int instance,String pVal);

private static native int NativeGetDistributionDirection(int instance);

private static native void NativeSetDistributionDirection(int instance,int pVal);

private static native double NativeGetDistributionStart(int instance);

private static native void NativeSetDistributionStart(int instance,double pVal);

private static native double NativeGetDistributionEnd(int instance);

private static native void NativeSetDistributionEnd(int instance,double pVal);

private static native double NativeGetPointSamplingInterval(int instance);

private static native void NativeSetPointSamplingInterval(int instance,double pVal);

private static native double NativeGetPointScalingFactor(int instance);

private static native void NativeSetPointScalingFactor(int instance,double pVal);

private static native double NativeGetPointSizeLimit(int instance);

private static native void NativeSetPointSizeLimit(int instance,double pVal);

private static native double NativeGetChangeSampleOnMove(int instance);

private static native void NativeSetChangeSampleOnMove(int instance,double pVal);

private static native int NativeGetDataFormat(int instance);

private static native int NativeGetDefaultLocation(int instance);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeSetAutoIntensity(int instance);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
