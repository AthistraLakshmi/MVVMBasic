package com.example.androidbasicmvvm.mvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EmployeeInfo {
    @SerializedName("id")
    @Expose
    var id:  String? = ""

    @SerializedName("employee_name")
    @Expose
    var employeeName:  String? = ""

    @SerializedName("employee_age")
    @Expose
    var employeeAge:  String? = ""

    @SerializedName("employee_salary")
    @Expose
    var employeeSalary:  String? = ""

   @SerializedName("profile_image")
    @Expose
    var profileImage:  String? = ""


}