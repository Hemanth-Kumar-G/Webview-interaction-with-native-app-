# WebViewAndroid

 A complete example of android webview, in this example you can get the idea how the transections between web pages and the native android component make place. like if someone need a control from web page and vise versa then how can we create a  bridge between two different plat forms.


<img width="1414" alt="App architectureal understanding" src="https://user-images.githubusercontent.com/20921274/126715038-1f39b1e0-ae6a-4d1a-8e17-c2f261cacde2.png">


# Binding JavaScript to Android code

When developing a web application that's designed specifically for the WebView in your Android app, you can create interfaces between your JavaScript code and client-side Android code. For example, your JavaScript code can call a method in your Android code to display a Dialog, instead of using JavaScript's alert() function.

To bind a new interface between your JavaScript and Android code, call addJavascriptInterface(), passing it a class instance to bind to your JavaScript and an interface name that your JavaScript can call to access the class.

For example, you can include the following class in your Android app:

## Native Code

```Kotlin

 binding.webView.isVerticalScrollBarEnabled = true
        binding.webView.addJavascriptInterface(
            WebAppInterface(), "NativeApp"
        )
        
        
  class WebAppInterface {

    @JavascriptInterface
    fun showMessage(toast: String) {
        showToastMsg(toast)
    }

    @JavascriptInterface
    fun getRandomString(): String {
        return "Hello webpage this message is from Android Native app"
    }
}      
        
```


# Web/JS code

```php
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="Content-Style-Type" content="text/css">
  <title></title>
  <meta name="Generator" content="Cocoa HTML Writer">
  <meta name="CocoaVersion" content="2022.5">
  <style type="text/css">
    p.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 12.0px Courier; color: #15813e; -webkit-text-stroke: #15813e}
    p.p2 {margin: 0.0px 0.0px 0.0px 0.0px; font: 12.0px Courier; color: #000000; -webkit-text-stroke: #000000; min-height: 14.0px}
    p.p3 {margin: 0.0px 0.0px 0.0px 0.0px; font: 12.0px Courier; color: #000000; -webkit-text-stroke: #000000}
    span.s1 {font-kerning: none; color: #000000; -webkit-text-stroke: 0px #000000}
    span.s2 {font-kerning: none}
    span.s3 {font-kerning: none; color: #2e5fe1; -webkit-text-stroke: 0px #2e5fe1}
    span.s4 {font-kerning: none; color: #8800a0; -webkit-text-stroke: 0px #8800a0}
  </style>
  
  <script type="text/javascript">
    function showAndroidToast(toast) {
        NativeApp.showMessage(toast);
    }
    
    function showAlert() {
        alert(NativeApp.getRandomString());
    }
    
</script>

</head>
<body>
<input type="button" value="Show Toast In native APP" onClick="showAndroidToast('Hello Android!, this is from web app')" />

<br/><br/>

<input type="button" value="Get Random From Native App" onClick="showAlert()" />
    

</body>
</html>

```
