package com.skyline.teapi;

public final class ILabelStyle extends TEIUnknownHandle {
    private ILabelStyle(int handle)
    {
        super(handle);
    }    
    public static ILabelStyle fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ILabelStyle(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("49B20A09-64BE-41A0-A02F-776103EAEC65");

    public IColor getTextColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetTextColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTextColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetTextColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IColor getBackgroundColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetBackgroundColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setBackgroundColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetBackgroundColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getFontName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetFontName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFontName(String value) throws ApiException
    {
        checkDisposed();
        NativeSetFontName(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getFontSize() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetFontSize(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFontSize(int value) throws ApiException
    {
        checkDisposed();
        NativeSetFontSize(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getBold() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetBold(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setBold(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetBold(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getItalic() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetItalic(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setItalic(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetItalic(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getUnderline() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetUnderline(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setUnderline(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetUnderline(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getMaxViewingHeight() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMaxViewingHeight(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMaxViewingHeight(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMaxViewingHeight(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getMinViewingHeight() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMinViewingHeight(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMinViewingHeight(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMinViewingHeight(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getTextOnImage() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetTextOnImage(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTextOnImage(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetTextOnImage(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getMultilineJustification() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetMultilineJustification(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMultilineJustification(String value) throws ApiException
    {
        checkDisposed();
        NativeSetMultilineJustification(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getTextAlignment() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetTextAlignment(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTextAlignment(String value) throws ApiException
    {
        checkDisposed();
        NativeSetTextAlignment(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getPivotAlignment() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetPivotAlignment(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPivotAlignment(String value) throws ApiException
    {
        checkDisposed();
        NativeSetPivotAlignment(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getLockMode() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetLockMode(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setLockMode(int value) throws ApiException
    {
        checkDisposed();
        NativeSetLockMode(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getLineToGround() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetLineToGround(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setLineToGround(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetLineToGround(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IColor getLineColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetLineColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setLineColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetLineColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getFrameFileName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetFrameFileName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFrameFileName(String value) throws ApiException
    {
        checkDisposed();
        NativeSetFrameFileName(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getLimitScreenSize() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetLimitScreenSize(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setLimitScreenSize(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetLimitScreenSize(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getScale() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetScale(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setScale(double value) throws ApiException
    {
        checkDisposed();
        NativeSetScale(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getMaxImageSize() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetMaxImageSize(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMaxImageSize(int value) throws ApiException
    {
        checkDisposed();
        NativeSetMaxImageSize(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IColor getIconColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetIconColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setIconColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetIconColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getShowTextBehavior() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetShowTextBehavior(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setShowTextBehavior(int value) throws ApiException
    {
        checkDisposed();
        NativeSetShowTextBehavior(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getSmallestVisibleSize() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetSmallestVisibleSize(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setSmallestVisibleSize(int value) throws ApiException
    {
        checkDisposed();
        NativeSetSmallestVisibleSize(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native int NativeGetTextColor(int instance);

private static native void NativeSetTextColor(int instance,IColor pVal);

private static native int NativeGetBackgroundColor(int instance);

private static native void NativeSetBackgroundColor(int instance,IColor pVal);

private static native String NativeGetFontName(int instance);

private static native void NativeSetFontName(int instance,String pVal);

private static native int NativeGetFontSize(int instance);

private static native void NativeSetFontSize(int instance,int pVal);

private static native boolean NativeGetBold(int instance);

private static native void NativeSetBold(int instance,boolean pVal);

private static native boolean NativeGetItalic(int instance);

private static native void NativeSetItalic(int instance,boolean pVal);

private static native boolean NativeGetUnderline(int instance);

private static native void NativeSetUnderline(int instance,boolean pVal);

private static native double NativeGetMaxViewingHeight(int instance);

private static native void NativeSetMaxViewingHeight(int instance,double pVal);

private static native double NativeGetMinViewingHeight(int instance);

private static native void NativeSetMinViewingHeight(int instance,double pVal);

private static native boolean NativeGetTextOnImage(int instance);

private static native void NativeSetTextOnImage(int instance,boolean pVal);

private static native String NativeGetMultilineJustification(int instance);

private static native void NativeSetMultilineJustification(int instance,String pVal);

private static native String NativeGetTextAlignment(int instance);

private static native void NativeSetTextAlignment(int instance,String pVal);

private static native String NativeGetPivotAlignment(int instance);

private static native void NativeSetPivotAlignment(int instance,String pVal);

private static native int NativeGetLockMode(int instance);

private static native void NativeSetLockMode(int instance,int pVal);

private static native boolean NativeGetLineToGround(int instance);

private static native void NativeSetLineToGround(int instance,boolean pVal);

private static native int NativeGetLineColor(int instance);

private static native void NativeSetLineColor(int instance,IColor pVal);

private static native String NativeGetFrameFileName(int instance);

private static native void NativeSetFrameFileName(int instance,String pVal);

private static native boolean NativeGetLimitScreenSize(int instance);

private static native void NativeSetLimitScreenSize(int instance,boolean pVal);

private static native double NativeGetScale(int instance);

private static native void NativeSetScale(int instance,double pVal);

private static native int NativeGetMaxImageSize(int instance);

private static native void NativeSetMaxImageSize(int instance,int pVal);

private static native int NativeGetIconColor(int instance);

private static native void NativeSetIconColor(int instance,IColor pVal);

private static native int NativeGetShowTextBehavior(int instance);

private static native void NativeSetShowTextBehavior(int instance,int pVal);

private static native int NativeGetSmallestVisibleSize(int instance);

private static native void NativeSetSmallestVisibleSize(int instance,int pVal);
};
