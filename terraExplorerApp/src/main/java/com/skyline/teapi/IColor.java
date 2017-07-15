package com.skyline.teapi;

public final class IColor extends TEIUnknownHandle {
    private IColor(int handle)
    {
        super(handle);
    }    
    public static IColor fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IColor(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("F32C5912-BD70-46D0-8B9C-9B1E8054EB11");

    public int getabgrColor() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetabgrColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setabgrColor(int value) throws ApiException
    {
        checkDisposed();
        NativeSetabgrColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void FromABGRColor(int abgrColor) throws ApiException
    {
        checkDisposed();
        NativeFromABGRColor(this.getHandle(),abgrColor);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void FromARGBColor(int ARGBColor) throws ApiException
    {
        checkDisposed();
        NativeFromARGBColor(this.getHandle(),ARGBColor);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void FromBGRColor(int BGRColor) throws ApiException
    {
        checkDisposed();
        NativeFromBGRColor(this.getHandle(),BGRColor);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void FromHTMLColor(String htmlColor) throws ApiException
    {
        checkDisposed();
        NativeFromHTMLColor(this.getHandle(),htmlColor);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void FromRGBColor(int RGBColor) throws ApiException
    {
        checkDisposed();
        NativeFromRGBColor(this.getHandle(),RGBColor);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double GetAlpha() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetAlpha(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void SetAlpha(double Alpha) throws ApiException
    {
        checkDisposed();
        NativeSetAlpha(this.getHandle(),Alpha);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int ToABGRColor() throws ApiException
    {
        checkDisposed();
        int result = (NativeToABGRColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int ToARGBColor() throws ApiException
    {
        checkDisposed();
        int result = (NativeToARGBColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int ToBGRColor() throws ApiException
    {
        checkDisposed();
        int result = (NativeToBGRColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String ToHTMLColor() throws ApiException
    {
        checkDisposed();
        String result = (NativeToHTMLColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int ToRGBColor() throws ApiException
    {
        checkDisposed();
        int result = (NativeToRGBColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeGetabgrColor(int instance);

private static native void NativeSetabgrColor(int instance,int pVal);

private static native void NativeFromABGRColor(int instance,int abgrColor);

private static native void NativeFromARGBColor(int instance,int ARGBColor);

private static native void NativeFromBGRColor(int instance,int BGRColor);

private static native void NativeFromHTMLColor(int instance,String htmlColor);

private static native void NativeFromRGBColor(int instance,int RGBColor);

private static native double NativeGetAlpha(int instance);

private static native void NativeSetAlpha(int instance,double Alpha);

private static native int NativeToABGRColor(int instance);

private static native int NativeToARGBColor(int instance);

private static native int NativeToBGRColor(int instance);

private static native String NativeToHTMLColor(int instance);

private static native int NativeToRGBColor(int instance);
};
