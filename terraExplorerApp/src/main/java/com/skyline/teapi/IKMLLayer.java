package com.skyline.teapi;

public final class IKMLLayer extends TEIUnknownHandle {
    private IKMLLayer(int handle)
    {
        super(handle);
    }    
    public static IKMLLayer fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IKMLLayer(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("252C85BC-B3A9-4AB3-9429-6FF9C31DEABA");

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

    public boolean getEditable() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetEditable(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getPath() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetPath(this.getHandle()));
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

    public void Refresh() throws ApiException
    {
        checkDisposed();
        NativeRefresh(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void Save() throws ApiException
    {
        checkDisposed();
        NativeSave(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String SaveAs(String KmlName) throws ApiException
    {
        checkDisposed();
        String result = (NativeSaveAs(this.getHandle(),KmlName));
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

private static native boolean NativeGetEditable(int instance);

private static native String NativeGetPath(int instance);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeRefresh(int instance);

private static native void NativeSave(int instance);

private static native String NativeSaveAs(int instance,String KmlName);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
