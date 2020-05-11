package com.example.androidbasicmvvm.mvvm.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.europet.app_libraries.volley.constants.UrlConstants
import com.example.androidbasicmvvm.R
import com.example.androidbasicmvvm.mvvm.model.EmployeeInfo
import com.example.androidbasicmvvm.mvvm.model.EmployeeListResponse
import com.example.androidbasicmvvm.mvvm.repository.MainRepository
import com.example.androidbasicmvvm.utils.isNetworkConnected
import com.example.androidbasicmvvm.volley.ApiCall.InputForAPI
import com.example.androidbasicmvvm.volley.ApiCall.getAuthorizationHeaderTwo

class MainViewModel(var mContext: Context) : ViewModel() {

    var homePageRepository = MainRepository()

    var liveProgressBar = MutableLiveData<Boolean>()
    var homeErrorMsg = MutableLiveData<String>()

    var employeeList = ArrayList<EmployeeInfo>()


    fun liveEmployeeDetailsList(): MutableLiveData<EmployeeListResponse> {
        return homePageRepository.employeeListResponse
    }

    fun loadEmployeeList() {
        if (isNetworkConnected(mContext)) {
            liveProgressBar.value = true
            homePageRepository.getEmployeeListApi(inputApiForGetEmployeeList())
        } else {
            homeErrorMsg.value = mContext.resources.getString(R.string.no_internet_connection)
        }
    }

    fun inputApiForGetEmployeeList(): InputForAPI {
        val inputForApi = InputForAPI(mContext)
        inputForApi.headers = getAuthorizationHeaderTwo(mContext)
        inputForApi.url = UrlConstants.baseURL_Empoyees
        return inputForApi
    }
}