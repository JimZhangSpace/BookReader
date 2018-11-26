package com.guomai.dushuhui.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeUtils {

	@SuppressWarnings("deprecation")
	public static String getTimeDesc(Date date) {
		final long oneDaySeconds = 24 * 3600L;
		final long oneHourSeconds = 3600L;
		Date now = new Date();
		long diff = now.getTime() - date.getTime();
		diff /= 1000;

		if (diff <= 60) {
			return "刚刚";
		}

		else if (diff < oneHourSeconds) {

			if (Math.abs(diff - 1800) < 5) {
				return "半小时前";
			}

			diff /= 60;
			String text = String.format("%d分钟前", diff);
			return text;
		}

		else if (diff == oneHourSeconds) {
			return "1小时前";
		}

		else if (diff <= oneDaySeconds) {

			int oldday = date.getDate();
			int nowday = now.getDate();

			if (oldday == nowday) {
				return String.format("%d小时前", diff / oneHourSeconds);
			} else {
				SimpleDateFormat df = new SimpleDateFormat("昨天 HH:mm");
				return df.format(date);
			}
		}

		else {
			int day = (int) (diff/oneDaySeconds);
			if(day<30)
			{
				return String.format("%d天前", day);
			}else
			{
				Calendar.getInstance().setTime(date);
				int oldYear = Calendar.getInstance().get(Calendar.YEAR);
				Calendar.getInstance().setTime(now);
				int nowYear = Calendar.getInstance().get(Calendar.YEAR);
				
				if(nowYear!=oldYear)
				{
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					return df.format(date);
				}else
				{
					SimpleDateFormat df = new SimpleDateFormat("MM-dd");
					return df.format(date);
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	public static String getDayDesc(Date date) {
		final long oneDaySeconds = 24 * 3600L;
		final long oneHourSeconds = 3600L;
		Date now = new Date();
		long diff = now.getTime() - date.getTime();
		diff /= 1000;
		
		if (diff <= 60) {
			return "刚刚";
		}
		
		else if (diff < oneHourSeconds) {
			
			if (Math.abs(diff - 1800) < 5) {
				return "半小时前";
			}
			
			diff /= 60;
			String text = String.format("%d分钟前", diff);
			return text;
		}
		
		else if (diff == oneHourSeconds) {
			return "1小时前";
		}
		
		else if (diff <= oneDaySeconds) {
			
			int oldday = date.getDate();
			int nowday = now.getDate();
			
			if (oldday == nowday) {
				return String.format("%d小时前", diff / oneHourSeconds);
			} else {
				SimpleDateFormat df = new SimpleDateFormat("昨天 HH:mm");
				return df.format(date);
			}
		}
		
		else {
			int day = (int) (diff/oneDaySeconds);
			if(day<30)
			{
				return String.format("%d天前", day);
			}else
			{
				return "30天前";
			}
		}
	}
	
	public static int getMinute(long millionSecond)
	{
		if(millionSecond<=0)
		{
			return 0;
		}else
		{
			return (int) (millionSecond/(60*1000));
		}
	}
	
	/**获取秒数的时间表示*/
	public static String getTimeDesc(int second) {
		String desc = "";
		if(second<0)
			return desc;
		if(second<=60)
		{
			desc= String.format("%02d:%02d", 0,second);
		}
		else if(second>60&&second<=60*60)
		{//小于一小时
			desc = String.format("%02d:%02d", second/60,second%60);
//			desc = second/60+context.getString(R.string.str_unit_minute)+second%60+context.getString(R.string.str_unit_second);
		}else if(second>60*60&&second<=24*60*60)
		{//大于一小时，则显示到分钟
//			desc = second/3600+""+(second%3600)/60;
			desc = String.format("%02d:%02d:%02d", second/3600,(second%3600)/60,second%60);;
//			desc =  second/3600+context.getString(R.string.str_unit_hour)+(second%3600)/60+context.getString(R.string.str_unit_minute);
		}
		else 
		{//大于一天则显示到小时
			desc = String.format(second/(24*3600)+"天"+//
					(second%(24*3600)/3600)+"小时");
		}
		return desc;
	}
	/**获取用户通话时间*/
	public static String getTime(int second) {
		String desc = "";
		if(second<0)
			return desc;
		if(second<=60)
		{
			desc= String.format("%d秒", second);
		}
		else if(second>60&&second<=60*60)
		{//小于一小时
			desc = String.format("%d分%d秒", second/60,second%60);
//			desc = second/60+context.getString(R.string.str_unit_minute)+second%60+context.getString(R.string.str_unit_second);
		}else if(second>60*60&&second<=24*60*60)
		{//大于一小时，则显示到分钟
//			desc = second/3600+""+(second%3600)/60;
			desc = String.format("%d小时%d分%d秒", second/3600,(second%3600)/60,second%60);;
//			desc =  second/3600+context.getString(R.string.str_unit_hour)+(second%3600)/60+context.getString(R.string.str_unit_minute);
		}
		else 
		{//大于一天则显示到小时
			desc = String.format(second/(24*3600)+"天"+//
					(second%(24*3600)/3600)+"小时");
		}
		return desc;
	}
	
	

}
