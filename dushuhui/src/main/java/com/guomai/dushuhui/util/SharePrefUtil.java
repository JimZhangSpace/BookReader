package com.guomai.dushuhui.util;

import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.guomai.dushuhui.MyApplication;

/**
 * SharePreferences操作工具类
 */
public class SharePrefUtil {
	@SuppressWarnings("unused")
	private static String tag = SharePrefUtil.class.getSimpleName();
	private final static String SP_NAME = "config";
	private static SharedPreferences sp;

	public interface KEY {
		/** 用户信息JSON */
		String USER_DETAIL = "user_detail";
		/** 服务器配置 */
		String SERVER_SETTING = "server_setting";// 服务器配置
		String Service_tip = "service_tip";// 服务器配置
		String PASSWORD = "password";
		String Service_Config = "password";
		String FIRST_USE = "first_use";
		String VERSION_CODE = "version_code";
		/** 推荐列表缓存 */
		String LAST_MSG_TIME = "LAST_MSG_TIME";
		/** 附近列表缓存 */
		String NEARBY_LIST = "nearby_list";
		/** 音乐发布心情缓存  */
		String PUBLISH_EDITING = "PUBLISH_EDITING";
		String TIME_DIF = "TIME_DIF";
		String USER_ACCOUNT = "user_account";
		String ALYPAY_ACCOUNT = "ALYPAY_ACCOUNT";
		String IP = "ip";
		String port = "port";
		String config = "config";
		String service_city = "service_city";
	}

	/**
	 * 保存布尔值
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveBoolean(String key, boolean value) {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);
		sp.edit().putBoolean(key, value).commit();
	}

	/**
	 * 保存字符串
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveString(String key, String value) {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);
		sp.edit().putString(key, value).commit();

	}

	public static void clear() {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);
		sp.edit().clear().commit();
	}

	/**
	 * 保存long型
	 * 
	 * @param
	 * @param key
	 * @param value
	 */
	public static void saveLong(String key, long value) {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);
		sp.edit().putLong(key, value).commit();
	}

	/**
	 * 保存int型
	 * 
	 * @param
	 * @param key
	 * @param value
	 */
	public static void saveInt(String key, int value) {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);
		sp.edit().putInt(key, value).commit();
	}

	/**
	 * 保存float型
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveFloat(String key, float value) {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);
		sp.edit().putFloat(key, value).commit();
	}

	/**
	 * 获取字符值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(String key, String defValue) {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);
		return sp.getString(key, defValue);
	}

	/**
	 * 获取int值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(String key, int defValue) {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);
		return sp.getInt(key, defValue);
	}

	/**
	 * 获取long值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static long getLong(String key, long defValue) {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);
		return sp.getLong(key, defValue);
	}

	/**
	 * 获取float值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static float getFloat(String key, float defValue) {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);
		return sp.getFloat(key, defValue);
	}

	/**
	 * 获取布尔值
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean( String key,
			boolean defValue) {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);
		return sp.getBoolean(key, defValue);
	}

	/**
	 * 将对象进行base64编码后保存到SharePref中
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public static void saveObj2json(String key, Object object) {
		if (sp == null)
			sp = MyApplication.getInst().getSharedPreferences(SP_NAME, 0);


		sp.edit().putString(key, JSON.toJSONString(object)).commit();
	}

}
