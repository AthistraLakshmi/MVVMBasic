package com.example.androidbasicmvvm

import android.app.Application
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class AppController : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        instance = this

    }


    companion object {
        private val TAG : String = AppController::class.java.simpleName
        private var instance: AppController? = null
        private var requestQueue : RequestQueue? = null
        @Synchronized
        fun getInstance(): AppController {
            return  instance as AppController
        }

    }



    override fun onCreate() {
        super.onCreate()

    }
    fun getRequestQueue () : RequestQueue?{

        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(
                instance
            )

        return requestQueue
    }

    fun <T> addrequestToQueue (request: Request<T>) {
        request.tag = TAG
        getRequestQueue()?.add(request)
    }

}
