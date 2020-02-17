package com.example.interview.ui.scanQRActivity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.interview.R
import com.example.interview.databinding.ActivityScanBinding
import com.example.interview.di.ViewModelProviderFactory
import com.example.interview.ui.scanResultActivity.ScanResultActivity
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import dagger.android.support.DaggerAppCompatActivity
import java.io.IOException
import javax.inject.Inject

class ScanQRActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewmodel: ScanQRViewmodel
    lateinit var binding: ActivityScanBinding

    private var cameraSource: CameraSource? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_scan)

        viewmodel = ViewModelProvider(this, providerFactory).get(ScanQRViewmodel::class.java)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this

    }

    override fun onResume() {
        super.onResume()
        initialiseDetectorsAndSources() // start scan
    }

    override fun onPause() {
        super.onPause()
        cameraSource!!.release() // release when pause
    }



    // scan
    private fun initialiseDetectorsAndSources() {

        Toast.makeText(applicationContext, "Barcode scanner started", Toast.LENGTH_SHORT).show()

        val barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()

        cameraSource = CameraSource.Builder(this, barcodeDetector!!)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()

        binding.surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            this@ScanQRActivity,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource!!.start(binding.surfaceView.holder)
                    } else {
                        ActivityCompat.requestPermissions(
                            this@ScanQRActivity,
                            arrayOf(Manifest.permission.CAMERA),
                            REQUEST_CAMERA_PERMISSION
                        )
                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource!!.stop()
            }
        })


        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                Toast.makeText(
                    applicationContext,
                    "To prevent memory leaks barcode scanner has been stopped",
                    Toast.LENGTH_SHORT
                ).show()
            }

            @SuppressLint("SetTextI18n")
            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() != 0) {


                    binding.txtBarcodeValue.post {
                            binding.btnAction.text = "LAUNCH URL"
                            viewmodel.intentData.value = barcodes.valueAt(0).displayValue

                    }

                }
            }
        })
    }



    // open webview with detected url
    fun openWebview(@Suppress("UNUSED_PARAMETER") v : View) {
        val intentData = viewmodel.intentData.value

        if (!intentData.equals("No Barcode Detected")) {
            val intent = Intent(this@ScanQRActivity, ScanResultActivity::class.java)
            intent.putExtra("url", intentData)
            startActivity(intent)
        }
    }


    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 201
    }
}