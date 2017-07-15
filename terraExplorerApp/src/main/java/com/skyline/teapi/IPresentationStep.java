package com.skyline.teapi;

public final class IPresentationStep extends TEIUnknownHandle {
    private IPresentationStep(int handle)
    {
        super(handle);
    }    
    public static IPresentationStep fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IPresentationStep(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("1EA450A1-388D-42A9-AB30-D8A5EE299A0F");

    public int getContinue() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetContinue(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setContinue(int value) throws ApiException
    {
        checkDisposed();
        NativeSetContinue(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getFlightSpeedFactor() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetFlightSpeedFactor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFlightSpeedFactor(int value) throws ApiException
    {
        checkDisposed();
        NativeSetFlightSpeedFactor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getWaitTime() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetWaitTime(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setWaitTime(int value) throws ApiException
    {
        checkDisposed();
        NativeSetWaitTime(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getCaptionTimeout() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCaptionTimeout(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCaptionTimeout(int value) throws ApiException
    {
        checkDisposed();
        NativeSetCaptionTimeout(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getIndex() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetIndex(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getDescription() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetDescription(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDescription(String value) throws ApiException
    {
        checkDisposed();
        NativeSetDescription(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getCaptionText() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetCaptionText(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCaptionText(String value) throws ApiException
    {
        checkDisposed();
        NativeSetCaptionText(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object getSetTimeVal() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetSetTimeVal(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setSetTimeVal(Object value) throws ApiException
    {
        checkDisposed();
        NativeSetSetTimeVal(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getAnimationLength() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetAnimationLength(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAnimationLength(int value) throws ApiException
    {
        checkDisposed();
        NativeSetAnimationLength(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object getAnimationStartDateAndTime() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetAnimationStartDateAndTime(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAnimationStartDateAndTime(Object value) throws ApiException
    {
        checkDisposed();
        NativeSetAnimationStartDateAndTime(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object getAnimationEndDateAndTime() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetAnimationEndDateAndTime(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAnimationEndDateAndTime(Object value) throws ApiException
    {
        checkDisposed();
        NativeSetAnimationEndDateAndTime(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object getReserved() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetReserved(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setReserved(Object value) throws ApiException
    {
        checkDisposed();
        NativeSetReserved(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getKeyStep() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetKeyStep(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setKeyStep(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetKeyStep(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getLocationSplineSpeedBehavior() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetLocationSplineSpeedBehavior(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setLocationSplineSpeedBehavior(int value) throws ApiException
    {
        checkDisposed();
        NativeSetLocationSplineSpeedBehavior(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getLocationSplineSpeed() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetLocationSplineSpeed(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setLocationSplineSpeed(double value) throws ApiException
    {
        checkDisposed();
        NativeSetLocationSplineSpeed(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getShowHideValue() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetShowHideValue(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setShowHideValue(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetShowHideValue(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native int NativeGetContinue(int instance);

private static native void NativeSetContinue(int instance,int pVal);

private static native int NativeGetFlightSpeedFactor(int instance);

private static native void NativeSetFlightSpeedFactor(int instance,int pVal);

private static native int NativeGetType(int instance);

private static native int NativeGetWaitTime(int instance);

private static native void NativeSetWaitTime(int instance,int pVal);

private static native int NativeGetCaptionTimeout(int instance);

private static native void NativeSetCaptionTimeout(int instance,int pVal);

private static native int NativeGetIndex(int instance);

private static native String NativeGetDescription(int instance);

private static native void NativeSetDescription(int instance,String pVal);

private static native String NativeGetID(int instance);

private static native String NativeGetCaptionText(int instance);

private static native void NativeSetCaptionText(int instance,String pVal);

private static native Object NativeGetSetTimeVal(int instance);

private static native void NativeSetSetTimeVal(int instance,Object pVal);

private static native int NativeGetAnimationLength(int instance);

private static native void NativeSetAnimationLength(int instance,int pVal);

private static native Object NativeGetAnimationStartDateAndTime(int instance);

private static native void NativeSetAnimationStartDateAndTime(int instance,Object pVal);

private static native Object NativeGetAnimationEndDateAndTime(int instance);

private static native void NativeSetAnimationEndDateAndTime(int instance,Object pVal);

private static native Object NativeGetReserved(int instance);

private static native void NativeSetReserved(int instance,Object pVal);

private static native boolean NativeGetKeyStep(int instance);

private static native void NativeSetKeyStep(int instance,boolean pVal);

private static native int NativeGetLocationSplineSpeedBehavior(int instance);

private static native void NativeSetLocationSplineSpeedBehavior(int instance,int pVal);

private static native double NativeGetLocationSplineSpeed(int instance);

private static native void NativeSetLocationSplineSpeed(int instance,double pVal);

private static native boolean NativeGetShowHideValue(int instance);

private static native void NativeSetShowHideValue(int instance,boolean pVal);
};
