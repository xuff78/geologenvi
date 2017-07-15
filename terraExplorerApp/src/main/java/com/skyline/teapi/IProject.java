package com.skyline.teapi;

public final class IProject extends TEIUnknownHandle {
    private IProject(int handle)
    {
        super(handle);
    }    
    public static IProject fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IProject(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("09191749-B22F-4E28-A119-65A512BD8D17");

    public String getName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITEVersionInfo getFileVersion() throws ApiException
    {
        checkDisposed();
        ITEVersionInfo result = ITEVersionInfo.fromHandle(NativeGetFileVersion(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object get_Settings(String paramName) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetSettings(this.getHandle(),paramName));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void set_Settings(String paramName,Object pVal) throws ApiException
    {
        checkDisposed();
        NativeSetSettings(this.getHandle(),paramName,pVal);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean Close(boolean bSuppressConfirmation) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeClose(this.getHandle(),bSuppressConfirmation));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean Close() throws ApiException
    {
        return Close(false);
    }

    public void Open(String ProjectURL,boolean Asynchronous,String User,String Password) throws ApiException
    {
        checkDisposed();
        NativeOpen(this.getHandle(),ProjectURL,Asynchronous,User,Password);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void Open(String ProjectURL) throws ApiException
    {
        Open(ProjectURL,false,"","");
    }

    public void Open(String ProjectURL,boolean Asynchronous) throws ApiException
    {
        Open(ProjectURL,Asynchronous,"","");
    }

    public void Open(String ProjectURL,boolean Asynchronous,String User) throws ApiException
    {
        Open(ProjectURL,Asynchronous,User,"");
    }

    public void Save() throws ApiException
    {
        checkDisposed();
        NativeSave(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String SaveAs(String ProjectFileName) throws ApiException
    {
        checkDisposed();
        String result = (NativeSaveAs(this.getHandle(),ProjectFileName));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native String NativeGetName(int instance);

private static native int NativeGetFileVersion(int instance);

private static native Object NativeGetSettings(int instance,String paramName);

private static native void NativeSetSettings(int instance,String paramName,Object pVal);

private static native boolean NativeClose(int instance,boolean bSuppressConfirmation);

private static native void NativeOpen(int instance,String ProjectURL,boolean Asynchronous,String User,String Password);

private static native void NativeSave(int instance);

private static native String NativeSaveAs(int instance,String ProjectFileName);
};
