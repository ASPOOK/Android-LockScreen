package com.lockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
	}
	
	// Æô¶¯ËøÆÁ¼àÌý
	public void startLockScreen(View v) {
		Intent sintent = new Intent();
		sintent.setClass(this, LockScreenService.class);
		startService(sintent);
	}

}
