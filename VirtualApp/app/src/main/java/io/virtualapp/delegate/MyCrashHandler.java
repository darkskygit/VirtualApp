package io.virtualapp.delegate;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Looper;
import android.util.Log;

import com.lody.virtual.client.VClientImpl;
import com.lody.virtual.client.core.CrashHandler;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.remote.InstalledAppInfo;

/**
 * author: weishu on 18/3/10.
 */
public class MyCrashHandler implements CrashHandler {

    private static final String TAG = "VApp";

    @Override
    public void handleUncaughtException(Thread t, Throwable e) {
        Log.i(TAG, "uncaught :" + t, e);

        if (t == Looper.getMainLooper().getThread()) {
            System.exit(0);
        } else {
            Log.e(TAG, "ignore uncaught exception of sub thread: " + t);
        }
    }
}
