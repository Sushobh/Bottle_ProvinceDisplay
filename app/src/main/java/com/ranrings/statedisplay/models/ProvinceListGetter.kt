package com.ranrings.statedisplay.models

class ProvinceListGetter (var countryCode : String){

    public interface ProvinceListListener {
        fun onFetched(data : ProvinceListResponse)
        fun onError(message : String)
    }


    fun call(listener : ProvinceListListener){
        val thread = Thread(Runnable {
            Thread.sleep(3000)
            when(countryCode){
                "India" -> {
                    listener.onFetched(ProvinceListResponse(listOf(
                        Province("Karnataka"),
                        Province("Maharashtra"),
                        Province("Delhi")

                    ), "asdakjdad"))
                }

                "USA" -> {
                    listener.onFetched(ProvinceListResponse(listOf(
                        Province("New York"),
                        Province("Texas"),
                        Province("California")

                    ), "asdakjdad"))
                }

                "Pakistan" -> {
                    listener.onFetched(ProvinceListResponse(listOf(
                        Province("Punjab"),
                        Province("Sindh"),
                        Province("Balochistan")

                    ), "asdakjdad"))
                }

            }
        })
        thread.start()
    }

}