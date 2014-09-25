package com.lockscreen;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

/**
 * 辅助锁屏的Home页，如果直接用锁屏页作为Home页，会执行重启，有切换动作
 * 
 * @author Andy
 * 
 */
public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private String mPackageName;
	private String mName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.e(TAG, "onPause");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.e(TAG, "onResume");

		// LockScreenActivity设为Launcher，已经启动了service
		Intent serviceIntent = new Intent();
		serviceIntent.setClass(this, LockScreenService.class);
		startService(serviceIntent);

		getLauncherPackageName(this);
		reCheckLauncher(this);

		// 如果是关闭锁屏页的状态下按Home键，启动系统的Home，否则后续的Home点击操作无效
		if (!LockScreenActivity.isStarted) {
			Intent intent = new Intent();
			intent.setComponent(new ComponentName(mPackageName, mName));
			startActivity(intent);
		}

		/*
		 * Move the task containing this activity to the back of the activity
		 * stack. The activity's order within the task is unchanged. If false
		 * then this only works if the activity is the root of a task; if true
		 * it will work for any 该Activity设置了android:launchMode="singleInstance"
		 */
		moveTaskToBack(false);// MainActivity作为Home页，把它拿到后边去，否则覆盖在锁屏页上边
	}

	@Override
	public void onNewIntent(Intent intent) {
		setIntent(intent);
		Log.e(TAG, "onNewIntent");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "onDestroy");
	}

	/**
	 * 该界面屏蔽其他按键，该页面是隐藏的，其实也无需屏蔽按键
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	// 获取系统Home的包名类名,默认用系统的，也可以让用户指定，百度锁屏及GO锁屏就是这样做的
	public String getLauncherPackageName(Context context) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		List<ResolveInfo> res = context.getPackageManager()
				.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				ResolveInfo r = res.get(i);
				if ((r.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
					Log.e(TAG, "系统Home-packageName:" + r.activityInfo.packageName);
					Log.e(TAG, "系统Home-Name:" + r.activityInfo.name);

					mPackageName = r.activityInfo.packageName;
					mName = r.activityInfo.name;
					break;
				}
			}
		}
		return null;
	}

	private void reCheckLauncher(Context context) {
		final Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		final ResolveInfo res = context.getPackageManager().resolveActivity(
				intent, 0);
		if (res.activityInfo == null) {
			return;
		}
		if (res.activityInfo.packageName.equals("android")) {
			return;
		} else {			
			return;
		}
	}

}
