package com.hemanthdev.webview.core.extensions

import android.view.LayoutInflater
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Returns a [Lazy] delegate to access the Activity's [ViewBinding] with
 * specified layout inflater without thread safety mode.
 *
 * This property must be used lazily after views are settled.
 */
@MainThread
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T,
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}