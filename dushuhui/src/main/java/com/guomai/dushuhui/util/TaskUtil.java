package com.guomai.dushuhui.util;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Process;

public class TaskUtil
{

	public static void executeAsync (Runnable runnable)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
		{
			runWithThreadPool(runnable);
		}
		else
		{
			runWithStandThread(runnable);
		}
	}

	private static void runWithStandThread (Runnable runnable)
	{
		new Thread(runnable)
		{
			@Override
			public void run ()
			{
				Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

				super.run();
			}
		}.start();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private static void runWithThreadPool (final Runnable runnable)
	{
		new AsyncTask<Void, Void, Void>()
		{

			@Override
			protected Void doInBackground (Void... params)
			{
				runnable.run();

				return null;
			}
		}.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}

}
