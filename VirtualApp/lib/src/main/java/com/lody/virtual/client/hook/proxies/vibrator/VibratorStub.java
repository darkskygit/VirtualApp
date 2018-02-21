package com.lody.virtual.client.hook.proxies.vibrator;

import android.content.Context;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;

import java.lang.reflect.Method;

import mirror.com.android.internal.os.IVibratorService;

/**
 * @author Lody
 * @see android.os.Vibrator
 */
public class VibratorStub extends BinderInvocationProxy {

    public VibratorStub() {
        super(IVibratorService.Stub.asInterface, Context.VIBRATOR_SERVICE);
    }

    @Override
    protected void onBindMethods() {
        //Samsung  {
        addMethodProxy(new VibrateMethodProxy("vibrateMagnitude"));
        addMethodProxy(new VibrateMethodProxy("vibratePatternMagnitude"));
        // }
        // 傻逼微信
        addMethodProxy(new ReplaceCallingPkgMethodProxy("vibrate") {
            @Override
            public Object call(Object who, Method method, Object... args) throws Throwable {
                return null;
            }
        });
        addMethodProxy(new VibrateMethodProxy("vibratePattern"));
        // 傻逼微信
        addMethodProxy(new ReplaceCallingPkgMethodProxy("cancelVibrate") {
            @Override
            public Object call(Object who, Method method, Object... args) throws Throwable {
                return null;
            }
        });
    }

    private final static class VibrateMethodProxy extends ReplaceCallingPkgMethodProxy {

        private VibrateMethodProxy(String name) {
            super(name);
        }

        @Override
        public boolean beforeCall(Object who, Method method, Object... args) {
            if (args[0] instanceof Integer) {
                args[0] = getRealUid();
            }
            return super.beforeCall(who, method, args);
        }
    }
}
