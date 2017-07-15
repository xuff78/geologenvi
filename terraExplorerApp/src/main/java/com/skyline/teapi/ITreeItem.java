package com.skyline.teapi;

public final class ITreeItem extends TEIUnknownHandle {
    private ITreeItem(int handle)
    {
        super(handle);
    }    
    public static ITreeItem fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITreeItem(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("FA7CEFFF-41DD-451D-87F8-6F85A3E4BA44");

    public String getName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setName(String value) throws ApiException
    {
        checkDisposed();
        NativeSetName(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getShowInViewerTree() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetShowInViewerTree(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setShowInViewerTree(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetShowInViewerTree(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getParentGroupName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetParentGroupName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native String NativeGetName(int instance);

private static native void NativeSetName(int instance,String pVal);

private static native boolean NativeGetShowInViewerTree(int instance);

private static native void NativeSetShowInViewerTree(int instance,boolean pVal);

private static native String NativeGetParentGroupName(int instance);
};
