package com.skyline.teapi;

public final class IAviWriter extends TEIUnknownHandle {
    private IAviWriter(int handle)
    {
        super(handle);
    }    
    public static IAviWriter fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IAviWriter(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("08BB0D01-F126-4D60-AFEB-46645D7E2AAB");

    public String CreateMovie(String Name,int Width,int Height,double FramesPerSecond,ICodecInfo pCodecInfo,boolean HideScreenOverlays) throws ApiException
    {
        checkDisposed();
        String result = (NativeCreateMovie(this.getHandle(),Name,Width,Height,FramesPerSecond,pCodecInfo,HideScreenOverlays));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object GetAvailableCodecs() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetAvailableCodecs(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native String NativeCreateMovie(int instance,String Name,int Width,int Height,double FramesPerSecond,ICodecInfo pCodecInfo,boolean HideScreenOverlays);

private static native Object NativeGetAvailableCodecs(int instance);
};
