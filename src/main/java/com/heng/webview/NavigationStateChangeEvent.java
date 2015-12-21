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

    private final boolean canGoBack;
    private final boolean canGoForward;
    private final String url;
    private final String title;
    private final boolean loading;

    protected NavigationStateChangeEvent(int viewTag,long timestampMs,boolean canGoBack,boolean canGoForward,
                                         String url,String title,boolean loading){
        super(viewTag,timestampMs);
        this.canGoBack = canGoBack;
        this.canGoForward = canGoForward;
        this.url = url;
        this.title = title;
        this.loading = loading;
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
        eventData.putBoolean("canGoBack", canGoBack);
        eventData.putBoolean("canGoForward", canGoForward);
        eventData.putString("url", url);
        eventData.putString("title", title);
        eventData.putBoolean("loading", loading);
        return eventData;
    }

}
