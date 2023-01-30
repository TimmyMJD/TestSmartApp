package tymofiy.dev.testsmatrapp.ui.fragments

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter

@SuppressLint("SetJavaScriptEnabled")
@BindingAdapter("settingsInit")
fun WebView.settingsInit(v: Boolean) {
    if (v) {
        this.webViewClient = WebViewClient()
        this.canGoBack()

        this.settings.apply {
            javaScriptCanOpenWindowsAutomatically = true
            javaScriptEnabled = true
            domStorageEnabled = true
            useWideViewPort = true

            userAgentString = userAgentString.replaceAfter(")", "")
        }
    }
}