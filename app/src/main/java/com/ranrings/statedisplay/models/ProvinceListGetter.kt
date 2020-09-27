package com.ranrings.statedisplay.models

import kotlinx.coroutines.delay
import java.lang.Exception

open class ProvinceListGetter : ProvinceListApi{

    override suspend fun getProvinceList(countryCode : String): ProvinceListResponse {
        delay(3000)
        when(countryCode){
            "India" -> {
                return ProvinceListResponse(listOf(
                    Province("Karnataka"),
                    Province("Maharashtra"),
                    Province("Delhi")

                ), "asdakjdad")
            }

            "USA" -> {
                return ProvinceListResponse(listOf(
                    Province("New York"),
                    Province("Texas"),
                    Province("California")

                ), "asdakjdad")
            }

            "Pakistan" -> {
                return ProvinceListResponse(listOf(
                    Province("Punjab"),
                    Province("Sindh"),
                    Province("Balochistan")

                ), "asdakjdad")
            }

        }

        throw Exception("Invalid state code")
    }

}