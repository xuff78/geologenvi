package com.skyline.teapi;

public final class ITerrainModel extends TEIUnknownHandle {
    private ITerrainModel(int handle)
    {
        super(handle);
    }    
    public static ITerrainModel fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITerrainModel(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("3C1044F0-7C27-432B-8B2F-0851685AE33A");

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

    public double getBestLOD() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetBestLOD(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setBestLOD(double value) throws ApiException
    {
        checkDisposed();
        NativeSetBestLOD(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getModelType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetModelType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
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

private static native String NativeGetModelFileName(int instance);

private static native void NativeSetModelFileName(int instance,String pVal);

private static native double NativeGetScaleFactor(int instance);

private static native void NativeSetScaleFactor(int instance,double pVal);

private static native boolean NativeGetFlipTexture(int instance);

private static native void NativeSetFlipTexture(int instance,boolean pVal);

private static native double NativeGetBestLOD(int instance);

private static native void NativeSetBestLOD(int instance,double pVal);

private static native int NativeGetModelType(int instance);

private static native double NativeGetScaleX(int instance);

private static native void NativeSetScaleX(int instance,double pVal);

private static native double NativeGetScaleY(int instance);

private static native void NativeSetScaleY(int instance,double pVal);

private static native double NativeGetScaleZ(int instance);

private static native void NativeSetScaleZ(int instance,double pVal);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
