package com.skyline.teapi;

public final class ITerrainLocation extends TEIUnknownHandle {
    private ITerrainLocation(int handle)
    {
        super(handle);
    }    
    public static ITerrainLocation fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITerrainLocation(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("CF518E74-64F7-470B-A250-11988880ADED");

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

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
