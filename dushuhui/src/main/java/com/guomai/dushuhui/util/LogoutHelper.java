package com.guomai.dushuhui.util;



import com.guomai.dushuhui.MyApplication;
import com.guomai.dushuhui.network.OkHttpManager;

public class LogoutHelper {
	public static void logout()
	{


		SharePrefUtil.clear();

		MyApplication.getInst().logOut();
		HttpTool.uid = "0";
		HttpTool.token = "";
		OkHttpManager.uid = "0";
		OkHttpManager.token = "";
//		UserPreferences.clear();
//		NotificationCenter.defaultCenter().publish(new LoginStatusChangeEvent());
	}
}
