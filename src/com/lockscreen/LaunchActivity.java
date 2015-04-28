package com.lockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 本界面只是App启动后的第一个界面，显示一个按钮，点击触发锁屏监听
 * 本界面并非锁屏页，也不是辅助锁屏定制的Home页
 * 
 * @author Andy
 *
 */
public class LaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
	}

	/**
	 * 启动锁屏 按钮的点击响应
	 * @param v
	 */
	public void startLockScreen(View v) {
		Intent sintent = new Intent();
		sintent.setClass(this, LockScreenService.class);
		startService(sintent);
		Toast.makeText(this, "锁屏已启动，请关闭屏幕后再点亮测试", Toast.LENGTH_SHORT).show();
	}

}
