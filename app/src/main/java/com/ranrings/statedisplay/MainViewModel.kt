package com.ranrings.statedisplay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranrings.statedisplay.models.Province
import com.ranrings.statedisplay.models.ProvinceListApi
import com.ranrings.statedisplay.others.AppController
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val countryCode : String,
                    private val provinceListApi: ProvinceListApi,
                    private val appController: AppController) : ViewModel(){

     val provinceListLiveData = MutableLiveData<List<Province>>(listOf())
     val progressBarDisplayLiveData = MutableLiveData<Boolean>(false)

     init {

     }

     fun onCreate() {
          viewModelScope.launch {
               callApi()
          }
     }

     private suspend fun callApi() {
          try {
               progressBarDisplayLiveData.value = true
               val data = provinceListApi.getProvincesList(countryCode)
               provinceListLiveData.postValue(data.provinces)
               appController.saveToken(data.token)
          }
          catch (e : Exception) {
               e.message?.let { appController.showToast(it) }
          }
          finally {
               progressBarDisplayLiveData.postValue(false)
          }
     }

}