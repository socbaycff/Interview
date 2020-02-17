package com.example.interview.di

import com.example.interview.ui.bookRoomActivity.BookRoomActivity
import com.example.interview.ui.scanQRActivity.ScanQRActivity
import com.example.interview.ui.scanResultActivity.ScanResultActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeBookRoom(): BookRoomActivity

    @ContributesAndroidInjector
    abstract fun contributeScanResult(): ScanResultActivity

    @ContributesAndroidInjector
    abstract fun contributeScanQR(): ScanQRActivity

}