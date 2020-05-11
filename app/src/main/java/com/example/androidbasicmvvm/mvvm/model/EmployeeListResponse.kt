package com.example.androidbasicmvvm.mvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class EmployeeListResponse {
    @SerializedName("data")
    @Expose
    var data: ArrayList<EmployeeInfo>? = null

    @SerializedName("status")
    @Expose
    var particulars:  String? = ""
}