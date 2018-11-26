/*
 * 
 * 
 * FileName: CollectionUtils.java
 * 
 * Description: 集合操作工具类文�?
 * 
 * History:
 * 1.0	john	2013-11-05	Create
 */

package com.guomai.dushuhui.util.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 集合辅助操作类，可用于获取元素�?判空等功�?
 * 
 * @author devilxie
 * @version 1.0
 * 
 */
public class CollectionUtils
{

	public static boolean isEmpty(Collection<? extends Object> c)
	{
		return c == null || c.size() == 0;
	}

	public static boolean isEmpty(Object[] objs)
	{
		return objs == null || objs.length == 0;
	}

	public static <T> T get(Collection<T> c, int index)
	{
		if (isEmpty(c))
			return null;

		if (index < 0 || index >= c.size())
			return null;

		if (c instanceof List)
			return (T) ((List<T>) c).get(index);

		List<? extends T> a = new ArrayList<T>(c);
		return a.get(index);
	}
}
