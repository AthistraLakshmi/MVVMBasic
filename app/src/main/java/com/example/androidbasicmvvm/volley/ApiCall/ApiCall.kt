package com.example.androidbasicmvvm.volley.ApiCall

import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.example.androidbasicmvvm.AppController
import com.example.androidbasicmvvm.R
import com.example.androidbasicmvvm.utils.isNetworkConnected
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.util.*


object ApiCall {

    private val TAG = ApiCall::class.java.simpleName
    private val MY_SOCKET_TIMEOUT_MS = 60 * 1000


    fun PostMethod(input: InputForAPI, volleyCallback: ResponseHandler) {
        //        AppDataBase database = AppDataBase.getAppDatabase(input.getContext());
        val url = input.url
        val context = input.context
        val params = input.jsonObject
        val headers = input.headers


        if (isNetworkConnected(context)) {

            Log.d(TAG, "url:$url--input: $params--headers: $headers")
            val jsonObjReq = object : JsonObjectRequest(
                Request.Method.POST,
                url, params,
                Response.Listener { response ->
                    Log.d(TAG, "url:$url,response: $response")

                        volleyCallback.setDataResponse(response)
                }, Response.ErrorListener { error ->
                    Log.d(TAG, "url:$url, onErrorResponse: $error")
                    if (error is TimeoutError || error is NoConnectionError) {

                        volleyCallback.setResponseError(context.resources.getString(R.string.no_internet_connection))

                    } else if (error is AuthFailureError) {

                        volleyCallback.setResponseError(context.resources.getString(R.string.authentication_error))

                    } else if (error is ServerError) {

                        volleyCallback.setResponseError(context.resources.getString(R.string.server_error))

                    } else if (error is NetworkError) {

                        volleyCallback.setResponseError(context.resources.getString(R.string.network_error))

                    } else if (error is ParseError) {

                        volleyCallback.setResponseError(context.resources.getString(R.string.parse_error))

                    }
                }) {
                override fun getHeaders(): HashMap<String, String>? {
                    return headers
                }
            }

            jsonObjReq.retryPolicy = DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            AppController.getInstance().addrequestToQueue(jsonObjReq)

        } else {
            volleyCallback.setResponseError(context.resources.getString(R.string.no_internet_connection))
        }
    }

    fun GetMethod(input: InputForAPI, volleyCallback: ResponseHandler) {

        val url = input.url
        val context = input.context
        val headers = input.headers
        if (isNetworkConnected(context)) {

            val jsonObjReq = object : JsonObjectRequest(Request.Method.GET,
                url, null,
                Response.Listener
                { response ->
                    Log.d(TAG, "GetMethod url:$url,response: $response")

                    volleyCallback.setDataResponse(response)

                }, Response.ErrorListener { error ->
                    Log.d(TAG, "url:$url, onErrorResponse: $error")
                    if (error is TimeoutError || error is NoConnectionError) {

                        volleyCallback.setResponseError(context.resources.getString(R.string.no_internet_connection))

                    } else if (error is AuthFailureError) {

                        volleyCallback.setResponseError(context.resources.getString(R.string.authentication_error))

                    } else if (error is ServerError) {

                        volleyCallback.setResponseError(context.resources.getString(R.string.server_error))

                    } else if (error is NetworkError) {

                        volleyCallback.setResponseError(context.resources.getString(R.string.network_error))

                    } else if (error is ParseError) {

                        volleyCallback.setResponseError(context.resources.getString(R.string.parse_error))

                    }
                }) {
                override fun getHeaders(): HashMap<String, String>? {
                    if (headers != null) {
                        return headers
                    }
                    return null
                }
            }

            jsonObjReq.retryPolicy = DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            AppController.getInstance().addrequestToQueue(jsonObjReq)

        } else {
            volleyCallback.setResponseError(context.resources.getString(R.string.no_internet_connection))
        }

    }

    fun PostStringMethod(input: InputForAPI, volleyCallback: ResponseHandler) {
        try {

            val stringRequest = object : StringRequest(1, input.url,
                Response.Listener { response ->
                    Log.d("ApiCAll-String", response.toString())
                },
                Response.ErrorListener { error ->
                    Log.d("ApiCAll", "Error" + error.networkResponse.statusCode)
                }) {
                override fun getBodyContentType(): String {
                    return String.format("application/json; charset=utf-8")
                }

                @Throws(AuthFailureError::class)
                override fun getBody(): ByteArray? {
                    try {
                        return if (input.jsonObject == null) null else (input.jsonObject.toString()).toByteArray()
                    } catch (uee: UnsupportedEncodingException) {
                        VolleyLog.wtf(
                            "Unsupported Encoding while trying to get the bytes of %s using %s",
                            input.jsonObject.toString(), "utf-8"
                        )
                        return null
                    }

                }
            }
            AppController.getInstance().addrequestToQueue(stringRequest)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    interface ResponseHandler {

        fun setDataResponse(response: JSONObject)

        fun setResponseError(error: String)

    }


}
