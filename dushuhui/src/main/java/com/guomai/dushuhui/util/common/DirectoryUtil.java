package com.guomai.dushuhui.util.common;

import android.os.Environment;


import com.guomai.dushuhui.MyApplication;

import java.io.File;

public class DirectoryUtil
{
	public static final String sdCardDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
	public static final String rootDirectory = sdCardDirectory + "/qingqinggou/";
	public static final String crashDirectory = sdCardDirectory + "/qingqinggou/crash/";
	public static final String logDirectory = sdCardDirectory + "/qingqinggou/log/";
//	public static final String championDirectory = sdCardDirectory + "/qingqinggou/hero/champion/";
	public static final String accountDirectory = sdCardDirectory + "/qingqinggou/account/";
	public static final String cacheDirectory = sdCardDirectory + "/qingqinggou/cache/";
	public static final String giftFolder = MyApplication.getInst().getFilesDir() + "/qingqinggou/cache/customgift/";
	public static final String imagecutCacheDirectory = sdCardDirectory + "/qingqinggou/cache/imagecut/";
	public static final String imageDirectory = sdCardDirectory + "/qingqinggou/image/";
	public static final String imagePreviewDirectory = sdCardDirectory + "/qingqinggou/prviewimage/";
	public static final String smallImageDirectory = sdCardDirectory + "/qingqinggou/smallimage/";
	public static final String requestDirectory = sdCardDirectory + "/qingqinggou/request/";
	public static final String searchDirectory = sdCardDirectory + "/qingqinggou/search/";
//	public static final String skinDirectory = sdCardDirectory + "/qingqinggou/skin/";
	public static final String userDirectory = sdCardDirectory + "/qingqinggou/user/";
	public static final String userImageDirectory = sdCardDirectory + "/DCIM/qingqinggou/";
	public static final String dbDir = MyApplication.getInst().getFilesDir() + "/db/qingqinggou/";
	public static final String catchDir = MyApplication.getInst().getFilesDir() + "/catch/";
	
	
	public static void mkAllDirs()
	{
		File dir = new File(rootDirectory);
    	dir.mkdirs();
    	
    	dir = new File(crashDirectory);
    	dir.mkdirs();
    	
    	dir = new File(logDirectory);
    	dir.mkdirs();
    	
    	dir = new File(userImageDirectory);
    	dir.mkdirs();
    	
    	dir = new File(accountDirectory);
    	dir.mkdirs();
    	
    	dir = new File(cacheDirectory);
    	dir.mkdirs();
    	
    	dir = new File(giftFolder);
    	dir.mkdirs();
    	
    	dir = new File(imageDirectory);
    	dir.mkdirs();
    	
    	
    	dir = new File(imagePreviewDirectory);
    	dir.mkdirs();
    	
    	dir = new File(requestDirectory);
    	dir.mkdirs();
    	
    	dir = new File(searchDirectory);
    	dir.mkdirs();
    	
//    	dir = new File(skinDirectory);
//    	dir.mkdirs();
    	
    	dir = new File(catchDir);
    	dir.mkdirs();
    	
    	dir = new File(userDirectory);
    	dir.mkdirs();
    	
    	dir = new File(imagecutCacheDirectory);
    	dir.mkdirs();
	}
	
	public static void delete(File file)
	{
		if(file.isFile())
		{
			file.delete();
		}else
		{
			File[] childFiles = file.listFiles();
			for(File child:childFiles)
			{
				delete(child);
			}
		}
	}
	
}
