package com.example.interview.ui.scanQRActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ScanQRViewmodel @Inject constructor(): ViewModel() {

    val intentData = MutableLiveData<String>()
    init {
        intentData.value = "No Barcode Detected"
    }
}