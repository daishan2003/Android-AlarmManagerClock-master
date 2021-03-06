package com.loonggg.alarmmanager.clock.service;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/12/23.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {
    private Handler mJobHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage( Message msg ) {
//            Toast.makeText( getApplicationContext(),
//                    "JobService task running", Toast.LENGTH_SHORT )
//                    .show();
            jobFinished( (JobParameters) msg.obj, false );
            return true;
        }

    } );

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        mJobHandler.sendMessage( Message.obtain( mJobHandler, 1, jobParameters ) );
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        mJobHandler.removeMessages( 1 );
        return false;
    }
}
