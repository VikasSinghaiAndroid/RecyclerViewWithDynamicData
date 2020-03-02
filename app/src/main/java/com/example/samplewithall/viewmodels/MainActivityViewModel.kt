package com.example.samplewithall.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.samplewithall.models.DataProperty
import com.example.samplewithall.repository.RetrofitManager
import com.example.samplewithall.utils.CommonUtils
import com.log4k.d
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val retrofitManager: RetrofitManager, private val commonUtils: CommonUtils
) : ViewModel() {

    fun getDataFromServer(): LiveData<DataProperty>? {
        d("ViewModel Get Data from server")
        return if (commonUtils.isInternetAvailable())
            retrofitManager.fetchDataFromServer()
        else {
            d("Internet is not available")
            commonUtils.toastShow("Please check your internet connection !!!")
            return null
        }
    }
}