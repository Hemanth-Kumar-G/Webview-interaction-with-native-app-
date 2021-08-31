package com.hemanthdev.webview.interfaces

import android.webkit.JavascriptInterface
import com.hemanthdev.webview.core.extensions.showToastMsg

class WebAppInterface {

    @JavascriptInterface
    fun showMessage(toast: String) {
        showToastMsg(toast)
    }

    @JavascriptInterface
    fun getRandomString(): String {
        return "Hello webpage     \n this message is from Android Native app"
    }
}