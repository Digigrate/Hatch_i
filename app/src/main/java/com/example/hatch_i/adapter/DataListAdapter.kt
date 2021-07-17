package com.example.hatch_i.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.hatch_i.R
import com.example.hatch_i.activity.MainActivity
import com.example.hatch_i.model.DataMachine


internal class DataListAdapter(
    private var dataList: List<DataMachine>
) :
    RecyclerView.Adapter<DataListAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var title: TextView = view.findViewById(R.id.title)
        var subtitle: TextView = view.findViewById(R.id.subtitle)
        var machinnumber: TextView = view.findViewById(R.id.machinnumber)


    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_details_data, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]
        holder.title.text = data.getTitle()
        holder.subtitle.text = data.getDescription()
        holder.machinnumber.text = data.getMachinenumber()


    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}