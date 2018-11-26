package com.guomai.dushuhui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;

import com.baidu.mapapi.SDKInitializer;
import com.guomai.dushuhui.config.AppConstant;
import com.guomai.dushuhui.main.MainActivity;
import com.guomai.dushuhui.model.UserDetail;
import com.guomai.dushuhui.network.OkHttpManager;
import com.guomai.dushuhui.util.CommonUitl;
import com.guomai.dushuhui.util.DynamicTimeFormat;
import com.guomai.dushuhui.util.SharePrefUtil;
import com.guomai.dushuhui.util.common.DirectoryUtil;
import com.guomai.dushuhui.util.imageloader.MyImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.waveswipe.DropBounceInterpolator;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.Screen;


import org.litepal.LitePal;
import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jonathanfinerty.once.Once;
import okhttp3.OkHttpClient;

public class MyApplication extends Application {


	static {//使用static代码段可以防止内存泄漏

		//启用矢量图兼容
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
		//设置全局默认配置（优先级最低，会被其他设置覆盖）
		SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
			@Override
			public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
				//全局设置（优先级最低）
				layout.setEnableLoadMore(false);
				layout.setEnableAutoLoadMore(true);
				layout.setEnableOverScrollDrag(false);
				layout.setEnableOverScrollBounce(true);
				layout.setEnableLoadMoreWhenContentNotFull(true);
				layout.setEnableScrollContentWhenRefreshed(true);
			}
		});
		SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
			@NonNull
			@Override
			public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
				//全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
				layout.setPrimaryColorsId(R.color.colorAccent, android.R.color.white);

				return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
			}
		});




	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
	public MainActivity mainAct;
	/**是否是开发模式*/
	public static final boolean DEVELOPER_MODE = true;
	private List<Activity> records = new ArrayList<Activity>();
	private static final String TAG = "MyApplication";
	public long lastMsgTime;
	public UserDetail user;
//	public ClientConfig config;
//
//	public List<PlaceData.Province> provinceList;
//
//	public MyLocation currentLocation;
//	public LocationService locationService;
	public boolean hasLogin;
	private Handler mHandler = new Handler();



//    public static interface OnLocationListener
//	{
//		public void onSuccess(MyLocation location);
//	}
//
//	private OnLocationListener myLocationListener;


	
	public void addActvity(Activity activity) {
		records.add(activity);
		Log.e(TAG,"Current Acitvity Size :" + getCurrentActivitySize());
	}

	public void removeActvity(Activity activity) {
		records.remove(activity);
		Log.e(TAG,"Current Acitvity Size :" + getCurrentActivitySize());
	}

	public void pop2MainActivity()
	{
		for (Activity activity : records) {
			if(activity instanceof MainActivity)
			{

			}
			else {
				activity.finish();
			}
		}
	}


	public List<Activity> getActivity()
	{
		return records;
	}
	
	public void exit() {
		
		for (Activity activity : records) {
			activity.finish();
		}

	}
	
	public Activity getTopActivity()
	{
		if(records!=null&&records.size()>0)
		{
			return records.get(records.size()-1);
		}
		return null;
	}
	

	public int getCurrentActivitySize() {
		return records.size();
	}

	private static MyApplication mInstance = null;

	/**是否是测试环境*/
	private boolean isDubg = true;

	@Override
	public void onCreate() {
		super.onCreate();
		try {
			//初始化数据库
			LitePal.initialize(this);
			SQLiteDatabase db = Connector.getDatabase();
			db.close();
			db = null;
			mInstance = this;
//			locationService = new LocationService(this);
			Once.initialise(this);
			//友盟配置
			Config.DEBUG = true;
			UMShareAPI.get(this);
			PlatformConfig.setWeixin(AppConstant.WX_APP_ID, AppConstant.WX_APP_SECRET);
			PlatformConfig.setQQZone(AppConstant.QQ_APP_ID, AppConstant.QQ_APP_SECRET);
			configOKGo();
//			UMShareAPI.init(this,AppConstant.UMKEY);
//			Glide.

//			PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
			//新版本中添加
//			if (!Once.beenDone(Once.THIS_APP_VERSION, "mengxiong5.0")) {
//			    SharePrefUtil.clear();
//			    Once.markDone("mengxiong5.0");
//			}

			ImagePicker.getInstance().setImageLoader(new MyImageLoader());
//			MyZoomMediaLoader.getInstance().init(new MyZoomMediaLoader());
//			CrashReport.initCrashReport(this, AppConstant.BuglyAppId,isDubg);
			SDKInitializer.initialize(getApplicationContext());
//			initLocation();
			resetUserInfo();


			lastMsgTime = SharePrefUtil.getLong(SharePrefUtil.KEY.LAST_MSG_TIME, 0);
//			// 创建缓存目录
			DirectoryUtil.mkAllDirs();
//			Fresco.initialize(mInstance, FrescoConstants.getImagePipelineConfig(mInstance));



			// crash handler
//	        AppCrashHandler.getInstance(this);
//			initCrashHandler();
	        //捕捉异常
			String configStr = SharePrefUtil.getString(SharePrefUtil.KEY.config, null);
//			if(configStr!=null)
//			{
//				config = JSON.parseObject(configStr, ClientConfig.class);
//			}
//			else
//			{
//				config = new ClientConfig();
//			}
//			TaskUtil.executeAsync(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						final long time = System.currentTimeMillis();
//						String json = ConvertUtils.toString(getResources().getAssets().open("PlaceData.json"));
//						final long readTime = System.currentTimeMillis();
//						ServerRsp rsp = JSON.parseObject(json,ServerRsp.class);
//						provinceList = JSON.parseArray(rsp.data, PlaceData.Province.class);
//					}catch (Exception e)
//					{
//						LogUtils.error(e);
//					}
//
//				}
//			});


		} catch (Exception e) {
			e.printStackTrace();
		}

		View view = View.inflate(this,R.layout.layout_desktop,null);
		view.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FloatWindow.get().hide();
			}
		});
		/*FloatWindow
				.with(getApplicationContext())
				.setView(view)
				//.setWidth(100)                               //设置控件宽高
				//.setHeight(Screen.width,0.2f)
				//.setHeight()
				.setX(50)                                   //设置控件初始位置
				.setY(Screen.height,0.8f)
				.setDesktopShow(true)                        //桌面显示
				//.setViewStateListener(mViewStateListener)    //监听悬浮控件状态改变
				//.setPermissionListener(mPermissionListener)  //监听权限申请结果
				.build();*/
	
	}

	/**配置okgo*/
	private void configOKGo() {
		OkGo.getInstance().init(this);
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		//全局的读取超时时间
		builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
		//全局的写入超时时间
		builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
		//全局的连接超时时间
		builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);

//		//使用sp保持cookie，如果cookie不过期，则一直有效
//		builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
//		//使用数据库保持cookie，如果cookie不过期，则一直有效
//		builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));
		//使用内存保持cookie，app退出后，cookie消失
		builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
		//---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
		HttpHeaders headers = new HttpHeaders();
//		headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
//		headers.put("commonHeaderKey2", "commonHeaderValue2");
		HttpParams params = new HttpParams();
//		params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
//		params.put("commonParamsKey2", "这里支持中文参数");
//-------------------------------------------------------------------------------------//

		OkGo.getInstance().init(this)                       //必须调用初始化
				.setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
				.setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
				.setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
				.setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
				.addCommonHeaders(headers)                      //全局公共头
				.addCommonParams(params);                       //全局公共参数


	}

	public void resetUserInfo() {
		String userJson = SharePrefUtil.getString(SharePrefUtil.KEY.USER_DETAIL, null);
		if(userJson!=null)
		{
			user = JSON.parseObject(userJson, UserDetail.class);
			if(user!=null)
			{
				//OkHttpManager.uid = user.uid+"";
//				CSession.getInst().setData(user.account,user.accountType,user.uid);
			}

		}

	}



    
    
	/**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }
	

	/**
	 * 获取application实例
	 * 
	 * @return
	 */
	public static MyApplication getInst() {
		return mInstance;
	}

	public List<Activity> getAllActivity() {
		return this.records;
	}
	
	public boolean hasLogin()
	{
		if(records!=null&&records.size()>0)
		{
			for(Activity act : records)
			{
//				if(act instanceof LoginActivity)
//				{
//					return true;
//				}
			}
		}
		return false;
	}
	
	/**退出登录**/
	public void logOut()
	{
		OkHttpManager.uid = "0";

		SharePrefUtil.clear();
		MyApplication.getInst().user = null;
		if(records!=null&&records.size()>0)
		{
			for(Activity act : records)
			{
//				if(act instanceof LoginActivity)
//				{
//
//				}else
//				{
//					act.finish();
//				}
			}
		}
		user = null;

		
		
	}

	
	






	 public boolean inMainProcess() {
	        String packageName = getPackageName();
	        String processName = CommonUitl.getProcessName(this);
	        return packageName.equals(processName);
	    }

	 
}
