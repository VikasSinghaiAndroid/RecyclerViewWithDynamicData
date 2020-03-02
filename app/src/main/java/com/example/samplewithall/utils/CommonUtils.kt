package com.example.samplewithall.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast
import javax.inject.Inject

class CommonUtils @Inject constructor(private val app: Application) {
    private val applicationContext = app.applicationContext

    fun toastShow(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    fun isInternetAvailable(): Boolean {
        var isInternetAvailable = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    isInternetAvailable = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            } else {
                val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo.also { it ->
                    isInternetAvailable = it?.isConnected!!
                }
            }
        }
        return isInternetAvailable
    }
}