package com.example.interview.ui.scanResultActivity

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.interview.R
import com.example.interview.databinding.ActivityScanResultBinding
import com.example.interview.di.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_scan_result.*
import javax.inject.Inject


class ScanResultActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         val binding = DataBindingUtil.setContentView<ActivityScanResultBinding>(this,R.layout.activity_scan_result)
        val webView = webView

        val viewModel = ViewModelProvider(this, providerFactory).get(ScanResultViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

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
