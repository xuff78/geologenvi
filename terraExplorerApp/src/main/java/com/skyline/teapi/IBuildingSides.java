package com.skyline.teapi;

public final class IBuildingSides extends TEIUnknownHandle {
    private IBuildingSides(int handle)
    {
        super(handle);
    }    
    public static IBuildingSides fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IBuildingSides(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("977DBAE7-9BC0-4155-B49F-3E6352D422CC");

    public int getFillType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetFillType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFillType(int value) throws ApiException
    {
        checkDisposed();
        NativeSetFillType(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IColor getColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IObjectTexture getTexture() throws ApiException
    {
        checkDisposed();
        IObjectTexture result = IObjectTexture.fromHandle(NativeGetTexture(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object get_Item(Object Index) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetItem(this.getHandle(),Index));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getCount() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCount(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeGetFillType(int instance);

private static native void NativeSetFillType(int instance,int pVal);

private static native int NativeGetColor(int instance);

private static native void NativeSetColor(int instance,IColor pVal);

private static native int NativeGetTexture(int instance);

private static native Object NativeGetItem(int instance,Object Index);

private static native int NativeGetCount(int instance);
};
