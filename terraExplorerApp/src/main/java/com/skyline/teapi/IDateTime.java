package com.skyline.teapi;

public final class IDateTime extends TEIUnknownHandle {
    private IDateTime(int handle)
    {
        super(handle);
    }    
    public static IDateTime fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IDateTime(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("156CD8B9-5979-4E3F-A0B4-0DD92BB86EFB");

    public int getTimeZoneMode() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetTimeZoneMode(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTimeZoneMode(int value) throws ApiException
    {
        checkDisposed();
        NativeSetTimeZoneMode(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getDisplaySun() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetDisplaySun(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDisplaySun(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetDisplaySun(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object getCurrent() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetCurrent(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCurrent(Object value) throws ApiException
    {
        checkDisposed();
        NativeSetCurrent(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getCurrentTimeBuffer() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCurrentTimeBuffer(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCurrentTimeBuffer(int value) throws ApiException
    {
        checkDisposed();
        NativeSetCurrentTimeBuffer(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object getFixedLocalTime() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetFixedLocalTime(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFixedLocalTime(Object value) throws ApiException
    {
        checkDisposed();
        NativeSetFixedLocalTime(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object getTimeRangeStart() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetTimeRangeStart(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTimeRangeStart(Object value) throws ApiException
    {
        checkDisposed();
        NativeSetTimeRangeStart(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object getTimeRangeEnd() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetTimeRangeEnd(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTimeRangeEnd(Object value) throws ApiException
    {
        checkDisposed();
        NativeSetTimeRangeEnd(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getTimeZoneKey() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetTimeZoneKey(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTimeZoneKey(String value) throws ApiException
    {
        checkDisposed();
        NativeSetTimeZoneKey(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getTimeZoneDisplayName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetTimeZoneDisplayName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getTimeZonesXML() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetTimeZonesXML(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void SetMode(int Mode) throws ApiException
    {
        checkDisposed();
        NativeSetMode(this.getHandle(),Mode);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native int NativeGetTimeZoneMode(int instance);

private static native void NativeSetTimeZoneMode(int instance,int pVal);

private static native boolean NativeGetDisplaySun(int instance);

private static native void NativeSetDisplaySun(int instance,boolean pVal);

private static native Object NativeGetCurrent(int instance);

private static native void NativeSetCurrent(int instance,Object pVal);

private static native int NativeGetCurrentTimeBuffer(int instance);

private static native void NativeSetCurrentTimeBuffer(int instance,int pSeconds);

private static native Object NativeGetFixedLocalTime(int instance);

private static native void NativeSetFixedLocalTime(int instance,Object pVal);

private static native Object NativeGetTimeRangeStart(int instance);

private static native void NativeSetTimeRangeStart(int instance,Object pVal);

private static native Object NativeGetTimeRangeEnd(int instance);

private static native void NativeSetTimeRangeEnd(int instance,Object pVal);

private static native String NativeGetTimeZoneKey(int instance);

private static native void NativeSetTimeZoneKey(int instance,String pVal);

private static native String NativeGetTimeZoneDisplayName(int instance);

private static native String NativeGetTimeZonesXML(int instance);

private static native void NativeSetMode(int instance,int Mode);
};
