package com.hemanthdev.webview.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hemanthdev.webview.base.BaseFragment
import com.hemanthdev.webview.core.extensions.gone
import com.hemanthdev.webview.core.extensions.visible
import com.hemanthdev.webview.core.utils.NavigationManager
import com.hemanthdev.webview.databinding.FragmentWebviewBinding
import com.hemanthdev.webview.interfaces.WebAppInterface


class WebViewFragment : BaseFragment() {

    private lateinit var binding: FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startWebView()
    }

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    private fun startWebView(url: String = KEY_DEFAULT_URL) {
        binding.progress.visible()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(
                view: WebView?,
                url: String?,
                favicon: Bitmap?
            ) {
                binding.progress.visible()
            }

            override fun onPageFinished(view: WebView, url: String) {
                binding.progress.gone()
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String?
            ): Boolean {
                NavigationManager(requireActivity()).openDefaultBrowser(url)
                return true
            }
        }

        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                binding.progress.gone()
            }

            override fun onCreateWindow(
                view: WebView?,
                dialog: Boolean,
                userGesture: Boolean,
                resultMsg: Message?
            ): Boolean {
                val result = view?.hitTestResult
                NavigationManager(requireActivity()).openDefaultBrowser(result?.extra)
                return false
            }
        }

        binding.webView.requestFocus()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.isVerticalScrollBarEnabled = true
        binding.webView.addJavascriptInterface(
            WebAppInterface(), "NativeApp"
        )
        binding.webView.settings.setSupportMultipleWindows(true)
        binding.webView.settings.defaultTextEncodingName = "utf-8"
        binding.webView.loadUrl(url)
    }

    companion object {
        const val KEY_DEFAULT_URL = "file:///android_asset/androidWebView.html"
    }
}
