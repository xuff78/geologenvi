
package com.skyline.teapi;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.UUID;

public class TEIUnknownHandle {
	
	public TEIUnknownHandle(int handle)
	{
		_handle = handle;
	}
	private int _handle;
    protected int getHandle() { return _handle; }
	protected void finalize()
	{
        if(_handle != 0)
		    IUnknownRelease(_handle);
        _handle = 0;
	}
    protected void checkDisposed() throws ApiException
    {
        if(_handle == 0)
            throw new ApiException("Can not access disposed object.");
    }

    public static TEIUnknownHandle fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new TEIUnknownHandle(handle);
    }

	public void dispose()
	{
		finalize();
	}

	public <T> T CastTo(Class<T> clazz)
	{
        checkDisposed();
		try {
			Method m = clazz.getDeclaredMethod("fromHandle",int.class);
			Field f = clazz.getDeclaredField("IID");
			f.setAccessible(true);
			UUID IID = (UUID)f.get(null);
			byte buff[] = new byte[16]; 
			long msb = IID.getMostSignificantBits();
			long lsb = IID.getLeastSignificantBits();
			byte a[] = {6,7,4,5,0,1,2,3};
			byte b[] = {7,6,5,4,3,2,1,0};
			for(int i = 0;i<8;i++)
			{
				buff[a[i]] = (byte)(msb & 0xff);
				msb >>= 8;
				buff[b[i]+8] = (byte)(lsb & 0xff);
				lsb >>= 8;
			}
			int newHandle = IUnknownQueryInterface(_handle,buff);
            @SuppressWarnings("unchecked")
			T newInstance = (T)m.invoke(null, newHandle);
			return newInstance;
		} catch (Exception e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
		}		
		return null;
	}

	private static native void IUnknownRelease(int handle);
	private static native void IUnknownAddRef(int handle);
    private static native int IUnknownQueryInterface(int handle, byte[] iid);
}
