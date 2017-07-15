package com.skyline.teapi;

public final class IPopupMessage extends TEIUnknownHandle {
    private IPopupMessage(int handle)
    {
        super(handle);
    }    
    public static IPopupMessage fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IPopupMessage(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("10E94AED-0B9E-4C85-80F1-AF0DAF85B9A1");

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

    public int getLeft() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetLeft(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setLeft(int value) throws ApiException
    {
        checkDisposed();
        NativeSetLeft(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getTop() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetTop(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTop(int value) throws ApiException
    {
        checkDisposed();
        NativeSetTop(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getWidth() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetWidth(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setWidth(int value) throws ApiException
    {
        checkDisposed();
        NativeSetWidth(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getHeight() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetHeight(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setHeight(int value) throws ApiException
    {
        checkDisposed();
        NativeSetHeight(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getCaption() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetCaption(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCaption(String value) throws ApiException
    {
        checkDisposed();
        NativeSetCaption(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getSrc() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetSrc(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setSrc(String value) throws ApiException
    {
        checkDisposed();
        NativeSetSrc(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getTimeout() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetTimeout(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTimeout(int value) throws ApiException
    {
        checkDisposed();
        NativeSetTimeout(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getInnerHTML() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetInnerHTML(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setInnerHTML(String value) throws ApiException
    {
        checkDisposed();
        NativeSetInnerHTML(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getInnerText() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetInnerText(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setInnerText(String value) throws ApiException
    {
        checkDisposed();
        NativeSetInnerText(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getAllowResize() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetAllowResize(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAllowResize(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetAllowResize(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getAllowDrag() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetAllowDrag(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAllowDrag(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetAllowDrag(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getAnchorToView() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetAnchorToView(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAnchorToView(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetAnchorToView(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getShowCaption() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetShowCaption(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setShowCaption(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetShowCaption(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getFocusToRender() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetFocusToRender(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFocusToRender(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetFocusToRender(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getAlign() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetAlign(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAlign(String value) throws ApiException
    {
        checkDisposed();
        NativeSetAlign(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getFlags() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetFlags(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFlags(int value) throws ApiException
    {
        checkDisposed();
        NativeSetFlags(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object GetParam(int Code) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetParam(this.getHandle(),Code));
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

private static native int NativeGetLeft(int instance);

private static native void NativeSetLeft(int instance,int pVal);

private static native int NativeGetTop(int instance);

private static native void NativeSetTop(int instance,int pVal);

private static native int NativeGetWidth(int instance);

private static native void NativeSetWidth(int instance,int pVal);

private static native int NativeGetHeight(int instance);

private static native void NativeSetHeight(int instance,int pVal);

private static native String NativeGetCaption(int instance);

private static native void NativeSetCaption(int instance,String pVal);

private static native String NativeGetSrc(int instance);

private static native void NativeSetSrc(int instance,String pVal);

private static native int NativeGetTimeout(int instance);

private static native void NativeSetTimeout(int instance,int pVal);

private static native String NativeGetInnerHTML(int instance);

private static native void NativeSetInnerHTML(int instance,String pVal);

private static native String NativeGetInnerText(int instance);

private static native void NativeSetInnerText(int instance,String pVal);

private static native boolean NativeGetAllowResize(int instance);

private static native void NativeSetAllowResize(int instance,boolean pVal);

private static native boolean NativeGetAllowDrag(int instance);

private static native void NativeSetAllowDrag(int instance,boolean pVal);

private static native boolean NativeGetAnchorToView(int instance);

private static native void NativeSetAnchorToView(int instance,boolean pVal);

private static native boolean NativeGetShowCaption(int instance);

private static native void NativeSetShowCaption(int instance,boolean pVal);

private static native boolean NativeGetFocusToRender(int instance);

private static native void NativeSetFocusToRender(int instance,boolean pVal);

private static native String NativeGetAlign(int instance);

private static native void NativeSetAlign(int instance,String pVal);

private static native int NativeGetFlags(int instance);

private static native void NativeSetFlags(int instance,int pVal);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
