package com.ranrings.statedisplay

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranrings.statedisplay.models.Province
import com.ranrings.statedisplay.models.ProvinceListApi
import com.ranrings.statedisplay.models.ProvinceListResponse

class MainViewModel(private val countryCode : String,var  context : Context) : ViewModel(){

     val provinceListLiveData = MutableLiveData<List<Province>>(listOf())
     val provinceListApi = ProvinceListApi(countryCode)
     val progressBarDisplayLiveData = MutableLiveData<Boolean>(false)

     init {
          callApi()
     }

     fun callApi() {
          progressBarDisplayLiveData.value = true
          provinceListApi.call(object : ProvinceListApi.ProvinceListListener {
               override fun onFetched(data: ProvinceListResponse) {
                    provinceListLiveData.postValue(data.provinces)
                    progressBarDisplayLiveData.postValue(false)
               }

               override fun onError(message: String) {
                    AppUtils.showToast(message,context)
                    progressBarDisplayLiveData.postValue(false)
               }

          })

     }

}