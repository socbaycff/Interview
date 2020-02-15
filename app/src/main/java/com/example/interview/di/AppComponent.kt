package com.example.interview.di

import com.example.interview.di.bookRoomActivity.BookRoomVMModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@Component(modules = [AndroidSupportInjectionModule::class, BookRoomVMModule::class, AppModuleBinding::class,AppModuleProvider::class, ViewModelFactoryModule::class, ActivityBuilderModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DaggerApplication): Builder

        fun build(): AppComponent
    }
}