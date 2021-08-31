package com.hemanthdev.webview.ui

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.hemanthdev.webview.base.BaseActivity
import com.hemanthdev.webview.core.extensions.replaceFragmentSafely
import com.hemanthdev.webview.core.extensions.viewBinding
import com.hemanthdev.webview.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    override fun getLayout(): ViewBinding = binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragmentSafely(WebViewFragment())
    }
}