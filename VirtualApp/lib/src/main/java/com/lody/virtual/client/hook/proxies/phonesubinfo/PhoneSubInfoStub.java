package com.lody.virtual.client.hook.proxies.phonesubinfo;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.Inject;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;

import java.lang.reflect.Method;

import mirror.com.android.internal.telephony.IPhoneSubInfo;

/**
 * @author Lody
 */
@Inject(MethodProxies.class)
public class PhoneSubInfoStub extends BinderInvocationProxy {
	public PhoneSubInfoStub() {
		super(IPhoneSubInfo.Stub.asInterface, "iphonesubinfo");
	}

	@Override
	protected void onBindMethods() {
		super.onBindMethods();
		addMethodProxy(new ReplaceLastPkgMethodProxy("getNaiForSubscriber"));
		addMethodProxy(new ReplaceLastPkgMethodProxy("getImeiForSubscriber"));
		addMethodProxy(new ReplaceCallingPkgMethodProxy("getDeviceSvn"));
		addMethodProxy(new ReplaceLastPkgMethodProxy("getDeviceSvnUsingSubId"));
		addMethodProxy(new ReplaceCallingPkgMethodProxy("getSubscriberId"));
		addMethodProxy(new StaticMethodProxy("getSubscriberIdForSubscriber") {
			// 傻逼支付宝
			@Override
			public Object call(Object who, Method method, Object... args) throws Throwable {
				return "";
			}
		});
		addMethodProxy(new ReplaceCallingPkgMethodProxy("getGroupIdLevel1"));
		addMethodProxy(new ReplaceLastPkgMethodProxy("getGroupIdLevel1ForSubscriber"));
		addMethodProxy(new ReplaceCallingPkgMethodProxy("getLine1Number"));
		addMethodProxy(new ReplaceLastPkgMethodProxy("getLine1NumberForSubscriber"));
		addMethodProxy(new ReplaceCallingPkgMethodProxy("getLine1AlphaTag"));
		addMethodProxy(new ReplaceLastPkgMethodProxy("getLine1AlphaTagForSubscriber"));
		addMethodProxy(new ReplaceCallingPkgMethodProxy("getMsisdn"));
		addMethodProxy(new ReplaceLastPkgMethodProxy("getMsisdnForSubscriber"));
		addMethodProxy(new ReplaceCallingPkgMethodProxy("getVoiceMailNumber"));
		addMethodProxy(new ReplaceLastPkgMethodProxy("getVoiceMailNumberForSubscriber"));
		addMethodProxy(new ReplaceCallingPkgMethodProxy("getVoiceMailAlphaTag"));
		addMethodProxy(new ReplaceLastPkgMethodProxy("getVoiceMailAlphaTagForSubscriber"));
	}

}
