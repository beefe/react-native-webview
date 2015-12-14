package com.heng.webview;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/**
 * Created by heng on 15/12/14.
 */
public class NavigationStateChangeEvent extends Event<NavigationStateChangeEvent> {

    public static final String EVENT_NAME = "navigationStateChange";

    private final boolean isLoading;
    private final String url;
    private final boolean canGoBack;
    private final boolean canGoForward;

    protected NavigationStateChangeEvent(int viewTag,long timestampMs,boolean isLoading,String url,boolean canGoBack,boolean canGoForward){
        super(viewTag,timestampMs);
        this.isLoading = isLoading;
        this.url = url;
        this.canGoBack = canGoBack;
        this.canGoForward = canGoForward;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(),getEventName(),serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putBoolean("loading", isLoading);
        eventData.putBoolean("canGoBack", canGoBack);
        eventData.putBoolean("canGoForward", canGoForward);
        eventData.putString("url", url);
        return eventData;
    }

}
