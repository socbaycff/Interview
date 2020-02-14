package com.example.interview.ui.scanResultActivity

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.interview.R
import kotlinx.android.synthetic.main.activity_booking_result.*


class ScanResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_result)
        val webView = webView

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
                if (url.startsWith("intent://")) {
                    val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                    if (intent != null) {
                        val fallbackUrl = intent.getStringExtra("browser_fallback_url")
                        return if (fallbackUrl != null) {
                            webView.loadUrl(fallbackUrl)
                            true
                        } else {
                            false
                        }
                    }
                }
                return false
            }
        }

        webView.loadUrl(intent.getStringExtra("url"))
    }
}