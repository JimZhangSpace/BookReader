package com.guomai.dushuhui.util;

import android.text.TextUtils;

import java.text.DecimalFormat;

public class NumberUtils {
	/***
	 * 获取钱的数字描述
	 * @param money	钱数，分为单位
	 * @return
	 */
	public static String getMoneyDesc(long money)
	{
		if(money%100==0)
		{
			return String.valueOf(money/100);
		}
		if(money%10==0)
		{
			float num = money/100.0f;
			DecimalFormat df1 = new DecimalFormat("#####.0");
			if(money<100)
				return String.format("0%s", df1.format(num));
			return df1.format(num);
		}
		float num = money/100.0f;
		DecimalFormat df1 = new DecimalFormat("#####.00");
		if(money<100)
			return String.format("0%s", df1.format(num));
		return df1.format(num);
	}
	
	public static String getLibaoDesc(double libao)
	{
		DecimalFormat df1 = new DecimalFormat("#####.0");
		if(libao<1)
		{
			return String.format("0%s", df1.format(libao));
		}
		else
		{
			return df1.format(libao);
		}
	}
	
	/**
	  * 判断字符串是否是整数
	  */
	 public static boolean isNumber(String value) {
		 if(TextUtils.isEmpty(value))
			 return false;
		  try {
			   Double.valueOf(value);
			   return true;
		  } catch (NumberFormatException e) {
			  return false;
		  }
	}
	 public static boolean isLong(String value) {
		 if(TextUtils.isEmpty(value))
			 return false;
		 try {
			 Long.valueOf(value);
			 return true;
		 } catch (NumberFormatException e) {
			 return false;
		 }
	 }
	
}
