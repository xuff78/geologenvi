package com.sichuan.geologenvi.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {

	public static DisplayImageOptions getImageOption(int Resid){
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(Resid)
				.showImageForEmptyUri(Resid)
				.showImageOnFail(Resid)
				.cacheInMemory(true)
				.cacheOnDisk(true)
				.considerExifParams(true)
				.build();
		return options;
	}

	public static void initImageLoader(Context context) {

		if(context!=null){
			if(!ImageLoader.getInstance().isInited()){

				ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
						.threadPriority(Thread.NORM_PRIORITY - 2)
						.memoryCacheSize(2*1024*1024)
						.denyCacheImageMultipleSizesInMemory()
						.discCacheFileNameGenerator(new Md5FileNameGenerator())
						.tasksProcessingOrder(QueueProcessingType.LIFO)
						.build();
				// Initialize ImageLoader with configuration.
				ImageLoader.getInstance().init(config);
			}
		}
	}

	public static Bitmap createTransparentBitmapFromBitmap(Bitmap bitmap,
													int replaceThisColor) {
		if (bitmap != null) {
			int picw = bitmap.getWidth();
			int pich = bitmap.getHeight();
			int[] pix = new int[picw * pich];
			bitmap.getPixels(pix, 0, picw, 0, 0, picw, pich);

			int sr = (replaceThisColor >> 16) & 0xff;
			int sg = (replaceThisColor >> 8) & 0xff;
			int sb = replaceThisColor & 0xff;

			for (int y = 0; y < pich; y++) {
				for (int x = 0; x < picw; x++) {
					int index = y * picw + x;
                /*  int r = (pix[index] >> 16) & 0xff;
                  int g = (pix[index] >> 8) & 0xff;
                  int b = pix[index] & 0xff;*/

					if (pix[index] == replaceThisColor) {

//                  if(x<topLeftHole.x) topLeftHole.x = x;
//                  if(y<topLeftHole.y) topLeftHole.y = y;
//                  if(x>bottomRightHole.x) bottomRightHole.x = x;
//                  if(y>bottomRightHole.y)bottomRightHole.y = y;

						pix[index] = Color.TRANSPARENT;
					} else {
						//break;
					}
				}
			}

			Bitmap bm = Bitmap.createBitmap(pix, picw, pich,
					Bitmap.Config.ARGB_8888);
			bitmap.recycle();
			return bm;
		}
		return null;
	}

	public static Drawable resizeToXY(Bitmap bm, int x, int y) {
		int width = bm.getWidth();
		int height = bm.getHeight();

		float scaleWidth = width / x;
		float scaleHeight = height / y;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, x, y, matrix, true);
		return new BitmapDrawable(resizedBitmap);
	}
	
	/**
	 * 收缩图片
	 * 
	 * @param uri
	 */
	public static void startPhotoZoom(Uri uri, String taget, Activity act) {
		Intent intent = new Intent("com.android.camera.action.CROP");// 调用Android系统自带的一个图片剪裁页面,
		intent.setDataAndType(uri, taget);
		intent.putExtra("crop", "true");// 进行修剪
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 350);
		intent.putExtra("outputY", 350);
		intent.putExtra("return-data", true);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true);
		act.startActivityForResult(intent, 0x22);
	}
	
	public static Bitmap getSavedDrawable(String path, Context con) {
		File file = new File(path);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		return BitmapFactory.decodeFile(file.toString(), options);
	}

	// 获得圆角图片
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

		Bitmap output = Bitmap
				.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	// 倒影
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2,
				matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2),
				Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff,
				TileMode.CLAMP);
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);

		return bitmapWithReflection;
	}

	// 算DIP
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
	
	// 算DIP
	public static int dip2px(Context context, int dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static void releaseImage(ImageView img) {
		if (img != null) {
			Bitmap bm = ((BitmapDrawable) img.getDrawable()).getBitmap();
			if (bm != null)
				bm.recycle();
			img.setImageDrawable(null);
		}
	}
	
	public static Bitmap toRoundCorner(Bitmap bitmap, float pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        //保证是方形，并且从中心画
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int w;
        int deltaX = 0;
        int deltaY = 0;
        if (width <= height) {
            w = width;
            deltaY = height - w;
        } else {
            w = height;
            deltaX = width - w;
        }
        final Rect rect = new Rect(deltaX, deltaY, w, w);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        //圆形，所有只用一个
        
        int radius = (int) (Math.sqrt(w * w * 2.0d) / 2);
        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
  
       
    }
	
	public static byte[] readStream ( InputStream inStream ) throws Exception
	{
		byte[] buffer = new byte[1024];
		int len = -1;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		while ((len = inStream.read(buffer)) != -1)
		{
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		return data;

	}
	
	public static Bitmap getPicFromBytes (byte[] bytes , BitmapFactory.Options opts )
	{
		if (bytes != null)
			if (opts != null)
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
			else
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return null;
	}
	
	/* 旋转图片 
    * @param angle 
    * @param bitmap 
    * @return Bitmap 
    */  
   public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
       //旋转图片 动作   
       Matrix matrix = new Matrix();;
       matrix.postRotate(angle);  
       System.out.println("angle2=" + angle);
       // 创建新的图片   
       Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
               bitmap.getWidth(), bitmap.getHeight(), matrix, true);  
       return resizedBitmap;  
   }
   
   public static String getSavePath(Context context) {
		String path=SharedPreferencesUtil.getString(context, "picSavePath");
		return path;
	}
   
   /** 
	 * 读取图片属性：旋转的角度 
	 * @param path 图片绝对路径 
	 * @return degree旋转的角度 
	 */  
   public static int readPictureDegree(String path) {
       int degree  = 0;  
       try {  
               ExifInterface exifInterface = new ExifInterface(path);
               int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
               switch (orientation) {  
               case ExifInterface.ORIENTATION_ROTATE_90:
                       degree = 90;  
                       break;  
               case ExifInterface.ORIENTATION_ROTATE_180:
                       degree = 180;  
                       break;  
               case ExifInterface.ORIENTATION_ROTATE_270:
                       degree = 270;  
                       break;  
               }  
       } catch (IOException e) {
               e.printStackTrace();  
       }  
       return degree;  
   }

	public static Drawable getRoundedCornerShape(int fillColor, Context con) {
		int strokeWidth = 2; // 3dp 边框宽度
		int roundRadius = ImageUtil.dip2px(con, 5); // 8dp 圆角半径
		int strokeColor = Color.WHITE;//边框颜色

		GradientDrawable gd = new GradientDrawable();//创建drawable
		gd.setColor(fillColor);
		gd.setCornerRadius(roundRadius);
		gd.setStroke(strokeWidth, strokeColor);
		return gd;
	}

	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	public static Bitmap getSmallBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 480, 800);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(filePath, options);
	}
}
