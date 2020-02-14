package com.example.interview.di.BookRoom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.interview.di.ViewModelKey
import com.example.interview.di.ViewModelProviderFactory
import com.example.interview.ui.BookRoomViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BookRoomVMModule {

    @Binds
    @IntoMap
    @ViewModelKey( BookRoomViewModel::class )
    abstract fun bindMainViewModel( mainViewModel: BookRoomViewModel ): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}
