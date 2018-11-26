package com.guomai.dushuhui.util.common;

import android.content.ContentResolver;
import android.content.res.Resources;

public class ImageLoaderUtil {
	public static final String URI_BASE_HTTP = "http://";
	public static final String URI_BASE_FILE = "file://";
	public static final String URI_BASE_ASSETS = "assets://";
	public static final String URI_BASE_DRAWABLE = "drawable://";
	
	public static String getLocalUri(String path) {
		if (path == null) {
			return null;
		}
		return URI_BASE_FILE + path;
	}
	public static String getDrawableUri(Resources r, int resId) {
		if (resId == 0) {
			return null;
		}
		return ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
			    + r.getResourcePackageName(resId) + "/"
			    + r.getResourceTypeName(resId) + "/"
			    + r.getResourceEntryName(resId);
	}
	
	public static String getPathByLocalUri(String uri) {
		if (uri == null) {
			return null;
		}
		return uri.replace(URI_BASE_FILE, "");
	}
	
	
	
	public static boolean canLoadSync(String uri) {
		if (uri.startsWith(URI_BASE_ASSETS) || uri.startsWith(URI_BASE_DRAWABLE)) {
			return true;
		}
		return false;
	}
}
