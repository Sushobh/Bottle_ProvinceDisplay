package com.ranrings.statedisplay.models

interface ProvinceListApi {
    suspend fun getProvincesList(countryCode : String) : ProvinceListResponse
}