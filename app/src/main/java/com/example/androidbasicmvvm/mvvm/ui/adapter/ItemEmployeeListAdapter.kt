package com.example.androidbasicmvvm.mvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbasicmvvm.R
import com.example.androidbasicmvvm.mvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.item_employee_details.view.*

class ItemEmployeeListAdapter(
    var mContext: Context,
    var mainVM: MainViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val TAG = "EmployeeList"


    class EmployeeListVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData() {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(
            R.layout.item_employee_details, parent,
            false
        );
        return EmployeeListVH(view)
    }

    override fun getItemCount(): Int {
        return mainVM.employeeList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val employeeInfo = mainVM.employeeList[holder.adapterPosition]
        if (employeeInfo != null) {
            holder.itemView.employeeName.text = employeeInfo.employeeName
        }
    }
}