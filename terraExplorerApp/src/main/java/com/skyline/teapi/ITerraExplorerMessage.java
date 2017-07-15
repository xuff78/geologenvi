package com.skyline.teapi;

public final class ITerraExplorerMessage extends TEIUnknownHandle {
    private ITerraExplorerMessage(int handle)
    {
        super(handle);
    }    
    public static ITerraExplorerMessage fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITerraExplorerMessage(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("0D211399-502C-4319-81C0-DD88A4753BA1");

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

    public int getTargetPosition() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetTargetPosition(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTargetPosition(int value) throws ApiException
    {
        checkDisposed();
        NativeSetTargetPosition(this.getHandle(),value);
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

    public String getURL() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetURL(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setURL(String value) throws ApiException
    {
        checkDisposed();
        NativeSetURL(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setType(int value) throws ApiException
    {
        checkDisposed();
        NativeSetType(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getBringToFront() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetBringToFront(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setBringToFront(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetBringToFront(this.getHandle(),value);
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

private static native int NativeGetTargetPosition(int instance);

private static native void NativeSetTargetPosition(int instance,int pVal);

private static native String NativeGetText(int instance);

private static native void NativeSetText(int instance,String pVal);

private static native String NativeGetURL(int instance);

private static native void NativeSetURL(int instance,String pVal);

private static native int NativeGetType(int instance);

private static native void NativeSetType(int instance,int pVal);

private static native boolean NativeGetBringToFront(int instance);

private static native void NativeSetBringToFront(int instance,boolean pVal);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
