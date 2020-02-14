package com.example.interview.di.BookRoom

import com.example.interview.ui.BookRoomActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BookRoomModule {
    @ContributesAndroidInjector
    abstract fun contributeBookRoom(): BookRoomActivity

}