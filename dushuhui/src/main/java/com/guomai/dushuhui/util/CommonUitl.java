package com.guomai.dushuhui.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.Reader;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({ "SimpleDateFormat", "DefaultLocale" })
public class CommonUitl {

	/**
	 * 判断是否为有效密码
	 * 
	 * @param paramString
	 *            字符串
	 * @return
	 */
	public static boolean isValidPasword(String paramString) {
		// String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		String regex = "[a-zA-Z0-9]{6,16}";
		if (paramString.matches(regex)) {
			return true;
		}
		return false;
	}

	public static int getAndroidSDKVersion() {
		int version = 0;
		try {
			version = VERSION.SDK_INT;
		} catch (NumberFormatException e) {
		}
		return version;
	}
	/**
	 * 读取本地文件中字符串
	 * @param fileName
	 * @return
	 */
	public static String getAssetsString(Context context, String fileName) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					context.getAssets().open(fileName), "UTF-8") );
			String line;
			while ((line = bf.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	public static String getVersion(Context context)//获取版本号
    {  
        try {  
            PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;  
        } catch (NameNotFoundException e) {
            e.printStackTrace();  
            return "未知版本";  
        }  
    }  
	
	
	public static int getVersionCode(Context context)//获取版本号(内部识别号)
	{  
	    try {  
	        PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
	        return pi.versionCode;  
	    } catch (NameNotFoundException e) {
	        e.printStackTrace();  
	        return 0;  
	    }  
	}  

	
	/**
	 * 检测网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetWorkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}

		return false;
	}

	/**
	 * long类型时间格式化
	 */
	public static String convertToTime(Date time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date date = new Date(time);
		return df.format(time);
	}

	/**
	 * long类型时间格式化
	 */
	public static String convertToTime(long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time);
		return df.format(date);
	}
	/**
	 * long类型时间格式化
	 */
	public static String convertToHourTime(long time) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date date = new Date(time);
		return df.format(date);
	}
	/**
	 * long类型时间格式化
	 */
	public static String convertToMonthDay(long time) {
		SimpleDateFormat df = new SimpleDateFormat("MM-dd");
		Date date = new Date(time);
		return df.format(date);
	}

	/**
	 * long类型时间格式化
	 */
	public static String convertToDate(long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(time);
		return df.format(date);
	}
	/**
	 * long类型时间格式化
	 */
	public static String convertToDate(long time, String patten) {
		SimpleDateFormat df = new SimpleDateFormat(patten);
		Date date = new Date(time);
		return df.format(date);
	}

	/**
	 * 检测Sdcard是否存在
	 * 
	 * @return
	 */
	public static boolean isExitsSdcard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}

	public static List<String> stringArr2List(String[] arr) {
		if (arr == null)
			return null;
		List<String> list = new ArrayList<String>();
		for (String a : arr) {
			list.add(a);
		}
		return list;
	}

	static String getString(Context context, int resId) {
		return context.getResources().getString(resId);
	}

	public static String getTopActivity(Context context) {
		ActivityManager manager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

		if (runningTaskInfos != null)
			return runningTaskInfos.get(0).topActivity.getClassName();
		else
			return "";
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
	 */

	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 判断是否为电话号码
	 * 
	 * @param paramString
	 *            字符串
	 * @return
	 */
	public static boolean isValidMobiNumber(String paramString) {
		// String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		String regex = "^(1)\\d{10}$";
		if (paramString.matches(regex)) {
			return true;
		}
		return false;
	}

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toLowerCase();

	}

	public static Date string2Date(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date string2DateTime(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static long string2DateMillion(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static long string2DateTimeMillion(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return formatter.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressLint("SimpleDateFormat")
	public static int getAge(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(dateStr);
			return getAge(date);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getAge(Date birthDay) {
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("出生时间大于当前时间!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;// 注意此处，如果不加1的话计算结果是错误的
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				} else {
					// do nothing
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		} else {
			// monthNow<monthBirth
			// donothing
		}

		return age;
	}

	public static String getConstellation(String birthday)
	{
		if(birthday!=null&&birthday.split("-").length==3)
		{
			String[] arr = birthday.split("-");
		    return CommonUitl.getConstellation(Integer.valueOf(arr[1]), Integer.valueOf(arr[2]));
		}else
		{
			return "未知星座";
		}
	}
	
	public static String getConstellation(int month, int day) {
		  String star = "";
		  if (month == 1 && day >= 20 || month == 2 && day <= 18) {
		   star = "水瓶座";
		  }
		  if (month == 2 && day >= 19 || month == 3 && day <= 20) {
		   star = "双鱼座";
		  }
		  if (month == 3 && day >= 21 || month == 4 && day <= 19) {
		   star = "白羊座";
		  }
		  if (month == 4 && day >= 20 || month == 5 && day <= 20) {
		   star = "金牛座";
		  }
		  if (month == 5 && day >= 21 || month == 6 && day <= 21) {
		   star = "双子座";
		  }
		  if (month == 6 && day >= 22 || month == 7 && day <= 22) {
		   star = "巨蟹座";
		  }
		  if (month == 7 && day >= 23 || month == 8 && day <= 22) {
		   star = "狮子座";
		  }
		  if (month == 8 && day >= 23 || month == 9 && day <= 22) {
		   star = "处女座";
		  }
		  if (month == 9 && day >= 23 || month == 10 && day <= 22) {
		   star = "天秤座";
		  }
		  if (month == 10 && day >= 23 || month == 11 && day <= 21) {
		   star = "天蝎座";
		  }
		  if (month == 11 && day >= 22 || month == 12 && day <= 21) {
		   star = "射手座";
		  }
		  if (month == 12 && day >= 22 || month == 1 && day <= 19) {
		   star = "摩羯座";
		  }
		  return star;
		 }

	/**
	 * 计算字符串中共有多少个汉字
	 * 
	 * @param str
	 */
	public static int getChineseCharacterCount(String str) {
		int ccCount = 0;
		String regEx = "[\u4e00-\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				ccCount = ccCount + 1;
			}
		}
		return ccCount;
		// System.out.println("字符串“"+str+"”中共有 " + ccCount + "个汉字 ");
	}

	public static void CopyStream(InputStream is, OutputStream os) {
		final int buffer_size = 1024;
		try {
			byte[] bytes = new byte[buffer_size];
			for (;;) {
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (Exception ex) {
		}
	}

	/**
	 * 获取指定宽度的图片
	 * 
	 * @param url
	 * @param width
	 * @return
	 */
	public static String getThumbImageUrl(String url, int width) {
		if(url==null)
			return null;
		return String.format("%s?imageView2/1/w/%d/h/%d/auto-orient", url, width,width);
	}

	public static String getThumbImageUrl(String url) {
		//http://78re52.com1.z0.glb.clouddn.com/resource/gogopher.jpg?imageView2/1/w/200/h/200/auto-orient
		return String.format("%s?imageView2/1/w/200/h/200/auto-orient", url);
	}

	public static String getOriginalImage(String url) {
		return getThumbImageUrl(url, 640);
	}

	/** 判断手机ROM是否有魅族的smartBar */
	public static boolean hasSmartBar() {
		try {
			boolean bool = ((Boolean) Class.forName("android.os.Build")
					.getMethod("hasSmartBar", new Class[0])
					.invoke(null, new Object[0])).booleanValue();
			return bool;
		} catch (Exception localException) {
			if (Build.DEVICE.equals("mx2"))
				return true;
			if ((Build.DEVICE.equals("mx")) || (Build.DEVICE.equals("m9")))
				return false;

		}
		return false;
	}

	/** 获取mac地址 */
	public static String getMacAddress(Context context) {
		if(VERSION.SDK_INT>=23)
		{
			return getMac();
		}
		
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);

		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress()==null?"":info.getMacAddress();

	}
	
	/** 
     * 获取手机的MAC地址 
     * @return 
     */  
    public static String getMac(){
        String str="";
        String macSerial="";
        try {  
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");  
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
  
            for (; null != str;) {  
                str = input.readLine();  
                if (str != null) {  
                    macSerial = str.trim();// 去空格  
                    break;  
                }  
            }  
        } catch (Exception ex) {
            ex.printStackTrace();  
        }  
        if (macSerial == null || "".equals(macSerial)) {  
            try {  
                return loadFileAsString("/sys/class/net/eth0/address")  
                        .toUpperCase().substring(0, 17);  
            } catch (Exception e) {
                e.printStackTrace();  
                  
            }  
              
        }  
        return macSerial;  
    }  
      public static String loadFileAsString(String fileName) throws Exception {
            FileReader reader = new FileReader(fileName);
            String text = loadReaderAsString(reader);
            reader.close();  
            return text;  
        }  
      public static String loadReaderAsString(Reader reader) throws Exception {
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[4096];  
            int readLength = reader.read(buffer);  
            while (readLength >= 0) {  
                builder.append(buffer, 0, readLength);  
                readLength = reader.read(buffer);  
            }  
            return builder.toString();  
        }  
	
	
	public static String getAppVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static int getAppVersionCode(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			return info.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static boolean isBackground(Context context) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(context.getPackageName())) {
				/*
				 * BACKGROUND=400 EMPTY=500 FOREGROUND=100 GONE=1000
				 * PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
				 */
				Log.i(context.getPackageName(), "此appimportace ="
						+ appProcess.importance
						+ ",context.getClass().getName()="
						+ context.getClass().getName());
				if (appProcess.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
					Log.i(context.getPackageName(), "处于后台"
							+ appProcess.processName);
					return true;
				} else {
					Log.i(context.getPackageName(), "处于前台"
							+ appProcess.processName);
					return false;
				}
			}
		}
		return false;
	}

	

	/** 获取文件夹大小 */
	public static double getDirSize(File file) {
		// 判断文件是否存在
		if (file.exists()) {
			// 如果是目录则递归计算其内容的总大小
			if (file.isDirectory()) {
				File[] children = file.listFiles();
				double size = 0;
				for (File f : children)
					size += getDirSize(f);
				return size;
			} else {// 如果是文件则直接返回其大小,以“兆”为单位
				double size = (double) file.length() / 1024 / 1024;
				return size;
			}
		} else {
			return 0.0;
		}
	}

	public static String getDoubleDesc(Context context, double value) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###,###.#");
		if (value < 1&&value>0) {
			return "0"+decimalFormat.format(value);
		}
		return decimalFormat.format(value);
	}

	public static void deleteFolder(String dir) {
		File delfolder = new File(dir);
		File oldFile[] = delfolder.listFiles();
		try {
			for (int i = 0; i < oldFile.length; i++) {
				if (oldFile[i].isDirectory()) {
					deleteFolder(dir + oldFile[i].getName() + File.separator); // 递归清空子文件夹
				}
				oldFile[i].delete();
			}
		} catch (Exception e) {
			System.out.println("清空文件夹操作出错!");
			e.printStackTrace();
		}
	}
	/**
	 * 计算地球上任意两点(经纬度)距离
	 * 
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static double Distance(double long1, double lat1, double long2,
			double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2
				* R
				* Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
						* Math.cos(lat2) * sb2 * sb2));
		return d;
	}
	
	public static boolean isInstallSoftware(Context context, String packageName) {
	    PackageManager packageManager = context.getPackageManager();
	    try {  
	        PackageInfo pInfo = packageManager.getPackageInfo(packageName,
	                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
	        //判断是否获取到了对应的包名信息 
	        if(pInfo!=null){  
	            return true;
	        }  
	    } catch (NameNotFoundException e) {
	        e.printStackTrace();  
	    }  
	    return false;
	} 
	public static boolean isInstallSoftware1(Context context, String packageName) {
		PackageManager packageManager = context.getPackageManager();
		List<PackageInfo> packList = packageManager.getInstalledPackages(0);
		for(PackageInfo info:packList)
		{
			if(info.packageName.contains(packageName))
			{
				return true;
			}
		}
		return false;
	} 
	
	
	/** 
	    * 判断某个界面是否在前台 
	    *  
	    * @param context 
	    * @param className 
	    *            某个界面名称 
	    */  
	   public static boolean isForeground(Context context, String className) {
	       if (context == null || TextUtils.isEmpty(className)) {
	           return false;  
	       }  
	  
	       ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	       List<RunningTaskInfo> list = am.getRunningTasks(1);
	       if (list != null && list.size() > 0) {  
	           ComponentName cpn = list.get(0).topActivity;
	           if (className.equals(cpn.getClassName())) {  
	               return true;  
	           }  
	       }  
	  
	       return false;  
	   }  
	   public static String getUUID(){
	        String s = UUID.randomUUID().toString();
	        //去掉“-”符号 
	        return "AND_" + s.replace("-", "");	
	    }
	        
	  public static boolean isQQ(String qq)
	  {
		  String regex = "^[1-9][0-9]{4,} $";
		  boolean flag;
		  try {
			     Pattern pattern = Pattern.compile(regex);
			     Matcher matcher = pattern.matcher(qq);
			     flag = matcher.matches();
			 } catch (Exception e) {
			     flag = false;
			 }
			 return flag;
	  }
	      
	  /**
	     * 获取当前进程名
	     * @param context
	     * @return 进程名
	     */
	    public static final String getProcessName(Context context) {
	        String processName = null;

	        // ActivityManager
	        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));

	        while (true) {
	            for (RunningAppProcessInfo info : am.getRunningAppProcesses()) {
	                if (info.pid == android.os.Process.myPid()) {
	                    processName = info.processName;

	                    break;
	                }
	            }

	            // go home
	            if (!TextUtils.isEmpty(processName)) {
	                return processName;
	            }

	            // take a rest and again
	            try {
	                Thread.sleep(100L);
	            } catch (InterruptedException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
 
	    public static String getIp(Context context)
	    {
	    	 //获取wifi服务  
	        WifiManager wifiManager = (WifiManager)context. getSystemService(Context.WIFI_SERVICE);
	        //判断wifi是否开启  
	        if (!wifiManager.isWifiEnabled()) {  
	        wifiManager.setWifiEnabled(true);    
	        }  
	        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
	        int ipAddress = wifiInfo.getIpAddress();   
	        return (ipAddress & 0xFF ) + "." +       
			      ((ipAddress >> 8 ) & 0xFF) + "." +       
			      ((ipAddress >> 16 ) & 0xFF) + "." +       
			      ( ipAddress >> 24 & 0xFF) ;  
	    }
	    
	  
}
