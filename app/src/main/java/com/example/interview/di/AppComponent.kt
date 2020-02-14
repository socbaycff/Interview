package com.example.interview.di

import com.example.interview.di.BookRoom.BookRoomModule
import com.example.interview.di.BookRoom.BookRoomVMModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@Component( modules = [AndroidSupportInjectionModule::class, BookRoomVMModule::class,AppModule::class,ViewModelFactoryModule::class,BookRoomModule::class])
interface AppComponent: AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DaggerApplication): Builder

        fun build(): AppComponent
    }
}