package com.guomai.dushuhui.util.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTypeTool
{

	public static int parseInt (String string)
	{
		try
		{
			return Integer.parseInt(string);
		}
		catch (NumberFormatException e)
		{
			return 0;
		}
	}

	public static short parseShort (String string)
	{
		try
		{
			return Short.parseShort(string);
		}
		catch (NumberFormatException e)
		{
			return 0;
		}
	}

	public static float parseFloat (String string)
	{
		try
		{
			return Float.parseFloat(string);
		}
		catch (NumberFormatException e)
		{
			return 0.0f;
		}
	}

	public static long parseLong (String string)
	{
		try
		{
			return Long.parseLong(string);
		}
		catch (NumberFormatException e)
		{
			return 0l;
		}
	}

	public static double parseDouble (String string)
	{
		try
		{
			return Double.parseDouble(string);
		}
		catch (NumberFormatException e)
		{
			return 0;
		}
	}

	public static boolean parseBoolean (String string)
	{
		try
		{
			return Boolean.parseBoolean(string);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}

	public static Date parseDate (SimpleDateFormat format, String string)
	{
		try
		{
			return format.parse(string);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Date();
	}

}
