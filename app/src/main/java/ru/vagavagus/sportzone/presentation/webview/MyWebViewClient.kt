package ru.vagavagus.sportzone.presentation.webview

import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import java.net.URLDecoder

class MyWebViewClient: WebViewClient() {
    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        val url = URLDecoder.decode(request?.url.toString(), "UTF-8")
        view?.loadUrl(url)
        return true
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        CookieManager.getInstance().flush()
    }
}