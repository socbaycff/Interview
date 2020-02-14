package com.example.interview.di.bookRoomActivity

import com.example.interview.ui.bookRoomActivity.BookRoomActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BookRoomModule {
    @ContributesAndroidInjector
    abstract fun contributeBookRoom(): BookRoomActivity

}