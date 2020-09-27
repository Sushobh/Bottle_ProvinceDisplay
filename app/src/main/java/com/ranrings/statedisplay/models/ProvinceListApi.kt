package com.ranrings.statedisplay.models

interface ProvinceListApi {
    suspend fun getProvinceList(countryCode : String) : ProvinceListResponse
}