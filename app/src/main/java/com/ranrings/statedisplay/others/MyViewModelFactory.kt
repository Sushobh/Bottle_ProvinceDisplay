package com.ranrings.statedisplay.others

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranrings.statedisplay.MainViewModel
import java.lang.Exception

class MyViewModelFactory(private vararg val arguments : Any) :  ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when(modelClass){
            MainViewModel::class.java -> {
               return MainViewModel(
                   arguments[0] as String,
                   arguments[1] as Context
               ) as T
            }
        }

        throw Exception("Invalid class")
    }
}