package ru.vagavagus.sportzone.presentation.webview

import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient

interface CallbackHandler {
    fun onShowFileChooser(
        message: ValueCallback<Array<Uri?>?>,
        params: WebChromeClient.FileChooserParams
    )
}