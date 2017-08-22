package com.skyline.teapi;

public final class ISlopeMap extends TEIUnknownHandle {
    private ISlopeMap(int handle)
    {
        super(handle);
    }    
    public static ISlopeMap fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ISlopeMap(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("02691FC4-B36C-4862-8EB5-CAB37781D4F0");

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

    public double getTop() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetTop(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTop(double value) throws ApiException
    {
        checkDisposed();
        NativeSetTop(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getLeft() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetLeft(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setLeft(double value) throws ApiException
    {
        checkDisposed();
        NativeSetLeft(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getRight() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetRight(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setRight(double value) throws ApiException
    {
        checkDisposed();
        NativeSetRight(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getBottom() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetBottom(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setBottom(double value) throws ApiException
    {
        checkDisposed();
        NativeSetBottom(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getWidth() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetWidth(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setWidth(double value) throws ApiException
    {
        checkDisposed();
        NativeSetWidth(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDepth() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDepth(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDepth(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDepth(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getDisplayStyle() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetDisplayStyle(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDisplayStyle(int value) throws ApiException
    {
        checkDisposed();
        NativeSetDisplayStyle(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getCoverageArea() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCoverageArea(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCoverageArea(int value) throws ApiException
    {
        checkDisposed();
        NativeSetCoverageArea(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getPaletteID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetPaletteID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPaletteID(String value) throws ApiException
    {
        checkDisposed();
        NativeSetPaletteID(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IColor getArrowColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetArrowColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setArrowColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetArrowColor(this.getHandle(),value);
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

private static native double NativeGetTop(int instance);

private static native void NativeSetTop(int instance,double pVal);

private static native double NativeGetLeft(int instance);

private static native void NativeSetLeft(int instance,double pVal);

private static native double NativeGetRight(int instance);

private static native void NativeSetRight(int instance,double pVal);

private static native double NativeGetBottom(int instance);

private static native void NativeSetBottom(int instance,double pVal);

private static native double NativeGetWidth(int instance);

private static native void NativeSetWidth(int instance,double pVal);

private static native double NativeGetDepth(int instance);

private static native void NativeSetDepth(int instance,double pVal);

private static native int NativeGetDisplayStyle(int instance);

private static native void NativeSetDisplayStyle(int instance,int pVal);

private static native int NativeGetCoverageArea(int instance);

private static native void NativeSetCoverageArea(int instance,int pVal);

private static native String NativeGetPaletteID(int instance);

private static native void NativeSetPaletteID(int instance,String pVal);

private static native int NativeGetArrowColor(int instance);

private static native void NativeSetArrowColor(int instance,IColor pVal);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeSetParam(int instance,int Code,Object Param);
};