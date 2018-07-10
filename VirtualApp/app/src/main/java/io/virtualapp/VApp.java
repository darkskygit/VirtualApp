package io.virtualapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.lody.virtual.client.NativeEngine;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.stub.VASettings;

import io.virtualapp.delegate.MyComponentDelegate;
import io.virtualapp.delegate.MyCrashHandler;
import io.virtualapp.delegate.MyPhoneInfoDelegate;
import jonathanfinerty.once.Once;

/**
 * @author Lody
 */
public class VApp extends Application {

    private static final String TAG = "VApp";

    public static final String XPOSED_INSTALLER_PACKAGE = "de.robv.android.xposed.installer";

    public static final String TAG_NEW_VERSION = "First launch new Version";
    public static final String TAG_SHOW_ADD_APP_GUIDE = "Should show add app guide";

    public static final int REQUEST_SELECT_APP = 5;

    public static final String EXTRA_APP_INFO_LIST = "va.extra.APP_INFO_LIST";

    private static VApp gApp;
    private SharedPreferences mPreferences;

    public static VApp getApp() {
        return gApp;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        NativeEngine.disableJit(Build.VERSION.SDK_INT);
        mPreferences = base.getSharedPreferences("va", Context.MODE_MULTI_PROCESS);
        VASettings.ENABLE_IO_REDIRECT = true;
        VASettings.ENABLE_INNER_SHORTCUT = false;
        try {
            VirtualCore.get().startup(base);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        gApp = this;
        super.onCreate();
        VirtualCore virtualCore = VirtualCore.get();
        virtualCore.initialize(new VirtualCore.VirtualInitializer() {

            @Override
            public void onMainProcess() {
                Once.initialise(VApp.this);
            }

            @Override
            public void onVirtualProcess() {
                //listener components
                virtualCore.setComponentDelegate(new MyComponentDelegate());
                //fake phone imei,macAddress,BluetoothAddress
                virtualCore.setPhoneInfoDelegate(new MyPhoneInfoDelegate());
                //fake task description's icon and title
                //virtualCore.setTaskDescriptionDelegate(new MyTaskDescriptionDelegate());
                virtualCore.setCrashHandler(new MyCrashHandler());
            }

            @Override
            public void onServerProcess() {
//                virtualCore.setAppRequestListener(new MyAppRequestListener(VApp.this));
//                virtualCore.addVisibleOutsidePackage("com.tencent.mobileqq");
//                virtualCore.addVisibleOutsidePackage("com.tencent.mobileqqi");
//                virtualCore.addVisibleOutsidePackage("com.tencent.minihd.qq");
//                virtualCore.addVisibleOutsidePackage("com.tencent.qqlite");
//                virtualCore.addVisibleOutsidePackage("com.facebook.katana");
//                virtualCore.addVisibleOutsidePackage("com.whatsapp");
//                virtualCore.addVisibleOutsidePackage("com.tencent.mm");
//                virtualCore.addVisibleOutsidePackage("com.immomo.momo");
            }
        });
    }

    public static SharedPreferences getPreferences() {
        return getApp().mPreferences;
    }

}
