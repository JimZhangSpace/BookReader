package com.guomai.dushuhui.util;

public interface ImageUploadCallBack {
	public void onSuccess(String key, String path);

	public void onProgress(String key, int percent);

	public void onTimeOut(String key);

	public void onFailed(String key);
}
