package com.guomai.dushuhui.util;

public class ImageUriWrapUtil {
	public static String wrapAsset(String path)
	{
		return "assets://"+path;
	}
	public static String wrapFile(String path)
	{
		return "file://"+path;
	}
	public static String wrapDrawable(String path)
	{
		return "drawable://"+path;
	}
	public static String wrapContent(String path)
	{
		return "content://"+path;
	}
}
