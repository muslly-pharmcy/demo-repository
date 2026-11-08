package com.muslly.volta

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.CookieManager
import android.graphics.Color
import android.view.View
import android.view.WindowManager

class MainActivity : Activity() {
    private lateinit var web: WebView
    private val startUrl = "https://id-preview--5b4df13e-7b0f-484d-9565-b95617dae3f9.lovable.app"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.WHITE
        window.navigationBarColor = Color.WHITE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        web = WebView(this)
        web.setBackgroundColor(Color.WHITE)
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.settings.databaseEnabled = true
        web.settings.cacheMode = WebSettings.LOAD_DEFAULT
        web.settings.loadsImagesAutomatically = true
        web.settings.useWideViewPort = true
        web.settings.loadWithOverviewMode = true
        web.settings.javaScriptCanOpenWindowsAutomatically = false
        web.settings.setSupportMultipleWindows(false)
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().setAcceptThirdPartyCookies(web, false)
        web.webChromeClient = WebChromeClient()
        web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                val uri = request.url
                return uri.scheme != "https" && uri.scheme != "about"
            }
        }
        setContentView(web)
        if (savedInstanceState == null) web.loadUrl(startUrl) else web.restoreState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        web.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    @Deprecated("Deprecated in Android")
    override fun onBackPressed() {
        if (this::web.isInitialized && web.canGoBack()) web.goBack() else super.onBackPressed()
    }

    override fun onResume() { super.onResume(); if (this::web.isInitialized) web.onResume() }
    override fun onPause() { if (this::web.isInitialized) web.onPause(); super.onPause() }
    override fun onDestroy() { if (this::web.isInitialized) web.destroy(); super.onDestroy() }
}
