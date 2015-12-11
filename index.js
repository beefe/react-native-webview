var React = require('react-native');

var { requireNativeComponent,PropTypes,NativeModules } = React;

var UIManager = NativeModules.UIManager;

var NativeWebView = requireNativeComponent('RCTWebView',WheelView);

var WEBVIEW_REF = 'webview';

var WebView = React.createClass({
    propTypes: {
        ...View.propTypes,
        automaticallyAdjustContentInsets: PropTypes.bool,
//        bounces: PropTypes.bool,
        contentInset: PropTypes.object,
        html: PropTypes.string,
//        injectedJavaScript: PropTypes.string,
//        onNavigationStateChange: PropTypes.func,
//        renderError: PropTypes.func,
//        renderLoading: PropTypes.func,
        scrollEnabled: PropTypes.bool,
//        startInLoadingState: PropTypes.bool,
        url: PropTypes.string,
        javaScriptEnabledAndroid: PropTypes.bool,
    },
    goBack: function(){
        UIManager.dispatchViewManagerCommand(
            React.findNodeHandle(this.refs.webview),
            UIManager.RCTWebView.Commands.goBack,
            null,
        );
    },
    goForward: function(){
        UIManager.dispatchViewManagerCommand(
            React.findNodeHandle(this.refs.webview),
            UIManager.RCTWebView.Commands.goForward,
            null,
        );
    },
    reload: function(){
        UIManager.dispatchViewManagerCommand(
            React.findNodeHandle(this.refs.webview),
            UIManager.RCTWebView.Commands.reload,
            null,
        );
    },
    render(){
        return(
            <NativeWebView
                {...this.props}
                ref={WEBVIEW_REF}
            />
        );
    }
});

module.exports = WebView;