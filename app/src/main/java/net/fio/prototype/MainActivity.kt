package net.fio.prototype

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * FIO Wallet prototype: a full-screen WebView that runs the demo
 * from app/src/main/assets/index.html, fully offline.
 */
class MainActivity : Activity() {

    private lateinit var web: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ink = Color.parseColor("#121117")
        window.statusBarColor = ink
        window.navigationBarColor = ink

        web = WebView(this).apply {
            setBackgroundColor(ink)
            overScrollMode = View.OVER_SCROLL_NEVER
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                allowFileAccess = true
                mediaPlaybackRequiresUserGesture = false
                textZoom = 100
                setSupportZoom(false)
            }
            webViewClient = object : WebViewClient() {
                // keep app pages inside the WebView, open web links in the browser
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    val url = request.url
                    if (url.scheme == "file") return false
                    return try {
                        startActivity(Intent(Intent.ACTION_VIEW, url)); true
                    } catch (e: Exception) {
                        true
                    }
                }
            }
        }
        setContentView(web)

        if (savedInstanceState == null) {
            web.loadUrl("file:///android_asset/index.html")
        } else {
            web.restoreState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        web.saveState(outState)
    }

    /** Hardware back goes to the previous screen in the demo; exits from the wallet home. */
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        web.evaluateJavascript("(window.fioBack && window.fioBack()) ? 'handled' : 'exit'") { result ->
            if (result == null || !result.contains("handled")) finish()
        }
    }

    override fun onPause() {
        super.onPause()
        web.onPause()
    }

    override fun onResume() {
        super.onResume()
        web.onResume()
    }

    override fun onDestroy() {
        web.destroy()
        super.onDestroy()
    }
}
