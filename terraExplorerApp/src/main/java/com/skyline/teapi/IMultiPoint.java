package com.skyline.teapi;

public final class IMultiPoint extends TEIUnknownHandle {
    private IMultiPoint(int handle)
    {
        super(handle);
    }    
    public static IMultiPoint fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IMultiPoint(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("84CE9DFF-65AD-11D5-85C1-0001023952C1");

    public int getDimension() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetDimension(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry getEnvelope() throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeGetEnvelope(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getGeometryType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetGeometryType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getGeometryTypeStr() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetGeometryTypeStr(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IWks getWks() throws ApiException
    {
        checkDisposed();
        IWks result = IWks.fromHandle(NativeGetWks(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ISpatialOperator getSpatialOperator() throws ApiException
    {
        checkDisposed();
        ISpatialOperator result = ISpatialOperator.fromHandle(NativeGetSpatialOperator(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ISpatialRelation getSpatialRelation() throws ApiException
    {
        checkDisposed();
        ISpatialRelation result = ISpatialRelation.fromHandle(NativeGetSpatialRelation(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object get_Item(Object Index) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetItem(this.getHandle(),Index));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getCount() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCount(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getNumGeometries() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetNumGeometries(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry get_Geometry(int Index) throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeGetGeometry(this.getHandle(),Index));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry AddGeometry(Object pVal) throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeAddGeometry(this.getHandle(),pVal));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry Clone() throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeClone(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void DeleteGeometry(int Index) throws ApiException
    {
        checkDisposed();
        NativeDeleteGeometry(this.getHandle(),Index);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IGeometry EndEdit() throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeEndEdit(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsEmpty() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsEmpty(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsSimple() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsSimple(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void SetEmpty() throws ApiException
    {
        checkDisposed();
        NativeSetEmpty(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void StartEdit() throws ApiException
    {
        checkDisposed();
        NativeStartEdit(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native int NativeGetDimension(int instance);

private static native int NativeGetEnvelope(int instance);

private static native int NativeGetGeometryType(int instance);

private static native String NativeGetGeometryTypeStr(int instance);

private static native int NativeGetWks(int instance);

private static native int NativeGetSpatialOperator(int instance);

private static native int NativeGetSpatialRelation(int instance);

private static native Object NativeGetItem(int instance,Object Index);

private static native int NativeGetCount(int instance);

private static native int NativeGetNumGeometries(int instance);

private static native int NativeGetGeometry(int instance,int Index);

private static native int NativeAddGeometry(int instance,Object pVal);

private static native int NativeClone(int instance);

private static native void NativeDeleteGeometry(int instance,int Index);

private static native int NativeEndEdit(int instance);

private static native boolean NativeIsEmpty(int instance);

private static native boolean NativeIsSimple(int instance);

private static native void NativeSetEmpty(int instance);

private static native void NativeStartEdit(int instance);
};
