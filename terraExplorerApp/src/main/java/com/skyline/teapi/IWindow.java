package com.skyline.teapi;

public final class IWindow extends TEIUnknownHandle {
    private IWindow(int handle)
    {
        super(handle);
    }    
    public static IWindow fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IWindow(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("6CAFF7CD-6A8B-44AC-B122-14F48D42AD1A");

    public boolean getDisablePresentationControl() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetDisablePresentationControl(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDisablePresentationControl(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetDisablePresentationControl(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IScreenRect getRect() throws ApiException
    {
        checkDisposed();
        IScreenRect result = IScreenRect.fromHandle(NativeGetRect(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IWorldPointInfo CenterPixelToWorld(int TypeFilterFlags) throws ApiException
    {
        checkDisposed();
        IWorldPointInfo result = IWorldPointInfo.fromHandle(NativeCenterPixelToWorld(this.getHandle(),TypeFilterFlags));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IWorldPointInfo CenterPixelToWorld() throws ApiException
    {
        return CenterPixelToWorld(WorldPointType.WPT_DEFAULT);
    }

    public int GetControls() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetControls(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int GetInputMode() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetInputMode(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IMouseInfo GetMouseInfo() throws ApiException
    {
        checkDisposed();
        IMouseInfo result = IMouseInfo.fromHandle(NativeGetMouseInfo(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPopupMessage GetPopupByCaption(String PopupCaption) throws ApiException
    {
        checkDisposed();
        IPopupMessage result = IPopupMessage.fromHandle(NativeGetPopupByCaption(this.getHandle(),PopupCaption));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String GetSnapShot(boolean toFile,int Width,int Height,String Format,int TerrainQuality) throws ApiException
    {
        checkDisposed();
        String result = (NativeGetSnapShot(this.getHandle(),toFile,Width,Height,Format,TerrainQuality));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String GetSnapShot() throws ApiException
    {
        return GetSnapShot(false,0,0,"",0);
    }

    public String GetSnapShot(boolean toFile) throws ApiException
    {
        return GetSnapShot(toFile,0,0,"",0);
    }

    public String GetSnapShot(boolean toFile,int Width) throws ApiException
    {
        return GetSnapShot(toFile,Width,0,"",0);
    }

    public String GetSnapShot(boolean toFile,int Width,int Height) throws ApiException
    {
        return GetSnapShot(toFile,Width,Height,"",0);
    }

    public String GetSnapShot(boolean toFile,int Width,int Height,String Format) throws ApiException
    {
        return GetSnapShot(toFile,Width,Height,Format,0);
    }

    public void HideMessageBarText() throws ApiException
    {
        checkDisposed();
        NativeHideMessageBarText(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IScreenPointInfo PixelFromWorld(IPosition Position,int Mode) throws ApiException
    {
        checkDisposed();
        IScreenPointInfo result = IScreenPointInfo.fromHandle(NativePixelFromWorld(this.getHandle(),Position,Mode));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IScreenPointInfo PixelFromWorld(IPosition Position) throws ApiException
    {
        return PixelFromWorld(Position,PixelFromWorldMode.PFW_IGNORE_Z_BUFFER);
    }

    public IWorldPointInfo PixelToWorld(int PixelX,int PixelY,int TypeFilterFlags) throws ApiException
    {
        checkDisposed();
        IWorldPointInfo result = IWorldPointInfo.fromHandle(NativePixelToWorld(this.getHandle(),PixelX,PixelY,TypeFilterFlags));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IWorldPointInfo PixelToWorld(int PixelX,int PixelY) throws ApiException
    {
        return PixelToWorld(PixelX,PixelY,WorldPointType.WPT_DEFAULT);
    }

    public void RemovePopup(IPopupMessage popup) throws ApiException
    {
        checkDisposed();
        NativeRemovePopup(this.getHandle(),popup);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void RemovePopupByCaption(String PopupCaption) throws ApiException
    {
        checkDisposed();
        NativeRemovePopupByCaption(this.getHandle(),PopupCaption);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetHUDLayer(Object pIStream,int Index,int MapMode) throws ApiException
    {
        checkDisposed();
        NativeSetHUDLayer(this.getHandle(),pIStream,Index,MapMode);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetHUDLayer(Object pIStream) throws ApiException
    {
        SetHUDLayer(pIStream,0,0);
    }

    public void SetHUDLayer(Object pIStream,int Index) throws ApiException
    {
        SetHUDLayer(pIStream,Index,0);
    }

    public void SetInputMode(int InputMode,String cursorURL,boolean AllowDrag) throws ApiException
    {
        checkDisposed();
        NativeSetInputMode(this.getHandle(),InputMode,cursorURL,AllowDrag);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetInputMode(int InputMode) throws ApiException
    {
        SetInputMode(InputMode,"",false);
    }

    public void SetInputMode(int InputMode,String cursorURL) throws ApiException
    {
        SetInputMode(InputMode,cursorURL,false);
    }

    public void ShowControls(int controlFlags) throws ApiException
    {
        checkDisposed();
        NativeShowControls(this.getHandle(),controlFlags);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ShowMessageBarText(String Text,int Alignment,int Timeout) throws ApiException
    {
        checkDisposed();
        NativeShowMessageBarText(this.getHandle(),Text,Alignment,Timeout);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ShowMessageBarText(String Text) throws ApiException
    {
        ShowMessageBarText(Text,MessageBarTextAlignment.MBT_CENTER,5000);
    }

    public void ShowMessageBarText(String Text,int Alignment) throws ApiException
    {
        ShowMessageBarText(Text,Alignment,5000);
    }

    public void ShowPopup(IPopupMessage popup) throws ApiException
    {
        checkDisposed();
        NativeShowPopup(this.getHandle(),popup);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native boolean NativeGetDisablePresentationControl(int instance);

private static native void NativeSetDisablePresentationControl(int instance,boolean pVal);

private static native int NativeGetRect(int instance);

private static native int NativeCenterPixelToWorld(int instance,int TypeFilterFlags);

private static native int NativeGetControls(int instance);

private static native int NativeGetInputMode(int instance);

private static native int NativeGetMouseInfo(int instance);

private static native int NativeGetPopupByCaption(int instance,String PopupCaption);

private static native String NativeGetSnapShot(int instance,boolean toFile,int Width,int Height,String Format,int TerrainQuality);

private static native void NativeHideMessageBarText(int instance);

private static native int NativePixelFromWorld(int instance,IPosition Position,int Mode);

private static native int NativePixelToWorld(int instance,int PixelX,int PixelY,int TypeFilterFlags);

private static native void NativeRemovePopup(int instance,IPopupMessage popup);

private static native void NativeRemovePopupByCaption(int instance,String PopupCaption);

private static native void NativeSetHUDLayer(int instance,Object pIStream,int Index,int MapMode);

private static native void NativeSetInputMode(int instance,int InputMode,String cursorURL,boolean AllowDrag);

private static native void NativeShowControls(int instance,int controlFlags);

private static native void NativeShowMessageBarText(int instance,String Text,int Alignment,int Timeout);

private static native void NativeShowPopup(int instance,IPopupMessage popup);
};
