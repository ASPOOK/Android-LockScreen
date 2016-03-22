package me.geed.lockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * This Activity is only used to start {@link LockScreenService}, it's unnecessary for lock screen
 *
 * @author Andy
 */
public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Button btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * start lock screen service
                 */
                Intent intent = new Intent();
                intent.setClass(LaunchActivity.this, LockScreenService.class);
                startService(intent);
                Toast.makeText(LaunchActivity.this, "锁屏服务已启动，请先关闭屏幕然后再打开屏幕进行测试", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
