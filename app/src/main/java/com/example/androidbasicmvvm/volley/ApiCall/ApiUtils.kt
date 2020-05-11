package com.example.androidbasicmvvm.volley.ApiCall

import android.content.Context
import com.app.europet.app_libraries.volley.constants.ConstantKeys
import java.util.HashMap


fun getContentTypeHeader(context: Context): HashMap<String, String> {

    val headers = HashMap<String, String>()
    headers.put(ConstantKeys.kContentType, ConstantKeys.kContentTypeValJSON)

    return headers
}

fun getAuthorizationHeaderTwo(context: Context): HashMap<String, String> {
    val headers = HashMap<String, String>()
   // headers.put(ConstantKeys.kAuthorizationKey, AppSetttings(context).getAccessToken())
    headers.put(ConstantKeys.kContentType, ConstantKeys.kContentTypeValUrlEncoded)
    return headers
}

fun getAuthorizationHeaderThree(context: Context): HashMap<String, String> {
    val headers = HashMap<String, String>()
   // headers.put(ConstantKeys.kAuthorizationKey, AppSetttings(context).getAccessToken())
    headers.put(ConstantKeys.kContentType, ConstantKeys.kContentTypeValJSON)
    return headers
}