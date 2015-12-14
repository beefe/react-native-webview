var React = require('react-native');

var { requireNativeComponent,PropTypes,NativeModules,View } = React;

var UIManager = NativeModules.UIManager;

var WEBVIEW_REF = 'webview';

var WebView = React.createClass({
    propTypes: {
        ...View.propTypes,
        automaticallyAdjustContentInsets: PropTypes.bool,
        contentInset: PropTypes.object,
        html: PropTypes.string,
        injectedJavaScript: PropTypes.string,
        onNavigationStateChange: PropTypes.func,
        renderError: PropTypes.func,
        renderLoading: PropTypes.func,
        startInLoadingState: PropTypes.bool,
        url: PropTypes.string,
        javaScriptEnabledAndroid: PropTypes.bool,
    },
    _onNavigationStateChange: function(event){
        if(this.props.onNavigationStateChange){
            this.props.onNavigationStateChange(event.nativeEvent);
        }
    },
    _getWebViewHandle: function(){
        return React.findNodeHandle(this.refs[WEBVIEW_REF]);
    },
    goBack: function(){
        UIManager.dispatchViewManagerCommand(
            _getWebViewHandle,
            UIManager.RCTWebView.Commands.goBack,
            null,
        );
    },
    goForward: function(){
        UIManager.dispatchViewManagerCommand(
            _getWebViewHandle,
            UIManager.RCTWebView.Commands.goForward,
            null,
        );
    },
    reload: function(){
        UIManager.dispatchViewManagerCommand(
            _getWebViewHandle,
            UIManager.RCTWebView.Commands.reload,
            null,
        );
    },
    render(){
        return(
            <NativeWebView
                ref={WEBVIEW_REF}
                {...this.props}
                onNavigationStateChange={this._onNavigationStateChange}
            />
        );
    }
});

var NativeWebView = requireNativeComponent('RCTWebView',WebView);

module.exports = WebView;