package com.ranrings.statedisplay.koin

import com.ranrings.statedisplay.AppFrontend
import com.ranrings.statedisplay.AppFrontentImplementation
import com.ranrings.statedisplay.MainViewModel
import com.ranrings.statedisplay.MyApp
import com.ranrings.statedisplay.models.ProvinceListApi
import com.ranrings.statedisplay.models.ProvinceListGetter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module




val appModule = module {
    single<ProvinceListApi> {  ProvinceListGetter() }
    single<AppFrontend> {  AppFrontentImplementation() }
    viewModel { (id: String ) -> MainViewModel(id, get(),get()) }
}