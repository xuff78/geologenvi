package com.skyline.teapi;

public final class IAttribute extends TEIUnknownHandle {
    private IAttribute(int handle)
    {
        super(handle);
    }    
    public static IAttribute fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IAttribute(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("21845937-C4D0-40B1-9C8F-518EACF4EA32");

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

    public int getSize() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetSize(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setSize(int value) throws ApiException
    {
        checkDisposed();
        NativeSetSize(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getImport() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetImport(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setImport(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetImport(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native String NativeGetName(int instance);

private static native void NativeSetName(int instance,String pVal);

private static native int NativeGetType(int instance);

private static native void NativeSetType(int instance,int pVal);

private static native int NativeGetSize(int instance);

private static native void NativeSetSize(int instance,int pVal);

private static native boolean NativeGetImport(int instance);

private static native void NativeSetImport(int instance,boolean pVal);
};
