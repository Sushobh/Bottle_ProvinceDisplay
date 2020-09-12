package com.ranrings.statedisplay.others

import android.content.Context
import android.widget.Toast

class AppControllerImpl(val context: Context) : AppController {

    override fun showToast(message: String) {
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }

    override fun saveToken(token: String) {

    }
}