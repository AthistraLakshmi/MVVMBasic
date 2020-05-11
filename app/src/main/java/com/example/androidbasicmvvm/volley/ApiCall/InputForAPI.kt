package com.example.androidbasicmvvm.volley.ApiCall

import android.content.Context

import org.json.JSONObject

import java.io.File
import java.util.ArrayList
import java.util.HashMap

class InputForAPI(var context: Context) {
    var jsonObject: JSONObject ?= null
    var url: String ?= null

    var headers: HashMap<String, String>? = HashMap()
    var file: File? = null

}
