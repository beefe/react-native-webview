package com.heng.webview;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.List;

/**
 * Created by heng on 15/12/9.
 */
public class WebViewPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return null;
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return null;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return null;
    }
}
