package com.example.androidbasicmvvm.utils

import android.content.Context
import android.net.ConnectivityManager


fun isNetworkConnected(context: Context?): Boolean {
    val isConnected: Boolean
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetInfo = connectivityManager
        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    val activeWIFIInfo = connectivityManager
        .getNetworkInfo(ConnectivityManager.TYPE_WIFI)

    isConnected = activeWIFIInfo!!.isConnected || activeNetInfo!!.isConnected
    return isConnected
}