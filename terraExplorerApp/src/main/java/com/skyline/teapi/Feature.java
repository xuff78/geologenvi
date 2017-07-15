package com.skyline.teapi;

public final class Feature extends TEIUnknownHandle {
    private Feature(int handle)
    {
        super(handle);
    }    
    public static Feature fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new Feature(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("AB641B7C-4415-4634-B2FE-E38B55A83CEB");

};
