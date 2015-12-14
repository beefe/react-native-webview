package com.heng.webview;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import java.util.Map;

import javax.annotation.Nullable;

/**
 */
public class WebViewManager extends SimpleViewManager<ReactWebView> {

    public static final String REACT_CLASS = "RCTWebView";

    public static final String AUTOMATICALLY_ADJUST_CONTENT_INSETS = "automaticallyAdjustContentInsets";
    public static final String BOUNCES = "bounces";
    public static final String CONTENT_INSET = "contentInset";
    public static final String HTML = "html";
    public static final String INJECTED_JAVA_SCRIPT = "injectedJavaScript";
    public static final String ON_NAVIGATION_STATE_CHANGE = "onNavigationStateChange";
    public static final String RENDER_ERROR = "renderError";
    public static final String RENDER_LOADING = "renderLoading";
    public static final String SCROLL_ENABLED = "scrollEnabled";
    public static final String START_IN_LOADING_STATE = "startInLoadingState";
    public static final String URL = "url";
    public static final String JAVA_SCRIPT_ENABLE_ANDROID = "javaScriptEnabledAndroid";

    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_RELOAD = 3;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ReactWebView createViewInstance(ThemedReactContext reactContext) {
        return new ReactWebView(reactContext);
    }

    @ReactProp(name = AUTOMATICALLY_ADJUST_CONTENT_INSETS,defaultBoolean = true)
    public void setAutomaticallyAdjustContentInsets(ReactWebView view, boolean automaticallyAdjustContentInsets) {
        view.getSettings().setLoadsImagesAutomatically(automaticallyAdjustContentInsets);
    }

//   @ReactProp(name = BOUNCES)
//    public void setBounces(ReactWebView view,boolean bounces){
//       // Unrealized
//    }

//    @ReactProp(name = CONTENT_INSET)
//    public void setContentInset(ReactWebView view,ReadableMap map){
//        int top = map.getInt("top");
//        int left = map.getInt("left");
//        int bottom = map.getInt("bottom");
//        int right = map.getInt("right");
//        view.setPadding(left, top, right, bottom);
//    }

    @ReactProp(name = HTML)
    public void setHtml(ReactWebView view, String html) {
        view.loadData(html, "text/html", "UTF-8");
    }

    @ReactProp(name = INJECTED_JAVA_SCRIPT)
    public void setInjectedJavaScript(ReactWebView view, String injectedJavaScript){
        view.setInjectedJavaScript(injectedJavaScript);
    }

//    @ReactProp(name = RENDER_ERROR)
//    public void setRenderError(ReactWebView view,Boolean value){
//        // Unrealized
//    }

//    @ReactProp(name = RENDER_LOADING)
//    public void setRenderLoading(ReactWebView view,Boolean value){
//        // Unrealized
//    }

    @ReactProp(name = SCROLL_ENABLED,defaultBoolean = false)
    public void setScrollEnabled(ReactWebView view, boolean scrollEnable) {
        view.setScrollEnabled(scrollEnable);
    }

//    @ReactProp(name = START_IN_LOADING_STATE)
//    public void setStartInLoadingState(ReactWebView view,boolean startInLoadingState){
//        // Unrealized
//    }

    @ReactProp(name = URL)
    public void setUrl(ReactWebView view, String url) {
        view.loadUrl(url);
    }

    @ReactProp(name = JAVA_SCRIPT_ENABLE_ANDROID)
    public void setJavaScriptEnableAndroid(ReactWebView view, boolean javaScriptEnableAndroid) {
        view.getSettings().setJavaScriptEnabled(javaScriptEnableAndroid);
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "goBack", COMMAND_GO_BACK,
                "goForward", COMMAND_GO_FORWARD,
                "reload", COMMAND_RELOAD
        );
    }

    @Override
    public void receiveCommand(ReactWebView root, int commandId, ReadableArray args) {
        switch (commandId) {
            case COMMAND_GO_BACK:
                root.goBack();
                break;
            case COMMAND_GO_FORWARD:
                root.goForward();
                break;
            case COMMAND_RELOAD:
                root.reload();
                break;
            default:
                throw new IllegalArgumentException(String.format(
                        "Unsupported command %d received by %s.",
                        commandId,
                        getClass().getSimpleName()));

        }
    }

    @Nullable
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                NavigationStateChangeEvent.EVENT_NAME, MapBuilder.of("registrationName", "onNavigationStateChange")
        );
    }
}
