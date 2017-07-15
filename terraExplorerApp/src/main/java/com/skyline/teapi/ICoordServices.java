package com.skyline.teapi;

public final class ICoordServices extends TEIUnknownHandle {
    private ICoordServices(int handle)
    {
        super(handle);
    }    
    public static ICoordServices fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ICoordServices(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("45B6FB93-499D-48A2-BF62-4A4C6895AFA7");

    public ICoordinateSystem getSourceCoordinateSystem() throws ApiException
    {
        checkDisposed();
        ICoordinateSystem result = ICoordinateSystem.fromHandle(NativeGetSourceCoordinateSystem(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setSourceCoordinateSystem(ICoordinateSystem value) throws ApiException
    {
        checkDisposed();
        NativeSetSourceCoordinateSystem(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String ChooseCSDialog(String Title,String WellKnownText) throws ApiException
    {
        checkDisposed();
        String result = (NativeChooseCSDialog(this.getHandle(),Title,WellKnownText));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String ConvertCoordinateToMGRS(double X,double Y) throws ApiException
    {
        checkDisposed();
        String result = (NativeConvertCoordinateToMGRS(this.getHandle(),X,Y));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ICoord2D ConvertMGRSToCoordinate(String bstrMGRS) throws ApiException
    {
        checkDisposed();
        ICoord2D result = ICoord2D.fromHandle(NativeConvertMGRSToCoordinate(this.getHandle(),bstrMGRS));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ICoordinateSystem CreateCoordinateSystem(String WellKnownText) throws ApiException
    {
        checkDisposed();
        ICoordinateSystem result = ICoordinateSystem.fromHandle(NativeCreateCoordinateSystem(this.getHandle(),WellKnownText));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPosition GetAimingAngles(IPosition From,IPosition To) throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeGetAimingAngles(this.getHandle(),From,To));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double GetDistance(double X1,double Y1,double X2,double Y2) throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDistance(this.getHandle(),X1,Y1,X2,Y2));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double GetDistance3D(IPosition From,IPosition To) throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDistance3D(this.getHandle(),From,To));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ICoord2D MoveCoord(double X,double Y,double moveWestEast,double moveSouthNorth) throws ApiException
    {
        checkDisposed();
        ICoord2D result = ICoord2D.fromHandle(NativeMoveCoord(this.getHandle(),X,Y,moveWestEast,moveSouthNorth));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ICoord2D Reproject(ICoordinateSystem From,ICoordinateSystem To,double X,double Y) throws ApiException
    {
        checkDisposed();
        ICoord2D result = ICoord2D.fromHandle(NativeReproject(this.getHandle(),From,To,X,Y));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeGetSourceCoordinateSystem(int instance);

private static native void NativeSetSourceCoordinateSystem(int instance,ICoordinateSystem pVal);

private static native String NativeChooseCSDialog(int instance,String Title,String WellKnownText);

private static native String NativeConvertCoordinateToMGRS(int instance,double X,double Y);

private static native int NativeConvertMGRSToCoordinate(int instance,String bstrMGRS);

private static native int NativeCreateCoordinateSystem(int instance,String WellKnownText);

private static native int NativeGetAimingAngles(int instance,IPosition From,IPosition To);

private static native double NativeGetDistance(int instance,double X1,double Y1,double X2,double Y2);

private static native double NativeGetDistance3D(int instance,IPosition From,IPosition To);

private static native int NativeMoveCoord(int instance,double X,double Y,double moveWestEast,double moveSouthNorth);

private static native int NativeReproject(int instance,ICoordinateSystem From,ICoordinateSystem To,double X,double Y);
};
