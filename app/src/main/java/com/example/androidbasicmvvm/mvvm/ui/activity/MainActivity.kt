package com.example.androidbasicmvvm.mvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidbasicmvvm.R
import com.example.androidbasicmvvm.databinding.ActivityMainBinding
import com.example.androidbasicmvvm.mvvm.ui.adapter.ItemEmployeeListAdapter
import com.example.androidbasicmvvm.mvvm.viewmodel.MainVMFactory
import com.example.androidbasicmvvm.mvvm.viewmodel.MainViewModel
import com.example.androidbasicmvvm.utils.isNetworkConnected
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var mainVM: MainViewModel
    lateinit var itemEmployeeListAdapter: ItemEmployeeListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.lifecycleOwner = this
        initBasics()
        setAdapters()
        setObservers()
        getEmployeeList()
    }

    fun initBasics() {

        //Creating instance for ViewModel
        mainVM = ViewModelProvider(this, MainVMFactory(this))[MainViewModel::class.java]
        mainBinding.mainVM = mainVM

    }

    fun setObservers(){
        mainVM.liveEmployeeDetailsList().observe(this, Observer { response ->
            if(response?.data != null ){
                if(response.data!!.isNotEmpty()){
                    mainVM.employeeList = response.data!!
                }else{
                    Toast.makeText(this, getString(R.string.no_results_found),3*1000).show()
                }
                itemEmployeeListAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this, getString(R.string.api_error),3*1000).show()
            }
        })
    }

    fun setAdapters(){
        itemEmployeeListAdapter = ItemEmployeeListAdapter(this, mainVM)
        recyclerViewEmployees.adapter = itemEmployeeListAdapter
    }
    fun getEmployeeList(){
        if(isNetworkConnected(this)){
            //If has network connection
            mainVM.loadEmployeeList()
        }else{
            Toast.makeText(this,getString(R.string.no_internet_connection), 3 * 1000 ).show()
        }
    }
}
