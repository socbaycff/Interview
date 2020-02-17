package com.example.interview.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@Component(modules = [AndroidSupportInjectionModule::class, ViewModelBindingModule::class, AppModuleBinding::class,AppModuleProvider::class, ViewModelFactoryModule::class, ActivityBuilderModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DaggerApplication): Builder

        fun build(): AppComponent
    }
}