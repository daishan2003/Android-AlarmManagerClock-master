package com.loonggg.lib.alarmmanager.clock;

import android.app.Activity;
import android.app.Service;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;


public class ClockAlarmActivity extends Activity {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;
    private int leftNumber, rightNumber;
    private int calculateResult;
    private int ringType;
    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_alarm);
        int flag = this.getIntent().getIntExtra("flag", 0);
        ringType = this.getIntent().getIntExtra("ringType", 0);
        message = getIntent().getStringExtra("msg");
        showDialogInBroadcastReceiver();
    }

    private void showDialogInBroadcastReceiver() {
//        if (flag == 1 || flag == 2) {
//        mediaPlayer = MediaPlayer.create(this, R.raw.beep);
//        mediaPlayer.setLooping(true);
//        mediaPlayer.start();
////        }
//        //数组参数意义：第一个参数为等待指定时间后开始震动，震动时间为第二个参数。后边的参数依次为等待震动和震动的时间
//        //第二个参数为重复次数，-1为不重复，0为一直震动
//        if (flag == 0 || flag == 2) {
        vibrator = (Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{100, 10, 100, 600}, 0);
//        }

        switch (ringType) {
            case 0:
                normalWakeUp();
                break;
            case 1:
                easyWakeUp();
                break;
            case 2:
                forceWakeUp();
                break;
        }

    }

    /**
     * 普通唤醒
     */
    private void normalWakeUp() {
        final SimpleDialog dialog = new SimpleDialog(this, R.style.Theme_dialog);
        dialog.show();
        dialog.setTitle(message);
        dialog.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.bt_confirm == v || dialog.bt_cancel == v) {
//                    if (flag == 1 || flag == 2) {
//                    mediaPlayer.stop();
//                    mediaPlayer.release();
//                    }
//                    if (flag == 0 || flag == 2) {
                    vibrator.cancel();
//                    }
                    dialog.dismiss();
                    finish();
                }
            }
        });
    }

    /**
     * 轻松唤醒
     */
    private void easyWakeUp() {
        final SimpleDialog dialog = new SimpleDialog(this, R.style.Theme_dialog);
        dialog.show();
        dialog.setTitle(message);
        dialog.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.bt_confirm == v || dialog.bt_cancel == v) {
//                    if (flag == 1 || flag == 2) {
//                    mediaPlayer.stop();
//                    mediaPlayer.release();
//                    }
//                    if (flag == 0 || flag == 2) {
                    vibrator.cancel();
//                    }
                    dialog.dismiss();
                    finish();
                }
            }
        });
    }

    /**
     * 强制唤醒
     */
    private void forceWakeUp() {
        final CalculateDialog dialog = new CalculateDialog(this, R.style.Theme_dialog);
        dialog.show();
        dialog.setTitle(message);
        leftNumber = (int) (Math.random() * 100);
        rightNumber = (int) (Math.random() * 100);
        String opt = "+";
        if (Math.random() >= 0.5) {
            calculateResult = leftNumber + rightNumber;
        } else {
            opt = "-";
            calculateResult = leftNumber - rightNumber;
        }
        dialog.setExpression(leftNumber + " " + opt + " " + rightNumber + " =");
        dialog.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.bt_confirm == v || dialog.bt_cancel == v) {
//                    if (flag == 1 || flag == 2) {
//                    mediaPlayer.stop();
//                    mediaPlayer.release();
//                    }
//                    if (flag == 0 || flag == 2) {
                    if (dialog.getCalculateResult() != calculateResult) {
                        Toast.makeText(ClockAlarmActivity.this, "计算错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
//                    }
                    vibrator.cancel();
                    dialog.dismiss();
                    finish();
                }
            }
        });
    }
}