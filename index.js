var React = require('react-native');

var { requireNativeComponent,PropTypes,NativeModules,View } = React;

var UIManager = NativeModules.UIManager;

var NativeWebView = requireNativeComponent('RCTWebView',WebView);

var WEBVIEW_REF = 'webview';

var WebView = React.createClass({
    propTypes: {
        ...View.propTypes,
        automaticallyAdjustContentInsets: PropTypes.bool,
        html: PropTypes.string,
        scrollEnabled: PropTypes.bool,
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