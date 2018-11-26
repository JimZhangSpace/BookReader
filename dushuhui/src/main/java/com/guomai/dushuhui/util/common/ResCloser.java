package com.guomai.dushuhui.util.common;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

/**
 * Author: donnyliu
 *
 * modify by lostjin  一个资源的清理类
 */
public class ResCloser
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
     * 早期Socket居然没实现  Closeable！
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
