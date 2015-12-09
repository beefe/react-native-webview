package com.heng.webview;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

/**
 * Created by heng on 15/12/9.
 */
public class WebViewManager extends SimpleViewManager<ReactWebView> {

    public static final String REACT_CLASS = "RCTWebView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ReactWebView createViewInstance(ThemedReactContext reactContext) {
        return new ReactWebView(reactContext);
    }


}
