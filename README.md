# react-native-webview
android webview for react-native

## Installation and How to use

#### Step 1 - NPM Install

```shell
npm install --save react-native-webview
```
#### Step 2 - Update Gradle Settings

```gradle
// file: android/settings.gradle
...

include ':reactwebview', ':app' 
project(':reactwebview').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-webview')
 // if there are more library
 // include ':app' , ':libraryone' , ':librarytwo' , 'more...'
 // project(':libraryonename').projectDir = new File(rootProject.projectDir, '../node_modules/libraryonemodule')
 // project(':librarytwoname').projectDir = new File(rootProject.projectDir, '../node_modules/librarytwomodule')
 // more..
```

#### Step 3 - Update app Gradle Build

```gradle
// file: android/app/build.gradle
...

dependencies {
    ...
    compile project(':reactwebview')
}
```

#### Step 4 - Register React Package

```java
...
import com.heng.wheel.WheelPackage;

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {

    private ReactInstanceManager mReactInstanceManager;
    private ReactRootView mReactRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .addPackage(new WebViewPackage()) // register webview package
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        ...
    }
...

```

#### Step 5 - Require and use in Javascript

```js
// file: index.android.js

var React = require('react-native');
var {
  AppRegistry,
  StyleSheet,
  ToastAndroid,
} = React;

var WebView = require('react-native-webview');

var WebViewTest = React.createClass({
    goBack: function() {
      this.refs.webview.goBack(); 
    },
    goForward: function() {
      this.refs.webview.goForward();
    },
    reload: function() {
      this.refs.webview.reload();
    },
    _onNavigationStateChange: function(event) {
      ToastAndroid.show(event.canGoBack + '',ToastAndroid.SHORT);
      ToastAndroid.show(event.canGoForward + '',ToastAndroid.SHORT);
      ToastAndroid.show(event.url ,ToastAndroid.SHORT);
      ToastAndroid.show(event.title ,ToastAndroid.SHORT);
      ToastAndroid.show(event.loading + '',ToastAndroid.SHORT);
    },
    render: function() {
    var reqUrl = "https://github.com/";
    return (
        <WebView
          ref='webview'
          automaticallyAdjustContentInsets={true}
          url={reqUrl}
          javaScriptEnabledAndroid={true}
          onNavigationStateChange={this._onNavigationStateChange}
          style={styles.webview}/>
    );
  }
});

var styles = StyleSheet.create({
  webview: {
    flex: 1,
  },
});

...

```

## Notes

- Only in the following versions tested , other versions do not guarantee success
```gradle
// file: react-native-wheel/build.gradle

android {
    compileSdkVersion 23  //@
    buildToolsVersion "23.0.1"  //@

    defaultConfig {
        minSdkVersion 16 
        targetSdkVersion 22  //@
    }
}

dependencies {
    compile 'com.facebook.react:react-native:0.16.1'  //@
}


```
