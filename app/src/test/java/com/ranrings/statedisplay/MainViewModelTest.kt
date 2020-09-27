package com.ranrings.statedisplay

import androidx.annotation.UiThread
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ranrings.statedisplay.models.Province
import com.ranrings.statedisplay.models.ProvinceListApi
import com.ranrings.statedisplay.models.ProvinceListGetter
import com.ranrings.statedisplay.models.ProvinceListResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.gen5.api.Assertions
import org.junit.gen5.api.BeforeEach
import org.junit.gen5.api.DisplayName
import org.mockito.Answers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.RuntimeException

internal class MainViewModelTest {



    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var provinceListApi : ProvinceListGetter

    @Mock
    lateinit var appFrontend : AppFrontend


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    var countryCode = "1"


    lateinit var mainViewModel: MainViewModel

    @Before
    fun beforeEach(){
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(appFrontend = appFrontend,provinceListApi = provinceListApi,countryCode = countryCode)
    }


    @DisplayName("Check that all mocks are initialized")
    @Test
    fun test1(){
        Assertions.assertNotNull(appFrontend)
        Assertions.assertNotNull(provinceListApi)
    }



    @DisplayName("Check that when api returns a valid response, live data is updated")
    @Test
    fun callApiTest() = runBlocking{
        val mockResponse = Mockito.mock(ProvinceListResponse::class.java)
        mockResponse.provinces = Mockito.mock(List::class.java) as List<Province>
        Mockito.`when`(provinceListApi.getProvinceList(countryCode)).thenReturn(mockResponse)
        mainViewModel.callApi()
        Assertions.assertEquals(mockResponse.provinces,mainViewModel.provinceListLiveData.value)
        Assertions.assertEquals(false,mainViewModel.progressBarDisplayLiveData.value)
    }

    @DisplayName("Check that when api throws an error a toast is shown")
    @Test
    fun test2() = runBlocking{
        Mockito.doAnswer(Answer {
            throw Exception("Alright bro!")
        }).`when`(provinceListApi).getProvinceList(Mockito.anyString())
        mainViewModel.callApi()
        Mockito.verify(appFrontend,Mockito.times(1)).showToast("Something went wrong")
        Assertions.assertEquals(false,mainViewModel.progressBarDisplayLiveData.value)
    }




}