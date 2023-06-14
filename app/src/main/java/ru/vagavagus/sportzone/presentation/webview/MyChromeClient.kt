package ru.vagavagus.sportzone.presentation.webview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Message
import android.util.Log
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class MyChromeClient(
    private val context: Context,
    private val callbackHandler: CallbackHandler
): WebChromeClient() {
    private val tag = this::class.simpleName
    private var tempView: View? = null
    private var tempViewCallback: CustomViewCallback? = null
    private var defaultOrientation = 0
    private var defaultSystemVisibility = 0

    override fun onCreateWindow(
        view: WebView?,
        isDialog: Boolean,
        isUserGesture: Boolean,
        resultMsg: Message?
    ): Boolean {
        return try {
            val transport = resultMsg!!.obj as WebView.WebViewTransport
            transport.webView = view
            resultMsg.sendToTarget()
            true
        } catch (e: Exception) {
            Log.e(tag, "onCreateWindow: ", e)
            false
        }
    }
    override fun onShowFileChooser(
        mWebView: WebView?,
        filePathCallback: ValueCallback<Array<Uri?>?>,
        fileChooserParams: FileChooserParams
    ): Boolean {
        callbackHandler.onShowFileChooser(filePathCallback, fileChooserParams)
        return true
    }

    override fun getDefaultVideoPoster(): Bitmap? {
        return if (tempView == null) null
        else BitmapFactory.decodeResource(context.resources, 2130837573)
    }

    override fun onHideCustomView() = with(context as ComponentActivity){
        (context.window.decorView as FrameLayout).removeView(tempView)
        tempView = null
        window.decorView.systemUiVisibility = defaultSystemVisibility
        requestedOrientation = defaultOrientation
        tempViewCallback?.onCustomViewHidden()
        tempViewCallback = null
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) = with(context as ComponentActivity){
        if(tempView != null) {
            onHideCustomView()
            return
        }

        tempView = view
        defaultSystemVisibility = context.window.decorView.systemUiVisibility
        defaultOrientation = context.requestedOrientation
        tempViewCallback = callback
        (context.window.decorView as FrameLayout).addView(
            tempView,
            FrameLayout.LayoutParams(-1, -1)
        )

        context.window.decorView.systemUiVisibility = 3846
    }
}