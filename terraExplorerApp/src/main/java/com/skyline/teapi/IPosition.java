package com.skyline.teapi;

public final class IPosition extends TEIUnknownHandle {
    private IPosition(int handle)
    {
        super(handle);
    }    
    public static IPosition fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IPosition(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("43784CA0-4578-4CA5-BA4C-CFD20C602AC5");

    public double getX() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setX(double value) throws ApiException
    {
        checkDisposed();
        NativeSetX(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getY() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setY(double value) throws ApiException
    {
        checkDisposed();
        NativeSetY(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getAltitude() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetAltitude(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAltitude(double value) throws ApiException
    {
        checkDisposed();
        NativeSetAltitude(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getYaw() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetYaw(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setYaw(double value) throws ApiException
    {
        checkDisposed();
        NativeSetYaw(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getPitch() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetPitch(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPitch(double value) throws ApiException
    {
        checkDisposed();
        NativeSetPitch(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getRoll() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetRoll(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setRoll(double value) throws ApiException
    {
        checkDisposed();
        NativeSetRoll(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getAltitudeType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetAltitudeType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAltitudeType(int value) throws ApiException
    {
        checkDisposed();
        NativeSetAltitudeType(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDistance() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDistance(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDistance(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDistance(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getCartesian() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetCartesian(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCartesian(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetCartesian(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IPosition AimTo(IPosition Position) throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeAimTo(this.getHandle(),Position));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void ChangeAltitudeType(int AltitudeType) throws ApiException
    {
        checkDisposed();
        NativeChangeAltitudeType(this.getHandle(),AltitudeType);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IPosition Copy() throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeCopy(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double DistanceTo(IPosition Position) throws ApiException
    {
        checkDisposed();
        double result = (NativeDistanceTo(this.getHandle(),Position));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void Init(double X,double Y,double Altitude,double Yaw,double Pitch,double Roll,int AltitudeType,double Distance) throws ApiException
    {
        checkDisposed();
        NativeInit(this.getHandle(),X,Y,Altitude,Yaw,Pitch,Roll,AltitudeType,Distance);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void Init() throws ApiException
    {
        Init(0,0,0,0,0,0,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,0);
    }

    public void Init(double X) throws ApiException
    {
        Init(X,0,0,0,0,0,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,0);
    }

    public void Init(double X,double Y) throws ApiException
    {
        Init(X,Y,0,0,0,0,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,0);
    }

    public void Init(double X,double Y,double Altitude) throws ApiException
    {
        Init(X,Y,Altitude,0,0,0,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,0);
    }

    public void Init(double X,double Y,double Altitude,double Yaw) throws ApiException
    {
        Init(X,Y,Altitude,Yaw,0,0,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,0);
    }

    public void Init(double X,double Y,double Altitude,double Yaw,double Pitch) throws ApiException
    {
        Init(X,Y,Altitude,Yaw,Pitch,0,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,0);
    }

    public void Init(double X,double Y,double Altitude,double Yaw,double Pitch,double Roll) throws ApiException
    {
        Init(X,Y,Altitude,Yaw,Pitch,Roll,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,0);
    }

    public void Init(double X,double Y,double Altitude,double Yaw,double Pitch,double Roll,int AltitudeType) throws ApiException
    {
        Init(X,Y,Altitude,Yaw,Pitch,Roll,AltitudeType,0);
    }

    public void InitFrom(Object var) throws ApiException
    {
        checkDisposed();
        NativeInitFrom(this.getHandle(),var);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean IsEqual(IPosition Position) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsEqual(this.getHandle(),Position));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPosition Lerp(IPosition Position,double Percentage) throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeLerp(this.getHandle(),Position,Percentage));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPosition Move(double Distance,double Yaw,double Pitch) throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeMove(this.getHandle(),Distance,Yaw,Pitch));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPosition MoveToward(IPosition Position,double Distance) throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeMoveToward(this.getHandle(),Position,Distance));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPosition ToAbsolute(int Accuracy) throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeToAbsolute(this.getHandle(),Accuracy));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPosition ToAbsolute() throws ApiException
    {
        return ToAbsolute(AccuracyLevel.ACCURACY_NORMAL);
    }

    public IPosition ToRelative(int Accuracy) throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeToRelative(this.getHandle(),Accuracy));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPosition ToRelative() throws ApiException
    {
        return ToRelative(AccuracyLevel.ACCURACY_NORMAL);
    }

private static native double NativeGetX(int instance);

private static native void NativeSetX(int instance,double pVal);

private static native double NativeGetY(int instance);

private static native void NativeSetY(int instance,double pVal);

private static native double NativeGetAltitude(int instance);

private static native void NativeSetAltitude(int instance,double pVal);

private static native double NativeGetYaw(int instance);

private static native void NativeSetYaw(int instance,double pVal);

private static native double NativeGetPitch(int instance);

private static native void NativeSetPitch(int instance,double pVal);

private static native double NativeGetRoll(int instance);

private static native void NativeSetRoll(int instance,double pVal);

private static native int NativeGetAltitudeType(int instance);

private static native void NativeSetAltitudeType(int instance,int pVal);

private static native double NativeGetDistance(int instance);

private static native void NativeSetDistance(int instance,double pVal);

private static native boolean NativeGetCartesian(int instance);

private static native void NativeSetCartesian(int instance,boolean pVal);

private static native int NativeAimTo(int instance,IPosition Position);

private static native void NativeChangeAltitudeType(int instance,int AltitudeType);

private static native int NativeCopy(int instance);

private static native double NativeDistanceTo(int instance,IPosition Position);

private static native void NativeInit(int instance,double X,double Y,double Altitude,double Yaw,double Pitch,double Roll,int AltitudeType,double Distance);

private static native void NativeInitFrom(int instance,Object var);

private static native boolean NativeIsEqual(int instance,IPosition Position);

private static native int NativeLerp(int instance,IPosition Position,double Percentage);

private static native int NativeMove(int instance,double Distance,double Yaw,double Pitch);

private static native int NativeMoveToward(int instance,IPosition Position,double Distance);

private static native int NativeToAbsolute(int instance,int Accuracy);

private static native int NativeToRelative(int instance,int Accuracy);
};
