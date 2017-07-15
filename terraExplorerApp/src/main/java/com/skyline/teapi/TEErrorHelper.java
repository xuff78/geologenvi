
package com.skyline.teapi;

import android.os.Looper;
import android.util.Log;

public final class TEErrorHelper {
     private static native int GetErrorCode();
     private static native String GetErrorMessage();
     private static native String GetErrorFunction();
     public static void ThrowExceptionOnError()
     {
         if(Looper.myLooper() == Looper.getMainLooper()) // main thread, warning
         {
            throw new ApiException(GetErrorFunction() + " can't be called on UI thread.");
         }
         int code = GetErrorCode();
         if (code != 0)
         {        	 
             String error = GetErrorMessage();
             Log.e("TEErrorHelper", error);
             if(error != null)            	 
                throw new ApiException(error);
             else
                throw new ApiException(String.format("%s failed: %d.", GetErrorFunction(),code));
         }
     }
}
