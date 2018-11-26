package com.guomai.dushuhui.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ImageUploadManager {
	private UploadManager uploadManager;
	
	private Map<String, UploadTask> uploadingTask;
	/** 默认图片上传30秒算超时 */
	public static final int upload_time = 60000;

	private class CheckHandler extends Handler {
		public void handleMessage(Message msg) {
			String key = (String) msg.obj;
			UploadTask task = uploadingTask.get(key);
			if (task != null) {
				if (task.callBack != null)
					task.callBack.onTimeOut(key);
				task.canceled = true;
				task = null;
				uploadingTask.remove(key);
			}
		};
	}

	private Handler checkTimeoutHander;

	private static ImageUploadManager _instance;

	private ImageUploadManager() {
		uploadManager = new UploadManager();
		uploadingTask = new HashMap<String, UploadTask>();
		checkTimeoutHander = new CheckHandler();
	}

	public static ImageUploadManager getInst() {
		if (_instance == null)
			_instance = new ImageUploadManager();
		return _instance;
	}

	public void uploadImage(final String path, String token, final String key,
                            final ImageUploadCallBack callBack) {
		Log.e("上传图片：" , path);
		UploadTask task = new UploadTask();
		task.callBack = callBack;
		task.canceled = false;
		uploadingTask.put(key, task);
		checkTimeoutHander.postDelayed(new Runnable() {

			@Override
			public void run() {
				Message msg = checkTimeoutHander.obtainMessage();
				msg.obj = key;
				msg.sendToTarget();
			}
		}, upload_time);

		uploadManager.put(path, key, token, new UpCompletionHandler() {

			public void complete(String key,
					com.qiniu.android.http.ResponseInfo info,
					JSONObject response) {

				if (info.isOK()) {// 上传成功
					if (callBack != null) {
						callBack.onSuccess(key, path);
					}
					uploadingTask.remove(key);
				} else {
					if (callBack != null) {
						// callBack.onTimeOut(key);
						callBack.onFailed(key);
					}
				}

			}

		}, getProgressUploadOption(new UpProgressHandler() {

			@Override
			public void progress(String arg0, double percent) {
				if (callBack != null)
					callBack.onProgress(key, (int) percent);

			}
		}, new UpCancellationSignal() {

			@Override
			public boolean isCancelled() {
				UploadTask task = uploadingTask.get(key);
				if (task != null)
					return task.canceled;
				return false;
			}
		}));
	}

	public void cancel(String key)
	{
		UploadTask task = uploadingTask.get(key);
		if(task!=null)
			task.canceled = true;
		
	}
	
	private static class UploadTask {
		public ImageUploadCallBack callBack;
		public boolean canceled;
	}

	private UploadOptions getProgressUploadOption(
			UpProgressHandler progressHandler, UpCancellationSignal signal) {
		UploadOptions options = new UploadOptions(null, null, false,
				progressHandler, signal);
		return options;
	}
}
