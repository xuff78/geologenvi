package com.sichuan.geologenvi.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Xml;



public class XmlParse {
	
	private static final String TAG = "XmlParse";
	
	public static String getPapers(Context context, String key_value, String parent_key) {
		try {
			InputStream inputStream = context.getAssets().open("plist.xml");
//			AssetManager mAssetManager = content.getAssets();
//			XmlResourceParser mXmlResourceParser = mAssetManager.openXmlResourceParser("plist.xml");
			// 使用pullParseXML来解析xml文件。
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(inputStream, "UTF-8");
			boolean tag = false;
			String dictName = "";
			String nameValue = "";
			// 直到文档的结尾处
			while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {
				// 如果遇到了开始标签
				if (parser.getEventType() == XmlResourceParser.START_TAG) {
					String tagName = parser.getName();// 获取标签的名字
					if ("dict".equals(tagName)) {
						dictName = parser.getAttributeValue(null, "name");
					}
					if ("string".equals(tagName)) {
						nameValue = parser.getAttributeValue(null, "name");
						tag = true;
					}
				}
				if (parser.getEventType() == XmlPullParser.TEXT) {
					if (tag) {
						if (key_value.equals(nameValue) && dictName.equals(parent_key)) {
							key_value = parser.getText();
							break;
						}
						tag = false;
					}
				}
				parser.next();// 获取解析下一个事件
			}
			inputStream.close();
		} catch (Exception e) {
			LogUtil.e(TAG, "数字字典转换失败", e);
		}
		return key_value;
	}
	

    public boolean writeToXml(Context context,String str){
        try {
            OutputStream out=context.openFileOutput("users.xml", Context.MODE_APPEND);
            OutputStreamWriter outw=new OutputStreamWriter(out);
            try {
                outw.write(str);
                outw.close();
                out.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        } catch (FileNotFoundException e) {
            return false;
        }
    }
    
    public String writeToString(String tag,String value){
        //实现xml信息序列号的一个对象
        XmlSerializer serializer=Xml.newSerializer();
        StringWriter writer=new StringWriter();
        try {
            //xml数据经过序列化后保存到String中，然后将字串通过OutputStream保存为xml文件
            serializer.setOutput(writer);
            
            //文档开始
            serializer.startDocument("utf-8", true);
            
            //开始一个节点
            serializer.startTag("", "dicts");
            
            //开始一个子节点
            serializer.startTag("", "dict");
            serializer.attribute("", "name", "cache");
            
            serializer.startTag("", "string");
            serializer.attribute("", "name", tag);
            serializer.text(value);
            serializer.endTag("", "string");
            
            serializer.endTag("", "dict");
            
            serializer.endTag("", "dicts");
            //关闭文档
            serializer.endDocument();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return writer.toString();
    }
    
    public static String getIntentLoginAction(Context context){
    	return getPapers(context, "intent_login", "constants");
    }
	
    public static String getIntentPayAction(Context context){
    	return getPapers(context, "intent_pay", "constants");
    }
    
    public static String getIntentHomeAction(Context context){
    	return getPapers(context, "intent_home", "constants");
    }
    
    public static String getDialogProgress(Context context){
    	return getPapers(context, "dialog_progress", "constants");
    }
    
}
