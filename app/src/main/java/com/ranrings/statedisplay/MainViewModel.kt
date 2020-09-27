package com.ranrings.statedisplay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranrings.statedisplay.models.Province
import com.ranrings.statedisplay.models.ProvinceListApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class MainViewModel(private val countryCode : String,
                    private val provinceListApi: ProvinceListApi,
                    private val appFrontend: AppFrontend
                    ) : ViewModel(){

     val provinceListLiveData = MutableLiveData<List<Province>>(listOf())
     val progressBarDisplayLiveData = MutableLiveData<Boolean>(false)


     fun onCreate() {
         viewModelScope.launch {
              callApi()
         }
     }



    suspend fun callApi() {
           try {
               progressBarDisplayLiveData.value = true
               val response = provinceListApi.getProvinceList("1")
               provinceListLiveData.postValue(response.provinces)
           }
           catch (e : Exception){
               appFrontend.showToast("Something went wrong")
           }
         finally {
             progressBarDisplayLiveData.value = false
         }
    }

}