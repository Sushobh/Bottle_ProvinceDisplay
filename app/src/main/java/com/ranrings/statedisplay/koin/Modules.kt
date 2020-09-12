package com.ranrings.statedisplay.koin

import com.ranrings.statedisplay.MainViewModel
import com.ranrings.statedisplay.MyApp
import com.ranrings.statedisplay.models.ProvinceListApi
import com.ranrings.statedisplay.models.ProvinceListGetter
import com.ranrings.statedisplay.others.AppController
import com.ranrings.statedisplay.others.AppControllerImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named


import org.koin.dsl.module

val appModule = module {

    single<ProvinceListApi> { ProvinceListGetter() }
    single<AppController> { AppControllerImpl(MyApp.appContext) }

    viewModel { (id: String) -> MainViewModel(id, get(),get()) }
}

