package com.skyline.teapi;

public final class ITerrainBuilding extends TEIUnknownHandle {
    private ITerrainBuilding(int handle)
    {
        super(handle);
    }    
    public static ITerrainBuilding fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITerrainBuilding(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("86914B3D-5A04-42F2-8C10-D62BD5ECACDC");

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

    public double getHeight() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetHeight(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setHeight(double value) throws ApiException
    {
        checkDisposed();
        NativeSetHeight(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getStyle() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetStyle(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setStyle(int value) throws ApiException
    {
        checkDisposed();
        NativeSetStyle(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IRoofFace getRoof() throws ApiException
    {
        checkDisposed();
        IRoofFace result = IRoofFace.fromHandle(NativeGetRoof(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IBuildingSides getSides() throws ApiException
    {
        checkDisposed();
        IBuildingSides result = IBuildingSides.fromHandle(NativeGetSides(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getEnableDraw() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetEnableDraw(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setEnableDraw(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetEnableDraw(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getTerrainTextureOffsetX() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetTerrainTextureOffsetX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTerrainTextureOffsetX(double value) throws ApiException
    {
        checkDisposed();
        NativeSetTerrainTextureOffsetX(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getTerrainTextureOffsetY() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetTerrainTextureOffsetY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTerrainTextureOffsetY(double value) throws ApiException
    {
        checkDisposed();
        NativeSetTerrainTextureOffsetY(this.getHandle(),value);
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

private static native int NativeGetGeometry(int instance);

private static native void NativeSetGeometry(int instance,IGeometry pVal);

private static native double NativeGetHeight(int instance);

private static native void NativeSetHeight(int instance,double pVal);

private static native int NativeGetStyle(int instance);

private static native void NativeSetStyle(int instance,int pVal);

private static native int NativeGetRoof(int instance);

private static native int NativeGetSides(int instance);

private static native boolean NativeGetEnableDraw(int instance);

private static native void NativeSetEnableDraw(int instance,boolean pVal);

private static native double NativeGetTerrainTextureOffsetX(int instance);

private static native void NativeSetTerrainTextureOffsetX(int instance,double pVal);

private static native double NativeGetTerrainTextureOffsetY(int instance);

private static native void NativeSetTerrainTextureOffsetY(int instance,double pVal);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
