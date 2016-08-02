package me.geed.lockscreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.geed.util.TimeUtil;

/**
 * if the screen is locked, this Activity will show.
 *
 * @author Andy
 */
public class LockScreenActivity extends Activity {
    /**
     * click this ImageView to unlock screen
     */
    private ImageView iv_key;

    /**
     * this TextView is used to show current time as an example, also you can show any thing on the {@link LockScreenActivity} you want to
     */
    private TextView tv_time;

    /**
     * check whether the screen is locked or not
     */
    public static boolean isLocked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        setContentView(R.layout.activity_lock_screen);

        isLocked = true;

        iv_key = (ImageView) findViewById(R.id.iv_key);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_time.setText(TimeUtil.getTime());

        iv_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                virbate();
                isLocked = false;
                Toast.makeText(LockScreenActivity.this, "Screen is unlocked", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        try {
            startService(new Intent(this, LockScreenService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_HOME)) {
            // Key code constant: Home key. This key is handled by the framework and is never delivered to applications.
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onBackPressed() {
        //return;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * virbate means that the screen is unlocked success
     */
    private void virbate() {
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
