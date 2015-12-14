package com.heng.webview;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.SystemClock;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;

/**
 */
public class ReactWebView extends WebView {

    EventDispatcher eventDispatcher;
    EventWebClient eventWebClient;

    protected class CustomWebChromeClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
        }
    }

    protected class EventWebClient extends WebViewClient {


        String injectedJavaScript;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        public void setInjectedJavaScript(String injectedJavaScript) {
            this.injectedJavaScript = injectedJavaScript;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            eventDispatcher.dispatchEvent(new NavigationStateChangeEvent(
                    getId(), SystemClock.uptimeMillis(), view.canGoBack(), view.canGoForward(),
                    url, view.getTitle(), true));
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            eventDispatcher.dispatchEvent(new NavigationStateChangeEvent(
                    getId(), SystemClock.uptimeMillis(), view.canGoBack(), view.canGoForward(),
                    url, view.getTitle(), true));
        }
    }

    public ReactWebView(ReactContext reactContext) {
        super(reactContext);
        eventDispatcher = reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
        eventWebClient = new EventWebClient();
        getSettings().setJavaScriptEnabled(true);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setGeolocationEnabled(false);
        getSettings().setAllowFileAccess(true);
        getSettings().setAllowFileAccessFromFileURLs(true);
        getSettings().setLoadsImagesAutomatically(true);
        getSettings().setBlockNetworkImage(false);
        getSettings().setBlockNetworkLoads(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setWebViewClient(eventWebClient);
        setWebChromeClient(new CustomWebChromeClient());
    }

    public void setInjectedJavaScript(String injectedJavaScript) {
        eventWebClient.setInjectedJavaScript(injectedJavaScript);
    }

}
