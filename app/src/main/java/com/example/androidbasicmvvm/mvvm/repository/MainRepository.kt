package com.example.androidbasicmvvm.mvvm.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.androidbasicmvvm.mvvm.model.EmployeeListResponse
import com.example.androidbasicmvvm.volley.ApiCall.ApiCall
import com.example.androidbasicmvvm.volley.ApiCall.InputForAPI
import com.google.gson.Gson
import org.json.JSONObject

class MainRepository {
    val TAG : String = "MainRepository"
    val employeeListResponse = MutableLiveData<EmployeeListResponse>()


    fun getEmployeeListApi(inputApi: InputForAPI) {
        Log.d(TAG, "Input_Request - URL" + (inputApi.url.toString()))
        Log.d(TAG, "Input_Request" + (inputApi.jsonObject.toString()))
        val gson = Gson()

        ApiCall.GetMethod(inputApi, object : ApiCall.ResponseHandler {
            override fun setDataResponse(response: JSONObject) {
                Log.d(ContentValues.TAG, "GetEmployeeList $response")
                val employeeListResp = gson.fromJson<EmployeeListResponse>(
                    response.toString(),
                    EmployeeListResponse::class.java
                )
                employeeListResponse.value = employeeListResp
            }

            override fun setResponseError(error: String) {

            }

        })
    }

}