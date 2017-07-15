package com.skyline.teapi;

public final class IBuildingFace extends TEIUnknownHandle {
    private IBuildingFace(int handle)
    {
        super(handle);
    }    
    public static IBuildingFace fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IBuildingFace(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("6459BDF1-9AAA-4B17-92E3-575C14308E51");

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

private static native int NativeGetFillType(int instance);

private static native void NativeSetFillType(int instance,int pVal);

private static native int NativeGetColor(int instance);

private static native void NativeSetColor(int instance,IColor pVal);

private static native int NativeGetTexture(int instance);
};
