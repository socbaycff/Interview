package com.example.interview.di

import com.example.interview.ui.bookRoomActivity.BookRoomActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeBookRoom(): BookRoomActivity

}