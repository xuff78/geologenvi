package com.sichuan.geologenvi.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

public class FileUtil {

	public final static String savePath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/THkjq/";

	// 存储Bitmap
	public static boolean saveBitmap(Bitmap bmp, String id, Context context) {
		boolean isfinish = true;
		File file = getFile(id + ".png", context);
		if (!file.exists() && !file.isDirectory()) {
			try {
				FileOutputStream out = new FileOutputStream(file);
				isfinish = bmp.compress(Bitmap.CompressFormat.PNG, 20, out);
			} catch (Exception e) {
				isfinish = false;
				e.printStackTrace();
			}
		}
		return isfinish;
	}

	public static File getFile(String fileName, Context con) {
		File file;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			isExist(savePath);
			file = new File(savePath, fileName);
		} else
			file = new File(con.getCacheDir(), fileName);
		return file;
	}

	public static void isExist(String path) {
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();
	}

	public static boolean copyFile(File sfile, File tid) {
		LogUtil.d("test", "download finish!!!");
		if (!sfile.exists())
			return false;
		if (tid.exists())
			tid.delete();

		BufferedInputStream reader = null;
		BufferedOutputStream writer = null;
		try {
			reader = new BufferedInputStream(new FileInputStream(sfile));
			writer = new BufferedOutputStream(new FileOutputStream(tid, false));
			byte[] buff = new byte[8192];
			int numChars;
			while ((numChars = reader.read(buff, 0, buff.length)) != -1) {
				writer.write(buff, 0, numChars);
			}
		} catch (IOException ex) {
			return false;
		} finally {
			try {
				if (reader != null) {
					writer.close();
					reader.close();
				}
			} catch (IOException ex) {
			}
		}
		return true;
	}

	// 解压缩包
	public static int openZip(Context context, String dataBasePath, String zipName) {
		int flag = 0;
		try {
			AssetManager am = context.getAssets();
			ZipInputStream zis = new ZipInputStream(am.open(zipName));
			FileOutputStream fos = new FileOutputStream(dataBasePath);
			zis.getNextEntry();// 读取下一个 ZIP 文件条目并将流定位到该条目数据的开始处。
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}

			fos.flush();
			fos.close();
			zis.close();
			flag = 1;
		} catch (Exception e) {
			flag = -1;
			e.printStackTrace();
		}
		return flag;
	}

	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	private static String[] PERMISSIONS_STORAGE = {
			Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE
	};

	/**
	 * Checks if the app has permission to write to device storage
	 *
	 * If the app does not has permission then the user will be prompted to grant permissions
	 *
	 * @param activity
	 */
	public static void verifyStoragePermissions(Activity activity) {
		// Check if we have write permission
		int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

		if (permission != PackageManager.PERMISSION_GRANTED) {
			// We don't have permission so prompt the user
			ActivityCompat.requestPermissions(
					activity,
					PERMISSIONS_STORAGE,
					REQUEST_EXTERNAL_STORAGE
			);
		}
	}

}
