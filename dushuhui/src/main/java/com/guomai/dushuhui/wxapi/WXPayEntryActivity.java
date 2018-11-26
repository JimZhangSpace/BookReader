package com.guomai.dushuhui.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.guomai.dushuhui.config.AppConstant;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	private final String TAG = "WXAPI";
	// IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
    	api = WXAPIFactory.createWXAPI(this, AppConstant.WX_APP_ID, false);
    	api.registerApp(AppConstant.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	// 微信发送请求到第三方应用时，会回调到该方法
	@Override
	public void onReq(BaseReq req) {
		//DO nothing
		
		Log.e(TAG,"发送微信请求"+req.transaction);
	}

	// 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
	public void onResp(BaseResp resp) {
		if(resp instanceof PayResp){
			Log.e(TAG,"onPayFinish,errCode="+resp.errCode);
			PayResp payResp = (PayResp) resp;
			//使用eventbus发送通知
//			NotificationCenter.defaultCenter().publish(payResp);
//			EventBus.getDefault().post(payResp);

			finish();
		}
		
	}

	
	
	
	
	
	
	

	
}