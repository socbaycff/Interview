package com.example.interview.di

import androidx.lifecycle.ViewModel
import com.example.interview.ui.bookRoomActivity.BookRoomViewModel
import com.example.interview.ui.scanQRActivity.ScanQRViewmodel
import com.example.interview.ui.scanResultActivity.ScanResultViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey( BookRoomViewModel::class )
    abstract fun bindBookVM(bookVM: BookRoomViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey( ScanResultViewModel::class )
    abstract fun bindResultVM( resultVM: ScanResultViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey( ScanQRViewmodel::class )
    abstract fun bindScanVM( scanQRVM: ScanQRViewmodel): ViewModel


}
