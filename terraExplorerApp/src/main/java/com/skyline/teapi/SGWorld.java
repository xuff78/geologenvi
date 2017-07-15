package com.skyline.teapi;

public final class SGWorld extends TEIUnknownHandle {
    private SGWorld(int handle)
    {
        super(handle);
    }    
    public static SGWorld fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new SGWorld(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("01F65968-A33F-4806-8300-A3DDB6B03A45");

};
