package com.guomai.dushuhui.util.common;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

/**
 * Author: john
 */
public class Res
{

	public static void close (Closeable res)
	{

		if (res == null)
			return;

		try
		{
			res.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * 
	 *
	 * @param socket
	 */
	public static void close (Socket socket)
	{
		if (socket == null)
			return;

		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
